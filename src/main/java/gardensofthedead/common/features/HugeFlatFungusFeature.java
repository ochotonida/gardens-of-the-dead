package gardensofthedead.common.features;

import com.mojang.serialization.Codec;
import gardensofthedead.common.features.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Material;

public class HugeFlatFungusFeature extends Feature<HugeFlatFungusConfiguration> {

    public HugeFlatFungusFeature(Codec<HugeFlatFungusConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<HugeFlatFungusConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource randomsource = context.random();
        ChunkGenerator chunkgenerator = context.chunkGenerator();
        HugeFlatFungusConfiguration configuration = context.config();
        Block validBaseBlock = configuration.validBaseState().getBlock();
        BlockState supportingBlock = level.getBlockState(pos.below());

        if (!supportingBlock.is(validBaseBlock)) {
            return false;
        }

        int height = Mth.nextInt(randomsource, 4, 9);

        if (!configuration.planted()) {
            int maxHeight = chunkgenerator.getGenDepth();
            if (pos.getY() + height + 1 >= maxHeight) {
                return false;
            }
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NONE);
        placeStem(level, randomsource, configuration, pos, height);
        placeHat(level, randomsource, configuration, pos, height);
        return true;
    }

    private static boolean isReplaceable(LevelAccessor level, BlockPos pos, boolean isTrunk) {
        return level.isStateAtPosition(pos, (state) -> {
            Material material = state.getMaterial();
            return state.getMaterial().isReplaceable() || isTrunk && material == Material.PLANT;
        });
    }

    private void placeStem(LevelAccessor level, RandomSource randomSource, HugeFlatFungusConfiguration configuration, BlockPos origin, int height) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        BlockState stemState = configuration.stemState();

        for (int y = 0; y < height; ++y) {
            pos.setWithOffset(origin, 0, y, 0);
            if (isReplaceable(level, pos, true)) {
                level.setBlock(pos, stemState, Block.UPDATE_ALL);
            }
        }

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (x != 0 || z != 0) {
                    boolean isCorner = x != 0 && z != 0;
                    if (!isCorner && randomSource.nextFloat() <= 0.75F || isCorner && randomSource.nextFloat() < 0.25F) {
                        pos.setWithOffset(origin, x, -1, z);
                        if (!level.isEmptyBlock(pos)) {
                            pos.move(Direction.UP);
                            boolean placed = tryPlaceStem(level, pos, configuration);
                            if (!isCorner && placed && randomSource.nextFloat() < 0.25F) {
                                pos.setWithOffset(origin, x, 1, z);
                                tryPlaceStem(level, pos, configuration);
                            }
                        }
                    }
                    if (!isCorner && randomSource.nextFloat() <= 0.75F  || isCorner && randomSource.nextFloat() < 0.25F) {
                        pos.setWithOffset(origin, x, height - 1, z);
                        boolean placed = tryPlaceStem(level, pos, configuration);
                        if (height > 4 && !isCorner && placed && randomSource.nextFloat() < 0.25F) {
                            pos.setWithOffset(origin, x, height - 2, z);
                            tryPlaceStem(level, pos, configuration);
                        }
                    }
                }
            }
        }
    }

    private boolean tryPlaceStem(LevelAccessor level, BlockPos.MutableBlockPos pos, HugeFlatFungusConfiguration configuration) {
        if (isReplaceable(level, pos, true)) {
            if (configuration.planted()) {
                if (!level.getBlockState(pos.below()).isAir()) {
                    level.destroyBlock(pos, true);
                }
            }
            level.setBlock(pos, configuration.stemState(), Block.UPDATE_ALL);
            return true;
        }
        return false;
    }

    private void placeHat(LevelAccessor level, RandomSource randomSource, HugeFlatFungusConfiguration configuration, BlockPos origin, int height) {
        float wobbliness = 0.1F + randomSource.nextFloat() * 0.15F;
        float wobbleAngle = randomSource.nextFloat() * (float) Math.PI * 2;
        float size = (6 + randomSource.nextFloat() * 3) / 2;
        int maxSize = Mth.ceil(size * 1.25);
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        boolean placeGlowingSpores = randomSource.nextFloat() < 0.4F;

        for (int x = -maxSize; x <= maxSize; x++) {
            for (int z = -maxSize; z <= maxSize; z++) {
                float angle = x == 0 && z == 0 ? 0 : (float) Mth.atan2(x, z);
                float wobble = (1 + wobbliness * Mth.cos(angle - wobbleAngle));
                float r = x * x + z * z;

                if (Mth.square(wobble) * r <= Mth.square(size)) {
                    boolean isEdge = Mth.square(wobble) * r > Mth.square(size - 1.5);
                    if (isEdge) {
                        pos.setWithOffset(origin, x, height - 1, z);
                    } else {
                        pos.setWithOffset(origin, x, height, z);
                    }

                    if (isReplaceable(level, pos, false)) {
                        if (configuration.planted() && !level.getBlockState(pos.below()).isAir()) {
                            level.destroyBlock(pos, true);
                        }
                        placeHatBlock(level, randomSource, configuration, pos, isEdge, placeGlowingSpores);
                    }
                }
            }
        }
    }

    private void placeHatBlock(LevelAccessor level, RandomSource randomSource, HugeFlatFungusConfiguration configuration, BlockPos.MutableBlockPos pos, boolean isEdge, boolean placeGlowingSpores) {
        setBlock(level, pos, configuration.hatState());
        float shroomlightChance = isEdge ? 0 : 0.05F;
        float hangingVineChance = isEdge ? 0.3F : 0.75F;
        float sproutChance = 0.2F;

        if (randomSource.nextFloat() < shroomlightChance) {
            if (level.isEmptyBlock(pos.below())) {
                setBlock(level, pos.below(), Blocks.SHROOMLIGHT.defaultBlockState());
            }
        } else if (randomSource.nextFloat() < hangingVineChance) {
            tryPlaceHangingSoulSpore(pos, level, randomSource, isEdge, placeGlowingSpores);
        }
        if (randomSource.nextFloat() < sproutChance) {
            if (level.isEmptyBlock(pos.above())) {
                setBlock(level, pos.above(), ModBlocks.SOULBLIGHT_SPROUTS.get().defaultBlockState());
            }
        }
    }
    
    private static void tryPlaceHangingSoulSpore(BlockPos pos, LevelAccessor level, RandomSource randomSource, boolean isEdge, boolean placeGlowingSpores) {
        BlockPos.MutableBlockPos below = pos.mutable().move(Direction.DOWN);
        if (level.isEmptyBlock(below)) {
            int length = Mth.nextInt(randomSource, 2, 3) + (isEdge ? 0 : 1);
            SoulSporeColumnFeature.placeSoulSporeColumn(level, randomSource, below, length, Direction.DOWN, isEdge && placeGlowingSpores ? 1 : 0);
        }
    }
}

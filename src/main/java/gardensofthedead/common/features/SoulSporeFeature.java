package gardensofthedead.common.features;

import com.mojang.serialization.Codec;
import gardensofthedead.common.blocks.SoulSporeBaseBlock;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SoulSporeFeature extends Feature<NoneFeatureConfiguration> {

    public SoulSporeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        // TODO
        return false;
    }

    public static void placeSoulSporeColumn(LevelAccessor level, RandomSource randomSource, BlockPos.MutableBlockPos pos, int length, Direction direction, float glowingChance) {
        for (int y = 1; y <= length; ++y) {
            if (level.isEmptyBlock(pos)) {
                if (y == length || !level.isEmptyBlock(pos.relative(direction))) {
                    BlockState topState = randomSource.nextFloat() < glowingChance && y > 1
                            ? ModBlocks.GLOWING_SOUL_SPORE.get().defaultBlockState()
                            : ModBlocks.SOUL_SPORE.get().defaultBlockState().setValue(SoulSporeBlock.GROWING, false);
                    level.setBlock(pos, topState.setValue(SoulSporeBaseBlock.DIRECTION, direction), Block.UPDATE_CLIENTS);
                    break;
                }

                BlockState state = ModBlocks.SOUL_SPORE.get().defaultBlockState()
                        .setValue(SoulSporeBaseBlock.DIRECTION, direction)
                        .setValue(SoulSporeBlock.GROWING, false);

                level.setBlock(pos, state, Block.UPDATE_CLIENTS);
            }

            pos.move(direction);
        }
    }
}

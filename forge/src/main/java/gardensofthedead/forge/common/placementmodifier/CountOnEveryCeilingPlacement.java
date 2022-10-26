package gardensofthedead.forge.common.placementmodifier;

import com.mojang.serialization.Codec;
import gardensofthedead.forge.common.init.ModPlacementModifiers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class CountOnEveryCeilingPlacement extends PlacementModifier {

    public static final Codec<CountOnEveryCeilingPlacement> CODEC =
            IntProvider.codec(0, 256)
                    .fieldOf("count")
                    .xmap(CountOnEveryCeilingPlacement::new, (placementModifier) -> placementModifier.count)
                    .codec();

    private final IntProvider count;

    private CountOnEveryCeilingPlacement(IntProvider count) {
        this.count = count;
    }

    public static CountOnEveryCeilingPlacement of(IntProvider count) {
        return new CountOnEveryCeilingPlacement(count);
    }

    public static CountOnEveryCeilingPlacement of(int count) {
        return of(ConstantInt.of(count));
    }

    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource randomSource, BlockPos pos) {
        Stream.Builder<BlockPos> builder = Stream.builder();
        int layer = 0;

        boolean flag;
        do {
            flag = false;

            for(int j = 0; j < this.count.sample(randomSource); ++j) {
                int x = randomSource.nextInt(16) + pos.getX();
                int z = randomSource.nextInt(16) + pos.getZ();
                int y = context.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
                int j1 = findOnGroundYPosition(context, x, y, z, layer);
                if (j1 != Integer.MAX_VALUE) {
                    builder.add(new BlockPos(x, j1, z));
                    flag = true;
                }
            }

            ++layer;
        } while(flag);

        return builder.build();
    }

    public PlacementModifierType<?> type() {
        return ModPlacementModifiers.COUNT_ON_EVERY_CEILING.get();
    }

    private static int findOnGroundYPosition(PlacementContext context, int xOrigin, int yOrigin, int zOrigin, int maxDepth) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(xOrigin, yOrigin, zOrigin);
        int layer = 0;
        BlockState previousState = context.getBlockState(pos);

        for (int y = yOrigin; y >= context.getMinBuildHeight() + 1; --y) {
            pos.setY(y - 1);
            BlockState currentState = context.getBlockState(pos);
            if (!isEmpty(previousState) && isEmpty(currentState) && !previousState.is(Blocks.BEDROCK)) {
                if (layer == maxDepth) {
                    return pos.getY();
                }

                ++layer;
            }

            previousState = currentState;
        }

        return Integer.MAX_VALUE;
    }

    private static boolean isEmpty(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) || state.is(Blocks.LAVA);
    }
}

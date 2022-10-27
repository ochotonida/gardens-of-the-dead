package gardensofthedead.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;

public class SoulblightForestVegetationFeature extends Feature<NetherForestVegetationConfig> {

    public SoulblightForestVegetationFeature(Codec<NetherForestVegetationConfig> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NetherForestVegetationConfig> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        BlockState state = level.getBlockState(origin.below());
        NetherForestVegetationConfig config = context.config();
        RandomSource randomSource = context.random();
        // only allow placement on soul sand/soil
        if (!state.is(Blocks.SOUL_SAND) && !state.is(Blocks.SOUL_SOIL)) {
            return false;
        } else {
            int y = origin.getY();
            if (y >= level.getMinBuildHeight() + 1 && y + 1 < level.getMaxBuildHeight()) {
                int amountPlaced = 0;

                for (int i = 0; i < config.spreadWidth * config.spreadWidth; ++i) {
                    BlockPos pos = origin.offset(randomSource.nextInt(config.spreadWidth) - randomSource.nextInt(config.spreadWidth), randomSource.nextInt(config.spreadHeight) - randomSource.nextInt(config.spreadHeight), randomSource.nextInt(config.spreadWidth) - randomSource.nextInt(config.spreadWidth));
                    BlockState randomPlant = config.stateProvider.getState(randomSource, pos);
                    if (level.isEmptyBlock(pos)
                            && pos.getY() > level.getMinBuildHeight()
                            && randomPlant.canSurvive(level, pos)
                            // don't stack soul spore on top of itself
                            && !level.getBlockState(pos.below()).is(randomPlant.getBlock())
                    ) {
                        level.setBlock(pos, randomPlant, 2);
                        ++amountPlaced;
                    }
                }
                return amountPlaced > 0;
            } else {
                return false;
            }
        }
    }
}

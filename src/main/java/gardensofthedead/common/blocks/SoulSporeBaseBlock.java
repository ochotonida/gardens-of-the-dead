package gardensofthedead.common.blocks;

import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class SoulSporeBaseBlock extends Block implements IPlantable {

    public SoulSporeBaseBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction direction, BlockState previousState, LevelAccessor level, BlockPos pos, BlockPos updatesPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        return super.updateShape(state, direction, previousState, level, pos, updatesPos);
    }

    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        return below.canSustainPlant(level, pos, Direction.UP, this)
                || below.isFaceSturdy(level, pos.below(), Direction.UP)
                || below.is(ModBlocks.SOUL_SPORE.get());
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.NETHER;
    }

    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return defaultBlockState();
    }
}

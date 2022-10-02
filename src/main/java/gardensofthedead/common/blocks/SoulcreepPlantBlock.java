package gardensofthedead.common.blocks;

import gardensofthedead.common.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoulcreepPlantBlock extends GrowingPlantBodyBlock {

    public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 16, 15);

    public SoulcreepPlantBlock(Properties properties) {
        super(properties, Direction.UP, SHAPE, false);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return ModBlocks.SOULCREEP.get();
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClientSide) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return false;
    }
}

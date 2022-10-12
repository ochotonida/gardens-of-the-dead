package gardensofthedead.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlistercrownBlock extends BushBlock {

    protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 12, 11);

    public BlistercrownBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || super.mayPlaceOn(state, level, pos);
    }
}

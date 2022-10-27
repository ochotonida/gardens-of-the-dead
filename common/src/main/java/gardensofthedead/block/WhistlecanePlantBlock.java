package gardensofthedead.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class WhistlecanePlantBlock extends WhistlecaneBaseBlock {

    public WhistlecanePlantBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos updatedPos) {
        if (direction == Direction.UP && !newState.is(getBodyBlock()) && !newState.is(getHeadBlock())) {
            return getHeadBlock().defaultBlockState();
        }

        return super.updateShape(state, direction, newState, level, pos, updatedPos);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return getHeadBlock().getCloneItemStack(level, pos, state);
    }

    @Override
    public String getDescriptionId() {
        return getHeadBlock().getDescriptionId();
    }
}

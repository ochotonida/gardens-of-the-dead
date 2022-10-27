package gardensofthedead.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SoulblightSproutsBlock extends RootsBlock {

    public SoulblightSproutsBlock(Properties properties) {
        super(properties);
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.WART_BLOCKS) || state.is(Blocks.SOUL_SAND) ||  super.mayPlaceOn(state, level, pos);
    }
}

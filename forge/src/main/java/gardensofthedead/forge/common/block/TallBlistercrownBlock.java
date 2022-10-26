package gardensofthedead.forge.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class TallBlistercrownBlock extends DoublePlantBlock {

    public TallBlistercrownBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.NYLIUM) || super.mayPlaceOn(state, level, pos);
    }

    public boolean canSurvive(BlockState p_154615_, LevelReader p_154616_, BlockPos p_154617_) {
        if (p_154615_.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return super.canSurvive(p_154615_, p_154616_, p_154617_);
        } else {
            BlockPos blockpos = p_154617_.below();
            BlockState blockstate = p_154616_.getBlockState(blockpos);
            return this.mayPlaceOn(blockstate, p_154616_, blockpos);
        }
    }
}

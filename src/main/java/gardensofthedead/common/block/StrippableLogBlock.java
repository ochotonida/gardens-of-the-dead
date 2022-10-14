package gardensofthedead.common.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.function.Supplier;

public class StrippableLogBlock extends RotatedPillarBlock {

    private final Supplier<? extends Block> strippedLogBlock;

    public StrippableLogBlock(final Properties properties, final Supplier<? extends Block> strippedLogBlock) {
        super(properties);
        this.strippedLogBlock = strippedLogBlock;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().canPerformAction(toolAction) && ToolActions.AXE_STRIP.equals(toolAction)) {
            return strippedLogBlock.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }
        return null;
    }
}

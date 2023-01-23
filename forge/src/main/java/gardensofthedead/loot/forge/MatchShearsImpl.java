package gardensofthedead.loot.forge;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolActions;

public class MatchShearsImpl {

    public static boolean test(ItemStack tool) {
        return tool.canPerformAction(ToolActions.SHEARS_DIG);
    }
}

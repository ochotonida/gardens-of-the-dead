package gardensofthedead.loot.fabric;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;

public class MatchShearsImpl {

    public static boolean test(ItemStack tool) {
        return tool.getItem() instanceof ShearsItem;
    }
}

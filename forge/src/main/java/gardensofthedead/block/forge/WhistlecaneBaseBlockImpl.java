package gardensofthedead.block.forge;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ToolActions;

public class WhistlecaneBaseBlockImpl {

    public static boolean canInstabreak(Player player) {
        return player.getMainHandItem().canPerformAction(ToolActions.SWORD_DIG);
    }
}

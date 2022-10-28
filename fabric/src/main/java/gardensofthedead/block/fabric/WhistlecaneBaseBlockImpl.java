package gardensofthedead.block.fabric;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;

public class WhistlecaneBaseBlockImpl {

    public static boolean canInstabreak(Player player) {
        return player.getMainHandItem().getItem() instanceof SwordItem;
    }
}

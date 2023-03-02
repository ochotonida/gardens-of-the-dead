package gardensofthedead.registry;

import gardensofthedead.mixin.WoodTypeInvoker;
import gardensofthedead.platform.PlatformServices;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashSet;
import java.util.Set;

public class ModWoodTypes {

    public static final Set<WoodType> VALUES = new HashSet<>();

    public static final WoodType SOULBLIGHT = create("soulblight");
    public static final WoodType WHISTLECANE = create("whistlecane");

    private static WoodType create(String id) {
        WoodType woodType = WoodTypeInvoker.newWoodType(PlatformServices.platformHelper.createWoodTypeName(id));
        WoodTypeInvoker.invokerRegister(woodType);
        VALUES.add(woodType);
        return woodType;
    }

    public static void register() {
        VALUES.forEach(PlatformServices.platformHelper::addWoodTypeMaterial);
    }
}

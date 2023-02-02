package gardensofthedead.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import gardensofthedead.mixin.WoodTypeInvoker;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashSet;
import java.util.Set;

public class ModWoodTypes {

    public static final Set<WoodType> VALUES = new HashSet<>();

    public static final WoodType SOULBLIGHT = create("soulblight");
    public static final WoodType WHISTLECANE = create("whistlecane");

    private static WoodType create(String id) {
        WoodType woodType = WoodTypeInvoker.newWoodType(createName(id));
        WoodTypeInvoker.invokerRegister(woodType);
        VALUES.add(woodType);
        return woodType;
    }

    public static void register() {
        VALUES.forEach(ModWoodTypes::addMaterial);
    }

    @ExpectPlatform
    public static String createName(String id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void addMaterial(WoodType woodType) {
        throw new AssertionError();
    }
}

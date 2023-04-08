package gardensofthedead.registry;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.mixin.WoodTypeInvoker;
import gardensofthedead.platform.PlatformServices;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashSet;
import java.util.Set;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

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
        for (WoodType woodType : VALUES) {
            String name = PlatformServices.platformHelper.getWoodTypeName(woodType);
            Sheets.SIGN_MATERIALS.put(woodType, new net.minecraft.client.resources.model.Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/" + name)));
            Sheets.HANGING_SIGN_MATERIALS.put(woodType, new net.minecraft.client.resources.model.Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/hanging/" + name)));
        }
    }
}

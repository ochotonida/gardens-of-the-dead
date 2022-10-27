package gardensofthedead.registry;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.HashSet;
import java.util.Set;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

public class ModWoodTypes {

    public static final Set<WoodType> VALUES = new HashSet<>();

    public static final WoodType SOULBLIGHT = create("soulblight");
    public static final WoodType WHISTLECANE = create("whistlecane");

    @SuppressWarnings("SameParameterValue")
    private static WoodType create(String id) {
        WoodType woodType = WoodType.register(WoodType.create(GardensOfTheDead.id(id).toString()));
        VALUES.add(woodType);
        return woodType;
    }

    public static void register() {
        VALUES.forEach(woodType -> Sheets.SIGN_MATERIALS.put(woodType, new Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/" + new ResourceLocation(woodType.name()).getPath()))));
    }
}

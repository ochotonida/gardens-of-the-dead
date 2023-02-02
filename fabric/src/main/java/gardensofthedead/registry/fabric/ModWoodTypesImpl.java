package gardensofthedead.registry.fabric;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

public class ModWoodTypesImpl {

    public static String createName(String id) {
        return id;
    }

    public static void addMaterial(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, new Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/" + woodType.name())));
    }
}

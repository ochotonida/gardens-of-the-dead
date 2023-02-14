package gardensofthedead.forge.platform;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.platform.ClientPlatformHelper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

public class ForgeClientPlatformHelper implements ClientPlatformHelper {

    @Override
    public String createWoodTypeName(String id) {
        return GardensOfTheDead.id(id).toString();
    }

    @Override
    public void addWoodTypeMaterial(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, new Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/" + new ResourceLocation(woodType.name()).getPath())));
    }
}

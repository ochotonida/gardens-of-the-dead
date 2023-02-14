package gardensofthedead.platform;

import net.minecraft.world.level.block.state.properties.WoodType;

public interface ClientPlatformHelper {

    String createWoodTypeName(String id);

    void addWoodTypeMaterial(WoodType woodType);
}

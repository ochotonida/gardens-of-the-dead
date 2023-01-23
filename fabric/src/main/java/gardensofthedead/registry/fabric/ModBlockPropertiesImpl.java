package gardensofthedead.registry.fabric;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModBlockPropertiesImpl {

    public static BlockBehaviour.Properties copy(BlockBehaviour.Properties properties) {
        return FabricBlockSettings.copyOf(properties);
    }

    public static BlockBehaviour.Properties of(Material material) {
        return FabricBlockSettings.of(material);
    }

    public static BlockBehaviour.Properties copyWithLoot(BlockBehaviour.Properties properties, ResourceLocation id) {
        ResourceLocation lootTable = new ResourceLocation(id.getNamespace(), "blocks/" + id.getPath());
        return FabricBlockSettings.copyOf(properties).drops(lootTable);
    }
}

package gardensofthedead.registry.forge;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlockPropertiesImpl {

    public static BlockBehaviour.Properties copy(BlockBehaviour.Properties properties) {
        return BlockBehaviour.Properties.copy(new BlockBehaviour(properties) {
            @Override
            public Item asItem() {
                throw new UnsupportedOperationException();
            }

            @Override
            protected Block asBlock() {
                throw new UnsupportedOperationException();
            }
        });
    }

    public static BlockBehaviour.Properties of(Material material) {
        return BlockBehaviour.Properties.of(material);
    }

    public static BlockBehaviour.Properties copyWithLoot(BlockBehaviour.Properties properties, ResourceLocation id) {
       return copy(properties).lootFrom(() -> ForgeRegistries.BLOCKS.getValue(id));
    }
}

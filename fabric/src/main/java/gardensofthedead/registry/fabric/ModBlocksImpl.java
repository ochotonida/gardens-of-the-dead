package gardensofthedead.registry.fabric;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocksImpl {

    public static Supplier<FlowerPotBlock> createFlowerPot(RegistrySupplier<? extends Block> plant, BlockBehaviour.Properties properties) {
        return () -> new FlowerPotBlock(plant.get(), properties);
    }

    public static Supplier<RotatedPillarBlock> createStrippableBlock(BlockBehaviour.Properties properties, Supplier<? extends Block> strippedBlock) {
        return () -> {
            RotatedPillarBlock result = new RotatedPillarBlock(properties);
            StrippableBlockRegistry.register(result, strippedBlock.get());
            return result;
        };
    }
}

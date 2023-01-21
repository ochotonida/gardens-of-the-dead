package gardensofthedead.registry.forge;

import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.forge.block.StrippableLogBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ModBlocksImpl {

    public static Supplier<FlowerPotBlock> createFlowerPot(RegistrySupplier<? extends Block> plant, BlockBehaviour.Properties properties) {
        Supplier<FlowerPotBlock> result = () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, properties);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(plant.getId(), result);
        return result;
    }

    public static Supplier<RotatedPillarBlock> createStrippableBlock(BlockBehaviour.Properties properties, Supplier<? extends Block> strippedBlock) {
        return () -> new StrippableLogBlock(properties, strippedBlock);
    }
}

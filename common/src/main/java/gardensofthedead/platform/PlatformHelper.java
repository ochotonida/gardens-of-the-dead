package gardensofthedead.platform;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public interface PlatformHelper {

    boolean isSword(ItemStack stack);

    boolean isShears(ItemStack stack);

    Supplier<FlowerPotBlock> createFlowerPot(RegistrySupplier<? extends Block> plant, BlockBehaviour.Properties properties);

    Supplier<RotatedPillarBlock> createStrippableBlock(RegistrySupplier<? extends Block> strippedBlock, BlockBehaviour.Properties properties);

    BlockBehaviour.Properties createBlockProperties(Material material);

    BlockBehaviour.Properties copyBlockProperties(BlockBehaviour.Properties properties);

    BlockBehaviour.Properties copyBlockPropertiesWithLoot(BlockBehaviour.Properties properties, ResourceLocation id);
}

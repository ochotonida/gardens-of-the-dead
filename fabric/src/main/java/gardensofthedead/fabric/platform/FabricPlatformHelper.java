package gardensofthedead.fabric.platform;

import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.platform.PlatformHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

public class FabricPlatformHelper implements PlatformHelper {

    @Override
    public boolean isSword(ItemStack stack) {
        return stack.getItem() instanceof SwordItem;
    }

    @Override
    public boolean isShears(ItemStack stack) {
        return stack.getItem() instanceof ShearsItem;
    }

    @Override
    public FlowerPotBlock createFlowerPot(RegistrySupplier<? extends Block> plant, BlockBehaviour.Properties properties) {
        return new FlowerPotBlock(plant.get(), properties);
    }

    @Override
    public RotatedPillarBlock createStrippableBlock(Supplier<? extends Block> strippedBlock, BlockBehaviour.Properties properties) {
        RotatedPillarBlock result = new RotatedPillarBlock(properties);
        StrippableBlockRegistry.register(result, strippedBlock.get());
        return result;
    }

    @Override
    public BlockBehaviour.Properties createBlockProperties(Material material) {
        return FabricBlockSettings.of(material);
    }

    @Override
    public BlockBehaviour.Properties copyBlockProperties(BlockBehaviour.Properties properties) {
        return FabricBlockSettings.copyOf(properties);
    }

    @Override
    public BlockBehaviour.Properties copyBlockPropertiesWithLoot(BlockBehaviour.Properties properties, ResourceLocation id) {
        ResourceLocation lootTable = new ResourceLocation(id.getNamespace(), "blocks/" + id.getPath());
        return FabricBlockSettings.copyOf(properties).drops(lootTable);
    }

    @Override
    public String createWoodTypeName(String id) {
        return id;
    }

    @Override
    public void addWoodTypeMaterial(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, new net.minecraft.client.resources.model.Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/" + woodType.name())));
    }
}

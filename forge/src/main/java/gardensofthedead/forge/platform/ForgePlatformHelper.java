package gardensofthedead.forge.platform;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.forge.block.StrippableLogBlock;
import gardensofthedead.platform.PlatformHelper;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static net.minecraft.client.renderer.Sheets.SIGN_SHEET;

public class ForgePlatformHelper implements PlatformHelper {

    @Override
    public boolean isSword(ItemStack stack) {
        return stack.canPerformAction(ToolActions.SWORD_DIG);
    }

    @Override
    public boolean isShears(ItemStack stack) {
        return stack.canPerformAction(ToolActions.SHEARS_DIG);
    }

    @Override
    public Supplier<FlowerPotBlock> createFlowerPot(RegistrySupplier<? extends Block> plant, BlockBehaviour.Properties properties) {
        Supplier<FlowerPotBlock> result = Suppliers.memoize(
                () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, properties)
        );
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(plant.getId(), result);
        return result;
    }

    @Override
    public Supplier<RotatedPillarBlock> createStrippableBlock(RegistrySupplier<? extends Block> strippedBlock, BlockBehaviour.Properties properties) {
        return () -> new StrippableLogBlock(properties, strippedBlock);
    }

    @Override
    public BlockBehaviour.Properties createBlockProperties(Material material) {
        return BlockBehaviour.Properties.of(material);
    }

    @Override
    public BlockBehaviour.Properties copyBlockProperties(BlockBehaviour.Properties properties) {
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

    @Override
    public BlockBehaviour.Properties copyBlockPropertiesWithLoot(BlockBehaviour.Properties properties, ResourceLocation id) {
        return copyBlockProperties(properties).lootFrom(() -> ForgeRegistries.BLOCKS.getValue(id));
    }

    @Override
    public String createWoodTypeName(String id) {
        return GardensOfTheDead.id(id).toString();
    }

    @Override
    public void addWoodTypeMaterial(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, new net.minecraft.client.resources.model.Material(SIGN_SHEET, GardensOfTheDead.id("entity/signs/" + new ResourceLocation(woodType.name()).getPath())));
    }
}

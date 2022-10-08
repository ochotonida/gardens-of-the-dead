package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        flatBlockItem(ModBlocks.SOUL_SPORE.get());
        flatBlockItem(ModBlocks.GLOWING_SOUL_SPORE.get());
        flatBlockItem(ModBlocks.SOULBLIGHT_FUNGUS.get());
        flatBlockItem(ModBlocks.SOULBLIGHT_SPROUTS.get());

        simpleBlockItem(ModBlocks.SOULBLIGHT_STEM.get());
        simpleBlockItem(ModBlocks.STRIPPED_SOULBLIGHT_STEM.get());
        simpleBlockItem(ModBlocks.SOULBLIGHT_HYPHAE.get());
        simpleBlockItem(ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get());
        simpleBlockItem(ModBlocks.BLIGHTWART_BLOCK.get());

        simpleBlockItem(ModBlocks.SOULBLIGHT_PLANKS.get());
        simpleBlockItem(ModBlocks.SOULBLIGHT_SLAB.get());
        simpleBlockItem(ModBlocks.SOULBLIGHT_STAIRS.get());
        simpleBlockItem(ModBlocks.SOULBLIGHT_PRESSURE_PLATE.get());

        simpleBlockItem(ModBlocks.SOULBLIGHT_FENCE_GATE.get());
    }

    private void simpleBlockItem(Block block) {
        ResourceLocation id = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));

        withExistingParent(id.getPath(), modLoc(BLOCK_FOLDER + '/' + id.getPath()));
    }

    public void flatBlockItem(Block block) {
        ResourceLocation id = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
        getBuilder(id.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", modLoc(BLOCK_FOLDER + '/' + id.getPath()));
    }
}

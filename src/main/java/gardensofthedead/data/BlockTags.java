package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModBlocks;
import gardensofthedead.common.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockTags extends BlockTagsProvider {

    public BlockTags(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).addTag(
                ModTags.Blocks.SOULBLIGHT_STEMS
        );

        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.BLIGHTWART_BLOCK.get()
        );

        // noinspection ConstantConditions
        tag(net.minecraft.tags.BlockTags.FLOWER_POTS).add(
                ForgeRegistries.BLOCKS.getValues()
                        .stream()
                        .filter(block -> ForgeRegistries.BLOCKS.getKey(block).getPath().equals(GardensOfTheDead.MODID))
                        .filter(block -> block instanceof FlowerPotBlock)
                        .toArray(Block[]::new)
        );

        tag(net.minecraft.tags.BlockTags.ENDERMAN_HOLDABLE).add(
                ModBlocks.SOULBLIGHT_FUNGUS.get()
        );

        tag(ModTags.Blocks.SOULBLIGHT_STEMS).add(
                ModBlocks.SOULBLIGHT_STEM.get(),
                ModBlocks.STRIPPED_SOULBLIGHT_STEM.get(),
                ModBlocks.SOULBLIGHT_HYPHAE.get(),
                ModBlocks.STRIPPED_SOULBLIGHT_HYPHAE.get()
        );

        tag(net.minecraft.tags.BlockTags.NON_FLAMMABLE_WOOD).addTag(
                ModTags.Blocks.SOULBLIGHT_STEMS
        );

        tag(net.minecraft.tags.BlockTags.LOGS).addTag(
                ModTags.Blocks.SOULBLIGHT_STEMS
        );

        tag(net.minecraft.tags.BlockTags.WART_BLOCKS).add(
                ModBlocks.BLIGHTWART_BLOCK.get()
        );
    }
}

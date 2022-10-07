package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModItems;
import gardensofthedead.common.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTags extends ItemTagsProvider {

    public ItemTags(DataGenerator generator, BlockTagsProvider blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.SOULBLIGHT_STEMS, ModTags.Items.SOULBlIGHT_STEMS);
        copy(BlockTags.LOGS, net.minecraft.tags.ItemTags.LOGS);
        copy(BlockTags.WART_BLOCKS, net.minecraft.tags.ItemTags.WART_BLOCKS);

        copy(BlockTags.WOODEN_FENCES, net.minecraft.tags.ItemTags.WOODEN_FENCES);
        copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        copy(BlockTags.WOODEN_BUTTONS, net.minecraft.tags.ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.PLANKS, net.minecraft.tags.ItemTags.PLANKS);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, net.minecraft.tags.ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.STANDING_SIGNS, net.minecraft.tags.ItemTags.SIGNS);
        copy(BlockTags.WOODEN_SLABS, net.minecraft.tags.ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_DOORS, net.minecraft.tags.ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_TRAPDOORS, net.minecraft.tags.ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.WOODEN_STAIRS, net.minecraft.tags.ItemTags.WOODEN_STAIRS);

        tag(net.minecraft.tags.ItemTags.NON_FLAMMABLE_WOOD).add(
                ModItems.SOULBLIGHT_STEM.get(),
                ModItems.STRIPPED_SOULBLIGHT_STEM.get(),
                ModItems.SOULBLIGHT_HYPHAE.get(),
                ModItems.STRIPPED_SOULBLIGHT_HYPHAE.get(),
                ModItems.SOULBLIGHT_PLANKS.get(),
                ModItems.SOULBLIGHT_SLAB.get(),
                ModItems.SOULBLIGHT_PRESSURE_PLATE.get(),
                ModItems.SOULBLIGHT_FENCE.get(),
                ModItems.SOULBLIGHT_TRAPDOOR.get(),
                ModItems.SOULBLIGHT_FENCE_GATE.get(),
                ModItems.SOULBLIGHT_STAIRS.get(),
                ModItems.SOULBLIGHT_BUTTON.get(),
                ModItems.SOULBLIGHT_DOOR.get(),
                ModItems.SOULBLIGHT_SIGN.get()
        );
    }
}

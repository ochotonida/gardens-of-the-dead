package gardensofthedead.data.providers;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.registry.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTagsProvider extends net.minecraft.data.tags.ItemTagsProvider {

    public ItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, GardensOfTheDead.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.SOULBLIGHT_STEMS, ModTags.Items.SOULBLIGHT_STEMS);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.WART_BLOCKS, ItemTags.WART_BLOCKS);

        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(ModTags.Blocks.FENCES, ModTags.Items.FENCES);
        copy(ModTags.Blocks.FENCE_GATES, ModTags.Items.FENCE_GATES);
        copy(ModTags.Blocks.WOODEN_FENCES, ModTags.Items.WOODEN_FENCES);
        copy(ModTags.Blocks.WOODEN_FENCE_GATES, ModTags.Items.WOODEN_FENCE_GATES);

        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);

        copy(ModTags.Blocks.MUSHROOMS, ModTags.Items.MUSHROOMS);

        tag(ItemTags.NON_FLAMMABLE_WOOD).add(
                gardensofthedead.data.providers.BlockTagsProvider.NON_FLAMMABLE_WOOD_ITEMS
                        .stream()
                        .map(Block::asItem)
                        .toArray(Item[]::new)
        );
    }
}

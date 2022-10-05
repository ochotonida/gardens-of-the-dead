package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTags extends ItemTagsProvider {

    public ItemTags(DataGenerator generator, BlockTagsProvider blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.SOULBLIGHT_STEMS, ModTags.Items.SOULBlIGHT_STEMS);
        copy(BlockTags.NON_FLAMMABLE_WOOD, net.minecraft.tags.ItemTags.NON_FLAMMABLE_WOOD);
        copy(BlockTags.LOGS, net.minecraft.tags.ItemTags.LOGS);
    }
}

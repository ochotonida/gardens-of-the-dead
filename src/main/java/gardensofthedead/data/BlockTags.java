package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
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
        // noinspection ConstantConditions
        tag(net.minecraft.tags.BlockTags.FLOWER_POTS).add(
                ForgeRegistries.BLOCKS.getValues()
                        .stream()
                        .filter(block -> ForgeRegistries.BLOCKS.getKey(block).getPath().equals(GardensOfTheDead.MODID))
                        .filter(block -> block instanceof FlowerPotBlock)
                        .toArray(Block[]::new)
        );
    }
}

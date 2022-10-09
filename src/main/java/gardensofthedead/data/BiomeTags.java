package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class BiomeTags extends BiomeTagsProvider {

    public BiomeTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(net.minecraft.tags.BiomeTags.IS_NETHER).add(
                ModBiomes.SOULBLIGHT_FOREST
        );

        tag(net.minecraft.tags.BiomeTags.HAS_BASTION_REMNANT).add(
                ModBiomes.SOULBLIGHT_FOREST
        );

        tag(net.minecraft.tags.BiomeTags.HAS_NETHER_FOSSIL).add(
                ModBiomes.SOULBLIGHT_FOREST
        );

        tag(Tags.Biomes.IS_HOT_NETHER).add(
                ModBiomes.SOULBLIGHT_FOREST
        );

        tag(Tags.Biomes.IS_DRY_NETHER).add(
                ModBiomes.SOULBLIGHT_FOREST
        );
    }
}

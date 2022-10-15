package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class BiomeTags extends BiomeTagsProvider {

    public BiomeTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, GardensOfTheDead.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            // noinspection ConstantConditions
            if (ForgeRegistries.BIOMES.getKey(biome).getNamespace().equals(GardensOfTheDead.MODID)) {
                tag(net.minecraft.tags.BiomeTags.IS_NETHER).add(biome);
                tag(Tags.Biomes.IS_HOT_NETHER).add(biome);
                tag(Tags.Biomes.IS_DRY_NETHER).add(biome);
            }
        }

        tag(net.minecraft.tags.BiomeTags.HAS_BASTION_REMNANT).add(
                ModBiomes.SOULBLIGHT_FOREST,
                ModBiomes.WHISTLING_WOODS
        );

        tag(net.minecraft.tags.BiomeTags.HAS_NETHER_FOSSIL).add(
                ModBiomes.SOULBLIGHT_FOREST
        );
    }
}

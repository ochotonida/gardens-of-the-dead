package gardensofthedead.forge.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.forge.common.feature.HugeFlatFungusFeature;
import gardensofthedead.forge.common.feature.SoulSporeColumnFeature;
import gardensofthedead.forge.common.feature.SoulblightForestVegetationFeature;
import gardensofthedead.forge.common.feature.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.forge.common.feature.configuration.SoulSporeColumnConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, GardensOfTheDead.MOD_ID);

    public static final RegistryObject<HugeFlatFungusFeature> HUGE_SOULBLIGHT_FUNGUS = FEATURES.register("soulblight_fungus", () -> new HugeFlatFungusFeature(HugeFlatFungusConfiguration.CODEC));
    public static final RegistryObject<SoulSporeColumnFeature> SOUL_SPORE_COLUMN = FEATURES.register("short_soul_spore_column", () -> new SoulSporeColumnFeature(SoulSporeColumnConfiguration.CODEC));
    public static final RegistryObject<SoulblightForestVegetationFeature> SOULBLIGHT_FOREST_VEGETATION = FEATURES.register("soulblight_forest_vegetation", () -> new SoulblightForestVegetationFeature(NetherForestVegetationConfig.CODEC));
}

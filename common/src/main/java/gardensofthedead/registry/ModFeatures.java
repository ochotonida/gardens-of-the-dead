package gardensofthedead.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.feature.HugeFlatFungusFeature;
import gardensofthedead.feature.SoulSporeColumnFeature;
import gardensofthedead.feature.SoulblightForestVegetationFeature;
import gardensofthedead.feature.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.feature.configuration.SoulSporeColumnConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registries.FEATURE);

    public static final RegistrySupplier<HugeFlatFungusFeature> HUGE_SOULBLIGHT_FUNGUS = FEATURES.register("soulblight_fungus", () -> new HugeFlatFungusFeature(HugeFlatFungusConfiguration.CODEC));
    public static final RegistrySupplier<SoulSporeColumnFeature> SOUL_SPORE_COLUMN = FEATURES.register("short_soul_spore_column", () -> new SoulSporeColumnFeature(SoulSporeColumnConfiguration.CODEC));
    public static final RegistrySupplier<SoulblightForestVegetationFeature> SOULBLIGHT_FOREST_VEGETATION = FEATURES.register("soulblight_forest_vegetation", () -> new SoulblightForestVegetationFeature(NetherForestVegetationConfig.CODEC));
}

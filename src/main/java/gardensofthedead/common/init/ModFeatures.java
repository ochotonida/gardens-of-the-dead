package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.features.HugeFlatFungusFeature;
import gardensofthedead.common.features.configuration.HugeFlatFungusConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, GardensOfTheDead.MODID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<HugeFlatFungusFeature> HUGE_SOULBLIGHT_FUNGUS = FEATURES.register("soulblight_fungus", () -> new HugeFlatFungusFeature(HugeFlatFungusConfiguration.CODEC));

    public static final RegistryObject<ConfiguredFeature<HugeFlatFungusConfiguration, ?>> SOULBLIGHT_FUNGUS_PLANTED = CONFIGURED_FEATURES.register("soulblight_fungus_planted", () -> new ConfiguredFeature<>(HUGE_SOULBLIGHT_FUNGUS.get(), new HugeFlatFungusConfiguration(
            Blocks.SOUL_SOIL.defaultBlockState(),
            ModBlocks.SOULBLIGHT_STEM.get().defaultBlockState(),
            ModBlocks.BLIGHTWART_BLOCK.get().defaultBlockState(),
            true
    )));

}

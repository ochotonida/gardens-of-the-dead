package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.features.HugeSoulblightFungusFeature;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, GardensOfTheDead.MODID);
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<HugeSoulblightFungusFeature> HUGE_SOULBLIGHT_FUNGUS = FEATURES.register("soulblight_fungus", () -> new HugeSoulblightFungusFeature(HugeFungusConfiguration.CODEC));

    public static final RegistryObject<ConfiguredFeature<HugeFungusConfiguration, ?>> SOULBLIGHT_FUNGUS_PLANTED = CONFIGURED_FEATURES.register("soulblight_fungus_planted", () -> new ConfiguredFeature<>(HUGE_SOULBLIGHT_FUNGUS.get(), new HugeFungusConfiguration(
            Blocks.SOUL_SOIL.defaultBlockState(),
            ModBlocks.SOULBLIGHT_STEM.get().defaultBlockState(),
            ModBlocks.BLIGHTWART_BLOCK.get().defaultBlockState(),
            Blocks.SHROOMLIGHT.defaultBlockState(), true
    )));

}

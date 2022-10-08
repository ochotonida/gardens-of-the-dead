package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.placementmodifiers.CountOnEveryCeilingPlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@SuppressWarnings("deprecation")
public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<PlacedFeature> SOULBLIGHT_FUNGI = register("soulblight_fungi", ModConfiguredFeatures.SOULBLIGHT_FUNGUS,
            CountOnEveryLayerPlacement.of(3),
            BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA))),
            BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Vec3i.ZERO.below(), Blocks.SOUL_SOIL)),
            BiomeFilter.biome()
    );

    public static final RegistryObject<PlacedFeature> SOULBLIGHT_FOREST_VEGETATION = register(
            "soulblight_forest_vegetation",
            ModConfiguredFeatures.SOULBLIGHT_FOREST_VEGETATION,
            CountOnEveryLayerPlacement.of(2),
            BiomeFilter.biome()
    );

    public static final RegistryObject<PlacedFeature> SHORT_STANDING_SOUL_SPORE_PATCH = register(
            "short_standing_soul_spore_patch",
            ModConfiguredFeatures.SHORT_STANDING_SOUL_SPORE_PATCH,
            CountOnEveryLayerPlacement.of(4),
            BiomeFilter.biome()
    );

    public static final RegistryObject<PlacedFeature> LONG_STANDING_SOUL_SPORE_PATCH = register(
            "long_standing_soul_spore_patch",
            ModConfiguredFeatures.LONG_STANDING_SOUL_SPORE_PATCH,
            CountOnEveryLayerPlacement.of(12),
            BiomeFilter.biome()
    );

    public static final RegistryObject<PlacedFeature> SHORT_HANGING_SOUL_SPORE_PATCH = register(
            "short_hanging_soul_spore_patch",
            ModConfiguredFeatures.SHORT_HANGING_SOUL_SPORE_PATCH,
            CountOnEveryCeilingPlacement.of(4),
            BiomeFilter.biome()
    );

    public static final RegistryObject<PlacedFeature> LONG_HANGING_SOUL_SPORE_PATCH = register(
            "long_hanging_soul_spore_patch",
            ModConfiguredFeatures.LONG_HANGING_SOUL_SPORE_PATCH,
            CountOnEveryCeilingPlacement.of(8),
            BiomeFilter.biome()
    );



    private static <T extends FeatureConfiguration> RegistryObject<PlacedFeature> register(String name, RegistryObject<ConfiguredFeature<T, ?>> feature, PlacementModifier... modifiers) {
        return PLACED_FEATURES.register(name, () -> new PlacedFeature(getHolder(feature.get()), List.of(modifiers)));
    }

    private static Holder<ConfiguredFeature<?, ?>> getHolder(ConfiguredFeature<?, ?> feature) {
        return getHolder(BuiltinRegistries.CONFIGURED_FEATURE, feature);
    }

    public static Holder<PlacedFeature> getHolder(PlacedFeature feature) {
        return getHolder(BuiltinRegistries.PLACED_FEATURE, feature);
    }

    private static <T> Holder<T> getHolder(Registry<T> registry, T object) {
        return registry.getHolderOrThrow(registry.getResourceKey(object).orElseThrow());
    }
}

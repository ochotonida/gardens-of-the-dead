package gardensofthedead.data.providers;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.placementmodifier.CountOnEveryCeilingPlacement;
import gardensofthedead.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class PlacedFeatureProvider {

    private static final Map<ResourceKey<PlacedFeature>, ResourceKey<ConfiguredFeature<?, ?>>> CONFIGURED_FEATURES = new HashMap<>();
    private static final Map<ResourceKey<PlacedFeature>, Supplier<List<PlacementModifier>>> PLACEMENT_MODIFIERS = new HashMap<>();

    public static void create(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        for (ResourceKey<PlacedFeature> key : PLACEMENT_MODIFIERS.keySet()) {
            Holder<ConfiguredFeature<?, ?>> configuredFeature = configuredFeatures.getOrThrow(CONFIGURED_FEATURES.get(key));
            List<PlacementModifier> modifiers = PLACEMENT_MODIFIERS.get(key).get();
            PlacedFeature placedFeature = new PlacedFeature(configuredFeature, modifiers);
            context.register(key, placedFeature);
        }
    }

    public static final ResourceKey<PlacedFeature> SOULBLIGHT_FUNGI = register(
            "soulblight_fungi",
            ConfiguredFeatureProvider.SOULBLIGHT_FUNGUS,
            CountOnEveryLayerPlacement.of(3),
            BlockPredicateFilter.forPredicate(BlockPredicate.not(BlockPredicate.matchesFluids(Fluids.LAVA))),
            BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Vec3i.ZERO.below(), Blocks.SOUL_SOIL)),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> SOULBLIGHT_FOREST_VEGETATION = register(
            "soulblight_forest_vegetation",
            ConfiguredFeatureProvider.SOULBLIGHT_FOREST_VEGETATION,
            CountOnEveryLayerPlacement.of(2),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> SHORT_STANDING_SOUL_SPORE_PATCH = register(
            "short_standing_soul_spore_patch",
            ConfiguredFeatureProvider.SHORT_STANDING_SOUL_SPORE_PATCH,
            CountOnEveryLayerPlacement.of(4),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> LONG_STANDING_SOUL_SPORE_PATCH = register(
            "long_standing_soul_spore_patch",
            ConfiguredFeatureProvider.LONG_STANDING_SOUL_SPORE_PATCH,
            CountOnEveryLayerPlacement.of(12),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> SHORT_HANGING_SOUL_SPORE_PATCH = register(
            "short_hanging_soul_spore_patch",
            ConfiguredFeatureProvider.SHORT_HANGING_SOUL_SPORE_PATCH,
            CountOnEveryCeilingPlacement.of(4),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> LONG_HANGING_SOUL_SPORE_PATCH = register(
            "long_hanging_soul_spore_patch",
            ConfiguredFeatureProvider.LONG_HANGING_SOUL_SPORE_PATCH,
            CountOnEveryCeilingPlacement.of(8),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> NOISY_CRIMSON_FUNGI = register(
            "noisy_crimson_fungi",
            TreeFeatures.CRIMSON_FUNGUS,
            NoiseBasedCountPlacement.of(6, 180, 0),
            CountOnEveryLayerPlacement.of(1),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> DENSE_WEEPING_VINES = register(
            "dense_weeping_vines",
            NetherFeatures.WEEPING_VINES,
            CountPlacement.of(40),
            InSquarePlacement.spread(),
            PlacementUtils.FULL_RANGE,
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> WHISTLECANE_COLUMN = register(
            "whistlecane_column",
            ConfiguredFeatureProvider.WHISTLECANE_COLUMN,
            () -> List.of(
                    CountOnEveryLayerPlacement.of(20),
                    PlacementUtils.filteredByBlockSurvival(ModBlocks.WHISTLECANE.get()),
                    BiomeFilter.biome()
            )
    );

    public static final ResourceKey<PlacedFeature> WHISTLING_WOODS_VEGETATION = register(
            "whistling_woods_vegetation",
            ConfiguredFeatureProvider.WHISTLING_WOODS_VEGETATION,
            CountOnEveryLayerPlacement.of(10),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> TALL_BLISTERCROWN_PATCH = register(
            "tall_blistercrown_patch",
            ConfiguredFeatureProvider.TALL_BLISTERCROWN_PATCH,
            CountOnEveryLayerPlacement.of(1),
            BiomeFilter.biome()
    );

    public static final ResourceKey<PlacedFeature> NETHER_WART_BLOCK_PILE = register(
            "nether_wart_block_pile",
            ConfiguredFeatureProvider.NETHER_WART_BLOCK_PILE,
            CountOnEveryLayerPlacement.of(1),
            RarityFilter.onAverageOnceEvery(2),
            BiomeFilter.biome()
    );

    private static ResourceKey<PlacedFeature> register(String name, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers) {
        return register(name, configuredFeature, () -> List.of(modifiers));
    }

    private static ResourceKey<PlacedFeature> register(String name, ResourceKey<ConfiguredFeature<?, ?>> configuredFeature, Supplier<List<PlacementModifier>> modifiers) {
        ResourceKey<PlacedFeature> key = ResourceKey.create(Registries.PLACED_FEATURE, GardensOfTheDead.id(name));
        CONFIGURED_FEATURES.put(key, configuredFeature);
        PLACEMENT_MODIFIERS.put(key, modifiers);
        return key;
    }
}

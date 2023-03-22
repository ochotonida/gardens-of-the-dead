package gardensofthedead.data.providers;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.block.SoulSporeBlock;
import gardensofthedead.block.SoulblightFungusBlock;
import gardensofthedead.block.WhistlecaneBlock;
import gardensofthedead.feature.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.feature.configuration.SoulSporeColumnConfiguration;
import gardensofthedead.registry.ModBlocks;
import gardensofthedead.registry.ModFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ConfiguredFeatureProvider {

    private static final Map<ResourceKey<ConfiguredFeature<?, ?>>, Supplier<ConfiguredFeature<?, ?>>> CONFIGURED_FEATURES = new HashMap<>();

    public static void create(BootstapContext<ConfiguredFeature<?, ?>> context) {
        for (ResourceKey<ConfiguredFeature<?, ?>> key : CONFIGURED_FEATURES.keySet()) {
            context.register(key, CONFIGURED_FEATURES.get(key).get());
        }
    }

    @SuppressWarnings("unused")
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOULBLIGHT_FUNGUS_PLANTED = register(
            SoulblightFungusBlock.SOULBLIGHT_FUNGUS_PLANTED,
            ModFeatures.HUGE_SOULBLIGHT_FUNGUS,
            () -> new HugeFlatFungusConfiguration(
                    SoulblightFungusBlock.REQUIRED_BLOCK.defaultBlockState(),
                    ModBlocks.SOULBLIGHT_STEM.get().defaultBlockState(),
                    ModBlocks.BLIGHTWART_BLOCK.get().defaultBlockState(),
                    true
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOULBLIGHT_FUNGUS = register(
            "soulblight_fungus",
            ModFeatures.HUGE_SOULBLIGHT_FUNGUS,
            () -> new HugeFlatFungusConfiguration(
                Blocks.SOUL_SOIL.defaultBlockState(),
                ModBlocks.SOULBLIGHT_STEM.get().defaultBlockState(),
                ModBlocks.BLIGHTWART_BLOCK.get().defaultBlockState(),
                false
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHORT_STANDING_SOUL_SPORE_PATCH = soulSporePatch(
            "short_standing_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_SHORT,
            Direction.UP,
            0.05F,
            Blocks.SOUL_SOIL
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> LONG_STANDING_SOUL_SPORE_PATCH = soulSporePatch(
            "long_standing_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_LONG,
            Direction.UP,
            0.05F,
            Blocks.SOUL_SAND
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHORT_HANGING_SOUL_SPORE_PATCH = soulSporePatch(
            "short_hanging_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_SHORT,
            Direction.DOWN,
            0,
            Blocks.SOUL_SOIL
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> LONG_HANGING_SOUL_SPORE_PATCH = soulSporePatch(
            "long_hanging_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_LONG,
            Direction.DOWN,
            0.05F,
            Blocks.SOUL_SAND
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOULBLIGHT_FOREST_VEGETATION = register(
            "soulblight_forest_vegetation",
            ModFeatures.SOULBLIGHT_FOREST_VEGETATION,
            () -> new NetherForestVegetationConfig(soulblightVegetationProvider(), 8, 4)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WHISTLECANE_COLUMN = register(
            "whistlecane_column",
            () -> Feature.BLOCK_COLUMN,
            () -> new BlockColumnConfiguration(
                    List.of(
                            BlockColumnConfiguration.layer(
                                    UniformInt.of(WhistlecaneBlock.MAX_HEIGHT - 4, WhistlecaneBlock.MAX_HEIGHT),
                                    BlockStateProvider.simple(ModBlocks.WHISTLECANE.get().defaultBlockState().setValue(WhistlecaneBlock.GROWING, false))
                            )
                    ),
                    Direction.UP,
                    BlockPredicate.ONLY_IN_AIR_PREDICATE,
                    true
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> WHISTLING_WOODS_VEGETATION = register(
            "whistling_woods_vegetation",
            () -> Feature.NETHER_FOREST_VEGETATION,
            () -> new NetherForestVegetationConfig(whistlingWoodsVegetationProvider(), 8, 4)
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_BLISTERCROWN_PATCH = register(
            "tall_blistercrown_patch",
            () -> Feature.RANDOM_PATCH,
            () -> FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK,
                    new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TALL_BLISTERCROWN.get())),
                    List.of(),
                    32
            )
    );

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_WART_BLOCK_PILE = register(
            "nether_wart_block_pile",
            () -> Feature.BLOCK_PILE,
            () -> new BlockPileConfiguration(
                    new WeightedStateProvider(
                            SimpleWeightedRandomList.<BlockState>builder()
                                    .add(Blocks.NETHER_WART_BLOCK.defaultBlockState(), 10)
                                    .add(Blocks.SHROOMLIGHT.defaultBlockState(), 1)
                    )
            )
    );


    private static WeightedStateProvider soulblightVegetationProvider() {
        return new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(ModBlocks.SOULBLIGHT_SPROUTS.get().defaultBlockState(), 48)
                .add(ModBlocks.SOULBLIGHT_FUNGUS.get().defaultBlockState(), 48)
                .add(Blocks.CRIMSON_FUNGUS.defaultBlockState(), 2)
                .add(Blocks.WARPED_FUNGUS.defaultBlockState(), 2)
        );
    }

    private static WeightedStateProvider whistlingWoodsVegetationProvider() {
        return new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(Blocks.CRIMSON_ROOTS.defaultBlockState(), 70)
                .add(ModBlocks.BLISTERCROWN.get().defaultBlockState(), 18)
                .add(Blocks.CRIMSON_FUNGUS.defaultBlockState(), 10)
                .add(Blocks.WARPED_FUNGUS.defaultBlockState(), 2)
        );
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> soulSporePatch(String name, int length, Direction direction, float glowingChance, Block... matchingBlocks) {
        return register(
                name,
                () -> Feature.RANDOM_PATCH,
                () -> new RandomPatchConfiguration(
                        64, 8, 4,
                        PlacementUtils.filtered(
                                ModFeatures.SOUL_SPORE_COLUMN.get(),
                                new SoulSporeColumnConfiguration(UniformInt.of(1, length), direction, glowingChance),
                                BlockPredicate.allOf(
                                        BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                        BlockPredicate.matchesBlocks(
                                                direction.getOpposite().getNormal(),
                                                matchingBlocks
                                        )
                                )
                        )
                )
        );
    }

    private static <T extends FeatureConfiguration> ResourceKey<ConfiguredFeature<?, ?>> register(ResourceKey<ConfiguredFeature<?, ?>> key, Supplier<? extends Feature<T>> feature, Supplier<T> config) {
        CONFIGURED_FEATURES.put(key, () -> new ConfiguredFeature<>(feature.get(), config.get()));
        return key;
    }

    private static <T extends FeatureConfiguration> ResourceKey<ConfiguredFeature<?, ?>> register(String name, Supplier<? extends Feature<T>> feature, Supplier<T> config) {
        ResourceKey<ConfiguredFeature<?, ?>> key = ResourceKey.create(Registries.CONFIGURED_FEATURE, GardensOfTheDead.id(name));
        CONFIGURED_FEATURES.put(key, () -> new ConfiguredFeature<>(feature.get(), config.get()));
        return key;
    }
}

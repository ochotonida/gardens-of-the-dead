package gardensofthedead.forge.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.forge.common.block.SoulSporeBlock;
import gardensofthedead.forge.common.block.WhistlecaneBlock;
import gardensofthedead.forge.common.feature.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.forge.common.feature.configuration.SoulSporeColumnConfiguration;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, GardensOfTheDead.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<HugeFlatFungusConfiguration, ?>> SOULBLIGHT_FUNGUS_PLANTED = CONFIGURED_FEATURES.register("soulblight_fungus_planted", () -> new ConfiguredFeature<>(ModFeatures.HUGE_SOULBLIGHT_FUNGUS.get(), new HugeFlatFungusConfiguration(
            Blocks.SOUL_SOIL.defaultBlockState(),
            ModBlocks.SOULBLIGHT_STEM.get().defaultBlockState(),
            ModBlocks.BLIGHTWART_BLOCK.get().defaultBlockState(),
            true
    )));

    public static final RegistryObject<ConfiguredFeature<HugeFlatFungusConfiguration, ?>> SOULBLIGHT_FUNGUS = CONFIGURED_FEATURES.register("soulblight_fungus", () -> new ConfiguredFeature<>(ModFeatures.HUGE_SOULBLIGHT_FUNGUS.get(), new HugeFlatFungusConfiguration(
            Blocks.SOUL_SOIL.defaultBlockState(),
            ModBlocks.SOULBLIGHT_STEM.get().defaultBlockState(),
            ModBlocks.BLIGHTWART_BLOCK.get().defaultBlockState(),
            false
    )));

    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> SHORT_STANDING_SOUL_SPORE_PATCH = soulSporePatch(
            "short_standing_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_SHORT,
            Direction.UP,
            0.05F,
            Blocks.SOUL_SOIL
    );

    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> LONG_STANDING_SOUL_SPORE_PATCH = soulSporePatch(
            "long_standing_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_LONG,
            Direction.UP,
            0.05F,
            Blocks.SOUL_SAND
    );

    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> SHORT_HANGING_SOUL_SPORE_PATCH = soulSporePatch(
            "short_hanging_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_SHORT,
            Direction.DOWN,
            0,
            Blocks.SOUL_SOIL
    );

    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> LONG_HANGING_SOUL_SPORE_PATCH = soulSporePatch(
            "long_hanging_soul_spore_patch",
            SoulSporeBlock.MAX_LENGTH_LONG,
            Direction.DOWN,
            0.05F,
            Blocks.SOUL_SAND
    );

    public static final RegistryObject<ConfiguredFeature<NetherForestVegetationConfig, ?>> SOULBLIGHT_FOREST_VEGETATION = register(
            "soulblight_forest_vegetation",
            ModFeatures.SOULBLIGHT_FOREST_VEGETATION,
            () -> new NetherForestVegetationConfig(soulblightVegetationProvider(), 8, 4)
    );

    public static final RegistryObject<ConfiguredFeature<BlockColumnConfiguration, ?>> WHISTLECANE_COLUMN = ModConfiguredFeatures.register(
            "whistlecane_column",
            () -> Feature.BLOCK_COLUMN,
            () -> new BlockColumnConfiguration(
                    List.of(
                        BlockColumnConfiguration.layer(
                                UniformInt.of(WhistlecaneBlock.MAX_HEIGHT - 5, WhistlecaneBlock.MAX_HEIGHT - 1),
                                BlockStateProvider.simple(ModBlocks.WHISTLECANE_PLANT.get().defaultBlockState())
                        ),
                        BlockColumnConfiguration.layer(
                                ConstantInt.of(1),
                                BlockStateProvider.simple(ModBlocks.WHISTLECANE.get().defaultBlockState().setValue(WhistlecaneBlock.GROWING, false))
                        )
                    ),
                    Direction.UP,
                    BlockPredicate.ONLY_IN_AIR_PREDICATE,
                    true
            )
    );

    public static final RegistryObject<ConfiguredFeature<NetherForestVegetationConfig, ?>> WHISTLING_WOODS_VEGETATION = register(
            "whistling_woods_vegetation",
            () -> Feature.NETHER_FOREST_VEGETATION,
            () -> new NetherForestVegetationConfig(whistlingWoodsVegetationProvider(), 8, 4)
    );

    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> TALL_BLISTERCROWN_PATCH = register(
            "tall_blistercrown_patch",
            () -> Feature.RANDOM_PATCH,
            () -> FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK,
                    new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.TALL_BLISTERCROWN.get())),
                    List.of(),
                    32
            )
    );

    public static final RegistryObject<ConfiguredFeature<BlockPileConfiguration, ?>> NETHER_WART_BLOCK_PILE = register(
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

    private static RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> soulSporePatch(String name, int length, Direction direction, float glowingChance, Block... matchingBlocks) {
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

    private static <T extends FeatureConfiguration> RegistryObject<ConfiguredFeature<T, ?>> register(String name, Supplier<? extends Feature<T>> feature, Supplier<T> config) {
        return CONFIGURED_FEATURES.register(name, () -> new ConfiguredFeature<>(feature.get(), config.get()));
    }
}

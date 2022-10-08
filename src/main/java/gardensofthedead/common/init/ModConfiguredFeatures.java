package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.features.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.common.features.configuration.SoulSporeColumnConfiguration;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NetherForestVegetationConfig;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, GardensOfTheDead.MODID);

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

    private static WeightedStateProvider soulblightVegetationProvider() {
        return new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                .add(ModBlocks.SOULBLIGHT_SPROUTS.get().defaultBlockState(), 48)
                .add(ModBlocks.SOULBLIGHT_FUNGUS.get().defaultBlockState(), 48)
                .add(Blocks.CRIMSON_FUNGUS.defaultBlockState(), 2)
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

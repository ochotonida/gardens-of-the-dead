package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.features.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.common.features.configuration.SoulSporeColumnConfiguration;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<ConfiguredFeature<HugeFlatFungusConfiguration, ?>> SOULBLIGHT_FUNGUS_PLANTED = CONFIGURED_FEATURES.register("soulblight_fungus_planted", () -> new ConfiguredFeature<>(ModFeatures.HUGE_SOULBLIGHT_FUNGUS.get(), new HugeFlatFungusConfiguration(
            Blocks.SOUL_SOIL.defaultBlockState(),
            ModBlocks.SOULBLIGHT_STEM.get().defaultBlockState(),
            ModBlocks.BLIGHTWART_BLOCK.get().defaultBlockState(),
            true
    )));

    public static RegistryObject<ConfiguredFeature<SoulSporeColumnConfiguration, ?>> SHORT_UPWARDS_SOUL_SPORE_COLUMN = soulSporeColumn(
            "short_upwards_soul_spore_column",
            SoulSporeBlock.MAX_LENGTH_SHORT,
            Direction.UP,
            0
    );

    public static RegistryObject<ConfiguredFeature<SoulSporeColumnConfiguration, ?>> LONG_UPWARDS_SOUL_SPORE_COLUMN = soulSporeColumn(
            "long_upwards_soul_spore_column",
            SoulSporeBlock.MAX_LENGTH_LONG,
            Direction.UP,
            0.05F
    );

    public static RegistryObject<ConfiguredFeature<SoulSporeColumnConfiguration, ?>> SHORT_DOWNWARDS_SOUL_SPORE_COLUMN = soulSporeColumn(
            "short_downwards_soul_spore_column",
            SoulSporeBlock.MAX_LENGTH_SHORT,
            Direction.DOWN,
            0.05F
    );

    public static RegistryObject<ConfiguredFeature<SoulSporeColumnConfiguration, ?>> LONG_DOWNWARDS_SOUL_SPORE_COLUMN = soulSporeColumn(
            "long_downwards_soul_spore_column",
            SoulSporeBlock.MAX_LENGTH_LONG,
            Direction.DOWN,
            0.1F
    );

    private static RegistryObject<ConfiguredFeature<SoulSporeColumnConfiguration, ?>> soulSporeColumn(String name, int maxLength, Direction direction, float glowingChance) {
        return CONFIGURED_FEATURES.register(name,
                () -> new ConfiguredFeature<>(ModFeatures.SOUL_SPORE_COLUMN.get(), new SoulSporeColumnConfiguration(
                        UniformInt.of(1, maxLength),
                        direction,
                        glowingChance
                ))
        );
    }
}

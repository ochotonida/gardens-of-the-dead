package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.blocks.SoulSporeBlock;
import gardensofthedead.common.features.HugeFlatFungusFeature;
import gardensofthedead.common.features.SoulSporeColumnFeature;
import gardensofthedead.common.features.configuration.HugeFlatFungusConfiguration;
import gardensofthedead.common.features.configuration.SoulSporeColumnConfiguration;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registry.FEATURE_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<HugeFlatFungusFeature> HUGE_SOULBLIGHT_FUNGUS = FEATURES.register("soulblight_fungus", () -> new HugeFlatFungusFeature(HugeFlatFungusConfiguration.CODEC));
    public static final RegistryObject<SoulSporeColumnFeature> SOUL_SPORE_COLUMN = FEATURES.register("short_soul_spore_column", () -> new SoulSporeColumnFeature(SoulSporeColumnConfiguration.CODEC));
}

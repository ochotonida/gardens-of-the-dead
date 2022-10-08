package gardensofthedead.common.features.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record SoulSporeColumnConfiguration(
        IntProvider length,
        Direction direction,
        float glowingChance
) implements FeatureConfiguration {
    public static final Codec<SoulSporeColumnConfiguration> CODEC = RecordCodecBuilder.create(
            (builder) -> builder.group(
                    IntProvider.NON_NEGATIVE_CODEC.fieldOf("length").forGetter(SoulSporeColumnConfiguration::length),
                    Direction.CODEC.fieldOf("direction").forGetter(SoulSporeColumnConfiguration::direction),
                    Codec.FLOAT.fieldOf("glowingChance").forGetter(SoulSporeColumnConfiguration::glowingChance)
            ).apply(builder, SoulSporeColumnConfiguration::new)
    );
}

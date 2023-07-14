package gardensofthedead.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record HugeFlatFungusConfiguration(
        BlockState validBaseState,
        BlockState stemState,
        BlockState hatState,
        BlockPredicate replaceableBlocks,
        boolean planted
) implements FeatureConfiguration {
    public static final Codec<HugeFlatFungusConfiguration> CODEC = RecordCodecBuilder.create((builder) -> builder.group(
                    BlockState.CODEC.fieldOf("valid_base_block").forGetter((config) -> config.validBaseState),
                    BlockState.CODEC.fieldOf("stem_state").forGetter((config) -> config.stemState),
                    BlockState.CODEC.fieldOf("hat_state").forGetter((config) -> config.hatState),
                    BlockPredicate.CODEC.fieldOf("replaceable_blocks").forGetter((config) -> config.replaceableBlocks),
                    Codec.BOOL.fieldOf("planted").orElse(false).forGetter((config) -> config.planted)
            ).apply(builder, HugeFlatFungusConfiguration::new)
    );
}

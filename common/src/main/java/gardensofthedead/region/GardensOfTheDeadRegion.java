package gardensofthedead.region;

import com.mojang.datafixers.util.Pair;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.registry.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public interface GardensOfTheDeadRegion {

    int WEIGHT = 4;
    ResourceLocation ID = GardensOfTheDead.id("nether");

    default void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        add(mapper,
                Climate.parameters(0.4F, 0, 0, 0, 0, 0, 0),
                ModBiomes.SOULBLIGHT_FOREST
        );
        add(mapper,
                Climate.parameters(-0.4F, 0, 0, 0, 0, 0, 0),
                ModBiomes.WHISTLING_WOODS
        );
    }

    void add(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.ParameterPoint parameters, ResourceKey<Biome> biome);
}

package gardensofthedead.forge.common.region;

import com.mojang.datafixers.util.Pair;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.forge.common.init.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class GardensOfTheDeadRegion extends Region {

    public GardensOfTheDeadRegion() {
        super(GardensOfTheDead.id("nether"), RegionType.NETHER, 4);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addBiome(mapper,
                Climate.parameters(0.4F, 0, 0, 0, 0, 0, 0),
                ModBiomes.SOULBLIGHT_FOREST
        );
        addBiome(mapper,
                Climate.parameters(-0.4F, 0, 0, 0, 0, 0, 0),
                ModBiomes.WHISTLING_WOODS
        );
    }
}

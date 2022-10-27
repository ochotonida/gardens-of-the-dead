package gardensofthedead.fabric.region;

import com.mojang.datafixers.util.Pair;
import gardensofthedead.region.GardensOfTheDeadRegion;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class GardensOfTheDeadRegionFabric extends Region implements GardensOfTheDeadRegion {

    public GardensOfTheDeadRegionFabric() {
        super(ID, RegionType.NETHER, WEIGHT);
    }

    @Override
    public void add(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.ParameterPoint parameters, ResourceKey<Biome> biome) {
        addBiome(mapper, parameters, biome);
    }
}

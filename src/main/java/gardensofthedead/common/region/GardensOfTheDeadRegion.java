package gardensofthedead.common.region;

import com.mojang.datafixers.util.Pair;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class GardensOfTheDeadRegion extends Region {

    public GardensOfTheDeadRegion() {
        super(GardensOfTheDead.id("nether"), RegionType.NETHER, 2);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // TODO
        addBiome(mapper,
                Climate.parameters(0, 0, 0, 0, 0, 0, 0),
                ModBiomes.SOULBLIGHT_FOREST
        );
    }
}

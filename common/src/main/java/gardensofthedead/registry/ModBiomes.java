package gardensofthedead.registry;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {

    public static final ResourceKey<Biome> SOULBLIGHT_FOREST = ResourceKey.create(Registries.BIOME, GardensOfTheDead.id("soulblight_forest"));
    public static final ResourceKey<Biome> WHISTLING_WOODS = ResourceKey.create(Registries.BIOME, GardensOfTheDead.id("whistling_woods"));
}

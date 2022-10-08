package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBiomes {

    public static DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create(Registry.BIOME_REGISTRY, GardensOfTheDead.MODID);

    public static final ResourceKey<Biome> SOULBLIGHT_FOREST = register("soulblight_forest");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, GardensOfTheDead.id(name));
    }

    static {
        register(SOULBLIGHT_FOREST, ModBiomes::soulblightForest);
    }

    public static void register(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier) {
        BIOME_REGISTER.register(key.location().getPath(), biomeSupplier);
    }

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3;
        $$1 = Mth.clamp($$1, -1, 1);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1);
    }

    public static Biome soulblightForest() {
        double energyBudget = 0.7D;
        double charge = 0.15D;

        MobSpawnSettings mobspawnsettings = (new MobSpawnSettings.Builder()) // TODO change spawns
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 20, 5, 5))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.GHAST, 50, 4, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2))
                .addMobCharge(EntityType.SKELETON, energyBudget, charge)
                .addMobCharge(EntityType.GHAST, energyBudget, charge)
                .addMobCharge(EntityType.ENDERMAN, energyBudget, charge)
                .addMobCharge(EntityType.STRIDER, energyBudget, charge)
                .build();


        BiomeGenerationSettings.Builder biomeGenerationSettings = (new BiomeGenerationSettings.Builder()) // TODO change decoration stuff
                .addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_SOUL_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_SOUL_SAND)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.SOULBLIGHT_FUNGI.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.SOULBLIGHT_FOREST_VEGETATION.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.SHORT_STANDING_SOUL_SPORE_PATCH.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.LONG_STANDING_SOUL_SPORE_PATCH.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.SHORT_HANGING_SOUL_SPORE_PATCH.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.LONG_HANGING_SOUL_SPORE_PATCH.get()));

        BiomeDefaultFeatures.addNetherDefaultOres(biomeGenerationSettings);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2)
                .downfall(0)
                .specialEffects( // TODO effect things
                        (new BiomeSpecialEffects.Builder())
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .fogColor(0x593a21)
                                .skyColor(calculateSkyColor(2))
                                .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.00625F))
                                .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SOUL_SAND_VALLEY)).build())
                .mobSpawnSettings(mobspawnsettings)
                .generationSettings(biomeGenerationSettings.build())
                .build();
    }
}

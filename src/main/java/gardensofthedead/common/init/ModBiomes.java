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

    public static final ResourceKey<Biome> SOULBLIGHT_FOREST = register("soulblight_forest", ModBiomes::soulblightForest);
    public static final ResourceKey<Biome> WHISTLING_WOODS = register("whistling_woods", ModBiomes::whistlingWoods);

    private static ResourceKey<Biome> register(String name, Supplier<Biome> biomeSupplier) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, GardensOfTheDead.id(name));
        BIOME_REGISTER.register(key.location().getPath(), biomeSupplier);
        return key;
    }

    @SuppressWarnings("SameParameterValue")
    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3;
        $$1 = Mth.clamp($$1, -1, 1);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1);
    }

    public static Biome soulblightForest() {
        double energyBudget = 1D;
        double charge = 0.8D;

        MobSpawnSettings spawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 3, 5, 5))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 5, 2, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HOGLIN, 21, 3, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 10, 3, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2))
                .addMobCharge(EntityType.SKELETON, energyBudget, charge)
                .addMobCharge(EntityType.ENDERMAN, energyBudget, charge)
                .addMobCharge(EntityType.ZOMBIFIED_PIGLIN, energyBudget, charge)
                .addMobCharge(EntityType.HOGLIN, energyBudget, charge)
                .addMobCharge(EntityType.PIGLIN, energyBudget, charge)
                .addMobCharge(EntityType.STRIDER, energyBudget, charge)
                .build();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
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

        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addNetherDefaultOres(generationSettings);

        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2)
                .downfall(0)
                .specialEffects(
                        new BiomeSpecialEffects.Builder()
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .fogColor(0x593a21)
                                .skyColor(calculateSkyColor(2))
                                .ambientParticle(new AmbientParticleSettings(ModParticleTypes.SOULBLIGHT_SPORE.get(), 0.01F))
                                .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST)).build())
                .mobSpawnSettings(spawnSettings)
                .generationSettings(generationSettings.build())
                .build();
    }

    public static Biome whistlingWoods() {
        MobSpawnSettings spawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIFIED_PIGLIN, 1, 2, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HOGLIN, 9, 3, 4))
                .addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.PIGLIN, 5, 3, 4))
                .addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2))
                .build();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
                .addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MiscOverworldPlacements.SPRING_LAVA);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_OPEN)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.NETHER_WART_BLOCK_PILE.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.DENSE_WEEPING_VINES.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.NOISY_CRIMSON_FUNGI.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.WHISTLECANE_COLUMN.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.TALL_BLISTERCROWN_PATCH.get()))
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.getHolder(ModPlacedFeatures.WHISTLING_WOODS_VEGETATION.get()));
        BiomeDefaultFeatures.addNetherDefaultOres(generationSettings);

        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2)
                .downfall(0)
                .specialEffects(
                        new BiomeSpecialEffects.Builder()
                                .waterColor(4159204)
                                .waterFogColor(329011)
                                .fogColor(0x5e1118)
                                .skyColor(calculateSkyColor(2))
                                .ambientParticle(new AmbientParticleSettings(ParticleTypes.CRIMSON_SPORE, 0.01F))
                                .ambientLoopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                                .ambientMoodSound(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2))
                                .ambientAdditionsSound(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111))
                                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST)).build())
                .mobSpawnSettings(spawnSettings)
                .generationSettings(generationSettings.build())
                .build();
    }
}

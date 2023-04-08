package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.data.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DataPackRegistriesHooks;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = GardensOfTheDead.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GardensOfTheDeadData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(packOutput, lookupProvider, existingFileHelper);

        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeClient(), new BlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput));
        generator.addProvider(event.includeServer(), new RecipeProvider(packOutput));
        generator.addProvider(event.includeClient(), new SoundDefinitionsProvider(packOutput, existingFileHelper));

        RegistrySetBuilder levelProvider = createLevelProvider();
        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(generator.getPackOutput(), event.getLookupProvider(), levelProvider, Set.of(GardensOfTheDead.MOD_ID)));
        lookupProvider = lookupProvider.thenApply(r -> constructRegistries(r, levelProvider));

        generator.addProvider(event.includeServer(), new BiomeTagsProvider(packOutput, lookupProvider, existingFileHelper));
    }

    @SuppressWarnings("UnstableApiUsage")
    private static HolderLookup.Provider constructRegistries(HolderLookup.Provider original, RegistrySetBuilder datapackEntriesBuilder) {
        var builderKeys = new HashSet<>(datapackEntriesBuilder.getEntryKeys());
        DataPackRegistriesHooks.getDataPackRegistriesWithDimensions()
                .filter(data -> !builderKeys.contains(data.key()))
                .forEach(data -> datapackEntriesBuilder.add(data.key(), context -> {}));
        return datapackEntriesBuilder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), original);
    }


    public static RegistrySetBuilder createLevelProvider() {
        RegistrySetBuilder builder = new RegistrySetBuilder();
        builder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureProvider::create);
        builder.add(Registries.PLACED_FEATURE, PlacedFeatureProvider::create);
        builder.add(Registries.BIOME, BiomeProvider::create);
        return builder;
    }
}

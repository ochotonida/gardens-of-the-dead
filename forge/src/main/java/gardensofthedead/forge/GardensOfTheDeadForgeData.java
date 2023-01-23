package gardensofthedead.forge;

import gardensofthedead.forge.data.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GardensOfTheDeadForgeData {

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(GardensOfTheDeadForgeData::gatherData);
    }

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        BlockStates blockStates = new BlockStates(generator, existingFileHelper);
        generator.addProvider(event.includeClient(), blockStates);

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ItemTagsProvider(generator, blockTagsProvider, existingFileHelper));
        generator.addProvider(event.includeClient(), new BiomeTags(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTables(generator));
        generator.addProvider(event.includeServer(), new Recipes(generator));
        generator.addProvider(event.includeServer(), new SoundDefinitions(generator, existingFileHelper));
    }
}

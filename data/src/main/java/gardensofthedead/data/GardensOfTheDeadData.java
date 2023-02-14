package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.data.providers.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GardensOfTheDead.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GardensOfTheDeadData {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        BlockStateProvider blockStates = new BlockStateProvider(generator, existingFileHelper);
        generator.addProvider(event.includeClient(), blockStates);

        BlockTagsProvider blockTagsProvider = new BlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ItemTagsProvider(generator, blockTagsProvider, existingFileHelper));
        generator.addProvider(event.includeClient(), new BiomeTagsProvider(generator, existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTableProvider(generator));
        generator.addProvider(event.includeServer(), new RecipeProvider(generator));
        generator.addProvider(event.includeServer(), new SoundDefinitionsProvider(generator, existingFileHelper));
    }
}

package gardensofthedead;

import gardensofthedead.data.BlockStates;
import gardensofthedead.data.ItemModels;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GardensOfTheDeadData {

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(GardensOfTheDeadData::gatherData);
    }

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        BlockStates blockStates = new BlockStates(generator, helper);
        generator.addProvider(event.includeClient(), blockStates);
        generator.addProvider(event.includeClient(), new ItemModels(generator, blockStates.models().existingFileHelper));
    }
}

package gardensofthedead;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GardensOfTheDeadClient {

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}

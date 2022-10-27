package gardensofthedead.fabric;

import net.fabricmc.api.ModInitializer;
import gardensofthedead.GardensOfTheDead;

public class GardensOfTheDeadFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        GardensOfTheDead.init();
    }
}

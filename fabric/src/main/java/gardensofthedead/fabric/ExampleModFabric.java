package gardensofthedead.fabric;

import net.fabricmc.api.ModInitializer;
import gardensofthedead.GardensOfTheDead;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        GardensOfTheDead.init();
    }
}

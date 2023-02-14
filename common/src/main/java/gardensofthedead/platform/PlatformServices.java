package gardensofthedead.platform;

import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import gardensofthedead.GardensOfTheDead;

import java.util.ServiceLoader;

public class PlatformServices {

    public static final PlatformHelper platformHelper = load(PlatformHelper.class);
    public static final ClientPlatformHelper clientPlatformHelper = Platform.getEnvironment() == Env.CLIENT ?
            load(ClientPlatformHelper.class) : null;

    public static <T> T load(Class<T> c) {
        final T loadedService = ServiceLoader.load(c)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + c.getName()));
        GardensOfTheDead.LOGGER.debug("Loaded {} for service {}", loadedService, c);
        return loadedService;
    }
}

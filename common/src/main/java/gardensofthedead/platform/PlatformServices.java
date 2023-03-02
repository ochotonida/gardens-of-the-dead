package gardensofthedead.platform;

import gardensofthedead.GardensOfTheDead;

import java.util.ServiceLoader;

public class PlatformServices {

    public static final PlatformHelper platformHelper = load(PlatformHelper.class);

    public static <T> T load(Class<T> c) {
        final T loadedService = ServiceLoader.load(c)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + c.getName()));
        GardensOfTheDead.LOGGER.debug("Loaded {} for service {}", loadedService, c);
        return loadedService;
    }
}

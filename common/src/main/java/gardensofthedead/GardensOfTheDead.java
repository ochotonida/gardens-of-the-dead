package gardensofthedead;

import gardensofthedead.network.NetworkHandler;
import gardensofthedead.registry.*;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GardensOfTheDead {

    public static final String MOD_ID = "gardens_of_the_dead";
    public static final Logger LOGGER = LogManager.getLogger();

    public static ResourceLocation id(String path) {
        return new ResourceLocation(GardensOfTheDead.MOD_ID, path);
    }

    public static void init() {
        NetworkHandler.register();

        ModBlocks.BLOCKS.register();
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register();
        ModItems.ITEMS.register();
        ModFeatures.FEATURES.register();
        ModConfiguredFeatures.CONFIGURED_FEATURES.register();
        ModPlacementModifiers.PLACEMENT_MODIFIER_TYPES.register();
        ModPlacedFeatures.PLACED_FEATURES.register();
        ModParticleTypes.PARTICLE_TYPES.register();
        ModSoundEvents.SOUND_EVENTS.register();
        ModBiomes.BIOME_REGISTER.register();
        ModLootConditions.LOOT_CONDITIONS.register();
    }
}

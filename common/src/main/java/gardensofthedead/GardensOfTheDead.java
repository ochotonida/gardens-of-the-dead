package gardensofthedead;

import dev.architectury.event.events.client.ClientTickEvent;
import gardensofthedead.registry.*;
import net.minecraft.resources.ResourceLocation;

public class GardensOfTheDead {

    public static final String MOD_ID = "gardens_of_the_dead";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(GardensOfTheDead.MOD_ID, path);
    }

    public static void init() {
        ModBlocks.BLOCKS.register();
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register();
        ModItems.ITEMS.register();
        ModBiomes.BIOME_REGISTER.register();
        ModFeatures.FEATURES.register();
        ModConfiguredFeatures.CONFIGURED_FEATURES.register();
        ModPlacedFeatures.PLACED_FEATURES.register();
        ModPlacementModifiers.PLACEMENT_MODIFIER_TYPES.register();
        ModParticleTypes.PARTICLE_TYPES.register();
        ModSoundEvents.SOUND_EVENTS.register();
        
        // System.out.println(ExampleExpectPlatform.getConfigDirectory().toAbsolutePath().normalize().toString());
    }
}

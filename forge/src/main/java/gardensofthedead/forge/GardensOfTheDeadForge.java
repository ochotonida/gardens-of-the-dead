package gardensofthedead.forge;

import dev.architectury.platform.forge.EventBuses;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.forge.common.init.*;
import gardensofthedead.forge.common.network.NetworkHandler;
import gardensofthedead.forge.common.region.GardensOfTheDeadRegion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(GardensOfTheDead.MOD_ID)
public class GardensOfTheDeadForge {

    public GardensOfTheDeadForge() {
        EventBuses.registerModEventBus(GardensOfTheDead.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        GardensOfTheDead.init();

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GardensOfTheDeadClient::init);
        GardensOfTheDeadData.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBiomes.BIOME_REGISTER.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        ModPlacementModifiers.PLACEMENT_MODIFIER_TYPES.register(modEventBus);
        ModParticleTypes.PARTICLE_TYPES.register(modEventBus);
        ModSoundEvents.SOUND_EVENTS.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new GardensOfTheDeadRegion());
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, GardensOfTheDead.MOD_ID, ModSurfaceRules.makeRules());
            ModItems.addCompostables();
            NetworkHandler.register();
        });
    }
}

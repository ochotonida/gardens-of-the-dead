package gardensofthedead;

import gardensofthedead.client.WhistleEventHandler;
import gardensofthedead.client.particle.SoulblightSporeProvider;
import gardensofthedead.client.particle.WhistlecaneSmokeParticle;
import gardensofthedead.common.init.ModBlockEntityTypes;
import gardensofthedead.common.init.ModParticleTypes;
import gardensofthedead.common.init.ModWoodTypes;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GardensOfTheDeadClient {

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(GardensOfTheDeadClient::onClientSetup);
        modEventBus.addListener(GardensOfTheDeadClient::onRegisterRenderers);
        modEventBus.addListener(GardensOfTheDeadClient::onRegisterParticleProviders);

        MinecraftForge.EVENT_BUS.addListener(WhistleEventHandler::onClientTick);
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(ModBlockEntityTypes.SIGN.get(), SignRenderer::new);
    }

    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ModWoodTypes.register();
    }

    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.register(ModParticleTypes.SOULBLIGHT_SPORE.get(), SoulblightSporeProvider::new);
        event.register(ModParticleTypes.WHISTLECANE_SMOKE.get(), WhistlecaneSmokeParticle.Provider::new);
    }
}

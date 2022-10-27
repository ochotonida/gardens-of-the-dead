package gardensofthedead.forge;

import dev.architectury.platform.forge.EventBuses;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.network.NetworkHandler;
import gardensofthedead.forge.region.GardensOfTheDeadRegionForge;
import gardensofthedead.registry.*;
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

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GardensOfTheDeadClientForge::init);
        GardensOfTheDeadData.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new GardensOfTheDeadRegionForge());
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, GardensOfTheDead.MOD_ID, ModSurfaceRules.makeRules());
            ModItems.addCompostables();
            NetworkHandler.register();
        });
    }
}

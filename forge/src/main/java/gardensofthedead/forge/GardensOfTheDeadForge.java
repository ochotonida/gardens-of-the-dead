package gardensofthedead.forge;

import dev.architectury.platform.forge.EventBuses;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.GardensOfTheDeadClient;
import gardensofthedead.forge.region.GardensOfTheDeadForgeRegion;
import gardensofthedead.registry.*;
import net.minecraft.world.level.block.ComposterBlock;
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
        GardensOfTheDeadForgeData.init();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GardensOfTheDeadClient::init);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GardensOfTheDeadForgeClient::init);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new GardensOfTheDeadForgeRegion());
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, GardensOfTheDead.MOD_ID, ModSurfaceRules.makeRules());
            ModItems.addCompostables((k, v) -> ComposterBlock.COMPOSTABLES.put(k, (float) v));
        });
    }
}

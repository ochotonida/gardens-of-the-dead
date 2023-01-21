package gardensofthedead.fabric;

import gardensofthedead.fabric.region.GardensOfTheDeadFabricRegion;
import gardensofthedead.registry.ModItems;
import gardensofthedead.registry.ModSurfaceRules;
import net.fabricmc.api.ModInitializer;
import gardensofthedead.GardensOfTheDead;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class GardensOfTheDeadFabric implements ModInitializer, TerraBlenderApi {

    @Override
    public void onInitialize() {
        GardensOfTheDead.init();

        ModItems.addCompostables(CompostingChanceRegistry.INSTANCE::add);
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new GardensOfTheDeadFabricRegion());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, GardensOfTheDead.MOD_ID, ModSurfaceRules.makeRules());
    }
}

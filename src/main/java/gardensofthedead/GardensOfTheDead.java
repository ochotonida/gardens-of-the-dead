package gardensofthedead;

import gardensofthedead.common.init.ModBiomes;
import gardensofthedead.common.init.ModBlocks;
import gardensofthedead.common.init.ModItems;
import gardensofthedead.common.region.GardensOfTheDeadRegion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import terrablender.api.Regions;

@Mod(GardensOfTheDead.MODID)
public class GardensOfTheDead {

    // TODO add compostables

    public static final String MODID = "gardens_of_the_dead";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    public GardensOfTheDead() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> GardensOfTheDeadClient::new);
        GardensOfTheDeadData.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBiomes.BIOME_REGISTER.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> Regions.register(new GardensOfTheDeadRegion()));
    }
}

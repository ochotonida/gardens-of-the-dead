package gardensofthedead.fabric;

import gardensofthedead.GardensOfTheDeadClient;
import gardensofthedead.client.particle.SoulblightSporeProvider;
import gardensofthedead.client.particle.WhistlecaneSmokeParticle;
import gardensofthedead.registry.ModBlockEntityTypes;
import gardensofthedead.registry.ModBlocks;
import gardensofthedead.registry.ModParticleTypes;
import gardensofthedead.registry.ModWoodTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class GardensOfTheDeadFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        GardensOfTheDeadClient.init();
        ModWoodTypes.register();
        BlockEntityRenderers.register(ModBlockEntityTypes.SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.HANGING_SIGN.get(), HangingSignRenderer::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.SOULBLIGHT_SPORE.get(), SoulblightSporeProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.WHISTLECANE_SMOKE.get(), WhistlecaneSmokeParticle.Provider::new);
        registerRenderTypes();
    }

    private static void registerRenderTypes() {
        for (Block block : List.of(
                ModBlocks.BLISTERCROWN.get(),
                ModBlocks.TALL_BLISTERCROWN.get(),
                ModBlocks.SOUL_SPORE.get(),
                ModBlocks.GLOWING_SOUL_SPORE.get(),
                ModBlocks.SOULBLIGHT_FUNGUS.get(),
                ModBlocks.SOULBLIGHT_SPROUTS.get(),
                ModBlocks.WHISTLECANE.get(),
                ModBlocks.TALL_BLISTERCROWN.get(),

                ModBlocks.POTTED_BLISTERCROWN.get(),
                ModBlocks.POTTED_SOUL_SPORE.get(),
                ModBlocks.POTTED_GLOWING_SOUL_SPORE.get(),
                ModBlocks.POTTED_SOULBLIGHT_FUNGUS.get(),
                ModBlocks.POTTED_SOULBLIGHT_SPROUTS.get(),
                ModBlocks.POTTED_WHISTLECANE.get(),

                ModBlocks.SOULBLIGHT_DOOR.get(),
                ModBlocks.SOULBLIGHT_TRAPDOOR.get(),
                ModBlocks.WHISTLECANE_DOOR.get(),
                ModBlocks.WHISTLECANE_TRAPDOOR.get()
        )) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        }
    }
}

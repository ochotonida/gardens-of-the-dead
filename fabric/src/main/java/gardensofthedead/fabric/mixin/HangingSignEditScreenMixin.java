package gardensofthedead.fabric.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.registry.ModWoodTypes;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.gui.screens.inventory.HangingSignEditScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HangingSignEditScreen.class)
public abstract class HangingSignEditScreenMixin extends AbstractSignEditScreen {

    public HangingSignEditScreenMixin(SignBlockEntity signBlockEntity, boolean bl) {
        super(signBlockEntity, bl);
    }

    @Unique
    private ResourceLocation texture = null;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void HangingSignEditScreen(SignBlockEntity signBlockEntity, boolean bl, CallbackInfo ci) {
        if (ModWoodTypes.VALUES.contains(woodType)) {
            texture = GardensOfTheDead.id("textures/gui/hanging_signs/" + this.woodType.name() + ".png");
        }
    }

    @Inject(method = "renderSignBackground", at = @At("HEAD"), cancellable = true)
    protected void renderSignBackground(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, BlockState blockState, CallbackInfo ci) {
        if (texture != null) {
            poseStack.translate(0.0F, -13.0F, 0.0F);
            RenderSystem.setShaderTexture(0, this.texture);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.scale(4.0F, 4.0F, 1.0F);
            blit(poseStack, -8, -8, 0.0F, 0.0F, 16, 16, 16, 16);
            ci.cancel();
        }
    }
}

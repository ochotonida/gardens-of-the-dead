package gardensofthedead.mixin;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class)
public interface WoodTypeInvoker {

    @Invoker("<init>")
    static WoodType newWoodType(String name) {
        throw new AssertionError();
    }

    @Invoker("register")
    static WoodType invokerRegister(WoodType woodType) {
        throw new AssertionError();
    }
}

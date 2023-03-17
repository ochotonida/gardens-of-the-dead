package gardensofthedead.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import gardensofthedead.mixin.SimpleParticleTypeInvoker;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;

public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registries.PARTICLE_TYPE);

    public static final RegistrySupplier<SimpleParticleType> SOULBLIGHT_SPORE = PARTICLE_TYPES.register("soulblight_spore", () -> SimpleParticleTypeInvoker.invokeConstructor(false));
    public static final RegistrySupplier<SimpleParticleType> WHISTLECANE_SMOKE = PARTICLE_TYPES.register("whistlecane_smoke", () -> SimpleParticleTypeInvoker.invokeConstructor(false));
}

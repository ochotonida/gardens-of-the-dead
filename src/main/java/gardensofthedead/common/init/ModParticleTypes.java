package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registry.PARTICLE_TYPE_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<SimpleParticleType> SOULBLIGHT_SPORE = PARTICLE_TYPES.register("soulblight_spore", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> WHISTLECANE_SMOKE = PARTICLE_TYPES.register("whistlecane_smoke", () -> new SimpleParticleType(false));
}

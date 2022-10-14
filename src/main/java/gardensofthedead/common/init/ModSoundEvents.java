package gardensofthedead.common.init;

import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registry.SOUND_EVENT_REGISTRY, GardensOfTheDead.MODID);

    public static final RegistryObject<SoundEvent> WHISTLECANE_WHISTLE = register("whistlecane_whistle", 32);

    private static RegistryObject<SoundEvent> register(String name, float range) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(GardensOfTheDead.id(name), range));
    }
}

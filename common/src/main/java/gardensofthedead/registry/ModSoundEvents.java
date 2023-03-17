package gardensofthedead.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(GardensOfTheDead.MOD_ID, Registries.SOUND_EVENT);

    public static final int WHISTLECANE_RANGE = 32;
    public static final RegistrySupplier<SoundEvent> WHISTLECANE_WHISTLE = register("block.whistlecane.whistle", WHISTLECANE_RANGE);

    public static final RegistrySupplier<SoundEvent> SOULBLIGHT_FOREST_MUSIC = register("music.soulblight_forest");
    public static final RegistrySupplier<SoundEvent> WHISTLING_WOODS_MUSIC = register("music.whistling_woods");

    private static RegistrySupplier<SoundEvent> register(String name, float range) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createFixedRangeEvent(GardensOfTheDead.id(name), range));
    }

    private static RegistrySupplier<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(GardensOfTheDead.id(name)));
    }
}

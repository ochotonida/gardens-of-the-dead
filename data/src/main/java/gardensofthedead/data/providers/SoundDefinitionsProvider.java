package gardensofthedead.data.providers;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.registry.ModSoundEvents;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;

public class SoundDefinitionsProvider extends net.minecraftforge.common.data.SoundDefinitionsProvider {

    public SoundDefinitionsProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GardensOfTheDead.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        subtitled(ModSoundEvents.WHISTLECANE_WHISTLE.get())
                .with(sound(GardensOfTheDead.id("block/whistlecane/whistle1"))
                        .attenuationDistance(ModSoundEvents.WHISTLECANE_RANGE)
                        .weight(1))
                .with(sound(GardensOfTheDead.id("block/whistlecane/whistle2"))
                        .attenuationDistance(ModSoundEvents.WHISTLECANE_RANGE)
                        .weight(2));
    }

    private SoundDefinition add(SoundEvent soundEvent) {
        SoundDefinition result = definition();
        add(soundEvent, result);
        return result;
    }

    private SoundDefinition subtitled(SoundEvent soundEvent) {
        return add(soundEvent).subtitle("%s.subtitle.%s".formatted(GardensOfTheDead.MOD_ID, soundEvent.getLocation().getPath()));
    }
}

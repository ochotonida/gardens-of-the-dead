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
        addWithSubtitle(ModSoundEvents.WHISTLECANE_WHISTLE.get())
                .with(sound(GardensOfTheDead.id("block/whistlecane/whistle1"))
                        .attenuationDistance(ModSoundEvents.WHISTLECANE_RANGE)
                        .weight(1))
                .with(sound(GardensOfTheDead.id("block/whistlecane/whistle2"))
                        .attenuationDistance(ModSoundEvents.WHISTLECANE_RANGE)
                        .weight(2));

        addMusic();
    }

    private void addMusic() {
        addWithDefaultMusic(ModSoundEvents.SOULBLIGHT_FOREST_MUSIC.get())
                .with(sound(GardensOfTheDead.id("music/soulblight_forest")).stream().volume(0.5).weight(7));
        addWithDefaultMusic(ModSoundEvents.WHISTLING_WOODS_MUSIC.get())
                .with(sound(GardensOfTheDead.id("music/whistling_woods")).stream().volume(0.5).weight(7));
    }

    private SoundDefinition add(SoundEvent soundEvent) {
        SoundDefinition result = definition();
        add(soundEvent, result);
        return result;
    }

    private SoundDefinition addWithSubtitle(SoundEvent soundEvent) {
        return add(soundEvent).subtitle("%s.subtitle.%s".formatted(GardensOfTheDead.MOD_ID, soundEvent.getLocation().getPath()));
    }

    private SoundDefinition addWithDefaultMusic(SoundEvent soundEvent) {
        return add(soundEvent)
                .with(sound("music/game/nether/nether1").stream())
                .with(sound("music/game/nether/nether2").stream())
                .with(sound("music/game/nether/nether3").stream())
                .with(sound("music/game/nether/nether4").stream());
    }
}

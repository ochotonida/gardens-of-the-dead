package gardensofthedead.data;

import gardensofthedead.GardensOfTheDead;
import gardensofthedead.common.init.ModSoundEvents;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class SoundDefinitions extends SoundDefinitionsProvider {

    public SoundDefinitions(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GardensOfTheDead.MODID, helper);
    }

    @Override
    public void registerSounds() {
        add(ModSoundEvents.WHISTLECANE_WHISTLE.get(), definition()
                .subtitle("%s.subtitle.whistlecane_whistles".formatted(GardensOfTheDead.MODID))
                .with(sound(GardensOfTheDead.id("whistlecane_whistle0")).volume(0.6).attenuationDistance(32).weight(1))
                .with(sound(GardensOfTheDead.id("whistlecane_whistle1")).volume(0.6).attenuationDistance(32).weight(2))
        );
    }
}

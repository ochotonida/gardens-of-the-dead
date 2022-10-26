package gardensofthedead.forge.common.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

import java.util.function.Supplier;

public class ModSoundTypes {

    public static final SoundType WHISTLECANE = Builder.copy(SoundType.BAMBOO).setPitch(0.5F).build();

    private static class Builder {

        private float volume = 1;
        private float pitch = 1;
        Supplier<SoundEvent> breakSound;
        Supplier<SoundEvent> stepSound;
        Supplier<SoundEvent> placeSound;
        Supplier<SoundEvent> hitSound;
        Supplier<SoundEvent> fallSound;

        public static Builder copy(SoundType soundType) {
            return new Builder()
                    .setVolume(soundType.getVolume())
                    .setPitch(soundType.getPitch())
                    .setBreakSound(soundType::getBreakSound)
                    .setStepSound(soundType::getStepSound)
                    .setPlaceSound(soundType::getPlaceSound)
                    .setHitSound(soundType::getHitSound)
                    .setFallSound(soundType::getFallSound);
        }

        public ForgeSoundType build() {
            return new ForgeSoundType(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
        }

        public Builder setVolume(float volume) {
            this.volume = volume;
            return this;
        }

        public Builder setPitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        public Builder setBreakSound(Supplier<SoundEvent> breakSound) {
            this.breakSound = breakSound;
            return this;
        }

        public Builder setStepSound(Supplier<SoundEvent> stepSound) {
            this.stepSound = stepSound;
            return this;
        }

        public Builder setPlaceSound(Supplier<SoundEvent> placeSound) {
            this.placeSound = placeSound;
            return this;
        }

        public Builder setHitSound(Supplier<SoundEvent> hitSound) {
            this.hitSound = hitSound;
            return this;
        }

        public Builder setFallSound(Supplier<SoundEvent> fallSound) {
            this.fallSound = fallSound;
            return this;
        }
    }
}

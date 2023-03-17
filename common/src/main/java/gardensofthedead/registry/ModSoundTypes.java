package gardensofthedead.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

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

        public SoundType build() {
            return new CustomSoundType(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
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

    private static class CustomSoundType extends SoundType {

        private final Supplier<SoundEvent> breakSound;
        private final Supplier<SoundEvent> stepSound;
        private final Supplier<SoundEvent> placeSound;
        private final Supplier<SoundEvent> hitSound;
        private final Supplier<SoundEvent> fallSound;

        public CustomSoundType(float volume, float pitch, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
            // noinspection ConstantConditions
            super(volume, pitch, null, null, null, null, null);
            this.breakSound = breakSound;
            this.stepSound = stepSound;
            this.placeSound = placeSound;
            this.hitSound = hitSound;
            this.fallSound = fallSound;
        }

        @Override
        public SoundEvent getBreakSound() {
            return breakSound.get();
        }

        @Override
        public SoundEvent getStepSound() {
            return stepSound.get();
        }

        @Override
        public SoundEvent getPlaceSound() {
            return placeSound.get();
        }

        @Override
        public SoundEvent getHitSound() {
            return hitSound.get();
        }

        @Override
        public SoundEvent getFallSound() {
            return fallSound.get();
        }
    }
}

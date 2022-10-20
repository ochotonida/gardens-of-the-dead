package gardensofthedead.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SuspendedParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class SoulblightSporeProvider extends SuspendedParticle.CrimsonSporeProvider {

    public SoulblightSporeProvider(SpriteSet sprite) {
        super(sprite);
    }

    public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double u, double v, double w) {
        SuspendedParticle particle = (SuspendedParticle) super.createParticle(particleType, level, x, y, z, u, v, w);

        RandomSource randomSource = level.random;
        double xSpeed = randomSource.nextGaussian() * 0.01;
        double ySpeed = Math.abs(randomSource.nextGaussian() * 0.03);
        double zSpeed = randomSource.nextGaussian() * 0.01;

        // noinspection ConstantConditions
        particle.setParticleSpeed(xSpeed, ySpeed, zSpeed);

        particle.setLifetime(particle.getLifetime() * 2);

        int color = 0xb28046;
        float r = (color >> 16 & 255) / 255F;
        float g = (color >> 8 & 255) / 255F;
        float b = (color & 255) / 255F;
        particle.setColor(r, g, b);
        return particle;
    }
}

package gardensofthedead.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class WhistlecaneSmokeParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    private WhistlecaneSmokeParticle(ClientLevel level, double x, double y, double z, double u, double v, double w, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        lifetime = random.nextInt(50) + 100;
        gravity = 3.0E-6F;
        xd = u;
        yd = v;
        zd = w;
        lifetime = (int) (8 / (level.random.nextFloat() * 0.8 + 0.2));
        lifetime = Math.max(lifetime, 1);
        setSpriteFromAge(sprites);
        setAlpha(0.9F);

        int color = 0xffb5ce;
        float r = (color >> 16 & 255) / 255F;
        float g = (color >> 8 & 255) / 255F;
        float b = (color & 255) / 255F;
        setColor(r, g, b);
    }

    @Override
    public void tick() {
        super.tick();
        setSpriteFromAge(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public record Provider(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {

        @Override
        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double u, double v, double w) {
            return new WhistlecaneSmokeParticle(level, x, y, z, u, v, w, sprites);
        }
    }
}

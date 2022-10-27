package gardensofthedead.client;

import gardensofthedead.registry.ModBlocks;
import gardensofthedead.registry.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;

import java.util.HashMap;
import java.util.Map;

public class WhistleEventHandler {

    private static final Map<BlockPos, WhistleEffect> WHISTLE_EFFECTS = new HashMap<>();

    private static ResourceKey<Level> dimension;

    public static void onClientTick(TickEvent.ClientTickEvent event) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null || Minecraft.getInstance().isPaused() || event.phase != TickEvent.Phase.START) {
            return;
        }

        if (level.dimension() != dimension) {
            dimension = level.dimension();
            WHISTLE_EFFECTS.clear();
        } else {
            WHISTLE_EFFECTS.entrySet().removeIf(
                    (entry) -> entry.getValue().whistlingTimeRemaining < -WhistleEffect.SMOKE_TIME
                            || !level.getBlockState(entry.getKey()).is(ModBlocks.WHISTLECANE.get())
            );
            WHISTLE_EFFECTS.forEach((pos, effect) -> effect.tick(level, pos));
        }
    }

    public static void add(BlockPos pos) {
        WHISTLE_EFFECTS.put(pos, new WhistleEffect());
    }

    private static class WhistleEffect {

        private static final int WHISTLING_TIME = 50;
        private static final int SMOKE_TIME = 30;

        private int whistlingTimeRemaining = WHISTLING_TIME;

        private void tick(Level level, BlockPos pos) {
            if (whistlingTimeRemaining-- >= 0) {
                int particleCount = 2;
                for (int i = 0; i < particleCount; i++) {
                    spawnParticle(level, pos, 0.2, 0.05);
                }
            }
            if (whistlingTimeRemaining % 5 == 0) {
                spawnParticle(level, pos, 0.1, 0);
            }
        }

        private static void spawnParticle(Level level, BlockPos pos, double verticalSpeed, double horizontalSpeed) {
            RandomSource randomsource = level.getRandom();
            Vec3 offset = ModBlocks.WHISTLECANE.get().defaultBlockState().getOffset(level, pos);
            double width = 5 / 16D;
            double x = pos.getX() + offset.x + 0.5F + randomsource.nextDouble() * width - width / 2;
            double z = pos.getZ() + offset.z + 0.5F + randomsource.nextDouble() * width - width / 2;
            double y = pos.getY() + offset.y + 1;
            double xSpeed = randomsource.nextDouble() * horizontalSpeed - horizontalSpeed / 2;
            double zSpeed = randomsource.nextDouble() * horizontalSpeed - horizontalSpeed / 2;
            level.addAlwaysVisibleParticle(ModParticleTypes.WHISTLECANE_SMOKE.get(), true, x, y, z, xSpeed, verticalSpeed, zSpeed);
        }
    }
}

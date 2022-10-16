package gardensofthedead.common.block.entity;

import gardensofthedead.common.init.ModBlockEntityTypes;
import gardensofthedead.common.init.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class WhistlecaneBlockEntity extends BlockEntity {

    private int whistlingTimeRemaining = - AMBIENT_SMOKE_TIME;

    private static final int WHISTLING_TIME = 50;
    private static final int AMBIENT_SMOKE_TIME = 30;

    public WhistlecaneBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.WHISTLECANE.get(), pos, state);
    }

    @Override
    public BlockEntityType<WhistlecaneBlockEntity> getType() {
        return ModBlockEntityTypes.WHISTLECANE.get();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        WhistlecaneBlockEntity whistleCane = (WhistlecaneBlockEntity) blockEntity;
        if (whistleCane.whistlingTimeRemaining >= -AMBIENT_SMOKE_TIME) {
            if (whistleCane.whistlingTimeRemaining-- >= 0) {
                int particleCount = 2;
                for (int i = 0; i < particleCount; i++) {
                    spawnParticle(level, pos, state, 0.2, 0.05);
                }
            }
            if (whistleCane.whistlingTimeRemaining % 5 == 0) {
                spawnParticle(level, pos, state, 0.1, 0);
            }
        }
    }

    public static void spawnParticle(Level level, BlockPos pos, BlockState state, double verticalSpeed, double horizontalSpeed) {
        RandomSource randomsource = level.getRandom();
        Vec3 offset = state.getOffset(level, pos);
        double width = 5 / 16D;
        double x = pos.getX() + offset.x + 0.5F + randomsource.nextDouble() * width - width / 2;
        double z = pos.getZ() + offset.z + 0.5F + randomsource.nextDouble() * width - width / 2;
        double y = pos.getY() + offset.y + 1;
        double xSpeed = randomsource.nextDouble() * horizontalSpeed - horizontalSpeed / 2;
        double zSpeed = randomsource.nextDouble() * horizontalSpeed - horizontalSpeed / 2;
        level.addAlwaysVisibleParticle(ModParticleTypes.WHISTLECANE_SMOKE.get(), true, x, y, z, xSpeed, verticalSpeed, zSpeed);
    }

    public void sendWhistlePacket() {
        if (getLevel() != null && !getLevel().isClientSide()) {
            ServerChunkCache chunkCache = ((ServerChunkCache) getLevel().getChunkSource());
            chunkCache.chunkMap
                    .getPlayers(new ChunkPos(getBlockPos()), false)
                    .forEach(player -> player.connection.send(
                            ClientboundBlockEntityDataPacket.create(this, WhistlecaneBlockEntity::createWhistlePacket))
                    );
        }
    }

    private static CompoundTag createWhistlePacket(BlockEntity blockEntity) {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("ShouldWhistle", true);
        return tag;
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet) {
        if (packet.getTag() != null && packet.getTag().getBoolean("ShouldWhistle")) {
            whistlingTimeRemaining = WHISTLING_TIME;
        } else {
            super.onDataPacket(connection, packet);
        }
    }
}

package gardensofthedead.network;

import dev.architectury.networking.NetworkChannel;
import gardensofthedead.GardensOfTheDead;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public class NetworkHandler {

    public static final NetworkChannel CHANNEL = NetworkChannel.create(GardensOfTheDead.id("networking_channel"));

    public static void register() {
        CHANNEL.register(WhistleEffectPacket.class, WhistleEffectPacket::encode, WhistleEffectPacket::new, WhistleEffectPacket::apply);
    }

    public static <T> void sendToTrackingPlayers(ServerLevel level, BlockPos pos, T message) {
        List<ServerPlayer> players = level.getChunkSource().chunkMap.getPlayers(level.getChunkAt(pos).getPos(), false);
        NetworkHandler.CHANNEL.sendToPlayers(players, message);
    }
}

package gardensofthedead.forge.common.network;

import gardensofthedead.GardensOfTheDead;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            GardensOfTheDead.id("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.registerMessage(0, WhistleEffectPacket.class, WhistleEffectPacket::encode, WhistleEffectPacket::new, WhistleEffectPacket::handle);
    }
}

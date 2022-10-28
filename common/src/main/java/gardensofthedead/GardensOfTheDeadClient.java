package gardensofthedead;

import dev.architectury.event.events.client.ClientTickEvent;
import gardensofthedead.client.WhistleEventHandler;

public class GardensOfTheDeadClient {

    public static void init() {
        ClientTickEvent.CLIENT_LEVEL_PRE.register(WhistleEventHandler::onClientTick);
    }
}

package rings_of_saturn.github.io.reactive_skins.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReactiveSkinsClient implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Reactive Skins");
    public static final String MOD_ID = "reactive_skins";
    public static final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
    }
}

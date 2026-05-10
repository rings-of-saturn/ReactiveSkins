package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.util.Identifier;

import java.util.HashMap;

import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.MOD_ID;
import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.client;

public class OverlayUtil {
    public static final HashMap<String, Identifier> overlayIDs = new HashMap<>();

    public static void initializeOverlays(){
        overlayIDs.put("bedrock",
                Identifier.of(
                        MOD_ID, "skin_overlays/bedrock.png"
                )
        );
    }

}

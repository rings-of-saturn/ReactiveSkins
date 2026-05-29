package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.HashMap;

import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.MOD_ID;
import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.client;
import static rings_of_saturn.github.io.reactive_skins.util.ImageUtil.mergeImage;

public class OverlayUtil {
    public static final HashMap<String, Identifier> overlayIDs = new HashMap<>();

    public static void initializeOverlays(){
        overlayIDs.put("bedrock",
                Identifier.of(
                        MOD_ID, "skin_overlays/bedrock.png"
                )
        );
        overlayIDs.put("snow_wide",
                Identifier.of(
                        MOD_ID, "skin_overlays/snow_wide.png"
                )
        );
        overlayIDs.put("snow_slim",
                Identifier.of(
                        MOD_ID, "skin_overlays/snow_slim.png"
                )
        );
        overlayIDs.put("mud",
                Identifier.of(
                        MOD_ID, "skin_overlays/mud.png"
                )
        );
    }

    public static NativeImage mergeOverlayWithImage(NativeImage baseImage, String overlay) throws IOException {
        if(client.getResourceManager().getResource(
                overlayIDs.get(overlay)).isPresent()) {
            return mergeImage(
                    baseImage,
                    NativeImage.read(client.getResourceManager().getResource(
                            overlayIDs.get(overlay)
                    ).get().getInputStream())
            );
        }
        return baseImage;
    }

    public static NativeImage mergeOverlayWithImage(NativeImage baseImage, String overlayBase, boolean slim) throws IOException {
        String overlay;
        if(slim){
            overlay = overlayBase+"_slim";
        } else {
            overlay = overlayBase+"_wide";
        }
        if(client.getResourceManager().getResource(
                overlayIDs.get(overlay)).isPresent()) {
            return mergeImage(
                    baseImage,
                    NativeImage.read(client.getResourceManager().getResource(
                            overlayIDs.get(overlay)
                    ).get().getInputStream())
            );
        }
        return baseImage;
    }

}

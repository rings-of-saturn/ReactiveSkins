package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.NativeImage;

import java.io.IOException;

import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.*;
import static rings_of_saturn.github.io.reactive_skins.util.OverlayUtil.overlayIDs;

public class ImageUtil {
    public static NativeImage modifySkin(NativeImage image, AbstractClientPlayerEntity player, boolean slim) throws IOException {
        if(image != null) {
            if(player.getBlockPos().getY() < -50){
                if(client.getResourceManager().getResource(
                        overlayIDs.get("bedrock")).isPresent()) {
                    return mergeImage(
                            image,
                            NativeImage.read(client.getResourceManager().getResource(
                                    overlayIDs.get("bedrock")
                            ).get().getInputStream())
                    );
                }
            }
        }
        return image;
    }

    public static NativeImage mergeImage(NativeImage bottom, NativeImage top){
        NativeImage returnImage = bottom;
        for (int i = 0; i < bottom.getWidth(); i++) {
            for (int j = 0; j < bottom.getHeight(); j++) {
                int topColor = top.getColor(i,j);
                returnImage.blend(i,j,topColor);
            }
        }
        return returnImage;
    }
}

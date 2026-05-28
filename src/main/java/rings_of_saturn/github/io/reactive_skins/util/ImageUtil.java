package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.SkinTextures;

import java.io.IOException;

import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.*;
import static rings_of_saturn.github.io.reactive_skins.util.SkinTexturesUtil.refreshToSkinTextures;

public class ImageUtil {
    public static NativeImage modifySkin(NativeImage image, AbstractClientPlayerEntity player, boolean slim, SkinTextures skinTextures) throws IOException {
        if(image != null) {
            //return OverlayUtil.mergeOverlayWithImage(image, "snow", slim);
        }
        return null;
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

    public static NativeImageBackedTexture loadAndSetImage(NativeImageBackedTexture texture, NativeImage newImage){
        if (texture.image != null) {
            texture.load(client.getResourceManager());
            texture.image = newImage;
            texture.upload();
        }
        return texture;
    }
}

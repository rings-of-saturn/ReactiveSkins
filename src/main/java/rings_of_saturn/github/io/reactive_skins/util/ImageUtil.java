package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.SkinTextures;

import java.io.IOException;

import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.*;

public class ImageUtil {
    public static NativeImage modifySkin(NativeImage image, AbstractClientPlayerEntity player, boolean slim, SkinTextures skinTextures) throws IOException {
        if(image != null) {
            NativeImage returnImage = image;
            if(
                    DataUtil.getPlayerData(player).snowTimer >= 5
            ) {
                returnImage = OverlayUtil.mergeOverlayWithImage(image, "snow", slim);
            }
            if (
                    DataUtil.getPlayerData(player).snowTimer == 1
            ) {
                returnImage = SkinTexturesUtil.refreshToSkinTextures(skinTextures, player);
            }

            if(
                    DataUtil.getPlayerData(player).bedrockDust
            ) {
                returnImage = OverlayUtil.mergeOverlayWithImage(image, "bedrock");
            }

            if(
                    DataUtil.getPlayerData(player).mudTimer >= 5
            ) {
                returnImage = OverlayUtil.mergeOverlayWithImage(image, "mud");
            }
            if (
                    DataUtil.getPlayerData(player).mudTimer == 1
            ) {
                returnImage = SkinTexturesUtil.refreshToSkinTextures(skinTextures, player);
            }

            return returnImage;
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

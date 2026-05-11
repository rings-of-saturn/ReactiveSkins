package rings_of_saturn.github.io.reactive_skins.util;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.*;

public class SkinTexturesUtil {
    public static final HashMap<String, Identifier> nameToID = new HashMap<>();

    public static final HashMap<String, NativeImageBackedTexture> textureToImage = new HashMap<>();

    public static void saveImageFromSkinTextures(SkinTextures skinTextures, AbstractClientPlayerEntity player) throws IOException {
        if (skinTextures.textureUrl() != null && nameToID.get(stylePlayerName(player.getName())) == null) {
            URL skinUrl = URI.create(skinTextures.textureUrl()).toURL();
            BufferedImage bufferedSkinImage = ImageIO.read(skinUrl);
            if(bufferedSkinImage != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(bufferedSkinImage, "png", stream);
                NativeImage skinImage = NativeImage.read(stream.toByteArray());
                NativeImageBackedTexture nativeImageTexture = new NativeImageBackedTexture(skinImage);
                nativeImageTexture.upload();
                textureToImage.put(stylePlayerName(player.getName()), nativeImageTexture);
                nameToID.put(stylePlayerName(player.getName()), client.getTextureManager().registerDynamicTexture(MOD_ID, nativeImageTexture));
            }
        }
    }

    public static NativeImage refreshToSkinTextures(SkinTextures skinTextures, AbstractClientPlayerEntity player) throws IOException {
        if (skinTextures.textureUrl() != null && nameToID.get(stylePlayerName(player.getName())) != null) {
            URL skinUrl = URI.create(skinTextures.textureUrl()).toURL();
            BufferedImage bufferedSkinImage = ImageIO.read(skinUrl);
            if(bufferedSkinImage != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(bufferedSkinImage, "png", stream);
                NativeImage skinImage = NativeImage.read(stream.toByteArray());
                NativeImageBackedTexture texture = textureToImage.get(stylePlayerName(player.getName()));
                texture.image = skinImage;
            }
        }
        return textureToImage.get(stylePlayerName(player.getName())).getImage();
    }

    public static String stylePlayerName(Text name){
        return name.getLiteralString().toLowerCase();
    }

    public static Identifier IdentifierFromName(Text name) {
        return nameToID.get(stylePlayerName(name));
    }

    public static void updateImage(AbstractClientPlayerEntity player, SkinTextures skinTextures) throws IOException {
        NativeImageBackedTexture texture = textureToImage.get(stylePlayerName(player.getName()));
        if(texture != null && texture.getImage() != null){
            updateTexture(texture, ImageUtil.modifySkin(texture.getImage(), player, player.getSkinTextures().model().name().toLowerCase().contains("slim"), skinTextures));
        }
    }

    public static void updateTexture(NativeImageBackedTexture texture, NativeImage newImage){
        if(texture != null){
            ImageUtil.loadAndSetImage(texture, newImage);
        }
    }
}

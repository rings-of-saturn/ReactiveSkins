package rings_of_saturn.github.io.reactive_skins.util;

import net.fabricmc.loader.api.FabricLoader;
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
import java.nio.file.Path;
import java.util.HashMap;

import static rings_of_saturn.github.io.reactive_skins.client.ReactiveSkinsClient.*;

public class SkinTexturesUtil {
    public static final Path skinPath = Path.of(FabricLoader.getInstance().getConfigDir().toString() + "/" + MOD_ID + "/skins");

    public static final HashMap<String, Identifier> nameToID = new HashMap<>();

    public static final HashMap<String, NativeImageBackedTexture> nameToImage = new HashMap<>();

    public static Path saveImageFromSkinTextures(SkinTextures skinTextures, AbstractClientPlayerEntity player) throws IOException {
        if (skinTextures.textureUrl() != null && nameToID.get(stylePlayerName(player.getName())) == null) {
            URL skinUrl = URI.create(skinTextures.textureUrl()).toURL();
            BufferedImage bufferedSkinImage = ImageIO.read(skinUrl);
            if(bufferedSkinImage != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ImageIO.write(bufferedSkinImage, "png", stream);
                NativeImage skinImage = NativeImage.read(stream.toByteArray());
                NativeImageBackedTexture nativeImageTexture = new NativeImageBackedTexture(skinImage);
                nativeImageTexture.upload();
                nameToImage.put(stylePlayerName(player.getName()), nativeImageTexture);
                nameToID.put(stylePlayerName(player.getName()), client.getTextureManager().registerDynamicTexture(MOD_ID, nativeImageTexture));
            }
            return skinPath;
        }
        return null;
    }

    public static String stylePlayerName(Text name){
        return name.getLiteralString().toLowerCase();
    }

    public static Identifier IdentifierFromName(Text name) {
        return nameToID.get(stylePlayerName(name));
    }

    public static void updateNativeImage(AbstractClientPlayerEntity player) {
        NativeImageBackedTexture texture = nameToImage.get(stylePlayerName(player.getName()));
        if(texture != null && texture.getImage() != null){
            texture.load(client.getResourceManager());
            updateTexture(texture, ImageUtil.modifySkin(texture.getImage(), player),stylePlayerName(player.getName()));
        }
    }

    public static void updateTexture(NativeImageBackedTexture texture, NativeImage newImage, String playerName){
        if(texture != null){
            texture.load(client.getResourceManager());
            texture.image = newImage;
            texture.upload();
            texture.load(client.getResourceManager());
            nameToImage.get(playerName);
        }
    }
}

package rings_of_saturn.github.io.reactive_skins.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rings_of_saturn.github.io.reactive_skins.util.SkinTexturesUtil;

import java.io.IOException;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRendererMixin {
    @Inject(method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    public void getTexture(AbstractClientPlayerEntity player, CallbackInfoReturnable<Identifier> cir) throws IOException {
        SkinTextures skinTextures = player.getSkinTextures();
        
        SkinTexturesUtil.saveImageFromSkinTextures(skinTextures, player);

        SkinTexturesUtil.updateImage(player, skinTextures);

        if(SkinTexturesUtil.IdentifierFromName(player.getName()) != null) {
            cir.setReturnValue(SkinTexturesUtil.IdentifierFromName(player.getName()));
        }
    }
}

package net.dbva.wrath.mixin;

import net.dbva.wrath.module.render.NoHurtCam;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "bobViewWhenHurt(Lnet/minecraft/client/util/math/MatrixStack;F)V", at = @At("HEAD"), cancellable = true)
    private void bobViewWhenHurt(MatrixStack matrices, float f, CallbackInfo ci) {
        if (NoHurtCam.HurtCam) ci.cancel();
    }
}

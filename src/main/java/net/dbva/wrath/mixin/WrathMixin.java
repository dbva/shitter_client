package net.dbva.wrath.mixin;

import net.dbva.wrath.Wrath;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(MinecraftClient.class)
public class WrathMixin {

	@Inject(method = "tick", at = @	At("HEAD"), cancellable = true) public void onTick(CallbackInfo ci) { Wrath.onTick(); }
}


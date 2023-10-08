package com.teampotato.chromaworldborderreforged.mixin;

import com.teampotato.chromaworldborderreforged.ChromaWorldBorderReforged;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.world.border.BorderStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.awt.*;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {
    @Shadow private int ticks;

    @Redirect(method = "renderWorldBounds", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/border/BorderStatus;getColor()I"))
    private int worldBorderRGB(BorderStatus instance) {
        if (ChromaWorldBorderReforged.rgbBorderEnabled.get()) {
            float hue = ((float) this.ticks * (ChromaWorldBorderReforged.borderColorChangeSpeed.get().floatValue() / 10.0F)) % 360.0F;
            return Color.getHSBColor(hue / 360, 1.0F, 1.0F).getRGB();
        }
        return instance.getColor();
    }
}

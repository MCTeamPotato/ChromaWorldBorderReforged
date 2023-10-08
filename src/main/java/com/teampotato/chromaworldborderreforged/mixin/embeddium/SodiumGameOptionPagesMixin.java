package com.teampotato.chromaworldborderreforged.mixin.embeddium;

import com.teampotato.chromaworldborderreforged.ChromaWorldBorderReforged;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.options.*;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SodiumGameOptionPagesMixin {
    @Shadow @Final private static SodiumOptionsStorage sodiumOpts;

    @Inject(method = "quality", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2, shift = At.Shift.BEFORE, remap = false), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void inject(CallbackInfoReturnable<OptionPage> cir, @NotNull List<OptionGroup> groups) {
        groups.add(OptionGroup.createBuilder()
                        .add(OptionImpl.createBuilder(Integer.TYPE, sodiumOpts)
                                .setName(new TranslationTextComponent("options.chromaWorldBorder.speed"))
                                .setTooltip(new TranslationTextComponent("options.chromaWorldBorder.speed.tooltip"))
                                .setControl(option -> new SliderControl(option, 0, 40, 1, ControlValueFormatter.number()))
                                .setBinding((sodiumGameOptions, integer) -> ChromaWorldBorderReforged.borderColorChangeSpeed.set(integer), sodiumGameOptions -> ChromaWorldBorderReforged.borderColorChangeSpeed.get())
                                .setImpact(OptionImpact.LOW)
                                .setFlags(new OptionFlag[]{OptionFlag.REQUIRES_RENDERER_RELOAD})
                                .build())
                        .add(OptionImpl.createBuilder(Boolean.TYPE, sodiumOpts)
                                .setName(new TranslationTextComponent("options.chromaWorldBorder.enable"))
                                .setTooltip(new TranslationTextComponent("options.chromaWorldBorder.enable.tooltip"))
                                .setControl(TickBoxControl::new)
                                .setBinding((sodiumGameOptions, aBoolean) -> ChromaWorldBorderReforged.rgbBorderEnabled.set(aBoolean), sodiumGameOptions -> ChromaWorldBorderReforged.rgbBorderEnabled.get())
                                .setImpact(OptionImpact.LOW)
                                .setFlags(new OptionFlag[]{OptionFlag.REQUIRES_RENDERER_RELOAD})
                                .build())

                .build()
        );
    }
}

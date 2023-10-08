package com.teampotato.chromaworldborderreforged;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("chromaworldborderreforged")
public class ChromaWorldBorderReforged {
    public static final ForgeConfigSpec config;
    public static final ForgeConfigSpec.BooleanValue rgbBorderEnabled;
    public static final ForgeConfigSpec.IntValue borderColorChangeSpeed;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("ChromaWorldBorderReforged");
        rgbBorderEnabled = builder.define("rgbBorderEnabled", true);
        borderColorChangeSpeed = builder.defineInRange("borderColorChangeSpeed", 5, 0, 10);
        builder.pop();
        config = builder.build();
    }

    public ChromaWorldBorderReforged() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, config);
    }
}

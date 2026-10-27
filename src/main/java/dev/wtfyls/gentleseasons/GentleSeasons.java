package dev.wtfyls.gentleseasons;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(GentleSeasons.MOD_ID)
public final class GentleSeasons {
    public static final String MOD_ID = "gentleseasons";

    public GentleSeasons() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GentleSeasonsConfig.SPEC);
        MinecraftForge.EVENT_BUS.register(new GentleSeasonsEvents());
    }
}

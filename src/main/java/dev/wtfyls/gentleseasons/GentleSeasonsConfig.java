package dev.wtfyls.gentleseasons;

import net.minecraftforge.common.ForgeConfigSpec;

public final class GentleSeasonsConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.DoubleValue DAY_LENGTH_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue RAIN_GROWTH_BONUS_CHANCE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("time");
        DAY_LENGTH_MULTIPLIER = builder
                .comment("How much longer the daylight cycle should be. 1.0 keeps vanilla timing; 2.0 makes the cycle progress at half speed.")
                .defineInRange("dayLengthMultiplier", 2.0, 1.0, 8.0);
        builder.pop();

        builder.push("crops");
        RAIN_GROWTH_BONUS_CHANCE = builder
                .comment("Chance to grant one additional age stage after a crop naturally grows while exposed to rain. 0.5 means 50%.")
                .defineInRange("rainGrowthBonusChance", 0.5, 0.0, 1.0);
        builder.pop();

        SPEC = builder.build();
    }

    private GentleSeasonsConfig() {}
}

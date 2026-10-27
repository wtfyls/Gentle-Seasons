package dev.wtfyls.gentleseasons;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.WeakHashMap;

public final class GentleSeasonsEvents {
    /*
     * Vanilla advances day time by one tick per server tick. Accumulating the
     * fractional correction and occasionally rewinding one tick slows that
     * clock without continuously resetting the time or disabling sleeping.
     */
    private final Map<ServerLevel, Double> dayTimeCorrections = new WeakHashMap<>();

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        MinecraftServer server = event.getServer();
        double multiplier = GentleSeasonsConfig.DAY_LENGTH_MULTIPLIER.get();
        if (multiplier <= 1.0) {
            dayTimeCorrections.clear();
            return;
        }

        double correctionPerTick = 1.0 - (1.0 / multiplier);
        for (ServerLevel level : server.getAllLevels()) {
            if (!level.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
                continue;
            }

            double accumulated = dayTimeCorrections.getOrDefault(level, 0.0) + correctionPerTick;
            long wholeTicks = (long) accumulated;
            if (wholeTicks > 0) {
                level.setDayTime(level.getDayTime() - wholeTicks);
                accumulated -= wholeTicks;
            }
            dayTimeCorrections.put(level, accumulated);
        }
    }

    @SubscribeEvent
    public void onCropGrow(BlockEvent.CropGrowEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        double bonusChance = GentleSeasonsConfig.RAIN_GROWTH_BONUS_CHANCE.get();
        if (bonusChance <= 0.0) {
            return;
        }

        BlockPos pos = event.getPos();
        BlockPos above = pos.above();
        if (!level.isRainingAt(above) || !level.canSeeSky(above) || level.getRandom().nextDouble() >= bonusChance) {
            return;
        }

        // Read the current state after vanilla/modded growth has completed.
        BlockState state = level.getBlockState(pos);
        IntegerProperty ageProperty = findAgeProperty(state);
        if (ageProperty == null) {
            return;
        }

        int age = state.getValue(ageProperty);
        int maximumAge = ageProperty.getPossibleValues().stream().mapToInt(Integer::intValue).max().orElse(age);
        if (age < maximumAge) {
            level.setBlock(pos, state.setValue(ageProperty, age + 1), 3);
        }
    }

    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        dayTimeCorrections.clear();
    }

    private static IntegerProperty findAgeProperty(BlockState state) {
        for (Property<?> property : state.getProperties()) {
            if (property instanceof IntegerProperty integerProperty && "age".equals(property.getName())) {
                return integerProperty;
            }
        }
        return null;
    }
}

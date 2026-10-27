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
    private static final long FULL_DAY_TICKS = 24_000L;
    private static final long DAYLIGHT_END_TICK = 12_000L;

    /*
     * Time is integer-valued in vanilla. Accumulating fractional corrections
     * lets the daylight and night portions run at different speeds without
     * repeatedly setting an absolute time. The vanilla daylight-cycle gamerule
     * remains authoritative.
     */
    private final Map<ServerLevel, CycleCorrection> cycleCorrections = new WeakHashMap<>();

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        MinecraftServer server = event.getServer();
        double dayMultiplier = GentleSeasonsConfig.DAY_LENGTH_MULTIPLIER.get();
        double nightMultiplier = GentleSeasonsConfig.NIGHT_LENGTH_MULTIPLIER.get();

        for (ServerLevel level : server.getAllLevels()) {
            if (!level.getGameRules().getBoolean(GameRules.RULE_DAYLIGHT)) {
                cycleCorrections.remove(level);
                continue;
            }

            long dayTime = level.getDayTime();
            long timeOfDay = Math.floorMod(dayTime, FULL_DAY_TICKS);
            boolean daylight = timeOfDay < DAYLIGHT_END_TICK;

            CycleCorrection previous = cycleCorrections.get(level);
            double accumulated = previous != null && previous.daylight() == daylight
                    ? previous.accumulated()
                    : 0.0;

            double correctionPerTick;
            if (daylight) {
                correctionPerTick = 1.0 - (1.0 / dayMultiplier);
            } else {
                correctionPerTick = (1.0 / nightMultiplier) - 1.0;
            }

            // No time correction is necessary at vanilla speed.
            if (correctionPerTick <= 0.0) {
                cycleCorrections.put(level, new CycleCorrection(daylight, 0.0));
                continue;
            }

            accumulated += correctionPerTick;
            long wholeTicks = (long) Math.floor(accumulated + 1.0e-9);
            if (wholeTicks > 0) {
                level.setDayTime(dayTime + (daylight ? -wholeTicks : wholeTicks));
                accumulated -= wholeTicks;
            }
            cycleCorrections.put(level, new CycleCorrection(daylight, accumulated));
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
        cycleCorrections.clear();
    }

    private static IntegerProperty findAgeProperty(BlockState state) {
        for (Property<?> property : state.getProperties()) {
            if (property instanceof IntegerProperty integerProperty && "age".equals(property.getName())) {
                return integerProperty;
            }
        }
        return null;
    }

    private record CycleCorrection(boolean daylight, double accumulated) {}
}

# 🌿 Gentle Seasons

[![Build](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-62A744)](https://www.minecraft.net/)
[![Forge](https://img.shields.io/badge/Forge-47.x-orange)](https://files.minecraftforge.net/)
[![Java](https://img.shields.io/badge/Java-17-blue)](https://adoptium.net/)
[![CurseForge](https://img.shields.io/badge/CurseForge-Gentle%20Seasons-orange?logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons)
[![License: ARR](https://img.shields.io/badge/License-All%20Rights%20Reserved-lightgrey)](LICENSE)

**Gentle Seasons** is a cozy gameplay and quality-of-life mod for Minecraft 1.20.1 Forge. It slows down daylight, shortens the night, and gives exposed crops a chance to grow an extra stage when natural growth happens during rain.

The goal is to make survival feel a little gentler without replacing the familiar Minecraft day/night and farming loop.

> **Development status:** Forge 1.20.1 currently builds in CI. Gameplay still needs in-game testing. Gentle Weather and Campfire Comfort are planned features and are not included in the current implementation.

## ✨ Features

| Feature | What it does | Status |
|---|---|---|
| 🌧️ **Rain-assisted crop growth** | When an age-based crop successfully grows while exposed to natural rain and open sky, it has a configurable chance to gain one additional growth stage. | Implemented; runtime testing pending |
| ☀️ **Longer daytime** | Daylight advances more slowly, making the default daytime last about 20 minutes at normal 20 TPS. | Implemented; runtime testing pending |
| 🌙 **Shorter nights** | Nighttime advances faster, making the default night last about 5 minutes at normal 20 TPS. | Implemented; sleep behavior needs testing |
| 🌦️ **Gentle Weather** | Longer clear periods and shorter rainstorms, with rain durations targeted at 5–10 minutes. | Planned |
| 🔥 **Campfire Comfort** | A cozy campfire-area effect intended to remove Mining Fatigue and Weakness and provide Regeneration I. | Planned |

The current rain-growth implementation looks for an integer block-state property named `age` and handles Forge's post-growth event. Other crops or mods need in-game compatibility checks before support can be guaranteed.

## ⚙️ Configuration

Gentle Seasons uses a Forge common config, normally located at `config/gentleseasons-common.toml`.

The current default settings are:

```toml
[time]
	# Daylight duration multiplier. 1.0 keeps vanilla timing; 2.0 makes daylight progress at half speed.
	# Range: 1.0 ~ 8.0
	dayLengthMultiplier = 2.0
	# Night duration multiplier. 1.0 keeps vanilla timing; 0.5 makes night progress at twice the vanilla speed.
	# Range: 0.1 ~ 1.0
	nightLengthMultiplier = 0.5

[crops]
	# Chance to grant one additional age stage after a crop naturally grows while exposed to rain. 0.5 means 50%.
	# Range: 0.0 ~ 1.0
	rainGrowthBonusChance = 0.5
```

| Option | Default | Meaning |
|---|---:|---|
| `dayLengthMultiplier` | `2.0` | Makes daylight progress at half the vanilla rate. |
| `nightLengthMultiplier` | `0.5` | Makes nighttime progress at twice the vanilla rate. |
| `rainGrowthBonusChance` | `0.5` | Gives a 50% chance of an extra age stage when eligible crop growth occurs during exposed rain. |

Use `1.0` for vanilla speed for either time period. The mod respects the `doDaylightCycle` gamerule. Sleep, commands that change time, weather transitions, and modded crops still need runtime testing.

## 🧪 Testing checklist

Test in a disposable world before using the mod in a main save.

- Toggle the daylight cycle with `/gamerule doDaylightCycle true` and `/gamerule doDaylightCycle false`.
- Compare the timing after `/time set day` and `/time set night`.
- Test sleeping in a bed in single-player and on a dedicated server.
- Use `/weather rain` and make sure crops are exposed to the sky.
- Test vanilla crops first, then modded age-based crops such as those added by farming mods.
- Check that mature crops do not grow past their maximum age and that the server log remains clean.

There is no custom `/lunchtime debug growth` command registered in the current source.

## 🛠️ Build from source

Requirements: **Java 17** and **Gradle 8.8**.

```bash
gradle build
```

The compiled mod jar is produced in `build/libs/`. GitHub Actions runs the target-matrix validation and Forge 1.20.1 build on pushes and pull requests, then uploads the built jar as a workflow artifact.

- [Latest GitHub Actions runs](https://github.com/wtfyls/Gentle-Seasons/actions)
- [Minecraft / loader support matrix](SUPPORT_MATRIX.md)
- [Contributing guide](CONTRIBUTING.md)

## 📦 Downloads

No public GitHub release has been published yet. Until a release is available, the CI artifact is a development build, not an officially runtime-tested release.

- [CurseForge project page](https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons)
- [GitHub Releases](https://github.com/wtfyls/Gentle-Seasons/releases)
- [GitHub Actions artifacts](https://github.com/wtfyls/Gentle-Seasons/actions)

## 👤 Credits

Created by **Fierolam** (`@wtfyls`).

## 📄 License

**All Rights Reserved (ARR).** The project is proprietary; use, modification, and redistribution require prior written permission. See [LICENSE](LICENSE).

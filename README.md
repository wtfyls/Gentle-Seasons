# Gentle Seasons

[![Build](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml)
![Minecraft 1.20.1](https://img.shields.io/badge/Minecraft-1.20.1-62A744)
![Forge 47.x](https://img.shields.io/badge/Forge-47.x-orange)
![Java 17](https://img.shields.io/badge/Java-17-blue)
[![CurseForge](https://img.shields.io/badge/CurseForge-page-orange?logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons)

Gentle Seasons is a cozy Minecraft 1.20.1 Forge mod with longer days, shorter nights, and rain-assisted crop growth.

## Features

- Longer days and shorter nights.
- Crops exposed to rain can get an extra growth stage.
- Day, night, and rain-growth settings are configurable.

Weather changes and campfire effects are planned, but aren't in the current build.

## Install

1. Make sure you are using **Minecraft 1.20.1**, **Forge 47.x**, and **Java 17**.
2. Download the jar for **Forge 1.20.1** from the [GitHub Releases](https://github.com/wtfyls/Gentle-Seasons/releases) page or a successful [Actions build](https://github.com/wtfyls/Gentle-Seasons/actions).
3. Put the jar in your Minecraft instance's `mods` folder.
4. For multiplayer, install the same compatible mod build on the dedicated server and the clients joining it.
5. Launch the game once. Forge creates the config file on startup.

There isn't a published GitHub release yet, so Actions artifacts are development builds and may need in-game testing.

## Configure

Close Minecraft or stop the server before editing the file. Open:

```text
config/gentleseasons-common.toml
```

The file is generated after the first launch with Gentle Seasons installed. These are the current defaults:

```toml
[time]
dayLengthMultiplier = 2.0
nightLengthMultiplier = 0.5

[crops]
rainGrowthBonusChance = 0.5
```

| Setting | Default | What it changes |
|---|---:|---|
| `dayLengthMultiplier` | `2.0` | How long daytime lasts. `2.0` makes the day twice as long; `1.0` is vanilla speed. Allowed: `1.0–8.0`. |
| `nightLengthMultiplier` | `0.5` | How long the night lasts. `0.5` makes night pass twice as fast; `1.0` is vanilla speed. Allowed: `0.1–1.0`. |
| `rainGrowthBonusChance` | `0.5` | Chance of one extra growth stage after an eligible crop grows while rain reaches it and it has open sky. `0.5` = 50%, `1.0` = 100%, `0.0` = off. Allowed: `0.0–1.0`. |

Save the file and restart Minecraft or the server to apply changes. Gentle Seasons respects the `doDaylightCycle` gamerule. Crop support depends on the block exposing an integer `age` property, so test modded crops in-game.

## Versions

✅ = CI build passes; in-game testing may still be needed.  
❌ = not built yet.

| Minecraft | Forge | Fabric | NeoForge |
|---|:---:|:---:|:---:|
| 1.20.1 | ✅* | ❌ | ❌ |
| 1.21.1 | ❌ | ❌ | ❌ |
| 1.21.11 | ❌ | ❌ | ❌ |
| 26.1 | ❌ | ❌ | ❌ |
| 26.1.1 | ❌ | ❌ | ❌ |
| 26.2 | ❌ | ❌ | ❌ |
| 26.3 | ❌ | ❌ | ❌ |

* Forge 1.20.1 is the only implemented target. A successful build doesn't replace in-game testing. See [SUPPORT_MATRIX.md](SUPPORT_MATRIX.md).

## Build and upload versions

To build the current Forge 1.20.1 project, install Java 17 and Gradle 8.8, then run:

```bash
gradle build
```

The jar is created in `build/libs/`.

When you make a build for a version and loader, put the jar in its matching repository folder:

```text
targets/<minecraft-version>/<loader>/
```

Examples:

- Forge 1.20.1: `targets/1.20.1/forge/gentle-seasons+mc1.20.1-forge.jar`
- Fabric 1.21.1: `targets/1.21.1/fabric/gentle-seasons+mc1.21.1-fabric.jar`
- NeoForge 26.1: `targets/26.1/neoforge/gentle-seasons+mc26.1-neoforge.jar`

Only upload a jar after building it for that exact Minecraft version and loader. In GitHub, open the target folder and choose **Add file → Upload files**. Update that folder's `TARGET.json` to reflect its real status. Don't label a build runtime-tested until you've launched it in-game.

For player downloads, attach the tested jars to a [GitHub Release](https://github.com/wtfyls/Gentle-Seasons/releases/new) with a version tag such as `v1.0.0). Uploading a jar to `targets/` keeps it in the repository; it does not publish a GitHub Release automatically.

[Builds](https://github.com/wtfyls/Gentle-Seasons/actions) · [Releases](https://github.com/wtfyls/Gentle-Seasons/releases) · [CurseForge](https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons)

## License

All Rights Reserved. See [LICENSE](LICENSE).

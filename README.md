# Gentle Seasons

[![Build](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml)
![Minecraft 1.20.1](https://img.shields.io/badge/Minecraft-1.20.1-62A744)
![Forge 47.x](https://img.shields.io/badge/Forge-47.x-orange)
![Java 17](https://img.shields.io/badge/Java-17-blue)
[![CurseForge](https://img.shields.io/badge/CurseForge-page-orange?logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons)

Gentle Seasons is a small cozy mod for Minecraft 1.20.1 Forge.

## Features

- Longer days and shorter nights.
- Crops exposed to rain can get an extra growth stage.
- Day, night, and rain-growth settings are configurable.

Weather changes and campfire effects are planned, but aren't implemented yet.

## Versions

✅ = CI build passes. In-game testing may still be needed.  
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

## Uploading mod jars

Put each version of the mod in its matching version-and-loader folder:

```text
targets/<minecraft-version>/<loader>/
```

Examples:

- Forge 1.20.1: `targets/1.20.1/forge/gentle-seasons+mc1.20.1-forge.jar`
- Fabric 1.21.1: `targets/1.21.1/fabric/gentle-seasons+mc1.21.1-fabric.jar`
- NeoForge 26.1: `targets/26.1/neoforge/gentle-seasons+mc26.1-neoforge.jar`

Upload the `.jar` to the matching folder in GitHub, then update that folder's `TARGET.json` when its status changes. Don't mark a target as runtime-tested until you've tested it in-game.

For public downloads, also attach the jar to a [GitHub Release](https://github.com/wtfyls/Gentle-Seasons/releases/new). Use a versioned tag, such as `v1.0.0`, and attach only builds for the versions listed in that release.

## Build

Requirements: Java 17 and Gradle 8.8.

```bash
gradle build
```

The jar is generated in `build/libs/`. GitHub Actions builds Forge 1.20.1 and uploads the jar as a workflow artifact.

[Builds](https://github.com/wtfyls/Gentle-Seasons/actions) · [Releases](https://github.com/wtfyls/Gentle-Seasons/releases) · [CurseForge](https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons)

## Config

The config is created at `config/gentleseasons-common.toml`.

| Setting | Default |
|---|---:|
| `dayLengthMultiplier` | `2.0` |
| `nightLengthMultiplier` | `0.5` |
| `rainGrowthBonusChance` | `0.5` |

## License

All Rights Reserved. See [LICENSE](LICENSE).

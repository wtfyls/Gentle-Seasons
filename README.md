# Gentle Seasons

[![Build](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/wtfyls/Gentle-Seasons/actions/workflows/build.yml)

**Gentle Seasons** is a small Minecraft mod focused on a calmer day/night rhythm and rain that helps crops grow.

## Features

- **Longer days** — slows the daylight cycle without disabling the daylight-cycle gamerule or replacing normal sleep behavior.
- **Rain-assisted crop growth** — exposed crops can receive an extra growth stage when a normal crop-growth event succeeds during rain.
- **Configurable behavior** — adjust the day-length multiplier and rain growth bonus chance in the common config.
- **Version-specific tracking** — each Minecraft version and loader target is tracked separately so planned ports are not confused with verified builds.

The current implementation targets **Minecraft 1.20.1 with Forge**. CI compilation confirms that the project builds; in-game behavior still needs testing before this target can be considered runtime-verified. No public release is available yet.

## Minecraft versions and loaders

These are the only versions currently in the project roadmap. A ✅ means the Gentle Seasons target has passed CI compilation; ❌ means that target has not yet been built and verified. These marks describe this mod, not general availability of the loader itself.

| Minecraft version | Forge | Fabric | NeoForge |
|:--|:--:|:--:|:--:|
| 1.20.1 | ✅* | ❌ | ❌ |
| 1.21.1 | ❌ | ❌ | ❌ |
| 1.21.11 | ❌ | ❌ | ❌ |
| 26.1 | ❌ | ❌ | ❌ |
| 26.1.1 | ❌ | ❌ | ❌ |
| 26.2 | ❌ | ❌ | ❌ |
| 26.3 | ❌ | ❌ | ❌ |

*The Forge 1.20.1 target is the only implemented build. CI compilation does not confirm gameplay behavior. For details, see [SUPPORT_MATRIX.md](SUPPORT_MATRIX.md). Planned targets are not downloadable releases; each needs a loader/version-specific implementation, a successful build, and runtime testing.

## Build the current Forge 1.20.1 project

Requirements: **Java 17** and **Gradle 8.8**.

```bash
gradle build
```

The compiled mod jar is produced in `build/libs/`. You can also check the latest [GitHub Actions build](https://github.com/wtfyls/Gentle-Seasons/actions).

## Project layout

- `src/main/` — current Forge 1.20.1 implementation
- `targets/<version>/<loader>/` — per-version and per-loader target status
- `support/minecraft-versions.json` — canonical list of Minecraft versions in scope
- `SUPPORT_MATRIX.md` — version/loader support table
- `platforms/README.md` — notes for loader-specific ports

## License

**All Rights Reserved (ARR).** No rights to use, modify, or redistribute this project are granted without prior written permission. See [LICENSE](LICENSE).

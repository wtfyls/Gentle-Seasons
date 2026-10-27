# Gentle Seasons — version support

✅ = CI build verified for this mod target. This confirms compilation only, not in-game testing.
❌ = not built and verified yet.

| Minecraft version | Forge | Fabric | NeoForge |
|---|:---:|:---:|:---:|
| 1.20.1 | ✅* | ❌ | ❌ |
| 1.21.1 | ❌ | ❌ | ❌ |
| 1.21.11 | ❌ | ❌ | ❌ |
| 26.1 | ❌ | ❌ | ❌ |
| 26.1.1 | ❌ | ❌ | ❌ |
| 26.2 | ❌ | ❌ | ❌ |
| 26.3 | ❌ | ❌ | ❌ |

* The Forge 1.20.1 target is the only implemented build. In-game behavior still needs testing.

## Notes

- Only the **Forge 1.20.1** project is implemented in the root Gradle build.
- CI compilation does not confirm the time-cycle behavior, rain growth behavior, multiplayer sleep behavior, or compatibility with third-party crops.
- All other listed loader/version combinations are planned targets, not supported downloads.
- Target folders are under `targets/<version>/<loader>/`; each `TARGET.json` records its status.
- Java targets: 1.20.1 uses Java 17; 1.21.1 and 1.21.11 use Java 21; 26.1, 26.1.1, 26.2, and 26.3 use Java 25.

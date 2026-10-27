# Contributing to Gentle Seasons

## Adding a Minecraft version or loader

1. Pick the exact Minecraft Java Edition release and loader in `targets/<minecraft-version>/<loader>/`.
2. Check the loader's official download/metadata for that exact Minecraft release. Do not guess dependency coordinates or reuse versions from another Minecraft release.
3. Add the loader-specific Gradle build and pin compatible plugin/dependency versions.
4. Port the time-cycle and rain-growth behavior without assuming that APIs or event order are identical between loaders.
5. Run the local Gradle build and add the target to CI.
6. Test day length, sunrise/sunset, sleeping, `/time` commands, the daylight-cycle gamerule, rain exposure, and both vanilla and modded crops in-game.
7. Update the target's `TARGET.json` only after CI passes. Mark a release available only after runtime testing and publishing the exact artifact.

## Target statuses

- `scaffold-only`: listed but not yet implemented/verified.
- `build-verified`: CI compilation passes; in-game testing may still be pending.
- `runtime-tested`: tested in the target game and loader combination.
- `released`: a public artifact exists for the exact target.

Do not mark a build as `released` without a published jar. Do not describe scaffold-only targets as supported downloads.

## License

This repository uses All Rights Reserved (ARR). Contributions do not grant users a general right to redistribute the project. Coordinate permission and attribution with the copyright holder before contributing.

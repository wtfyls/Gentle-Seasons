# GitHub setup

## Repository About

Set these in the **About** panel on the repo page.

**Description**
```text
🌿 A cozy Minecraft 1.20.1 Forge mod with longer days and rain-assisted crop growth.
```

**Website**
```text
https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons
```

**Topics**
```text
minecraft, minecraft-mod, forge, minecraft-1-20-1, cozy, farming, quality-of-life, lunchtime
```

## Upload mod versions

Put each jar in `targets/<minecraft-version>/<loader>/`. For example:

- `targets/1.20.1/forge/gentle-seasons+mc1.20.1-forge.jar`
- `targets/1.21.1/fabric/gentle-seasons+mc1.21.1-fabric.jar`

Update that folder's `TARGET.json` when the build status changes. For public downloads, attach the jar to a [GitHub Release](https://github.com/wtfyls/Gentle-Seasons/releases/new).

## Before publishing

- Test the jar in the matching Minecraft and loader version.
- Confirm the GitHub Actions build passes.
- Make sure the release notes describe the features actually in that build.
- The repository uses All Rights Reserved, not MIT.

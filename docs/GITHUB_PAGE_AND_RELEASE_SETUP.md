# GitHub page and release setup

This checklist records the intended repository metadata and release process for Gentle Seasons.

## Repository About section

Open [Gentle-Seasons repository settings](https://github.com/wtfyls/Gentle-Seasons) and edit the **About** panel.

**Description**
```text
🌿 A cozy gameplay and QoL mod for Minecraft 1.20.1 Forge — rain crop growth bonus, longer daytime, gentler weather & campfire comfort.
```

**Website**
```text
https://www.curseforge.com/minecraft/mc-mods/labs-gentle-seasons
```

**Topics**
```text
minecraft, minecraft-mod, forge, minecraft-1-20-1, cozy, farming, quality-of-life, farmers-delight, lunchtime
```

Enable the **Releases** and **Packages** sections on the repository home page if those options are available.

> Note: the current source implements the day/night timing and rain-growth bonus. Gentle Weather and Campfire Comfort are planned, so the About description should be updated if those features are not implemented before the public release.

## Logo

The current GitHub repository does not track a `logo.png` file. Add the intended logo to the repository root before embedding it in the README; otherwise the image will show as a broken link. The local path from a developer's machine is not available to GitHub visitors.

## Before publishing v1.0.0

- [ ] Add and track `logo.png` if the project logo is ready.
- [ ] Implement and runtime-test the full advertised feature set, including gentle weather and campfire effects, or remove those features from the release description.
- [ ] Test bed sleeping, gamerules, manual time changes, vanilla crops, and supported modded crops.
- [ ] Confirm the current GitHub Actions build is green.
- [ ] Download the jar produced by the successful build and verify its filename. The Gradle project currently names its output using the `gentle-seasons` archive name; do not assume the old local filename.
- [ ] Publish a versioned release only after the binary and notes describe the actual tested behavior.
- [ ] Confirm the repository license. The tracked `LICENSE` currently says All Rights Reserved, so do not use an MIT badge unless the license is intentionally changed.

## Release page

Use [Releases → Draft a new release](https://github.com/wtfyls/Gentle-Seasons/releases/new) when the release is ready. Suggested tag and title:

- Tag: `v1.0.0`
- Title: `Gentle Seasons v1.0.0 (Minecraft 1.20.1 Forge)`

A draft is not published until you explicitly publish it.

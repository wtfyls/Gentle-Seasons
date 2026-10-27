# Loader-specific ports

The implemented baseline is Forge for Minecraft 1.20.1, built from the root Gradle project.

Fabric, NeoForge, and other Minecraft versions are roadmap targets only. Each port must use the matching loader API and Java target, compile in CI, and be tested in-game before its status is promoted. Do not copy Forge event handling into another loader and mark it supported without validating the equivalent lifecycle hooks and behavior.

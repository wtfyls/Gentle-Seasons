#!/usr/bin/env python3
"""Validate the Gentle Seasons Minecraft/loader target catalog."""
from __future__ import annotations

import json
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
VERSION_FILE = ROOT / "support" / "minecraft-versions.json"
LOADERS = ("forge", "fabric", "neoforge")
BUILD_STATUSES = {"build-verified", "scaffold-only", "runtime-tested", "released"}
EXPECTED_VERSIONS = {
    "1.20.1": 17,
    "1.21.1": 21,
    "1.21.11": 21,
    "26.1": 25,
    "26.1.1": 25,
    "26.2": 25,
    "26.3": 25,
}


def fail(message: str) -> None:
    print(f"ERROR: {message}", file=sys.stderr)
    raise SystemExit(1)


def main() -> None:
    try:
        catalog = json.loads(VERSION_FILE.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError) as exc:
        fail(f"cannot read version catalog: {exc}")

    versions = catalog.get("versions")
    if not isinstance(versions, list) or not versions:
        fail("support/minecraft-versions.json must contain a non-empty versions list")

    seen_versions: set[str] = set()
    for entry in versions:
        version = entry.get("version")
        java = entry.get("java")
        if not isinstance(version, str) or not version:
            fail(f"invalid version entry: {entry!r}")
        if version in seen_versions:
            fail(f"duplicate Minecraft version: {version}")
        seen_versions.add(version)
        if java not in (17, 21, 25):
            fail(f"unexpected Java target for {version}: {java}")

    actual_versions = {entry["version"]: entry["java"] for entry in versions}
    if actual_versions != EXPECTED_VERSIONS:
        fail(f"version catalog mismatch: expected {EXPECTED_VERSIONS}, found {actual_versions}")

    expected = {(entry["version"], loader) for entry in versions for loader in LOADERS}
    found: set[tuple[str, str]] = set()
    for version in versions:
        for loader in LOADERS:
            path = ROOT / "targets" / version["version"] / loader / "TARGET.json"
            if not path.is_file():
                fail(f"missing target file: {path.relative_to(ROOT)}")
            try:
                target = json.loads(path.read_text(encoding="utf-8"))
            except (OSError, json.JSONDecodeError) as exc:
                fail(f"invalid JSON in {path.relative_to(ROOT)}: {exc}")
            identity = (target.get("minecraft_version"), target.get("loader"))
            if identity != (version["version"], loader):
                fail(f"target identity mismatch in {path.relative_to(ROOT)}: {identity}")
            if target.get("java_version") != version["java"]:
                fail(f"Java target mismatch in {path.relative_to(ROOT)}")
            if target.get("status") not in BUILD_STATUSES:
                fail(f"unknown status in {path.relative_to(ROOT)}: {target.get('status')}")
            if not target.get("artifact_name", "").endswith(f"-{loader}.jar"):
                fail(f"artifact naming mismatch in {path.relative_to(ROOT)}")
            found.add(identity)

    if found != expected:
        fail(f"target coverage mismatch: missing={sorted(expected - found)}, extra={sorted(found - expected)}")

    verified = []
    for version in versions:
        for loader in LOADERS:
            path = ROOT / "targets" / version["version"] / loader / "TARGET.json"
            target = json.loads(path.read_text(encoding="utf-8"))
            if target["status"] == "build-verified":
                verified.append((version["version"], loader))
    if verified != [("1.20.1", "forge")]:
        fail(f"expected only Forge 1.20.1 to be build-verified at this stage; found {verified}")

    print(f"Validated {len(versions)} Minecraft versions and {len(found)} target combinations.")
    print("Baseline target: Forge 1.20.1. All other combinations are marked scaffold-only.")


if __name__ == "__main__":
    main()

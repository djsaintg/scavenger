# 🏹 Scavenger

**Unofficial Fabric mod** for Minecraft 26.3: arrows that hit a block drop as items you can pick up.

A skeleton misses you twelve times, and you walk away with twelve arrows. Missed shots at a creeper land as arrows on the ground, not a stuck texture you can't get back.

`Java 25` `Fabric Loader 0.20.1` `Mixin` `Minecraft 26.3`

**v1.0.0 — Stable**

---

## ✨ Features
- 🎯 **Arrows drop on landing**: when an arrow hits a block, it pops out as a normal dropped item
- 💀 **Mob arrows too**: arrows from skeletons, strays, bogged, pillagers and every other archer can be picked up
- 🧪 **Keeps what it was**: tipped arrows keep their potion, and spectral arrows stay spectral
- 🔘 **Still hits things**: buttons, target blocks, TNT and other blocks still react to the hit first
- ♾️ **No free arrows**: Infinity, multishot extras and creative-mode arrows drop nothing, just like vanilla
- 🔱 **Tridents untouched**: loyalty and riptide work exactly as before
- 🪶 **Server side only**: no textures, no new items, no Fabric API

## 📦 Install
1. Install Fabric Loader 0.20.1+ for Minecraft 26.3
2. Drop `scavenger-1.0.0.jar` into `mods/`

Works in singleplayer and on Fabric servers. Players joining a server don't need it.

## 🛠️ Build
```bash
JAVA_HOME=/path/to/jdk-25 ./gradlew build
# -> build/libs/scavenger-1.0.0.jar
```

## 🗂️ How it works
- `AbstractArrowMixin`: when vanilla finishes an arrow's block hit, the arrow is swapped for an item entity of the same arrow and then removed

## 📄 License
MIT

*Code with rhythm. Ship with grace.*

# TowerDefense

A 2D Tower Defense strategy game built for the desktop platform using the [libGDX](https://libgdx.com/) framework.

## 👥 Team Members
* **Muhamedzhan** - Project Lead, Programmer, Designer.

## 🚀 How to Run
This project uses [Gradle](https://gradle.org/) to manage dependencies. To play the game, you do not need to install complex developer tools.

**Option 1: Run via Gradle (Recommended)**
Open your terminal or command prompt in the project root folder and run the following command:
```bash
./gradlew lwjgl3:run

```

**Option 2: Run via Executable JAR**
If you have built the application's runnable jar (`./gradlew lwjgl3:jar`), you can find it at `lwjgl3/build/libs` and run it directly:

```bash
java -jar towerdefense.jar

```

## 🎮 Controls

The game is controlled using the mouse and keyboard:

| Key / Action | Function |
| --- | --- |
| **[Left Click]** | Place a tower on the grid or select an existing tower |
| **[SPACE]** | Start the next wave of enemies |
| **[1]** | Select Arrow Tower (Fast) |
| **[2]** | Select Cannon Tower (Splash Damage) |
| **[3]** | Select Ice Tower (Slows enemies) |
| **[4]** | Upgrade selected tower: 2x Damage |
| **[5]** | Upgrade selected tower: Increase Range |
| **[6]** | Upgrade selected tower: Increase Attack Speed |
| **[P] / [ESC]** | Pause / Resume the game |

## ✨ Architecture & Design Patterns

This project heavily utilizes Object-Oriented Programming, SOLID principles, and various Software Design Patterns to ensure a clean, maintainable, and scalable codebase:

* **Singleton Pattern:** Used in `GameEngine.getInstance()` to globally manage core game states like Gold, Lives, and Waves without passing the instance around.
* **Factory Method Pattern:** Used to encapsulate the creation logic of game entities. Examples include `GoblinFactory`, `OrcFactory`, `BossFactory` for enemies, and `ArrowTowerFactory`, `CannonTowerFactory`, `IceTowerFactory` for towers.
* **Strategy Pattern:** Used to define interchangeable attack behaviors for towers (`ArrowAttackStrategy`, `CannonAttackStrategy`, `IceAttackStrategy`), allowing towers to dynamically calculate damage and visual effects.
* **Decorator Pattern:** Used for the tower upgrade system (`DoubleDamageDecorator`, `RangeBoostDecorator`, `SpeedBoostDecorator`). It allows adding new behaviors to placed towers dynamically at runtime without altering their original classes.
* **Observer Pattern:** Implemented via `WaveManager` and `GameEventListener` to decouple game events. It notifies the system when a wave starts, when an enemy is killed, or when the player loses a life.
* **State Pattern:** Used to manage the lifecycle of enemies (e.g., `DeadState`), allowing enemies to safely change their behavior based on their current status without complex if-else logic.
* **Object Pool Pattern:** Implemented via `Pool<Shot>` for projectiles. Instead of continuously creating and destroying shot objects (which causes memory leaks and garbage collection lag), shots are recycled and reused.
*
**Screen State Machine:** Inherently used via libGDX `Game` and `Screen` interfaces to cleanly manage application state transitions (e.g., MainMenu, GameScreen, GameOverScreen).



## ⚠️ Known Issues

* No known issues at this time.



## 🛠 Gradle Tasks & Flags

Useful Gradle tasks and flags included in this project:

* `--continue`: when using this flag, errors will not stop the tasks from running.
* `build`: builds sources and archives of every project.
* `clean`: removes `build` folders, which store compiled classes and built archives.
* `lwjgl3:jar`: builds application's runnable jar.
* `lwjgl3:run`: starts the application.


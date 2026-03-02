# Asteroid Clone

A 2D top-down space shooter built in Java using Swing. Navigate a player-controlled cube through a field of moving asteroids, shooting them to earn points while avoiding collisions.

## Features

* **Fluid Movement**: Space-like physics with acceleration and friction for the player.
* **Dynamic Targeting**: The player cube rotates to follow the mouse cursor and fires bullets toward the click location.
* **Collision System**: Implements collision detection for Bullet-to-Asteroid and Player-to-Asteroid interactions.
* **Difficulty Scaling**: Each time a wave of asteroids is cleared, the next wave spawns with an additional asteroid.
* **Screen Wrapping**: Both the player and asteroids wrap around to the opposite side of the screen when they leave the boundaries.
* **Immunity Mechanic**: After losing a life, the player gains a brief period of immunity (approx. 3 seconds) to prevent instant successive deaths.
* **HUD**: Real-time score and lives tracking displayed on the screen.

## How to Play

1.  **Launch**: Run the `Main` class to open the game window.
2.  **Move**: Use **W, A, S, D** keys to move the player cube.
3.  **Aim & Shoot**: Move the mouse to rotate and **Click** to fire bullets.
4.  **Goal**: Destroy asteroids to increase your score (+100 per asteroid).
5.  **Lives**: You start with 3 lives. The game ends when all lives are lost.

## Controls Summary

| Input | Action |
| :--- | :--- |
| **W** | Accelerate Up |
| **S** | Accelerate Down |
| **A** | Accelerate Left |
| **D** | Accelerate Right |
| **Mouse Move** | Aim Player |
| **Mouse Click** | Fire Bullet |

## Project Structure

* `Frame.java`: Sets up the main window and initializes the game panel.
* `Panel.java`: Contains the main game loop, update logic, and rendering coordination.
* `Cube.java`: Handles player input, physics-based movement, and rotation.
* `Asteroid.java` & `AsteroidManager.java`: Manage individual asteroid behavior and global asteroid wave spawning.
* `Bullet.java` & `BulletSpawner.java`: Handle projectile trajectory and firing mechanics.
* `LifeCounter.java`: Manages the UI elements and game-over state.
* `KeyManager.java` & `MouseHandler.java`: Process user input events.

## Requirements
* Java Runtime Environment (JRE) 8 or higher.
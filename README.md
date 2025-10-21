# Pacman Game in Java Swing

## My firts University project

## Overview
This is a simple Pacman-style game developed in **Java** using **Swing**. The player controls Pacman to collect candies and bonuses while avoiding enemies. The game includes a scoring system, lives, and a high-score leaderboard.  

This project was created as a learning exercise and demonstrates basic game development concepts in Java, including:

- GUI with **Swing**
- Keyboard input handling
- Collision detection
- Object-oriented design
- Simple threading for game loop
- Persistent high scores using file serialization

---

## Features

- **Player (Pacman)**:
  - Move using arrow keys.
  - Collect candies to increase score.
  - Collect bonuses (speed boost, extra life, freeze enemies, double XP).

- **Enemies**:
  - Move around the map.
  - Collision with Pacman reduces life.

- **Maze**:
  - Walls block movement.
  - Open paths for navigation.

- **Score & Lives**:
  - Score counter updates as Pacman collects candies.
  - Life counter decreases on collision with enemies.

- **High Scores**:
  - Player can enter their name after game over.
  - Scores are saved to a file and displayed in a leaderboard.

- **Menu**:
  - Start game, view high scores, or exit.

---

## How to Run

1. Clone or download the project.
2. Make sure Java 8 or later is installed.
3. Run the `MenuScreen` class as the main entry point:

```bash
javac *.java
java MenuScreen

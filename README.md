# ❌⭕ Unbeatable Neon Tic-Tac-Toe

> "Even I’m surprised you didn’t see that coming. And I’m literally code." - *The AI*

Welcome to the **Unbeatable Tic-Tac-Toe**, a console-based Java game that refuses to lose. Powered by the Minimax algorithm, this AI calculates every possible move to ensure it either wins or forces a draw.

But it doesn't just beat you—it does it in **style** with neon colors and optional **roasts** to hurt your feelings when you inevitably lose.

---

## ✨ Features

- **🤖 Unbeatable AI**: Uses the **Minimax Algorithm** to calculate optimal moves. It is mathematically impossible to beat this AI.
- **🎨 Neon Graphics**: Beautiful ANSI escape code visuals with **Neon Pink (X)**, **Neon Cyan (O)**, and **Neon Green** grids.
- **🔥 Roast Mode**: Enable "Roast Mode" to get absolutely destroyed by insults from `roasts.txt` upon defeat.
- **🎮 Flexible Gameplay**: Choose to go first or let the AI make the opening move.
- **🔢 Simple Controls**: Intuitive 1-9 number pad mapping for making moves.

---

## 📸 Preview

The game features a vibrant terminal interface:

```text
 --- --- ---
| X |   | O |
 --- --- ---
| O | X |   |
 --- --- ---
|   |   | X |
 --- --- ---
```

*(Colors may vary based on your terminal support for ANSI codes)*

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have Java installed.
  ```bash
  java -version
  ```

### Installation & Run

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/tic-tac-toe-java.git
   cd tic-tac-toe-java
   ```
2. **Compile the Code**

   ```bash
   javac -d . tik_tak_toe_unbeatable.java
   ```
3. **Run the Game**
   Make sure `roasts.txt` is in the same directory as where you run the command.

   ```bash
   java ticTacToe.tik_tak_toe_unbeatable
   ```

---

## 🕹️ How to Play

1. **Visual Map**: The board positions correspond to the number pad:
   ```
    1 | 2 | 3 
   ---|---|---
    4 | 5 | 6 
   ---|---|---
    7 | 8 | 9 
   ```
2. **Make a Move**: When prompted, enter the number (1-9) for the spot you want to claim.
3. **Lose (Gracefully)**: Try your best to draw, because winning isn't an option.

---

## 📂 Project Structure

```
├── tik_tak_toe_unbeatable.java  # Main game logic and Minimax algorithm
├── roasts.txt                   # Collection of roasts for the loser
└── README.md                    # This file
```

## 🧠 The Algorithm

This game uses **Minimax**, a recursive algorithm used in decision theory and game theory.

1. **Lookahead**: It simulates all possible future moves.
2. **Scoring**: It assigns a score (+10 for AI win, -10 for Human win, 0 for Draw).
3. **Optimization**: It picks the move that maximizes the AI's score while assuming the human plays perfectly to minimize it.

## 🤝 Contributing

Feel free to fork this repository and add more roasts to `roasts.txt` or optimize the code!

---

*Made with ☕ and <3 in Java.*

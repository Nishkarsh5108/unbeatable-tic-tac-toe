// every time it makes a move it thinks of all possible outcomes and picks the one with the best probability of winning
package ticTacToe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class tik_tak_toe_unbeatable {
    public static boolean wantRoast = true;
    public static int userRow = 0;
    public static int userColumn = 0;
    public static boolean gameOver = false;

    public static void printRoast() {
        List<String> roasts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("roasts.txt")))
         {
            String line;
            while ((line = br.readLine()) != null) {
                roasts.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading roasts: " + e.getMessage());
            return;
        }

        if (roasts.size() < 3) {
            System.out.println("Not enough roasts in the file!");
            return;
        }

        // Shuffle and pick 3 random roasts
        Collections.shuffle(roasts);
        System.out.println(roasts.get(0));
        System.out.println(roasts.get(1));
        System.out.println(roasts.get(2));
    }

    public static int getRandomElement(int[] array) {
        if (array.length == 0) {
            System.out.println("Lmao, we're both trash.");
            System.exit(0);
        }
        Random random = new Random();
        int randomIndex = random.nextInt(array.length); // Get a random index
        return array[randomIndex]; // Return the element at the random index
    }

    public static void printGrid(char grid[][]) {
        // ANSI escape codes for neon colors
        String neonX = "\u001B[38;5;206mX\u001B[0m"; // Bright pink for X
        String neonO = "\u001B[38;5;51mO\u001B[0m"; // Neon cyan for O
        String neonGrid = "\u001B[38;5;46m---\u001B[0m"; // Neon green for grid borders

        System.out.println(" " + neonGrid + " " + neonGrid + " " + neonGrid);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Replace X and O with neon versions
                if (grid[i][j] == 'x') {
                    System.out.print("| " + neonX + " ");
                } else if (grid[i][j] == 'o') {
                    System.out.print("| " + neonO + " ");
                } else {
                    System.out.print("|   "); // Empty spaces remain white
                }
            }
            System.out.println("|"); // End of the row
            System.out.println(" " + neonGrid + " " + neonGrid + " " + neonGrid);
        }
    }

    public static char[][] takeInput(char grid[][]) {
        Scanner sc = new Scanner(System.in);
        boolean validInput = false; // To track if a valid position has been entered

        while (!validInput) { // Keep asking until a valid position is provided
            System.out.println("enter the position");
            int n = sc.nextInt();
            index(n);

            // Check if the position is already occupied
            if (grid[userRow][userColumn] == ' ') {
                grid[userRow][userColumn] = 'x';
                validInput = true; // Exit loop if the input is valid
            } else {
                System.out.println("please enter a valid position"); // Inform the user
            }
        }

        return grid; // returns the modified grid
    }

    public static void index(int n) { // to make the randomisation and taking input process i made this funtion so
                                      // that an integer value represents row and column
        n -= 1;
        userRow = n / 3;
        userColumn = n % 3;
    }

    public static int calculateN(int row, int column) {
        return (row * 3 + column + 1);
    }

    public static int[] emptyBlocks(char grid[][]) {
        int[] temp = new int[9]; // Store temp empty spaces
        int count = 0; // To track the number of empty spaces

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    temp[count] = calculateN(i, j);
                    count++;
                }
            }
        }

        // Create an array of exactly 'count' size to store the empty spaces
        int[] emptySpaces = new int[count];
        System.arraycopy(temp, 0, emptySpaces, 0, count);
        return emptySpaces;
    }

    public static int isComputerWinning(char[][] grid) {
        int row = -1, column = -1; // Variables to store the position of the empty element if found

        // Check rows and columns using nested loops
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (checkAndSetPosition(grid[i][0], grid[i][1], grid[i][2], 'o')) {
                row = i;
                column = findEmpty(grid[i][0], grid[i][1], grid[i][2], 0, 1, 2);
                break;
            }
            // Check columns
            if (checkAndSetPosition(grid[0][i], grid[1][i], grid[2][i], 'o')) {
                column = i;
                row = findEmpty(grid[0][i], grid[1][i], grid[2][i], 0, 1, 2);
                break;
            }
        }

        // Manual diagonal checks
        if (row == -1 && column == -1) {
            // Check the main diagonal
            if (checkAndSetPosition(grid[0][0], grid[1][1], grid[2][2], 'o')) {
                row = findEmpty(grid[0][0], grid[1][1], grid[2][2], 0, 1, 2);
                column = row; // Since row == column on the main diagonal
            }

            // Check the anti-diagonal
            if (checkAndSetPosition(grid[0][2], grid[1][1], grid[2][0], 'o')) {
                row = findEmpty(grid[0][2], grid[1][1], grid[2][0], 0, 1, 2);
                column = 2 - row; // Because row + column = 2 on the anti-diagonal
            }
        }

        if (row != -1 && column != -1) {
            return calculateN(row, column);
        }

        return -1; // No winning condition found
    }

    public static int isUserWinning(char[][] grid) {
        int row = -1, column = -1; // Variables to store the position of the empty element if found

        // Check rows and columns using nested loops
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (checkAndSetPosition(grid[i][0], grid[i][1], grid[i][2], 'x')) {
                row = i;
                column = findEmpty(grid[i][0], grid[i][1], grid[i][2], 0, 1, 2);
                break;
            }
            // Check columns
            if (checkAndSetPosition(grid[0][i], grid[1][i], grid[2][i], 'x')) {
                column = i;
                row = findEmpty(grid[0][i], grid[1][i], grid[2][i], 0, 1, 2);
                break;
            }
        }

        // Manual diagonal checks
        if (row == -1 && column == -1) {
            // Check the main diagonal
            if (checkAndSetPosition(grid[0][0], grid[1][1], grid[2][2], 'x')) {
                row = findEmpty(grid[0][0], grid[1][1], grid[2][2], 0, 1, 2);
                column = row; // Since row == column on the main diagonal
            }

            // Check the anti-diagonal
            if (checkAndSetPosition(grid[0][2], grid[1][1], grid[2][0], 'x')) {
                row = findEmpty(grid[0][2], grid[1][1], grid[2][0], 0, 1, 2);
                column = 2 - row; // Because row + column = 2 on the anti-diagonal
            }
        }

        if (row != -1 && column != -1) {
            return calculateN(row, column);
        }

        return -1; // No winning condition found
    }

    // Helper function to check if there are two 'o's or two 'x's and one empty spot
    // in a triplet
    public static boolean checkAndSetPosition(char a, char b, char c, char player) {
        return (a == player && b == player && c == ' ') ||
                (a == player && c == player && b == ' ') ||
                (b == player && c == player && a == ' ');
    }

    // Helper function to find the index of the empty element (' ') in a triplet
    public static int findEmpty(char a, char b, char c, int idxA, int idxB, int idxC) {
        if (a == ' ') {
            return idxA;
        } else if (b == ' ') {
            return idxB;
        } else {
            return idxC;
        }
    }

    public static boolean isGridFullCheck(char[][] grid) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public static int minimax(char[][] grid, int depth, boolean isMaximizing) {
        if (checkWinner(grid, 'o'))
            return 10 - depth; // If AI wins, return high score
        if (checkWinner(grid, 'x'))
            return depth - 10; // If user wins, return low score
        if (isGridFullCheck(grid))
            return 0; // If draw, return neutral score

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = 'o'; // Try AI move
                        int score = minimax(grid, depth + 1, false);
                        grid[i][j] = ' '; // Undo move
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = 'x'; // Try user move
                        int score = minimax(grid, depth + 1, true);
                        grid[i][j] = ' '; // Undo move
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public static int[] findBestMove(char[][] grid) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = { -1, -1 };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') { // Check if the spot is empty
                    grid[i][j] = 'o'; // Try the move
                    int score = minimax(grid, 0, false); // Get the score using MiniMax
                    grid[i][j] = ' '; // Undo the move

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove; // Return the best move found
    }

    public static char[][] computerMakesMove(char grid[][]) {
        
        int userWinningMove = isUserWinning(grid); // Check if user is about to win
        int computerWinningMove = isComputerWinning(grid); // Check if computer can win

        if (computerWinningMove != -1) {
            // Computer can win, so make the winning move
            index(computerWinningMove);
            grid[userRow][userColumn] = 'o';
        } else if (userWinningMove != -1) {
            // Block user's winning move
            index(userWinningMove);
            grid[userRow][userColumn] = 'o';
        } else {
            // Instead of random move, use MiniMax to find the best move!
            int[] bestMove = findBestMove(grid);
            grid[bestMove[0]][bestMove[1]] = 'o';

        }

        return grid;
    }

    public static void isGridFull(char grid[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    gameOver = false;
                    return; // If any empty space is found, exit early
                }
            }
        }
        gameOver = true; // Set gameOver to true if no empty spaces are found
        System.err.println("Lmao, we're both trash.");
        System.exit(0);
    }

    // Function to check if someone has won
    public static void didSomeoneWin(char[][] grid) {
        // Check for 'x' (User's win condition)
        if (checkWinner(grid, 'x')) {
            System.out.println("Bruh, you cheated. I swear.");
            gameOver = true;
            System.exit(0);
        }
        // Check for 'o' (Computer's win condition)
        else if (checkWinner(grid, 'o')) {
            if (wantRoast) printRoast();
            else System.out.println("You LOST and I WON!!");
            gameOver = true;
            System.exit(0);
        }
    }

    // Helper function to check if a player (either 'x' or 'o') has won
    public static boolean checkWinner(char[][] grid, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Check if the entire row is filled with the player's symbol
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) {
                return true;
            }
            // Check if the entire column is filled with the player's symbol
            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        // Main diagonal (top-left to bottom-right)
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        }

        // Anti-diagonal (top-right to bottom-left)
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
            return true;
        }

        return false; // No win condition found for the player
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Example usage
        char[][] sampleGrid = {
                { '1', '2', '3' },
                { '4', '5', '6' },
                { '7', '8', '9' }
        };

        char[][] grid = {
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' }
        };

        printGrid(grid);
        System.out.println("welcome HUMAN,\nLets Start now.....\n");
        System.out.println("now lets learn how the positioning works\nEnter the position such that");
        printGrid(sampleGrid);
        System.out.println("do you want to get roasted after loosing or not(answer yes or no)");
        String ans = sc.nextLine();
        if (ans.equals("yes")) wantRoast = true;
        else if (ans.equals("no")) wantRoast = false;
        else System.out.println("okay i will roast you bro");       

        System.out.println(
                "\nwho will be playing first\nEnter 1 if you wanna play first\nEnter 2 if I should play first");
        int playingFirst = sc.nextInt();

        if (playingFirst == 1) {
            while (gameOver != true) {
                didSomeoneWin(grid);
                isGridFull(grid);
                grid = takeInput(grid);// user makes a move
                computerMakesMove(grid); // the pc makes a move accordingly
                printGrid(grid);
            }
        } else {
            grid[1][1] = 'o';
            printGrid(grid);
            while (gameOver != true) {
                didSomeoneWin(grid);
                isGridFull(grid);
                grid = takeInput(grid);// user makes a move
                computerMakesMove(grid); // the pc makes a move accordingly
                printGrid(grid);
            }
        }

    }
}
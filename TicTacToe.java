import java.util.Scanner;

/**
 * File Name: AdvancedTicTacToe.java
 * Description:
 * Advanced command-line Tic Tac Toe game with:
 * - Input validation
 * - Retry attempts for invalid or occupied cells
 * - Responsive user interaction
 * - Clean and modular design
 */

public class AdvancedTicTacToe {

    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("      🎮 ADVANCED TIC TAC TOE 🎮");
        System.out.println("======================================");
        printInstructions();

        boolean playAgain;
        do {
            startNewGame();
            playAgain = askPlayAgain();
        } while (playAgain);

        System.out.println("👋 Thanks for playing! Goodbye.");
        scanner.close();
    }

    // ---------------- GAME FLOW ----------------

    private static void startNewGame() {
        initializeBoard();
        currentPlayer = 'X';
        int moveCount = 0;
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            takePlayerMove();
            moveCount++;

            if (checkWinner()) {
                printBoard();
                System.out.println("🏆 Player " + currentPlayer + " WINS!");
                gameOver = true;
            } else if (moveCount == SIZE * SIZE) {
                printBoard();
                System.out.println("🤝 Game Draw! No more moves.");
                gameOver = true;
            } else {
                switchPlayer();
            }
        }
    }

    // ---------------- INITIALIZATION ----------------

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printInstructions() {
        System.out.println("How to Play:");
        System.out.println("• The board is a 3x3 grid (0 to 2)");
        System.out.println("• Player X goes first");
        System.out.println("• Enter row and column numbers");
        System.out.println("• Invalid or occupied cells will retry");
        System.out.println();
    }

    // ---------------- BOARD DISPLAY ----------------

    private static void printBoard() {
        System.out.println("\n   0   1   2");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < SIZE - 1) System.out.print("|");
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("  ---+---+---");
        }
        System.out.println();
    }

    // ---------------- PLAYER MOVE ----------------

    private static void takePlayerMove() {
        int row, col;

        while (true) {
            try {
                System.out.print("👉 Player " + currentPlayer + ", enter ROW (0-2): ");
                row = scanner.nextInt();

                System.out.print("👉 Player " + currentPlayer + ", enter COLUMN (0-2): ");
                col = scanner.nextInt();

                if (!isWithinBounds(row, col)) {
                    System.out.println("❌ Out of bounds! Please enter values between 0 and 2.");
                    continue;
                }

                if (board[row][col] != ' ') {
                    System.out.println("⚠ Cell already occupied! Choose another cell.");
                    continue;
                }

                board[row][col] = currentPlayer;
                break;

            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter numbers only.");
                scanner.nextLine(); // clear buffer
            }
        }
    }

    private static boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    // ---------------- GAME LOGIC ----------------

    private static boolean checkWinner() {
        // Rows & Columns
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer)
                return true;

            if (board[0][i] == currentPlayer &&
                board[1][i] == currentPlayer &&
                board[2][i] == currentPlayer)
                return true;
        }

        // Diagonals
        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer)
            return true;

        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer)
            return true;

        return false;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // ---------------- REPLAY ----------------

    private static boolean askPlayAgain() {
        System.out.print("🔁 Do you want to play again? (y/n): ");
        char choice = scanner.next().toLowerCase().charAt(0);
        scanner.nextLine(); // clear buffer
        return choice == 'y';
    }
}

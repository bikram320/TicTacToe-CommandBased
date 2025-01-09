package TicTacToeGame;


import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {

        char[][] board = new char[3][3];
        initializeBoard(board);

        char player = 'X';
        boolean gameOver = false;
        int moves = 0; // To track the number of moves
        Scanner sc = new Scanner(System.in);

        try {
            while (!gameOver && moves < 9) {
                printBoard(board);
                System.out.println();
                System.out.println(player + ", enter your move :  ");
                System.out.print("Enter your row :");
                int row = sc.nextInt();
                System.out.print("Enter your column :");
                int col = sc.nextInt();
                System.out.println();

                // Validate input
                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid input. Please enter row and column between 0 and 2.");
                    continue;
                }

                // Check if cell is already occupied
                if (board[row][col] != ' ') {
                    System.out.println("Cell already occupied. Choose another cell.");
                    continue;
                }

                //if cell is not occupied
                board[row][col] = player;
                moves++;

                // Check for win
                if (haveWon(board, player)) {
                    printBoard(board);
                    System.out.println(player + " has won the game!");
                    gameOver = true;
                } else if (moves == 9) { // Check for draw
                    printBoard(board);
                    System.out.println("The game is a draw!");
                } else {
                    player = (player == 'X') ? 'O' : 'X';
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    // Initialize the board with empty spaces
    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Print the board in a user-friendly way
    public static void printBoard(char[][] board) {
        System.out.println("  0 1 2");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {

                System.out.print(board[i][j]);

                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("  -----");
            }
        }
    }

    // Check if the player has won
    public static boolean haveWon(char[][] board, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }
}

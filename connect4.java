import java.util.Scanner;

public class ConnectFour {
public static void main(String[] args) {
Scanner in = new Scanner(System.in);
 char[][] grid = createEmptyGrid();
    
    int turn = 1;
    char player = 'R';
    boolean winner = false;        
    
    while (!winner && turn <= 42) {
        int play;
        do {
            displayGrid(grid);
            System.out.print("Player " + player + ", choose a column: ");
            play = in.nextInt();
        } while (!validatePlay(play, grid));
        
        dropChecker(play, player, grid);
        winner = isWinner(player, grid);
        player = switchPlayer(player);
        turn++;
    }
    
    displayGrid(grid);
    
    if (winner) {
        String winningPlayer = (player == 'R') ? "Red" : "Black";
        System.out.println(winningPlayer + " won");
    } else {
        System.out.println("Tie game");
    }
}

public static char[][] createEmptyGrid() {
    char[][] grid = new char[6][7];
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = ' ';
        }
    }
    return grid;
}

public static void displayGrid(char[][] grid) {
    System.out.println(" 0 1 2 3 4 5 6");
    System.out.println("---------------");
    for (int row = 0; row < grid.length; row++) {
        System.out.print("|");
        for (int col = 0; col < grid[0].length; col++) {
            System.out.print(grid[row][col]);
            System.out.print("|");
        }
        System.out.println();
        System.out.println("---------------");
    }
    System.out.println(" 0 1 2 3 4 5 6");
    System.out.println();
}

public static boolean validatePlay(int column, char[][] grid) {
    if (column < 0 || column >= grid[0].length) {
        return false;
    }
    
    if (grid[0][column] != ' ') {
        return false;
    }
    
    return true;
}

public static void dropChecker(int column, char player, char[][] grid) {
    for (int row = grid.length - 1; row >= 0; row--) {
        if (grid[row][column] == ' ') {
            grid[row][column] = player;
            break;
        }
    }
}

public static boolean isWinner(char player, char[][] grid) {
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length - 3; col++) {
            if (grid[row][col] == player && 
                grid[row][col + 1] == player &&
                grid[row][col + 2] == player &&
                grid[row][col + 3] == player) {
                return true;
            }
        }
    }
    
    for (int row = 0; row < grid.length - 3; row++) {
        for (int col = 0; col < grid[0].length; col++) {
            if (grid[row][col] == player && 
                grid[row + 1][col] == player &&
                grid[row + 2][col] == player &&
                grid[row + 3][col] == player) {
                return true;
            }
        }
    }
    
    for (int row = 3; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length - 3; col++) {
            if (grid[row][col] == player && 
                grid[row - 1][col + 1] == player &&
                grid[row - 2][col + 2] == player &&
                grid[row - 3][col + 3] == player) {
                return true;
            }
        }
    }
    
    for (int row = 0; row < grid.length - 3; row++) {
        for (int col = 0; col < grid[0].length - 3; col++) {
            if (grid[row][col] == player && 
                grid[row + 1][col + 1] == player &&
                grid[row + 2][col + 2] == player &&
                grid[row + 3][col + 3] == player) {
                return true;
            }
        }
    }
    
    return false;
}

  public static char switchPlayer(char currentPlayer) {
    return (currentPlayer == 'R') ? 'B' : 'R';
  }
}

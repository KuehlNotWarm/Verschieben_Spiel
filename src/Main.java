import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int inputBoardSize;

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        executeGameLogic();
    }

    private static void executeGameLogic() {
        ArrayList<ArrayList<Integer>> gameBoard = initBoardRandom();
        printBoard(gameBoard);

        ArrayList<int[]> neighbors = findNeighborsOfZero(gameBoard);

        System.out.println("Select a neighbor to swap with 0:");
        for (int i = 0; i < neighbors.size(); i++) {
            int[] neighbor = neighbors.get(i);
            int neighborValue = gameBoard.get(neighbor[0]).get(neighbor[1]);
            System.out.println(i + ": Neighbor at Row " + neighbor[0] + ", Column " + neighbor[1] + " (Value: " + neighborValue + ")");
        }

        int choice = scan.nextInt();
        if (choice >= 0 && choice < neighbors.size()) {
            swapWithZero(gameBoard, neighbors.get(choice));
            printBoard(gameBoard);  // Print the updated board
        } else {
            System.out.println("Invalid choice");
        }
    }

    /**
     * Prints the current state of the game board.
     */
    private static void printBoard(ArrayList<ArrayList<Integer>> gameBoard) {
        for (ArrayList<Integer> row : gameBoard) {
            for (int cell : row) {
                System.out.print(cell + "\t");  // Use tab for better spacing
            }
            System.out.println();  // New line after each row
        }
        System.out.println();  // Extra line for better separation
    }


    /**
     * Erzeugt auf dem Spielbrett eine zufällige Stellung. Kann zum Erzeugen der Startstellung genutzt werden.
     */
    private static ArrayList<ArrayList<Integer>> initBoardRandom() {
        System.out.println("enter board size N: (NxN)");
        inputBoardSize = scan.nextInt();
        int counter = 0;
        ArrayList<ArrayList<Integer>> gameBoard = new ArrayList<>(inputBoardSize);

        for (int i = 0; i < inputBoardSize; i++) {
            gameBoard.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < inputBoardSize; i++) {
            for (int j = 0; j < inputBoardSize; j++) {
                gameBoard.get(i).add(counter);
                counter++;
            }
        }

        ArrayList<Integer> flattenedList = new ArrayList<>();
        for (ArrayList<Integer> row : gameBoard) {
            flattenedList.addAll(row);
        }

        // Shuffling the flattened list
        Collections.shuffle(flattenedList);

        // Reorganizing the shuffled elements back into the 2D ArrayList
        int index = 0;
        for (int i = 0; i < inputBoardSize; i++) {
            for (int j = 0; j < inputBoardSize; j++) {
                gameBoard.get(i).set(j, flattenedList.get(index));
                index++;
            }

        }


        Collections.shuffle(gameBoard);
        for (int i = 0; i < inputBoardSize; i++) {
            Collections.shuffle(gameBoard.get(i));
        }
        System.out.print(gameBoard);
        System.out.println();
        System.out.println(gameBoard.get(2).get(1));
        return gameBoard;
    }

    private static int[] getEmptyFieldIndex(ArrayList<ArrayList<Integer>> gameBoard) {
        for (int i = 0; i < gameBoard.size(); i++) {
            for (int j = 0; j < gameBoard.get(i).size(); j++) {
                if (gameBoard.get(i).get(j) == 0) {
                    return new int[]{i, j}; // Return the indices as an array
                }
            }
        }
        return new int[]{-1, -1}; // Return -1, -1 if 0 is not found
    }

    private static ArrayList<int[]> findNeighborsOfZero(ArrayList<ArrayList<Integer>> gameBoard) {
        ArrayList<int[]> neighbors = new ArrayList<>();
        int[] positionOfZero = getEmptyFieldIndex(gameBoard);

        int rowIndexOfZero = positionOfZero[0];
        int colIndexOfZero = positionOfZero[1];

        if (rowIndexOfZero == -1) {
            return neighbors;
        }

        // Add neighbors with their positions
        if (rowIndexOfZero > 0) neighbors.add(new int[]{rowIndexOfZero - 1, colIndexOfZero}); // Above
        if (rowIndexOfZero < gameBoard.size() - 1)
            neighbors.add(new int[]{rowIndexOfZero + 1, colIndexOfZero}); // Below
        if (colIndexOfZero > 0) neighbors.add(new int[]{rowIndexOfZero, colIndexOfZero - 1}); // Left
        if (colIndexOfZero < gameBoard.get(rowIndexOfZero).size() - 1)
            neighbors.add(new int[]{rowIndexOfZero, colIndexOfZero + 1}); // Right

        return neighbors;
    }

    private static void swapWithZero(ArrayList<ArrayList<Integer>> gameBoard, int[] swapPosition) {
        int[] zeroPosition = getEmptyFieldIndex(gameBoard);

        int temp = gameBoard.get(zeroPosition[0]).get(zeroPosition[1]);
        gameBoard.get(zeroPosition[0]).set(zeroPosition[1], gameBoard.get(swapPosition[0]).get(swapPosition[1]));
        gameBoard.get(swapPosition[0]).set(swapPosition[1], temp);
    }

}
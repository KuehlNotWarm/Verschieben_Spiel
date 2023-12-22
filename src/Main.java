import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static ArrayList<ArrayList<Integer>> board; // Das Spielbrett, repräsentiert als 2D-ArrayList
    private static int sizeBoard; // Größe des Spielbretts
    private static int emptyRow, emptyCol; // Position des leeren Feldes (Reihe, Spalte)
    private static int moveCount; // Anzahl der Züge

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Spielbrettgröße einlesen
        System.out.print("Bitte gib die Größe des Spielbretts ein (z.B. 3 für ein 3x3-Spielbrett): ");
        sizeBoard = scanner.nextInt();

        // Prüft ob Eingabe mind. 3 ist
        while (sizeBoard < 2) {
            System.out.println("Die Größe muss mindestens 2 sein. Bitte gib eine gültige Größe ein:");
            sizeBoard = scanner.nextInt();
        }

        boolean playAgain = true;
        while (playAgain) {
            initializeBoard(); // Initialisiert das Spielbrett
            while (!isSolved()) { // Wiederholt, bis das Puzzle gelöst ist
                printBoard();
                System.out.println("Spielzug: " + moveCount);
                System.out.print("Geben Sie die Zahl ein, die getauscht werden soll: ");
                int numberToBeMoved = scanner.nextInt(); //Zahl die getauscht werden soll
                if (!move(numberToBeMoved)) {
                    System.out.println("! ! ! INVALIDER ZUG ! ! !");
                }
            }

            System.out.println("Puzzle gelöst in " + moveCount + " Zügen!");
            printBoard();
            System.out.print("Möchtest du noch eine Runde spielen? (ja/nein): ");
            playAgain = scanner.next().trim().equalsIgnoreCase("ja");
        }
        scanner.close();
        System.out.println("Spiel beendet. Danke fürs Spielen!");
    }

    // Initialisiert das Spielbrett mit zufälligen Zahlen und einem leeren Feld
    private static void initializeBoard() {
        board = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < sizeBoard * sizeBoard; i++) {
            numbers.add(i);
        }
        numbers.add(null); // Null repräsentiert das leere Feld
        Collections.shuffle(numbers); //mischt das array

        int positionNumbersArray = 0; //Zählvariable, aktuelle
        for (int i = 0; i < sizeBoard; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < sizeBoard; j++) {
                row.add(numbers.get(positionNumbersArray));
                if (numbers.get(positionNumbersArray) == null) {
                    emptyRow = i; //erster Index des Leeren Feldes (Reihe)
                    emptyCol = j; //zweiter Index des leeren Feldes (Spalte)
                }
                positionNumbersArray++;
            }
            board.add(row);
        }
        moveCount = 0;
    }

    // Gibt das Spielbrett aus
    private static void printBoard() {
        for (ArrayList<Integer> row : board) {
            for (Integer num : row) {
                System.out.print(num == null ? "  " : num + " ");
            }
            System.out.println();
        }
    }

    // Versucht, eine Zahl zu bewegen, und gibt zurück, ob der Zug gültig war
    private static boolean move(int number) {
        for (int i = Math.max(0, emptyRow - 1); i <= Math.min(sizeBoard - 1, emptyRow + 1); i++) {
            for (int j = Math.max(0, emptyCol - 1); j <= Math.min(sizeBoard - 1, emptyCol + 1); j++) {
                if (board.get(i).get(j) != null && board.get(i).get(j) == number) {
                    if (i == emptyRow || j == emptyCol) {
                        board.get(emptyRow).set(emptyCol, number);
                        board.get(i).set(j, null);
                        emptyRow = i;
                        emptyCol = j;
                        moveCount++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Überprüft, ob das Puzzle gelöst ist
    private static boolean isSolved() {
        int expected = 1;
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if ((i != sizeBoard - 1 || j != sizeBoard - 1) && (board.get(i).get(j) == null || board.get(i).get(j) != expected)) {
                    return false;
                }
                expected++;
            }
        }
        return true;
    }
}

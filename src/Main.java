import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static ArrayList<ArrayList<Integer>> board; // Das Spielbrett, repräsentiert als 2D-ArrayList
    private static int inputSizeBoard; // Größe des Spielbretts
    private static int nullRow, nullCol; // Position des leeren Feldes (Reihe, Spalte)
    private static int moveCount; // Anzahl der Züge

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Spielbrettgröße einlesen
        System.out.print("Bitte gib die Größe des Spielbretts ein (z.B. 3 für ein 3x3-Spielbrett): ");
        inputSizeBoard = scanner.nextInt();

        // Prüft ob Eingabe mind. 3 ist
        while (inputSizeBoard < 2) {
            System.out.println("Die Größe muss mindestens 2 sein. Bitte gib eine gültige Größe ein:");
            inputSizeBoard = scanner.nextInt();
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

            System.out.println("Puzzle gelöst mit " + moveCount + " Zügen!");
            printBoard();
            System.out.print("Möchtest du noch eine Runde spielen? (ja/nein): ");
            playAgain = scanner.next().trim().equalsIgnoreCase("ja"); // wenn Spieler ja eingibt -> gibt true zurück
        }
        scanner.close();
        System.out.println("Spiel beendet. Danke fürs Spielen!");
    }

    /**
     * Initialisiert das Spielbrett zu Beginn eines neuen Spiels
     * mit zufälligen Zahlen und einem leeren Feld.
     */
    private static void initializeBoard() {
        // 2-Dimensional Array erstellt
        board = new ArrayList<>();

        // 1-Dimensionaler Array erstellt, um die Zahlen darin mischen zu können
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int i = 1; i < inputSizeBoard * inputSizeBoard; i++) {
            numbersArray.add(i); //  N x X Zahlen adden
        }

        numbersArray.add(null); // Null ist leeres Feld
        Collections.shuffle(numbersArray); // Mischt 1D-Array

        /* Initialisieren des 2D-Arrays */
        int indexNumbersArray = 0; // Aktuelle Position im numbersArray

        for (int row = 0; row < inputSizeBoard; row++) {
            ArrayList<Integer> rowArray = new ArrayList<>();

            for (int col = 0; col < inputSizeBoard; col++) {
                rowArray.add(numbersArray.get(indexNumbersArray)); // Fügt ArrayList zahlen aus numbersArray hinzu
                if (numbersArray.get(indexNumbersArray) == null) {
                    nullRow = row; // erster Index des Leeren Feldes (Reihe)
                    nullCol = col; // zweiter Index des leeren Feldes (Spalte)
                }
                indexNumbersArray++;
            }
            board.add(rowArray); // fügt dem board die jeweilige Reihe (i) hinzu
        }
        moveCount = 0; //setzt den Spielzug zurück
    }

    // Gibt das Spielbrett aus
    private static void printBoard() {
        // Durchläuft jedes Element in jeder Zeile und gibt es in der Konsole aus
        for (ArrayList<Integer> row : board) {
            for (Integer num : row) {
                // bedingung ? ausdruck_wenn_wahr : ausdruck_wenn_falsch
                System.out.print(num == null ? "  " : num + " ");
            }
            System.out.println();
        }
    }

    // Versucht, eine Zahl zu bewegen, und gibt zurück, ob der Zug gültig war
    private static boolean move(int numberToBoMoved) {
        // Durchlaufen der Zeilen um das leere Feld herum
        int topFromNull = Math.max(0, nullRow - 1);
        int bottomFromNull = Math.min(inputSizeBoard - 1, nullRow + 1);
        int leftFromNull = Math.max(0, nullCol - 1);
        int rightFromNull = Math.min(inputSizeBoard - 1, nullCol + 1);


        for (int row = topFromNull; row <= bottomFromNull; row++) {
            // Durchlaufen der Spalten um das leere Feld herum
            for (int col = leftFromNull; col <= rightFromNull; col++) {
                // Überprüfung, ob die aktuelle Zelle die gesuchte Zahl enthält und nicht leer ist
                if (board.get(row).get(col) != null && board.get(row).get(col) == numberToBoMoved) {
                    // Überprüfung, ob die Zahl in derselben Zeile oder Spalte wie das leere Feld liegt
                    if (row == nullRow || col == nullCol) {
                        // Verschieben der Zahl in das leere Feld
                        board.get(nullRow).set(nullCol, numberToBoMoved);
                        // Aktualisieren der alten Position der Zahl auf 'null' (leeres Feld)
                        board.get(row).set(col, null);
                        // Aktualisieren der Position des leeren Feldes
                        nullRow = row;
                        nullCol = col;
                        // Erhöhen des Spielzugszählers
                        moveCount++;
                        // Rückgabe von 'true', da der Zug erfolgreich war
                        return true;
                    }
                }
            }
        }
        // Rückgabe von 'false', falls kein gültiger Zug möglich war
        return false;
    }

    // Überprüft, ob das Puzzle gelöst ist
    private static boolean isSolved() {
        //Überprüft die Anordnung 1-8 + 0
        int expectedNumber = 1; // Aufsteigende Reihenfolge, zahlen fangen im Spielbrett bei 1 an
        for (int row = 0; row < inputSizeBoard; row++) {
            for (int col = 0; col < inputSizeBoard; col++) {
               //prüft, ob es sich nicht um das letzte feld handelt ([2][2]) und ob es entweder null ist, oder nicht der erwarteten Zahl entspricht. Falls doch gibt es false zurück
                if ((row != inputSizeBoard - 1 || col != inputSizeBoard - 1) && (board.get(row).get(col) == null || board.get(row).get(col) != expectedNumber)) {
                    return false;
                }
                expectedNumber++; // erhöht Zahl um 1
            }
        }
        return true; // Bedingungen sind alle erfüllt und das Spieler hat gewonnen
    }
}

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
                System.out.print("Zahl eingeben, die Bewegt werden soll: ");
                int num = scanner.nextInt(); //Zahl die getauscht werden soll
                if (!move(num)) {
                    System.out.println("Invalider Zug");
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
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int i = 1; i < sizeBoard * sizeBoard; i++) {
            numbersArray.add(i);
        }
        numbersArray.add(null); // Null repräsentiert das leere Feld
        Collections.shuffle(numbersArray); //mischt das array

        int positionNumbersArray = 0; // Zählvariable, aktuelle
        for (int i = 0; i < sizeBoard; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < sizeBoard; j++) {
                row.add(numbersArray.get(positionNumbersArray)); // Fügt dem reihen ArrayList zahlen aus numbersArray hinzu
                if (numbersArray.get(positionNumbersArray) == null) {
                    emptyRow = i; // erster Index des Leeren Feldes (Reihe)
                    emptyCol = j; // zweiter Index des leeren Feldes (Spalte)
                }
                positionNumbersArray++;
            }
            board.add(row); // fügt dem board die jeweilige Reihe (i) hinzu
        }
        moveCount = 0; //setzt den Spielzug zurück
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
        // Durchlaufen der Zeilen um das leere Feld herum
        int topFromNull = Math.max(0, emptyRow - 1);
        int bottomFromNull = Math.min(sizeBoard - 1, emptyRow + 1);
        int leftFromNull = Math.max(0, emptyCol - 1);
        int rightFromNull = Math.min(sizeBoard - 1, emptyCol + 1);


        for (int row = topFromNull; row <= bottomFromNull; row++) {
            // Durchlaufen der Spalten um das leere Feld herum
            for (int col = leftFromNull; col <= rightFromNull; col++) {
                // Überprüfung, ob die aktuelle Zelle die gesuchte Zahl enthält und nicht leer ist
                if (board.get(row).get(col) != null && board.get(row).get(col) == number) {
                    // Überprüfung, ob die Zahl in derselben Zeile oder Spalte wie das leere Feld liegt
                    if (row == emptyRow || col == emptyCol) {
                        // Verschieben der Zahl in das leere Feld
                        board.get(emptyRow).set(emptyCol, number);
                        // Aktualisieren der alten Position der Zahl auf 'null' (leeres Feld)
                        board.get(row).set(col, null);
                        // Aktualisieren der Position des leeren Feldes
                        emptyRow = row;
                        emptyCol = col;
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
        int expectedNumber = 1; // Zahlen im Spielbrett fangen bei 1 an
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                // prüft, ob es sich nicht um das letzte feld handelt ([2][2]) und ob es entweder null ist, oder nicht der erwarteten Zahl entspricht. Falls doch gibt es false zurück
                if ((i != sizeBoard - 1 || j != sizeBoard - 1) && (board.get(i).get(j) == null || board.get(i).get(j) != expectedNumber)) {
                    return false;
                }
                expectedNumber++; // erhöht Zahl um 1
            }
        }
        return true; // Bedingungen sind alle erfüllt und der Spieler hat gewonnen
    }
}

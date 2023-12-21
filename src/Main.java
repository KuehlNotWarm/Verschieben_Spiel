import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static int boardLength = 9;

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        initBoardRandom();
    }

    /**
     * Gibt das Spielbrett mit aktueller Stellung am Bildschirm aus.
     */
    private static void printBoard(ArrayList<Integer> board) {
        char[][] gameBoard = {
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}
        };

        for (int i = 0; i < 5; i = i+2) {
            for (int j = 0; j < 5; j = i+2) {
                char[i][j]=
            }
        }

        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /**
     * Erzeugt auf dem Spielbrett eine zufällige Stellung. Kann zum Erzeugen der Startstellung genutzt werden.
     */
    private static ArrayList<ArrayList<Integer>> initBoardRandom(){
        System.out.println("enter board size N: (NxN)");
        int inputBoardSize = scan.nextInt();
        ArrayList<ArrayList<Integer>> gameBoard = new ArrayList<>(inputBoardSize);

        System.out.println(gameBoard);
        return gameBoard;
    }

    /**
     *
     * Tauscht zwei Felder auf dem Spielbrett.
     */
    private static void swapFields(){

    }

    /**
     * Prüft, ob zwei Felder horizontal oder vertikal benachbart sind.
     */
    private static void isAdjacentFields(){

    }

    /**
     * Ermittelt alle Felder, die zum angegebenen Feld horizontal oder vertikal benachbart sind.
     */
    private static void getAdjacentFields(){

    }

    /**
     * Prüft, ob die aktuelle Stellung die Endstellung ist.
     */
    private static void isGameOver(){

    }

    /**
     * Ermittelt die Position (Index) zu einem Feld.
     */
    private static void getFieldIndex(){

    }

    /**
     * Ermittelt die Position (Index) des leeren Feldes.
     */
    private static void getEmptyFieldIndex(){

    }
}
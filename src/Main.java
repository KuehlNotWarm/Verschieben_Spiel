import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    static int[] fieldArray;
    static Random rand = new Random();


    public static void main(String[] args) {

        initBoardRandom();
    }

    /**
     * Gibt das Spielbrett mit aktueller Stellung am Bildschirm aus.
     */
    private static void printBoard() {

        char[][] gameBoard = {
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}
        };

        for(char[] row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /**
     * Erzeugt auf dem Spielbrett eine zufällige Stellung. Kann zum erzeugen der Startsellung genutzt werden.
     */
    private static ArrayList<Integer> initBoardRandom(){
        int boardLenth = 9;
        ArrayList<Integer> board = new ArrayList<>();
        for (int i = 0; i < boardLenth ; i++) {
            board.add(i);
        }
        Collections.shuffle(board);
        return board;
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
     * Ermittelt die Position (Index) des leerren Feldes.
     */
    private static void getEmptyFieldIndex(){

    }
}
import java.util.Arrays;
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
    private static void initBoardRandom(){
        fieldArray = new int[9];
        int randomField = rand.nextInt(9);
        fieldArray[randomField] = 10;
//        int count = 0;
//        for(int i : fieldArray){
//            if(i == 0){
//                fieldArray[count] = getField();
//            }
//        }
        for (int i = 0; i <fieldArray.length; i++) {
            if(fieldArray[i] == 0){
                fieldArray[i] = getField();
            }
        }
        System.out.print(Arrays.toString(fieldArray));
    }

    /**
     * Rekursive methode für random Zahl in slot
     */
    public static int getField() {
        int zufall = rand.nextInt(8) + 1;
        //random Zahl initialisiert
        for (int e : fieldArray) {
            if (zufall == e) {
                getField();
            }
        }
        return zufall;
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
package bingo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author PascualQuiles
 */
public class Bingo {

    private final String RESETCOLOR = "\u001B[0m";
    private final String ROJO = "\u001B[31m";

    private final ArrayList<Integer> NUMEROS = new ArrayList<>(Arrays.asList(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
            38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55,
            56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73,
            74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91,
            92, 93, 94, 95, 96, 97, 98, 99));

    private ArrayList<Integer> bombo;

    public Bingo() {
        bombo = (ArrayList) NUMEROS.clone();
    }

    /**
     * Función que crea un carton de 5*5
     * @return devuelve el Array con el cartón
     */
    public int[][] crearCarton(){
        ArrayList numerosPosibles = (ArrayList) NUMEROS.clone();
        int [][] carton = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                carton[i][j]= (int) numerosPosibles.remove((int)(Math.random()*numerosPosibles.size()));
            }
        }
        for (int i = 0; i < 11; i++) {

            carton = casillasVacias(carton);
        }
        return carton;
    }


    /**
     * Función que sirve para crear huecos vacíos en el cartón
     * @param arr Array de números bidimensional que cambiar
     * @return un array con una casilla, no previamente asignada, asignada a -1
     */
    private int[][] casillasVacias(int[][] arr){

            int x = (int)(Math.random()*5);
            int y = (int)(Math.random()*5);

            if (arr[y][x] != -1){
                arr[y][x] = -1;
            } else {
                casillasVacias(arr);
            }
            return arr;

    }

    /**
     * Función qeu muestra un cartón por consola
     * @param arr Cartón que se quiere dibujar
     */
    public void dibujarCarton(int [][] arr){
        System.out.println("+------------------------+");
        for (int i = 0; i < 5; i++) {
            System.out.print("|");
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == -1){
                    System.out.print(" XX |");
                } else if (arr[i][j] <10) {
                    System.out.print(" 0" + arr[i][j] + " |");
                } else {
                    System.out.print(" " + arr[i][j] + " |");
                }
            }
            System.out.println();
            System.out.println("+------------------------+");
        }
    }

    /**
     * Función que elige un número aleatorio de los que quedan en el bombo
     * @return el número sacado
     */
    public int sacarNumero(){

        return (int)(bombo.remove((int)(Math.random()*bombo.size())));

    }


    /**
     * Función que busca si un número está presente en el cartón
     * @param n el número que se busca
     * @param arr el cartón en que se busca
     * @return -10 si no se ha encontrado el número, la posición en formato i*10+j si se ha encontrado
     */
    public int  numeroPresente(int n, int [][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == n){
                    arr[i][j] = -1;
                    return i*10+j;
                }
            }
        }
        return -10;
    }


    /**
     * Función que comprueba si se ha complatado una fila
     * @param arr el cartóna  comprobar
     * @param y la fila que se quiere comrpobar
     * @return <code>true</code> si se ha encontrado <code>false</code> si no se ha encontrado
     */
    public boolean linea(int [][] arr, int y){
        for (int i = 0; i < arr[y].length; i++) {
            if (arr[y][i] != -1) return false;
        }
        return true;
    }

    /**
     * Función que comprueba si se ha completado una columna
     * @param arr el cartóna  comprobar
     * @param x la columna que se quiere comrpobar
     * @return <code>true</code> si se ha encontrado <code>false</code> si no se ha encontrado
     */
    public boolean columna(int [][] arr, int x){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][x] != -1) return false;
        }
        return true;
    }

    /**
     * Función que comprueba si se ha completado la diagonal izqauierda
     * @param arr EL cartón qeu se quiere comprobar
     * @return <code>true</code> si se ha encontrado <code>false</code> si no se ha encontrado
     */
    public boolean diagonalIzda(int [][] arr){
        int i,j;
        for ( i = 0 , j = 0; i < arr.length ; i++, j++) {
            if (arr[i][j] != -1) return false;
        }
        return true;
    }

    /**
     * Función que comprueba si se ha completado la diagonal izquierda
     * @param arr EL cartón que se quiere comprobar
     * @return <code>true</code> si se ha encontrado <code>false</code> si no se ha encontrado
     */
    public boolean diagonalDcha(int [][] arr){
        int i,j;
        for ( i = arr.length-1 , j = 0; i >= 0 ; i--, j++) {
            if (arr[i][j] != -1) return false;
        }
        return true;
    }


    /**
     * Función que una vez encontrado una coincidencia númerica en el cartón dibuja aquello que se halla completado
     * @param arr cartón qeu se quiere comprobar
     * @param y índice de posición vertical en la matriz del número encontrado
     * @param x índice de posición horizontal en la matriz del número encontrado
     * @param encontrado indica la cualidad de lo que se ha completado
     */
    public void dibujarEncontrado(int[][] arr, int y, int x, Encontrado encontrado){

        System.out.println("+------------------------+");
        for (int i = 0; i < 5; i++) {

            System.out.print("|");

            for (int j = 0; j < 5; j++) {

                /*Comprobamos cual de los tipos de compleción es y si cumple el requisito adicional
                para ser una casilla resaltada
                * */
                if (encontrado == Encontrado.BINGO ||
                    (encontrado == Encontrado.FILA && i == y) ||
                    (encontrado == Encontrado.COLUMNA && j == x) ||
                    (encontrado == Encontrado.DIAGONALIZDA && i == j) ||
                    (encontrado == Encontrado.DIAGONALDCHA && i+j == 4) ||
                    (encontrado == Encontrado.UNO && i == y && x == j)) {

                    System.out.print(ROJO + " XX " + RESETCOLOR +"|" );

                } else {

                    if (arr[i][j] == -1) {
                        System.out.print(" XX |");
                    } else if (arr[i][j] < 10) {
                        System.out.print(" 0" + arr[i][j] + " |");
                    } else {
                        System.out.print(" " + arr[i][j] + " |");
                    }

                }
            }
            System.out.println();
            System.out.println("+------------------------+");
        }
    }


    /**
     * Comprueba si un cartón ha sido completado con bingo
     * @param arr cartón que se quiere comprobar
     * @return <code>true</code> si el cartón ha sido completado, <code>false</code> si no ha sido completado
     */
    public boolean esBingo(int [][] arr){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] != -1) return false;
            }
        }
        return true;
    }

}

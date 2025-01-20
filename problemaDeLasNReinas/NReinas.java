package problemaDeLasNReinas;

import java.util.Scanner;

/**
 * @author Pascual Quiles
 * */

/*El problema de las N reinas consiste en situar N reinas en un tablero de ajedrez de N x N sin que se amenacen entre ellas.
* Una reina amenaza a otra si esta en la misma fila, la misma columna o en la misma diagonal.
* Con una N dada por el usuario, el programa buscará e imprimirá todas las soluciones existentes para dicha N
* */

public class NReinas {

    /*La clase contiene una matriz bidimensional que conformará el tablero
    y un contador que almacenará el número de soluciones de una n dada*/
    
    public int [][] tablero;
    public int contador;

    //El constructor recibe n para inicializar la matriz y establece el contador en 0

    public NReinas(int n) {
        tablero = new int[n][n];
        contador = 0;
    }

    //función que mostrará la disposición de las soluciones recorriendo la matriz

    public void mostrarTablero() {

        System.out.print("  ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("  " + ejeX[i]);
        }
        System.out.println("\n");

        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i+1 +"   ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /*función que comprueba que la posición en la que se intenta colocar
    una reina no está amenazada por otra reina ya colocada*/

    public boolean esValido(int fila, int columna) {
        int i;
        int j;

        /*En cada fila solo puede y debe haber una reina, como cuando encontramos una posición que es
        * válida saltamos a la siguiente fila, sabemos que no hay que comprobar que haya ya reinas
        * en el eje horizontal. Además, como vamos de arriba a abajo sabemos que no habrá
        * ninguna reina por debajo de la posición que estamos comprobando, lo cual nos deja
        * con solo 3 ángulos que comprobar*/

        //comprobamos que no haya ninguna reina en el eje vertical
        for ( i = 0; i < fila; i++) {
            if (tablero[i][columna] == 1) {
                return false;
            }
        }

        //comprobamos que no haya ninguna reina en la diagonal superior izda.
        for ( i = fila-1 ,  j = columna-1;  i >= 0 && j >= 0 ; i--, j--) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        //comprobamos la diagonal superior derecha
        for (i=fila-1, j = columna+1; i >= 0 && j < tablero.length ; i--, j++ ) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        //si no hay ninguna reina amenazando la posicion devolvemos true
        return true;
    }


    /*función que implementa la recursividad para encontrar todas las soluciones al problema*/

    public void buscarSoluciones(int fila) {

        /*El caso base es que la fila sea igual a la longitud del tablero,
        * puesto que eso significa que ya se han añadido reinas en cada
        * una de las filas y por tanto se ha encontrado una solución*/

        if (fila == tablero.length) {
            mostrarTablero();
            contador++;
        } else {

            //recorremos todas las columnas de la fila dada
            for (int columna = 0; columna < tablero.length; columna++) {

                /*Comprobamos una  a una si la posición en la que nos encontramos es válida,
                * de ser así establecemos en esa posición un 1 indicando que hay una reina
                * y volvemos a llamar a la función en la fila siguiente. Luego establecemos
                * la posición de nuevo a 0 para hacer backtracking ya sea porque por un camino
                * se ha llegado a un punto muerto o a una solución. De esta forma se comprueban
                * todas las combinaciones posibles
                */

                if (esValido(fila, columna)) {
                    tablero[fila][columna] = 1;
                    buscarSoluciones(fila + 1);
                    tablero[fila][columna] = 0;
                }
            }
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca n para buscar el número de soluciones: ");
            int n = sc.nextInt();

        NReinas solucionar = new NReinas(n);
        solucionar.buscarSoluciones(0);

        System.out.println("Número total de soluciones = " + solucionar.contador);
    }

    static char[] ejeX = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'};
}

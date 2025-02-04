package ahorcado;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author PascualQuiles
 */

public class Partida {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido al juego del ahorcado");

        Ahorcado partida = new Ahorcado();

        partida.mostrarEstado();
        boolean victoria = false;

        //bucle que se ejecuta hasta qeu el usuario pierda o gane
        do {

            char letra;
            //comprobamos que el usuario introduzca una letra válida y qeu no haya sido introducida antes
            while (true) {

                System.out.println("Introduzca una letra");
                String a = sc.nextLine();

                if (partida.esLetra(a)){

                    letra = a.charAt(0);
                    if (partida.letraNoUsada(letra)){
                        break;
                    }
                }
                System.out.println("Input inválido");
            }

            //comprobamos que la letra esté en la palabra
            if (partida.anadirLetra(letra)){

                System.out.println("¡Acertaste una letra!");
                victoria = partida.victoria();

            } else{

                System.out.println("Fallaste :(");
            }


            partida.mostrarEstado();

        }while (!victoria && partida.getFallos()<6);

        if (victoria){

            System.out.println("!Has ganado!");

        } else {

            System.out.println("Perdiste");
            System.out.println("La palabra era: " + Arrays.toString(partida.getPalabraEscogida()));

        }
    }
}

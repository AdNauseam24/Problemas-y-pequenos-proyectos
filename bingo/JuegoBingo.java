package bingo;

import java.util.Scanner;

public class JuegoBingo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int contadorRondas = 0;

        Bingo b = new Bingo();

        int [][] cartonUsuario = new int[5][5];
        int [][] cartonA = b.crearCarton();
        int [][] cartonB = b.crearCarton();
        int [][] cartonC = b.crearCarton();


        System.out.println("Cartón A: ");
        b.dibujarCarton(cartonA);
        System.out.println("Cartón B: ");
        b.dibujarCarton(cartonB);
        System.out.println("Cartón C: ");
        b.dibujarCarton(cartonC);

        char eleccion;

        do {
            System.out.println("Elija un cartón A, B o C");
            eleccion = sc.next().charAt(0);
        }while (eleccion != 'A' && eleccion != 'B' && eleccion != 'C'
        && eleccion != 'a' && eleccion != 'b' && eleccion != 'c');

        switch (eleccion){
            case 'A', 'a':
                cartonUsuario = cartonA;
                System.out.println("Has elegido el cartón A");
                break;
            case 'B', 'b':
                cartonUsuario = cartonB;
                System.out.println("Has elegido el cartón B");
                break;
            case 'C', 'c':
                cartonUsuario = cartonC;
                System.out.println("Has elegido el cartón C");
                break;
        }

        System.out.println("Todo listo para comenzar");
        sc.nextLine();

        Encontrado estado = Encontrado.UNO;

        do {

            System.out.println("Presione Intro para sacar un número");
            sc.nextLine();


            int n = b.sacarNumero();
            System.out.println("¡Ha salido el " + n + "!");

            int presente = b.numeroPresente(n, cartonUsuario);

            //un valor de -10 indica que el número no estaba presente
            if (presente != -10) {

                System.out.println("!Número encontrado!");

                estado = Encontrado.UNO;

                int y = presente/10;
                int x = presente%10;

                b.dibujarEncontrado(cartonUsuario, y, x, estado);

                if (b.linea(cartonUsuario, y)){
                    estado = Encontrado.FILA;
                    System.out.println("!Has hecho fila!");
                    b.dibujarEncontrado(cartonUsuario, y, x, estado);
                }  if (b.columna(cartonUsuario, x)){
                    estado = Encontrado.COLUMNA;
                    System.out.println("!Has hecho fila!");
                    b.dibujarEncontrado(cartonUsuario, y, x, estado);
                }  if (y == x && b.diagonalIzda(cartonUsuario)) {
                    estado = Encontrado.DIAGONALIZDA;
                    System.out.println("!Has hecho fila!");
                    b.dibujarEncontrado(cartonUsuario, y, x, estado);
                }  if (y+x == 4 && b.diagonalDcha(cartonUsuario)) {
                    estado = Encontrado.DIAGONALDCHA;
                    System.out.println("!Has hecho fila!");
                    b.dibujarEncontrado(cartonUsuario, y, x, estado);
                }

                if (b.esBingo(cartonUsuario)){
                    estado = Encontrado.BINGO;
                }

            } else {
                System.out.println("Número no encontrado");
            }

            contadorRondas++;

        }while (estado != Encontrado.BINGO);

        System.out.println("¡Has hecho BINGO!");
        b.dibujarEncontrado(cartonUsuario,0,0,estado);
        System.out.println("Has ganado en " + contadorRondas + " rondas");

    }
}

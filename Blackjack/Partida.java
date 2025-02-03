package Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Partida {

    public static void main(String[] args) {

        //Array de las cartas que se mostrarán en mesa
        ArrayList<String> cartasEnMesa;
        int apuesta;

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);

        //creamos un nuevo jugador que empieza con 100 fichas
        Jugador j = new Jugador();
        System.out.println("Bienvenido, introduce tu nombre");
        String nombre = sc.nextLine();

        System.out.println("Saludos " + nombre);

        //bucle para cada una de las apuestas iniciales
        do {

            int cantidadEnApuesta = 0;

            //El jugador elige una cantidad de sus fichas para apostar
            System.out.println("Tienes " + j.getPuntos() + " fichas");
            System.out.println("Elige cuantas fichas vas a apostar");

            while (true){

                 apuesta = sc2.nextInt();

                 if (!(apuesta<=0 || apuesta>j.getPuntos())){
                     break;
                 }

                 System.out.println("Elige una cantidad válida de fichas");
            }

            j.apostarPuntos(apuesta);
            cantidadEnApuesta +=apuesta;
            boolean aumentarApuesta;
            boolean derrotado;
            boolean victoria;

            //bucle por si dentro de la misma apuesta inicial se aumenta la cantidad
            do {

                //se crea una baraja
                //se inicializan las cartas en mesa
                String continuar="";
                cartasEnMesa = new ArrayList<>();
                Baraja b = new Baraja();
                int puntuacionTot = 0;
                 derrotado = false;
                 victoria = false;

                 //bucle para ir sacando cartas
                do {
                    int puntuacion  = b.sacarCarta();
                    puntuacionTot += puntuacion;
                    String cartaSacada = String.valueOf(puntuacion);

                    switch (cartaSacada) {
                        case "11":
                            cartaSacada = "J";
                            break;
                        case "12":
                            cartaSacada = "Q";
                            break;
                        case "13":
                            cartaSacada = "K";
                            break;
                        case "1":
                            cartaSacada = "A";
                            break;
                    }

                    cartasEnMesa.add(cartaSacada);
                    for (String s : cartasEnMesa) {
                        System.out.print("[" + s + "]");
                    }
                    System.out.println();
                    System.out.println("Tu puntuación actual es de: " + puntuacionTot);

                    if (puntuacionTot>21){
                        derrotado = true;
                    } else if (puntuacionTot==21) {
                        victoria = true;
                    } else {
                        System.out.println("Si deseas sacar otra carta escribe SI");
                        continuar = sc.nextLine();
                    }

                }while (!derrotado && !victoria && continuar.equalsIgnoreCase("si"));

                if (!victoria && !derrotado){
                    //si el jugador se retira sin llegar a 21 o pasarse saca la casa
                    System.out.println("Saca el crupier");

                    b = new Baraja();
                    int puntCrupier = 0;
                    cartasEnMesa = new ArrayList<>();

                    while (true){

                        int puntuacion = b.sacarCarta();
                         puntCrupier  += puntuacion;

                         if (puntCrupier>21){
                             puntCrupier-=puntuacion;
                             break;
                         }

                        String cartaSacada = String.valueOf(puntuacion);

                        switch (cartaSacada) {
                            case "11":
                                cartaSacada = "J";
                                break;
                            case "12":
                                cartaSacada = "Q";
                                break;
                            case "13":
                                cartaSacada = "K";
                                break;
                            case "1":
                                cartaSacada = "A";
                                break;
                        }
                        cartasEnMesa.add(cartaSacada);
                    }

                    for (String s : cartasEnMesa) {
                        System.out.print("[" + s + "]");
                    }

                    System.out.println();
                    System.out.println("Puntuación Crupier: " + puntCrupier);

                    if (puntCrupier>puntuacionTot){
                        derrotado = true;
                    } else if (puntCrupier<puntuacionTot){
                        victoria = true;
                    }
                }
                aumentarApuesta = false;

                if (derrotado){
                    System.out.println("Perdiste tus fichas");
                    break;
                } else if (victoria) {
                    cantidadEnApuesta*=2;
                    System.out.println("Has ganado");
                    System.out.println("La cantidad que recibirías ahora mismo es de: " + cantidadEnApuesta);
                    System.out.println("Si deseas jugar otra ronda y duplicarlo pulsa SI ");
                    aumentarApuesta = sc.nextLine().equalsIgnoreCase("si");
                }else {
                    System.out.println("Has empatado");
                    System.out.println("Si deseas juguar otra ronda pulsa si, en caso contrario ganarás las fichas acumuladas");
                    aumentarApuesta = sc.nextLine().equalsIgnoreCase("si");
                }

            } while (aumentarApuesta);
            if (!derrotado){
                j.anadirPuntos(cantidadEnApuesta);
            }

            System.out.println("Tienes un total de: " + j.getPuntos() + " fichas");
            if (j.getPuntos()>0){
            System.out.println("Si deseas comenzar con una nueva apuesta inicial escribe si");
            if (!sc.nextLine().equalsIgnoreCase("si")){
                break;
            }} else {
                System.out.println("Has perdido");
                break;
            }

        }while (true);
        System.out.println("Tu puntuación final es de: " + j.getPuntos());



    }
}

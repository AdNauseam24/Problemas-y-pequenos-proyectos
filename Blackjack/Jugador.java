package Blackjack;

public class Jugador {
    private int puntos = 100;

    public int getPuntos() {
        return puntos;
    }
    public int anadirPuntos(int n){
        puntos += n;
        return puntos;
    }
    public int apostarPuntos(int n){
        puntos -= n;
        return puntos;
    }

}

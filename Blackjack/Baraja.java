package Blackjack;

import java.util.ArrayList;
import java.util.Arrays;

public class Baraja {
   private ArrayList<Integer> cartas;

    public Baraja() {
        cartas = new ArrayList<>(Arrays.asList(1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13));
    }

    public int sacarCarta(){
        return cartas.remove((int)(Math.random()*cartas.size()));
    }
}

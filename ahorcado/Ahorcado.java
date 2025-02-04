package ahorcado;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author PascualQuiles
 */

public class Ahorcado {
    private final String[] palabras = {
            "gato", "perro", "avion", "pelota", "futuro", "libro", "sol", "luna", "estrella", "agua",
            "rojo", "verde", "azul", "amarillo", "naranja", "flor", "arbol", "rio", "montana", "viento",
            "computadora", "telefono", "ciudad", "pais", "continente", "planta", "camino", "puente",
            "puerta", "ventana", "cielo", "tierra", "mar", "playa", "nieve", "lluvia", "granizo", "neblina",
            "nube", "coche", "tren", "bicicleta", "moto", "espejo", "puno", "manzana", "pera", "platano",
            "uva", "sandia", "tomate", "zanahoria", "lechuga", "carnes", "pollo", "cerdo", "res", "pescado",
            "mariscos", "pan", "queso", "huevo", "taza", "tenedor", "cuchillo", "cucharon", "plato",
            "sal", "pimienta", "azucar", "salsa", "pastel", "helado", "chocolate", "galleta", "miel",
            "te", "cafe", "agua", "jugo", "leche", "bebida", "alcohol", "vino", "cerveza", "champan",
            "cigarro", "fumar", "cielo", "estrella", "universo", "galaxia", "planeta", "luz", "oscuridad",
            "espacio", "tiempo", "futuro", "pasado", "presente", "amor", "amistad", "familia", "felicidad"
    };

    private char [] palabraEscogida;
    private char [] palabraOculta;
    private int fallos;
    private char [] letrasUsadas;

    /**
     *
     * @return el número de fallos del usuario
     */
    public int getFallos() {
        return fallos;
    }

    public Ahorcado() {
        String escogido = palabras[(int)(Math.random()*palabras.length)];
        fallos = 0;
        letrasUsadas = new char[27];
        palabraEscogida = escogido.toCharArray();
        palabraOculta = new char[palabraEscogida.length];
        Arrays.fill(palabraOculta,'_');
    }

    /**
     *
     * @param n el número de fallos del usuario
     * @return una cadena que dibuja el ahorcado
     */
    public  String dibujoAhorcado(int n){
        switch (n){
            case 0: return " +---+\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========";
            case 1: return "+---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========";
            case 2: return "+---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    "  |   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========";
            case 3: return " +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|   |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========";
            case 4: return "+---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    "      |\n" +
                    "      |\n" +
                    "=========";
            case 5: return "+---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " /    |\n" +
                    "      |\n" +
                    "=========";
            case 6: return "+---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "=========";
        }
        return ""; //siempre se retornará uno de los valores del switch
    }

    /**
     * Imprime las letras que ya se han jugado esta partida
     */
    public void mostrarLetrasUsadas(){
        int contador = 0;
        while (contador<28 && letrasUsadas[contador] != (char)0){
            System.out.print("[" + letrasUsadas[contador] + "]");
            contador++;
        }
        System.out.println();
    }

    /**
     * Recorre el array de letras usadas para buscar si la letra introducida ya existe
     * @param c letra introducida por el usuario
     * @return <code>true</code> si la letra no ha sido introducida aun, <code>false</code> si ya existe
     */
    public boolean letraNoUsada(char c){
        int contador = 0;
        while (contador<28 && letrasUsadas[contador] != (char)0){
            if (letrasUsadas[contador] == c){
                return false;
            }
            contador++;
        }
        return true;
    }

    /**
     * Aumenta los fallos del usuario
     */
    public void usuarioFallar(){
        fallos++;
    }

    /**
     * imprime los huecos y las letras adivinadas
     */
    public void mostrarPalabraOculta(){
        for (int i = 0; i < palabraOculta.length; i++) {
            System.out.print(palabraOculta[i]);
        }
        System.out.println();
    }

    /**
     * Función que intenta añadir una letra acertada, no comprueba las letras ya usadas. Añade fallos.
     * @param c letra introducida por el usuario
     * @return <code>true</code> si la letra coincide con alguna de las letras de la palabra escogida, <code>false</code> si no hay coincidencias
     */
    public boolean anadirLetra(char c){
       boolean encontrado = false;
        for (int i = 0; i <palabraEscogida.length ; i++) {
            if (palabraEscogida[i] == c){
                encontrado = true;
                palabraOculta[i] = c;
            }
        }
        if (!encontrado){
            this.usuarioFallar();
        }
        anadirLetraUsada(c);
        return encontrado;
    }

    /**
     * Comprueba si el usuario ha ganado
     * @return <code>true</code> si todas las letras han sido acertadas, <code>false</code> en caso contrario
     */
    public boolean victoria(){
        return Arrays.equals(palabraOculta,palabraEscogida);
    }

    /**
     * Función que comprueba que el usuario haya introducido una letra del alfabeto español sin tildes
     * @param a carácter introducido por el usuario
     * @return <code>true</code> si el carácter introducido es una letra, <code>false</code> si no lo es
     */
    public boolean esLetra(String a){
        Pattern pattern = Pattern.compile("^[a-zA-zñ]{1,1}$");
        Matcher matcher = pattern.matcher(a);
        return matcher.find();
    }

    /**
     * Función que dibuja el estado de la partida;
     */
    public void mostrarEstado(){
        System.out.println("*************************************");
        System.out.println(dibujoAhorcado(getFallos()));
        System.out.println("Palabra:");
        mostrarPalabraOculta();
        System.out.println("Letras usadas:");
        mostrarLetrasUsadas();
        System.out.println("*************************************");
    }

    /**
     * Añade una letra a la lista de letras usadas
     * @param c La letra que se quiere añadir
     */
    public void anadirLetraUsada(char c){
        int contador = 0;
        boolean insertado = false;
        do {

            if (letrasUsadas[contador] == (char)0){
                letrasUsadas[contador] = c;
                insertado = true;
            }
            contador++;

        }while(contador<28 && !insertado);
    }

    public char[] getPalabraEscogida() {
        return palabraEscogida;
    }
}

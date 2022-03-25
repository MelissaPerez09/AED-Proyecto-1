/**
 * Permite hacer las operaciones.
 * @author Jimena Hernandez/21199
 * @author Mark Albrand/21004
 * @author Emily Perez/21385
 * @version 24/03/2022
 * Universidad del Valle de Guatemala
 * Algoritmos y estructuras de Datos
 * Proyecto 01 - interprete LISP
 *
 * Aritmeticos.java
 */

public class Controlador {

    //PROPIEDADES
    private static Vista vista = new Vista();
    private static Parser parser = new Parser();

    /**
    * Método Main del programa
    **/ 
    public static void main(String[] args){
        String input= "";

        vista.inicio();
        input= vista.getOperacion();

        while(!input.equals("(EXIT)")){
            if(input.equals("(HELP)")){
                vista.ayuda();
                input= vista.getOperacion();
                continue;
            }else{
            vista.mensaje(parser.parse(input));
            input= vista.getOperacion();
            }
        }
        vista.salida();
    }

}

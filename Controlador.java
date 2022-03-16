/**
 * Esta es la clase que permite hacer las operaciones.
 * Universidad del Valle de Guatemala
 * @author: Mark Albrand
 * @author: Jimena Hernández
 * @author: Emily Pérez
 * @version: 2-mar-22
 */
public class Controlador {

    /**
    * Main del programa.
    **/ 
    private static Vista vista = new Vista();
    private static Parser parser = new Parser();

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

/**
 * Guarda y ejecuta funciones personalizadas del usuario
 * @author Jimena Hernandez/21199
 * @author Mark Albrand/21004
 * @author Emily Perez/21385
 * @version 24/03/2022
 * Universidad del Valle de Guatemala
 * Algoritmos y estructuras de Datos
 * Proyecto 01 - interprete LISP
 *
 * Funciones.java
 */

 //LIBRERIAS
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Funciones {
    
    //PRODPIEDADES
    private String param;
    private String nombre;
    private ArrayList<String> procesos = new ArrayList<>();

    /**
     * (DEFUN FUN (X))
     * @param definicion
     * @param lineas
     */
    public Funciones(String definicion, ArrayList<String> lineas){
        definicion = definicion.replace("(", "");
        definicion = definicion.replace(")", "");

        String[] datos = definicion.split(" ");
        this.nombre = datos[1];
        this.param = datos[2];

        for (int i = 0; i<lineas.size(); i++){
            if(lineas.get(i) != null){
                this.procesos.add(lineas.get(i));
            }
        }
    }

    /**
     * Método para obtener nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para evaluar la función
     * @param var
     * @param funcs
     * @return resultado
     */
    public String eval(String var, ArrayList<Funciones> funcs){
        Parser localParser = new Parser();
        localParser.setFunciones(funcs);
        if(var.contains(".")){
            double resultado = 0;
            for (int i = 0; i < procesos.size(); i++) {
                String linea = procesos.get(i).replace(param, var);
                resultado += Double.parseDouble(localParser.parse(linea));
            }
            return Double.toString(resultado);
        }else if(!var.contains(".")){
            int resultado = 0;
            for (int i = 0; i < procesos.size(); i++) {
                String linea = procesos.get(i).replace(param, var);
                try{  // Operación normal
                    resultado += Integer.parseInt(localParser.parse(linea));
                }catch (Exception e){  // Operación no reconocida
                    try{
                        String resultado1 = localParser.parse(linea);
                        if(resultado1.equals("Expresión inválida. Ingrese '(EXIT)' para salir.")){
                            throw new InputMismatchException();
                        }
                    }catch (Exception f){
                        linea = linea.replace("(", "");
                        linea = linea.replace(")", "");
                        String[] detalle = linea.split(" ");


                        String func = detalle[0];

                        if(func.equals("if")){
                            String cond = "(" + detalle[1] + " " + detalle[2] + " " + detalle[3] + ")";
                            boolean condR = false;
                            try {
                                condR = Boolean.parseBoolean(localParser.parse(cond));
                            }catch (Exception g){
                                return "Uso inválido de la condicional 'IF'";
                            }

                            if(condR){
                                continue;
                            }else {
                                i++;
                            }
                        }else if (func.equals("return")){
                            return detalle[1].toString();
                        }
                    }

                }
            }
            return Integer.toString(resultado);
        }
        return "No se pudo evaluar la función";
    }
}

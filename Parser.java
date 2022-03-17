/**
 * Esta es la clase que evalúa una operación del lenguaje LISP
 * Universidad del Valle de Guatemala
 * @author: Mark Albrand
 * @author: Jimena Hernández
 * @author: Emily Pérez
 * @version: 2-mar-22
 */

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private HashMap<String, String> variables = new HashMap<String, String>();  // Almacenamiento de las variables personales

    /**
     * Método principal para evaluar una línea a código LISP
     * @param linea Línea ingresada por el usuario
     * @return El resultado de la operación en formato String
     */
    public String parse(String linea){
        Pattern pattern;
        Matcher matcher;

        // Operaciones aritméticas simples
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [0-9.]+ [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];
            String b = datos[2];

            if(Objects.equals(datos[0], "+")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.add(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.add(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "-")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.sub(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.sub(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "/")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.div(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.div(x, y);

                    return Integer.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "*")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);

                    double resultado = Aritmeticos.mult(x, y);

                    return Double.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);

                    int resultado = Aritmeticos.mult(x, y);

                    return Integer.toString(resultado);
                }
            }
        }

        // Operaciones aritméticas simples (Una variable)
        pattern = Pattern.compile("^[(]{1}[+\\-]{2} [0-9]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];

            if(Objects.equals(datos[0], "++")){
                if(a.contains(".")){
                    double x = Double.parseDouble(a);

                    double resultado = Aritmeticos.incr(x);

                    return Double.toString(resultado);
                }else{
                    int x = Integer.parseInt(a);

                    int resultado = Aritmeticos.incr(x);

                    return Integer.toString(resultado);
                }
            }else if (Objects.equals(datos[0], "--")){
                if(a.contains(".")){
                    double x = Double.parseDouble(a);

                    double resultado = Aritmeticos.decr(x);

                    return Double.toString(resultado);
                }else{
                    int x = Integer.parseInt(a);

                    int resultado = Aritmeticos.decr(x);

                    return Integer.toString(resultado);
                }
            }
        }

        //operaciones dentro de operaciones con dos paréntesis a la derecha
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [0-9]+ [(]{1}.+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex
        matcher = pattern.matcher(linea);
        
        if(matcher.find()){
            
        }

        //operaciones dentro de operaciones con dos paréntesis a la izquierda
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [(][+*\\-/] [0-9.]+ [0-9.]+[)]{1} [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex
        matcher = pattern.matcher(linea);

        if (matcher.find()){

        }

        //operaciones dentro de operaciones con dos paréntesis
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [(]{1}[+*\\-/] + [0-9.]+ [0-9.][)]{1} [(]{1}[+*\\-/] [0-9.]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex
        matcher = pattern.matcher(linea);

        if (matcher.find()){

        }
        

        return "Expresión inválida. Ingrese '(EXIT)' para salir.";
    }
}

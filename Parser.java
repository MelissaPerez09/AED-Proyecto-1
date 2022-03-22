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
        pattern = Pattern.compile("^[(]{1}[\\-+\\*/] [0-9.]+ [(]{1}.+[)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);
        
        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];
            String b = datos[3];
            String c = datos[4];

            //opera números decimales
            if(a.contains(".") || b.contains(".") || c.contains(".")){
                String suboperacion = "(" + datos[2] + " " + datos[3] + " " + datos[4] + ")";
                String resultado = parse(suboperacion);
                String operacionF = "(" + datos[0] + " " + datos[1] + " " + resultado + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;

            //opera números enteros
            } else{
                String suboperacion = "(" + datos[2] + " " + datos[3] + " " + datos[4] + ")";
                String resultado = parse(suboperacion);
                String operacionF = "(" + datos[0] + " " + datos[1] + " " + resultado + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;
            }
        }

        

        // Operaciones lógicas simples. Mayor o menor
        pattern = Pattern.compile("^[(]{1}[<>]{1}[=]{0,1} [0-9.]+ [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación lógica simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];
            String b = datos[2];
            if(Objects.equals(datos[0], "<")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    boolean resultado = Logicas.less(x, y);
                    return Boolean.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);
                    boolean resultado = Logicas.less(x, y);
                    return Boolean.toString(resultado);
                }
            } else if(Objects.equals(datos[0], ">")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    boolean resultado = Logicas.greater(x, y);
                    return Boolean.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);
                    boolean resultado = Logicas.greater(x, y);
                    return Boolean.toString(resultado);
                }
            } else if(Objects.equals(datos[0], "<=")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    boolean resultado = Logicas.lessEq(x, y);
                    return Boolean.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);
                    boolean resultado = Logicas.lessEq(x, y);
                    return Boolean.toString(resultado);
                }
            } else if(Objects.equals(datos[0], ">=")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    boolean resultado = Logicas.greaterEq(x, y);
                    return Boolean.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);
                    boolean resultado = Logicas.greaterEq(x, y);
                    return Boolean.toString(resultado);
                }
            }
        }

        // Operaciones lógicas simples. Igual a
        pattern = Pattern.compile("^[(]{1}[=]{2} [0-9.]+ [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación lógica simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[1];
            String b = datos[2];
            if(Objects.equals(datos[0], "==")){
                if(a.contains(".") || b.contains(".")){
                    // Número con decimales
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    boolean resultado = Logicas.equal(x, y);
                    return Boolean.toString(resultado);
                } else{
                    // Número entero
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);
                    boolean resultado = Logicas.equal(x, y);
                    return Boolean.toString(resultado);
                }
            }
        }

        // Definición de variables
        pattern = Pattern.compile("[(]{1}let [A-z]+ [0-9.]+|[\"]+[A-z]+[\"]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una definición de variable
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");

            String[] datos = linea.split(" ");
            // 0: let ; 1: nombre ; 2: valor
            String nombre = datos[1];
            String valor = datos[2];

            variables.put(nombre, valor);

            return ("Se ha asignado correctamente " + nombre + " con el valor " + valor);

        }
        //operaciones dentro de operaciones con dos paréntesis a la izquierda
        pattern = Pattern.compile("^[(]{1}[\\-+\\*/]  [(][\\-+\\*/]  [0-9.]+ [0-9.]+[)]{1} [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);
        if (matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[2];
            String b = datos[3];
            String c = datos[4];

            //opera números decimales
            if(a.contains(".") || b.contains(".") || c.contains(".")){
                String suboperacion = "(" + datos[1] + " " + datos[2] + " " + datos[3] + ")";
                String resultado = parse(suboperacion);
                String operacionF = "(" + datos[0] + " " + resultado + " " + datos[4] + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;

            //opera números enteros
            } else{
                String suboperacion = "(" + datos[1] + " " + datos[2] + " " + datos[3] + ")";
                String resultado = parse(suboperacion);
                String operacionF = "(" + datos[0] + " " + resultado + " " + datos[4] + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;
            }
        }

        //operaciones dentro de operaciones con dos paréntesis
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [0-9.][)]{1} [(]{1}[+*-\\/] [0-9.]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex
        matcher = pattern.matcher(linea);

        if (matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String a = datos[2];
            String b = datos[3];
            String c = datos[5];
            String d = datos[6];
            
            //opera números decimales
            if(a.contains(".") || b.contains(".") || c.contains(".") || d.contains(".")){
                String suboperacion1 = "(" + datos[1] + " " + datos[2] + " " + datos[3] + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + datos[4] + " " + datos[5] + " " + datos[6] + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " +  resultado2 + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;

            //opera números enteros
            } else{
                String suboperacion1 = "(" + datos[1] + " " + datos[2] + " " + datos[3] + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + datos[4] + " " + datos[5] + " " + datos[6] + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " +  resultado2 + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;
            }
        }
        
        return "Expresión inválida. Ingrese '(EXIT)' para salir.";
    }


}

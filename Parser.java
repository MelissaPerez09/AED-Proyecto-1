/**
 * Esta es la clase que evalúa una operación del lenguaje LISP
 * Universidad del Valle de Guatemala
 * @author Mark Albrand
 * @author Jimena Hernández
 * @author Emily Pérez
 * @version 2-mar-22
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private HashMap<String, String> variables = new HashMap<String, String>();  // Almacenamiento de las variables personales
    private ArrayList<Funciones> funciones = new ArrayList<Funciones>();
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
        pattern = Pattern.compile("[(]{1}setq [A-z]+ [0-9.]+|[\"]+[A-z]+[\"]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex para una definición de variable
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


        //operaciones dentro de operaciones con dos paréntesis a la izquierda
        pattern = Pattern.compile("^[(]{1}[\\-+\\*/] [(][\\-+\\*/] [0-9.]+ [0-9.]+[)]{1} [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
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
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex
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

        // **************************************************************************************************** //
        // **************************************** Variables ************************************************* //
        // **************************************************************************************************** //

        // Operaciones aritméticas simples. Variable a la izquierda
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [A-z]+ [0-9.]+[)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable = datos[1];
            String operando = datos[2];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String newOp = "(" + operacion + " " + valorVariable + " " + operando + ")";
                return parse(newOp);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Operaciones dentro de operaciones. Una variable
        // Variable en posición 1
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable = datos[2];
            String b = datos[3];
            String c = datos[5];
            String d = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable)) {
                String valorVariable = variables.get(variable);
                String suboperacion1 = "(" + operando1 + " " + valorVariable + " " + b + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + c + " " + d + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }
        //operaciones dentro de operaciones. Dos paréntesis a la derecha. Variable en posición 1.
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [A-z.]+ [(]{1}[+*-/]+ [0-9.]+ [0-9.][)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable = datos[1];
            String operador = datos[2];
            String num1 = datos[3];
            String num2 = datos[4];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String suboperacion = "(" + operador + " " + num1 + " " + num2 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + valorVariable + " " + resultado + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }


        //operaciones dentro de operaciones. Dos paréntesis a la derecha. Variable en posición 3.
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [0-9.]+ [(]{1}[+*-/]+ [A-z.]+ [0-9.][)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String num1 = datos[1];
            String operador = datos[2];
            String variable = datos[3];
            String num2 = datos[4];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String suboperacion = "(" + operador + " " + valorVariable + " " + num2 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + num1 + " " + resultado + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }
        // Operaciones aritméticas simples. Variable a la derecha
        pattern = Pattern.compile("^^[(]{1}[+*-/]{1} [0-9.]+ [A-z.]+[)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operando = datos[1];
            String variable = datos[2];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String newOp = "(" + operacion + " " + operando + " " + valorVariable + ")";
                return parse(newOp);
            
            }else{
                return (variable + " no está definida.");
            }
        }

        // Variable en posición 2
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable = datos[3];
            String a = datos[2];
            String c = datos[5];
            String d = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable)) {
                String valorVariable = variables.get(variable);
                String suboperacion1 = "(" + operando1 + " " + a + " " + valorVariable + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + c + " " + d + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else {
                return (variable + " no está definida.");
            }
        }
        //operaciones dentro de operaciones. Dos paréntesis a la derecha. Variable en posición 4.
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [0-9.]+ [(]{1}[+*-/]+ [0-9.]+ [A-z.][)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String num1 = datos[1];
            String operador = datos[2];
            String num2 = datos[3];
            String variable = datos[4];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String suboperacion = "(" + operador + " " + num2 + " " + valorVariable + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + num1 + " " + resultado + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Variable en posición 3
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable = datos[5];
            String a = datos[2];
            String b = datos[3];
            String d = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable)) {
                String valorVariable = variables.get(variable);
                String suboperacion1 = "(" + operando1 + " " + a + " " + b + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable + " " + d + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else {
                return (variable + " no está definida.");
            }
        }
        //operaciones dentro de operaciones. Dos paréntesis a la derecha. Dos variables [1], [3]
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [A-z.]+ [(]{1}[+*-/]+ [A-z.]+ [0-9.][)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable1 = datos[1];
            String operador = datos[2];
            String variable2 = datos[3];
            String num1 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String suboperacion = "(" + operador + " " + valorVariable2 + " " + num1 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + valorVariable1 + " " + resultado + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la derecha. Dos variables [3], [4]
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [0-9.]+ [(]{1}[+*-/]+ [A-z.]+ [A-z.][)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String num1 = datos[1];
            String operador = datos[2];
            String variable1 = datos[3];
            String variable2 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String suboperacion = "(" + operador + " " + valorVariable1 + " " + valorVariable2 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + num1 + " " + resultado + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la derecha. Dos variables [1], [4]
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [A-z.]+ [(]{1}[+*-/]+ [0-9.]+ [A-z.][)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable1 = datos[1];
            String operador = datos[2];
            String num1 = datos[3];
            String variable2 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String suboperacion = "(" + operador + " " + num1 + " " + valorVariable2 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + valorVariable1 + " " + resultado + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la derecha. Tres variables [1], [3], [4]
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [A-z.]+ [(]{1}[+*-/]+ [A-z.]+ [A-z.][)]{2}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable1 = datos[1];
            String operador = datos[2];
            String variable2 = datos[3];
            String variable3 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String valorVariable3 = variables.get(variable3);
                String suboperacion = "(" + operador + " " + valorVariable2 + " " + valorVariable3 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + valorVariable1 + " " + resultado + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la IZQUIERDA. Variable en posición 2.
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [(]{1}[+*-/] [A-z.]+ [0-9.]+[)]{1} +[0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operador = datos[1];
            String variable = datos[2];
            String num1 = datos[3];
            String num2 = datos[4];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String suboperacion = "(" + operador + " " + valorVariable + " " + num1 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + resultado + " " + num2 + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Variable en posición 4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable = datos[6];
            String a = datos[2];
            String b = datos[3];
            String c = datos[5];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable)) {
                String valorVariable = variables.get(variable);
                String suboperacion1 = "(" + operando1 + " " + a + " " + b + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + c + " " + valorVariable + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }
        //operaciones dentro de operaciones. Dos paréntesis a la IZQUIERDA. Variable en posición 3.
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [(]{1}[+*-/] [0-9.]+ [A-z.]+[)]{1} +[0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operador = datos[1];
            String num1 = datos[2];
            String variable = datos[3];
            String num2 = datos[4];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String suboperacion = "(" + operador + " " + num1 + " " + valorVariable + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + resultado + " " + num2 + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Operaciones aritméticas simples. Variable a ambos lados
        pattern = Pattern.compile("^[(]{1}[+*-/]{1} [A-z.]+ [A-z.]+[)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable1 = datos[1];
            String variable2 = datos[2];

            if(variables.containsKey(variable1)&&variables.containsKey(variable2) ){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String newOp = "(" + operacion + " " + valorVariable1 + " " + valorVariable2 + ")";
                return parse(newOp);
            }else{
                return (variable1 +"y "+ variable2 + " no está definida.");
            }
        }
        //operaciones dentro de operaciones. Dos paréntesis a la IZQUIERDA. Variable en posición 4.
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [(]{1}[+*-/] [0-9.]+ [0-9.]+[)]{1} +[A-z.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operador = datos[1];
            String num1 = datos[2];
            String num2 = datos[3];
            String variable = datos[4];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String suboperacion = "(" + operador + " " + num1 + " " + num2 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + resultado + " " + valorVariable + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else{
                return (variable + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la IZQUIERDA. Dos variables [2], [3]
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [(]{1}[+*-/] [A-z.]+ +[A-z.][)]{1} +[0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operador = datos[1];
            String variable1 = datos[2];
            String variable2 = datos[3];
            String num1 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String suboperacion = "(" + operador + " " + valorVariable1 + " " + valorVariable2 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + resultado + " " + num1 + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la IZQUIERDA. Dos variables [3], [4]
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [(]{1}[+*-/] [0-9.]+ +[A-z.][)]{1} +[A-z.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operador = datos[1];
            String num1 = datos[2];
            String variable1 = datos[3];
            String variable2 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String suboperacion = "(" + operador + " " + num1 + " " + valorVariable1 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + resultado + " " + valorVariable2 + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la IZQUIERDA. Dos variables [2], [4]
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [(]{1}[+*-/] [A-z.]+ +[0-9.][)]{1} +[A-z.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operador = datos[1];
            String variable1 = datos[2];
            String num1 = datos[3];
            String variable2 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String suboperacion = "(" + operador + " " + valorVariable1 + " " + num1 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + resultado + " " + valorVariable2 + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        //operaciones dentro de operaciones. Dos paréntesis a la IZQUIERDA. Dos variables [2], [3], [4]
        pattern = Pattern.compile("^[(]{1}[+*-/]+ [(]{1}[+*-/] [A-z.]+ +[A-z.][)]{1} +[A-z.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  //Regex
        matcher = pattern.matcher(linea);

        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String operador = datos[1];
            String variable1 = datos[2];
            String variable2 = datos[3];
            String variable3 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2)){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String valorVariable3 = variables.get(variable3);
                String suboperacion = "(" + operador + " " + valorVariable1 + " " + valorVariable2 + ")"; //evalúa el paréntesis
                String resultado = parse(suboperacion); //guarda el resultado del paréntesis
                String operacionF = "(" + operacion + " " + resultado + " " + valorVariable3 + ")"; //opera el resultado del paréntesis con el valor de la variable
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
            return "Expresión inválida. Ingrese '(EXIT)' para salir.";

        }

    

        // Operaciones aritméticas simples una variable
        pattern = Pattern.compile("^[(]{1}[+*-/]{2} [A-z.]+[)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
    
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
    
            String operacion = datos[0];
            String variable = datos[1];

    
            if(variables.containsKey(variable)){
                String valorVariable1 = variables.get(variable);
                String newOp = "(" + operacion + " " + valorVariable1 + ")";
                return parse(newOp);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Dos variables
        // Variable en posición 1 y 2
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[2];
            String variable2 = datos[3];
            String c = datos[5];
            String d = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);

                String suboperacion1 = "(" + operando1 + " " + valorVariable1 + " " + valorVariable2 + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + c + " " + d + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        // Variable en posición 1 y 3
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[2];
            String variable2 = datos[5];
            String b = datos[3];
            String d = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);

                String suboperacion1 = "(" + operando1 + " " + valorVariable1 + " " + b + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable2 + " " + d + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }
        // Variable en posición 1 y 4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[2];
            String variable2 = datos[6];
            String b = datos[3];
            String c = datos[5];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);

                String suboperacion1 = "(" + operando1 + " " + valorVariable1 + " " + b + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + c + " " + valorVariable2 + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }
        // Variable en posición 2 y 4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[3];
            String variable2 = datos[6];
            String a = datos[2];
            String c = datos[5];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);

                String suboperacion1 = "(" + operando1 + " " + a + " " + valorVariable1 + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + c + " " + valorVariable2 + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }
        // Variable en posición 3 y 4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[5];
            String variable2 = datos[6];
            String a = datos[2];
            String b = datos[3];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);

                String suboperacion1 = "(" + operando1 + " " + a + " " + b + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable1 + " " + valorVariable2 + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }
        // Variable en posición 2 y 3
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[3];
            String variable2 = datos[5];
            String a = datos[2];
            String d = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);

                String suboperacion1 = "(" + operando1 + " " + a + " " + valorVariable1 + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable2 + " " + d + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }
        }

        // 3 variables
        // Variable en posición 1-2-3
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[2];
            String variable2 = datos[3];
            String variable3 = datos[5];
            String d = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2) && variables.containsKey(variable3)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String valorVariable3 = variables.get(variable3);

                String suboperacion1 = "(" + operando1 + " " + valorVariable1 + " " + valorVariable2 + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable3 + " " + d + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if(!variables.containsKey(variable3)){
                return (variable3 + " no está definida");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable2 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable2 + " no está definida. " + variable3 + " no está definida.");
            }
        }
        // Variable en posición 1-2-4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [0-9.]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[2];
            String variable2 = datos[3];
            String variable3 = datos[6];
            String c = datos[5];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2) && variables.containsKey(variable3)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String valorVariable3 = variables.get(variable3);

                String suboperacion1 = "(" + operando1 + " " + valorVariable1 + " " + valorVariable2 + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + c + " " + valorVariable3 + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if(!variables.containsKey(variable3)){
                return (variable3 + " no está definida");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable2 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable2 + " no está definida. " + variable3 + " no está definida.");
            }
        }
        // Variable en posición 1-3-4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [0-9.]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[2];
            String variable2 = datos[5];
            String variable3 = datos[6];
            String b = datos[3];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2) && variables.containsKey(variable3)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String valorVariable3 = variables.get(variable3);

                String suboperacion1 = "(" + operando1 + " " + valorVariable1 + " " + b + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable2 + " " + valorVariable3 + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if(!variables.containsKey(variable3)){
                return (variable3 + " no está definida");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable2 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable2 + " no está definida. " + variable3 + " no está definida.");
            }
        }
        // Variable en posición 2-3-4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [0-9.]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[3];
            String variable2 = datos[5];
            String variable3 = datos[6];
            String a = datos[2];
            String operando1 = datos[1];
            String operando2 = datos[4];

            //opera números decimales
            if (variables.containsKey(variable1) && variables.containsKey(variable2) && variables.containsKey(variable3)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String valorVariable3 = variables.get(variable3);

                String suboperacion1 = "(" + operando1 + " " + a + " " + valorVariable1 + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable2 + " " + valorVariable3 + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if(!variables.containsKey(variable3)){
                return (variable3 + " no está definida");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable2 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable2 + " no está definida. " + variable3 + " no está definida.");
            }
        }

        // 4 variables
        // Variable en posición 2-3-4
        pattern = Pattern.compile("^[(]{1}[+*-\\/] [(]{1}[+*-\\/]+ [A-z]+ [A-z]+[)]{1} [(]{1}[+*-\\/] [A-z]+ [A-z]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);

        if(matcher.find()) {
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
            String variable1 = datos[2];
            String variable2 = datos[3];
            String variable3 = datos[5];
            String variable4 = datos[6];
            String operando1 = datos[1];
            String operando2 = datos[4];

            if (variables.containsKey(variable1) && variables.containsKey(variable2) && variables.containsKey(variable3) && variables.containsKey(variable4)) {
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String valorVariable3 = variables.get(variable3);
                String valorVariable4 = variables.get(variable4);

                String suboperacion1 = "(" + operando1 + " " + valorVariable1 + " " + valorVariable2 + ")";
                String resultado1 = parse(suboperacion1);
                String suboperacion2 = "(" + operando2 + " " + valorVariable3 + " " + valorVariable4 + ")";
                String resultado2 = parse(suboperacion2);
                String operacionF = "(" + datos[0] + " " + resultado1 + " " + resultado2 + ")";
                return parse(operacionF);
            }else if(!variables.containsKey(variable1)){
                return (variable1 + " no está definida.");
            }else if(!variables.containsKey(variable2)){
                return (variable2 + " no está definida.");
            }else if(!variables.containsKey(variable3)){
                return (variable3 + " no está definida");
            }else if (!variables.containsKey(variable1) && !variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable2 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable2)){
                return (variable1 + " no está definida. " + variable2 + " no está definida.");
            }else if(!variables.containsKey(variable1) && !variables.containsKey(variable3)){
                return (variable1 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable2) && !variables.containsKey(variable3)){
                return (variable2 + " no está definida. " + variable3 + " no está definida.");
            }else if(!variables.containsKey(variable2) && !variables.containsKey(variable3) && !variables.containsKey(variable3) && !variables.containsKey(variable4)){
                return (variable1 + " no está definida. " + variable2 + " no está definida. " + variable3 + " no está definida. " + variable4 + " no está definida.");
            }
        }
        // Operaciones lógicas simples dos variables 
        pattern = Pattern.compile("^[(]{1}[<>]{1}[=]{0,1} [A-z.] [A-z.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
    
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
    
            String operacion = datos[0];
            String variable1 = datos[1];
            String variable2 = datos[2];

    
            if(variables.containsKey(variable1)&& (variables.containsKey(variable2))){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String newOp = "(" + operacion + " " + valorVariable1+ " " + valorVariable2+ ")";
                return parse(newOp);
            }else{
                return (variable1 + " no está definida.");
            }
        }

 // Operaciones lógicas simples dos variables 
        pattern = Pattern.compile("^[(]{1}[<>]{1}[=]{0,1} [A-z.] [A-z.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
    
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
    
            String operacion = datos[0];
            String variable1 = datos[1];
            String variable2 = datos[2];

    
            if(variables.containsKey(variable1)&& (variables.containsKey(variable2))){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String newOp = "(" + operacion + " " + valorVariable1+ " " + valorVariable2+ ")";
                return parse(newOp);
            }else{
                return (variable1 + " no está definida.");
            }
        }

         // Operaciones lógicas simples dos variables 
        pattern = Pattern.compile("^[(]{1}[<>]{1}[=]{0,1} [A-z.] [A-z.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
    
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");
    
            String operacion = datos[0];
            String variable1 = datos[1];
            String variable2 = datos[2];

    
            if(variables.containsKey(variable1)&& (variables.containsKey(variable2))){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String newOp = "(" + operacion + " " + valorVariable1+ " " + valorVariable2+ ")";
                return parse(newOp);
            }else{
                return (variable1 + " no está definida.");
            }
        }
    
        // Operaciones lógicas simples variable derecha
        pattern = Pattern.compile("^^[(]{1}[<>]{1}[=]{0,1} [0-9.] [A-z.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String dato = datos[1];
            String variable = datos[2];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String newOp = "(" + operacion + " " + dato+ " " + valorVariable+ ")";
                return parse(newOp);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Operaciones lógicas  variable izquierda
        pattern = Pattern.compile("^[(]{1}[<>]{1}[=]{0,1} [A-z.] [0-9.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable = datos[1];
            String dato = datos[2];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String newOp = "(" + operacion + " " + valorVariable+ " " + dato+ ")";
                return parse(newOp);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Operaciones lógicas simples. == con variable izquierda
        pattern = Pattern.compile("^[(]{1}[=]{2} [A-z.] [0-9.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable = datos[1];
            String dato = datos[2];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String newOp = "(" + operacion + " " + valorVariable+ " " + dato+ ")";
                return parse(newOp);
            }else{
                return (variable + " no está definida.");
            }
        }

        // Operaciones lógicas simples. == con variable derecha
        pattern = Pattern.compile("^[(]{1}[=]{2} [0-9.] [A-z.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String dato = datos[1];
            String variable = datos[2];

            if(variables.containsKey(variable)){
                String valorVariable = variables.get(variable);
                String newOp = "(" + operacion + " " + dato+ " " + valorVariable+ ")";
                return parse(newOp);
            }else{
                return (variable + " no está definida.");
            }
        }


        // Operaciones lógicas simples. == con 2 variables
        pattern = Pattern.compile("^[(]{1}[=]{2} [A-z.] [A-z.][)]{1}", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
            if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String operacion = datos[0];
            String variable1 = datos[1];
            String variable2 = datos[2];

            if(variables.containsKey(variable1)&& (variables.containsKey(variable2))){
                String valorVariable1 = variables.get(variable1);
                String valorVariable2 = variables.get(variable2);
                String newOp = "(" + operacion + " " + valorVariable1+ " " + valorVariable2+ ")";
                return parse(newOp);
            }else{
                return (variable1+" y "+ variable2 + " no están definidas.");
            }
        }

         // **************************************************************************************************** //
        // **************************************** Función ************************************************* //
        // **************************************************************************************************** //

         // Definicion de funciones
        pattern = Pattern.compile("^[(]{1}defun [A-z]+ [(][A-z.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
        if(matcher.find()){
            ArrayList<String> nuevalinea = Vista.getLinea();
            Funciones funcion = new Funciones(linea, nuevalinea);
            funciones.add(funcion);
            return "Se definió la función";
        }

        //Uso de funciones personalizadas
        pattern = Pattern.compile("^[(]{1}[A-z]+ [(][0-9.]+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String funcion = datos[0];
            String variable = datos[1];

            for (int i = 0; i < funciones.size(); i++) {
                if (funciones.get(i).getNombre().equals(funcion)){
                    return funciones.get(i).eval(variable, funciones);
                }
            }

            return "La función no está definida";
        }

        //Uso de funciones personalizadas
        pattern = Pattern.compile("^[(]{1}[+\\-*/]{1} [(]{1}[A-z]+ [(][+\\-*/]{1} [0-9.]+ [0-9.]+[)]{2} [(]{1}[A-z]+ [(][+\\-*/]{1} [0-9.]+ [0-9.]+[)]{3}$", Pattern.CASE_INSENSITIVE);  // Regex para una operación simple
        matcher = pattern.matcher(linea);
        if(matcher.find()){
            linea = linea.replace("(", "");
            linea = linea.replace(")", "");
            String[] datos = linea.split(" ");

            String funcion1 = datos[1];
            String variable1 = datos[3];
            String funcion2 = datos[5];
            String variable2 = datos[7];

            String r1 = "";
            String r2 = "";

            for (int i = 0; i < funciones.size(); i++) {
                if (funciones.get(i).getNombre().equals(funcion1)){
                    String var1 = "(" + datos[2] + " " + datos[3] + " " + datos[4] + ")";
                    var1 = parse(var1);
                    String op1 = "(" + funcion1 + " (" + var1 + "))";
                    r1 = parse(op1);
                }
            }

            for (int i = 0; i < funciones.size(); i++) {
                if (funciones.get(i).getNombre().equals(funcion2)){
                    String var2 = "(" + datos[6] + " " + datos[7] + " " + datos[8] + ")";
                    var2 = parse(var2);
                    String op2 = "(" + funcion2 + " (" + var2 + "))";
                    r2 = parse(op2);
                }
            }

            String opF = "(" + datos[0] + " " + r1 + " " + r2 + ")";
            return parse(opF);



            //return "La función no está definida";
        }


        return "Expresión inválida. Ingrese '(EXIT)' para salir.";
    }

    public void setFunciones(ArrayList<Funciones> funciones) {
        this.funciones = funciones;
    }
}

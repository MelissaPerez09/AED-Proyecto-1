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

import javax.naming.spi.DirStateFactory.Result;

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
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [0-9.]+ [(]{1}.+[)]{2}$", Pattern.CASE_INSENSITIVE);  // Regex
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
                String operacionF = "(" + datos[0] + " " + datos[1] + resultado + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;
            }
        }

        //operaciones dentro de operaciones con dos paréntesis a la izquierda
        pattern = Pattern.compile("^[(]{1}[+*\\-/] [(][+*\\-/] [0-9.]+ [0-9.]+[)]{1} [0-9.]+[)]{1}$", Pattern.CASE_INSENSITIVE);  // Regex
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
                String operacionF = "(" + datos[0] + " " + "(" + " " + resultado + ")" + " " + datos[3] + ")";
                String resultadoF = parse(operacionF);
                return resultadoF;

            //opera números enteros
            } else{
                String suboperacion = "(" + datos[1] + " " + datos[2] + " " + datos[3] + ")";
                String resultado = parse(suboperacion);
                String operacionF = "(" + datos[0] + " " + "(" + " " + resultado + ")" + " " + datos[3] + ")";
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
            
            //evalúa el primer paréntesis con suma
            if(Objects.equals(datos[1], "+")){
                if(a.contains(".") || b.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    double resultado = Aritmeticos.add(x, y);
                    return Double.toString(resultado);

                } else{
                    // Número entero
                    Integer x = Integer.parseInt(a);
                    Integer y = Integer.parseInt(b);
                    Integer resultado1 = Aritmeticos.add(x, y);

                    //evalúa el segundo paréntesis
                    if(Objects.equals(datos[4], "+")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.add(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "-")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.sub(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "*")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.mult(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "/")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.div(z, w);
                       
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                }
            }
            //evalúa el primer paréntesis con resta
            if(Objects.equals(datos[1], "-")){
                if(a.contains(".") || b.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    double resultado = Aritmeticos.sub(x, y);
                    return Double.toString(resultado);

                } else{
                    // Número entero
                    Integer x = Integer.parseInt(a);
                    Integer y = Integer.parseInt(b);
                    Integer resultado1 = Aritmeticos.sub(x, y);

                    //evalúa el segundo paréntesis
                    if(Objects.equals(datos[4], "+")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.add(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "-")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.sub(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "*")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.mult(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "/")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.div(z, w);
                       
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                }
            }

            //evalúa el primer paréntesis con división
            if(Objects.equals(datos[1], "/")){
                if(a.contains(".") || b.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    double resultado = Aritmeticos.div(x, y);
                    return Double.toString(resultado);

                } else{
                    // Número entero
                    Integer x = Integer.parseInt(a);
                    Integer y = Integer.parseInt(b);
                    Integer resultado1 = Aritmeticos.div(x, y);

                    //evalúa el segundo paréntesis
                    if(Objects.equals(datos[4], "+")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.add(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "-")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.sub(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "*")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.mult(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "/")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.div(z, w);
                       
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                }
            }

            //evalúa el primer paréntesis con multiplicación
            if(Objects.equals(datos[1], "*")){
                if(a.contains(".") || b.contains(".")){
                    //*******FALTA POR CORREGIR*********/
                    double x = Double.parseDouble(a);
                    double y = Double.parseDouble(b);
                    double resultado = Aritmeticos.mult(x, y);
                    return Double.toString(resultado);

                } else{
                    // Número entero
                    Integer x = Integer.parseInt(a);
                    Integer y = Integer.parseInt(b);
                    Integer resultado1 = Aritmeticos.mult(x, y);

                    //evalúa el segundo paréntesis
                    if(Objects.equals(datos[4], "+")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.add(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "-")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.sub(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "*")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.mult(z, w);
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                    if(Objects.equals(datos[4], "/")){
                        Integer z = Integer.parseInt(c);
                        Integer w = Integer.parseInt(d);
                        Integer resultado2 = Aritmeticos.div(z, w);
                       
                        //evalúa ambos paréntesis
                        if(Objects.equals(datos[0], "+")){
                            Integer resultadoF = Aritmeticos.add(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "-")){
                            Integer resultadoF = Aritmeticos.sub(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "*")){
                            Integer resultadoF = Aritmeticos.mult(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                        if(Objects.equals(datos[0], "/")){
                            Integer resultadoF = Aritmeticos.div(resultado1, resultado2);
                            return Integer.toString(resultadoF);
                        }
                    }
                }
            }
        }
        
        return "Expresión inválida. Ingrese '(EXIT)' para salir.";
    }
}

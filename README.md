# Algoritmos y Estructuras de Datos
## Proyecto 1
### Intérprete para lisp

Se realizó un Intérprete del lenguaje Common Lisp utilizando Java. 

Este intérprete permite ejecutar operaciones de Lisp y obtener los resultados en tiempo real, sin necesidad de compilarlo.

## Integrantes

```diff
+ Mark Albrand/21004
+ Jimena Hernández/21199
+ Emily Pérez/21385
```

## Índice
1. [Diagramas UML](https://github.com/markalbrand56/AED-Proyecto-1/tree/build/UML)
2. [Pruebas unitarias](https://github.com/markalbrand56/AED-Proyecto-1/blob/build/ParserTest.java)

## Ejemplos

### Realizar una operación aritmética
`(- 1.0 2)`

### Definir una variable
`(setq var 2)`

### Definir una función. Fibonacci
`(defun fib (x))`

`(if (<= x 1))`

`(return n)`

`(+ (fib (- x 1)) (fib (- x 2)))`

`(END)`

## Restricciones
1. La condicional `if` es exclusiva para las funciones, la siguiente línea debe de ser su return. La siguiente a esta debe ser el return en un caso `else` (sin esta palabra)
2. Las funciones deben llevar un parámetro3. 

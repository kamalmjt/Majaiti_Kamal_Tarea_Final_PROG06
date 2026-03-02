# Gestión de Biblioteca - Proyecto Java

Aplicación de consola para la administración de un catálogo de libros. El programa permite gestionar el inventario de una biblioteca, controlar los préstamos y asegurar la persistencia de los datos mediante archivos externos.

## Funcionalidades principales

El sistema ofrece las siguientes operaciones a través de una interfaz de comandos:

- Alta de nuevos ejemplares: Registro de libros con ISBN, título y autor.
- Gestión de inventario: Visualización de todos los libros registrados y su estado actual.
- Préstamos y devoluciones: Actualización del estado de disponibilidad de los ejemplares mediante su ISBN.
- Búsqueda optimizada: Localización inmediata de libros utilizando una estructura de datos indexada.
- Persistencia CSV: Carga automática al iniciar y guardado de datos en un archivo de valores separados por punto y coma.

## Estructura del proyecto

El código se organiza en las siguientes clases:

- MainCli: Punto de entrada de la aplicación y lógica del menú de usuario.
- Biblioteca: Motor del sistema que gestiona la lógica de almacenamiento (ArrayList) y de indexación (HashMap).
- Libro: Clase de entidad que define las propiedades y el comportamiento de cada ejemplar.
- FicheroLibros: Módulo encargado del tratamiento de entrada/salida de archivos y el parseo del CSV.
- Utilidades: Métodos auxiliares para la validación de tipos y el filtrado de entradas mediante expresiones regulares.

## Requisitos y ejecución

Para ejecutar el programa es necesario tener instalado el JDK (Java Development Kit).

1. Compilación:
   javac *.java

2. Ejecución:
   java MainCli

El programa buscará el archivo "datosBiblioteca.csv" en la raíz del directorio para cargar la información previa. Si el archivo no existe, se creará uno nuevo al seleccionar la opción de guardado.

## Detalles técnicos

- Validaciones: El sistema utiliza Regex para asegurar que los campos de texto y códigos ISBN no contengan caracteres no permitidos.
- Eficiencia: Se utiliza un HashMap para las búsquedas por ISBN, garantizando un tiempo de respuesta constante independientemente del volumen de libros.
- Tratamiento de errores: Control de excepciones en la entrada de datos por teclado y en la manipulación de ficheros para evitar cierres inesperados del programa.

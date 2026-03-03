# Gestión de Biblioteca - Proyecto Java

Aplicación para la administración de un catálogo de libros con soporte para interfaces de consola (CLI) y gráfica (GUI). El programa permite gestionar el inventario, controlar los préstamos y asegurar la persistencia de los datos mediante archivos CSV.

## Funcionalidades principales

El sistema ofrece las siguientes operaciones:

- **Alta de nuevos ejemplares:** Registro de libros con ISBN, título y autor.
- **Gestión de inventario:** Visualización de todos los libros registrados y su estado actual (Disponible/No disponible).
- **Préstamos y devoluciones:** Actualización del estado de los ejemplares mediante su ISBN.
- **Búsqueda optimizada:** Localización de libros utilizando un índice basado en HashMap para mayor velocidad.
- **Persistencia CSV:** Carga y guardado de datos en un archivo de valores separados por punto y coma.

## Estructura del proyecto

El código se organiza en las siguientes clases:

- **MainCli:** Interfaz de línea de comandos basada en menús.
- **MainGUI:** Interfaz gráfica moderna desarrollada con Swing.
- **Biblioteca:** Motor del sistema que gestiona la lógica de almacenamiento (ArrayList) y de indexación (HashMap).
- **Libro:** Clase de entidad que define las propiedades de cada ejemplar.
- **FicheroLibros:** Módulo encargado del tratamiento de entrada/salida de archivos (I/O).
- **Utilidades:** Métodos auxiliares para la validación de datos y gestión de entradas.

## Requisitos y ejecución

Es necesario tener instalado el JDK (Java Development Kit).

1. **Compilación:**
   ```bash
   javac *.java
   ```

2. **Ejecución (CLI):**
   ```bash
   java MainCli
   ```

3. **Ejecución (GUI):**
   ```bash
   java MainGUI
   ```


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FicheroLibros {

	public static boolean leerFichero(String fichero, Biblioteca biblioteca, boolean usarGUI) {
		FileReader lectorFichero;
		int lineaLeida = 0;
		String linea = "";
		String titulo = "";
		String autor = "";
		String isbn = "";
		// intento leer el fichero.
		try {
			lectorFichero = new FileReader(fichero);
			BufferedReader BuferFichero = new BufferedReader(lectorFichero);

			// leer lineas mientras exista una nueva linea
			try {
				while ((linea = BuferFichero.readLine()) != null) {
					linea = linea.trim();
					lineaLeida = lineaLeida + 1;
					// Si la linea esta vacia no hacemos nada.
					if (linea.isEmpty()) {
						System.out.println("La linea " + lineaLeida + " esta vacia, se ignora la linea..");
						continue;
					}
					String[] partesLinea = linea.split(";");

					if (partesLinea.length != 4) {
						Utilidades.mensajeAdaptativo(usarGUI, true, "El formato de la linea " + lineaLeida + " es incorrecto, se ignora el dato..");
						
						continue;
					}

					// Validamos si el formato es correcto.

					if (Utilidades.validarStringNumerosYLetrasInsensibleMayus(partesLinea[0].trim())) {
						titulo = partesLinea[0].trim();
					} else {
						Utilidades.mensajeAdaptativo(usarGUI, true, "El titulo no es valido, se ignora la linea Nº: " + lineaLeida);
						continue;
					}

					if (Utilidades.validarStringNumerosYLetrasInsensibleMayus(partesLinea[1].trim())) {
						autor = partesLinea[1].trim();
					} else {
						
						Utilidades.mensajeAdaptativo(usarGUI, true, "El autor no es valido, se ignora la linea Nº: " + lineaLeida);
						continue;
					}

					if (Utilidades.validarStringNumerosYLetrasInsensibleMayus(partesLinea[2].trim())) {
						isbn = partesLinea[2].trim();
					} else {
						Utilidades.mensajeAdaptativo(usarGUI, true, "El isbn no es valido, se ignora la linea Nº: " + lineaLeida);
						continue;
					}

					String disponibleStr = partesLinea[3].trim();

					boolean disponible;

					if (Utilidades.validarStrBool(disponibleStr)) {

						try {
							disponible = Boolean.parseBoolean(disponibleStr);

						} catch (Exception e) {

							Utilidades.mensajeAdaptativo(usarGUI, true, "Error al marcar la disponibilidad del libro Nº ,"+ lineaLeida + " del CSV, el formato no es correcto y se marca como no disponible por defecto.\n " + e);
							// Lo marcamos como no dispnible hasta que lo encuentre el bibliotecario.
							disponible = false;
						}
					} else {

						Utilidades.mensajeAdaptativo(usarGUI, true, "Error al marcar la disponibilidad del libro Nº ,"+ lineaLeida + " del CSV, el formato no es correcto y se marca como no disponible por defecto.");
						// Lo marcamos como no dispnible hasta que lo encuentre el bibliotecario.
						disponible = false;

					}

					Libro libroLeido = new Libro(isbn, titulo, autor, disponible);
					biblioteca.altaLibro(libroLeido);

				}

				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block

				Utilidades.mensajeAdaptativo(usarGUI, true, "Error de IO al leer el fichero");
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Utilidades.mensajeAdaptativo(usarGUI, true, "No se ha encontrado el fichero indicado.");
			e.printStackTrace();
			return false;
		}

	}

	public static boolean escribirFichero(String fichero, Biblioteca biblioteca, boolean usarGUI) {
		FileWriter escritorFichero;

		// Comprobamos que el fichero no es un directorio para evitar un crash.
		// https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
		File ficheroCSV = new File(fichero);
		if (ficheroCSV.exists() && ficheroCSV.isDirectory()) {

			if (ficheroCSV.exists())Utilidades.mensajeAdaptativo(usarGUI, true, "No existe fichero de datos. Se creará al guardar.");
			if (!ficheroCSV.isFile())Utilidades.mensajeAdaptativo(usarGUI, true, "Error ageno al programa, el fichero que intentas leer es un directorio.");

			return false;
		}

		try {
			escritorFichero = new FileWriter(fichero);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Utilidades.mensajeAdaptativo(usarGUI, true,
					"Error: La ruta del fichero que intentas escribir no existe....");
			e.printStackTrace();
			return false;
		}
		try (BufferedWriter buferEscritura = new BufferedWriter(escritorFichero)) {
			ArrayList<Libro> libros = biblioteca.getLibros();
			for (Libro libro : libros) {
				String lineaLibro = libro.getTitulo() + ";" + libro.getAutor() + ";" + libro.getIsbn() + ";"
						+ libro.getBoolStrDisponible();
				buferEscritura.write(lineaLibro);
				buferEscritura.newLine();
			}

			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utilidades.mensajeAdaptativo(usarGUI, true, "Error: Al preparar el buffer de escritura de ficheros...");
			try {
				escritorFichero.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
	}

};

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

// Utilidades para validar la informacion que se introduce.
public class Utilidades {

	public static Scanner sc = new Scanner(System.in);

	// Utilidades para validar informacion que introduce el usuario.

	// Regex para validar codigo de dispositivo.
	public static boolean validarStringNumerosYLetrasInsensibleMayus(String string) {
		// Evitamos null pointer exception..si viene nulo.
		if (string==null || string.isEmpty() ) return false;
		// Evitamos espacios al principio y al final.
		string = string.trim();
		/*
		 * Explicacion breve.. ^ indica el comienzo del String. Luego entre corchetes va
		 * el patron de caracteres aceptados es sensible a mayusculas, minusculas. +
		 * Indica minimo 1 vez. $ indica fin de String. Agrego acentos porque fallan xd:
		 * áéíóúÁÉÍÓÚñÑ Fuente:
		 * https://es.stackoverflow.com/questions/431121/como-puedo-agregarle-una-coma-a
		 * -una-expresi%C3%B3n-delimitadora-de-caracteres
		 */
		Pattern patronRegex = Pattern.compile("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ ]+$");
		Matcher comprobadorRegex = patronRegex.matcher(string);
		return comprobadorRegex.matches();
	}

	public static boolean validarStringSoloLetras(String string) {
		/*
		 * Explicacion breve.. ^ indica el comienzo del String. Luego entre corchetes va
		 * el patron de caracteres aceptados es sensible a mayusculas, minusculas. +
		 * Indica minimo 1 vez. $ indica fin de String. Fuente de los acentos:
		 * https://es.stackoverflow.com/questions/431121/como-puedo-agregarle-una-coma-a
		 * -una-expresi%C3%B3n-delimitadora-de-caracteres
		 */
		// Evitamos null pointer exception..si viene nulo.
		if (string==null || string.isEmpty() ) return false;
		// Evitamos espacios al principio y al final.
		string = string.trim();
		Pattern patronRegex = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
		Matcher comprobadorRegex = patronRegex.matcher(string);
		return comprobadorRegex.matches();
	}

	public static boolean validarDouble(Double numero) {
		return (numero > 0.0) ? true : false;
	}

	public static boolean validarStrBool(String strBool) {
		// Evitamos null pointer exception..si viene nulo.
		if (strBool==null || strBool.isEmpty() ) return false;

		if (strBool.toLowerCase().trim().equals("true"))
			return true;
		if (strBool.toLowerCase().trim().equals("false"))
			return true;
		return false;

	}

	public static int leerInteger() {
		int numObtenido = 0;
		boolean lecturaIntegerValida = false;
		while (!lecturaIntegerValida) {
			try {
				numObtenido = Integer.parseInt(sc.nextLine());
				lecturaIntegerValida = true;
			} catch (Exception e) {
				System.out.println("Error al intentar leer el valor numerico.\nError: " + e);
				System.out.print("Dime una entrada valida [int]: ");
				lecturaIntegerValida = false;
			}
		}
		return numObtenido;
	}

	public static void pausarInteracion() {
		System.out.print("Pulsa intro para continuar....");
		sc.nextLine();
	}

	public static String leerStringValida(String complementoMensaje) {
		String stringLeida = sc.nextLine();
		while (Utilidades.validarStringNumerosYLetrasInsensibleMayus(stringLeida) == false) {
			System.out.println("El " + complementoMensaje + " introducido no es valido.");
			System.out.print("Dime un " + complementoMensaje + "valido [a-zA-Z0-9]: ");
			stringLeida = sc.nextLine();
		}
		return stringLeida;
	}

	public static void leerCSVBiblioteca(String csvBiblioteca, Biblioteca bibliteca) {

		// Comprobamos si existe el fichero.
		// https://stackoverflow.com/questions/1816673/how-do-i-check-if-a-file-exists-in-java
		File ficheroCSV = new File(csvBiblioteca);
		if (!ficheroCSV.exists() || !ficheroCSV.isFile()) {
			System.out.println(
					"El fichero CSV de la biblioteca no existe o es un directorio, no se cargan libros guardados...");
			return;
		} else {
			boolean resultadoLecturaCSV = FicheroLibros.leerFichero(csvBiblioteca, bibliteca);
			String mensaje = (resultadoLecturaCSV) ? "Fichero CSV cargado correctamente"
					: "Error al cargar el fichero csv";
			System.out.println(mensaje);
		}
	}

	public static String leerStringValidaGUI(String placeHolder, String Asunto) {
		String stringLeida = JOptionPane.showInputDialog(null, Asunto, placeHolder);
		while (Utilidades.validarStringNumerosYLetrasInsensibleMayus(stringLeida) == false) {
			// Mensaje de error: https://masqueprogramar.wordpress.com/2019/03/04/ejemplos-showmessagedialog/
			Utilidades.MensajeError("El dato introducido no es válido [a-zA-Z0-9].");
			stringLeida = JOptionPane.showInputDialog(null, Asunto, placeHolder);
		}
		return stringLeida;
	}
	
	
	public static void MensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	
	public static void MensajeInfo(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
				JOptionPane.INFORMATION_MESSAGE);
	}
}

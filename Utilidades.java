import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Utilidades para validar la informacion que se introduce.
public class Utilidades {

	public static Scanner sc = new Scanner(System.in);

	// Utilidades para validar informacion que introduce el usuario.

	// Regex para validar codigo de dispositivo.
	public static boolean validarStringNumerosYLetrasInsensibleMayus(String string) {
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

		if (strBool.toLowerCase().trim().equals("true"))
			return true;
		if (strBool.toLowerCase().trim().equals("false"))
			return true;
		return false;

	}

	public static int leerInteger() {
		int numObtenido=0;
		boolean lecturaIntegerValida=false;
		while (!lecturaIntegerValida) {
		try {
			numObtenido = Integer.parseInt(sc.nextLine());
			lecturaIntegerValida=true;
		} catch (Exception e) {
			System.out.println("Error al intentar leer el valor numerico.\nError: " + e);
			System.out.print("Dime una entrada valida [int]: ");
			lecturaIntegerValida=false;
		}
		}
		return numObtenido;
	}

	public static void pausarInteracion() {
		System.out.print("Pulsa intro para continuar....");
		sc.nextLine();
	}
	
	
	public static String leerStringValida(String complementoMensaje) {
		String stringLeida=sc.nextLine();
		while (Utilidades.validarStringNumerosYLetrasInsensibleMayus(stringLeida) == false) {
			System.out.println("El " + complementoMensaje + " introducido no es valido.");
			System.out.print("Dime un " + complementoMensaje + "valido [a-zA-Z0-9]: ");
			stringLeida = sc.nextLine();
		}
		return stringLeida;
	}


}

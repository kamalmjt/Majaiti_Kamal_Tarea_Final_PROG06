import java.io.File;

public class MainCli {
	
	public static Biblioteca biblioteca = new Biblioteca();
	public static final String  csvBiblioteca="./datosBiblioteca.csv";
	

	public static void main(String[] args) {
		Utilidades.leerCSVBiblioteca(MainCli.csvBiblioteca, MainCli.biblioteca, false);
		imprimirMenuPrincipal();

	}
	
	



	public static void imprimirMenuPrincipal() {
		int opcion=99;
		while (opcion!=7) {
			System.out.println("---- Programa de gestion de Bibliotecas -----");
			System.out.println("1. Añadir Libro.");
			System.out.println("2. Mostrar Libros.");
			System.out.println("3. Prestar Libro.");
			System.out.println("4. Devolver libro.");
			System.out.println("5. Buscar libro por ISBN.");
			System.out.println("6. Guardar datos en fichero.");
			System.out.println("7. Salir.");
			System.out.print("Escoge una opcion:");
			opcion=Utilidades.leerInteger();
			
			
			switch (opcion) {
			case 1:
				MainCli.pedirDatosLibro();
				Utilidades.pausarInteracion();
				break;
				
			case 2:
				String listado=MainCli.biblioteca.mostrarListadoLibros();
				System.out.println(listado);
				Utilidades.pausarInteracion();
				break;
				
			case 3:
				MainCli.prestarLibro();
				Utilidades.pausarInteracion();
				break;
				
			case 4:
				MainCli.devolverLibro();
				Utilidades.pausarInteracion();
				break;
				
			case 5:
				MainCli.buscarLibro();
				Utilidades.pausarInteracion();
				break;
				
			case 6:
				boolean resultadoSalvado=FicheroLibros.escribirFichero(csvBiblioteca, biblioteca, false);
				String mensaje=(resultadoSalvado)?"Guardado realizado correctamente.":"Error al guardar el fichero";
				System.out.println(mensaje);
				Utilidades.pausarInteracion();
				break;
				
			case 7:
				System.out.println("Saliendo del programa....");
				System.exit(0);
				break;
				
			default: 
				System.out.println("Opcion no valida");
				break;
			}
		}
		// Dudo que el codigo llegue aqui....
		System.out.println("Saliendo del programa....");
	}
	
	private static void pedirDatosLibro() {
		System.out.println("---- Programa de gestion de Bibliotecas -----");
		System.out.println("---- Opcion Añadir Libro -----");
		System.out.print("Dime el ISBN: ");
		String isbn=Utilidades.leerStringValida("ISBN");
		System.out.print("Dime el Titulo: ");
		String titulo=Utilidades.leerStringValida("Titulo");
		System.out.print("Dime el Autor: ");
		String autor=Utilidades.leerStringValida("autor");
		
		Libro libroNuevoAlta=new Libro(isbn, titulo, autor, true);
		boolean resultadoAlta=MainCli.biblioteca.altaLibro(libroNuevoAlta);
		String mensaje=(resultadoAlta)?"El libro se ha dado de alta correctamente.":"El libro no se ha dado de alta";
		System.out.println(mensaje);
		return;		
	}

	
	private static void prestarLibro() {
		System.out.println("---- Programa de gestion de Bibliotecas -----");
		System.out.println("---- Opcion Prestar Libro -----");
		System.out.print("Dime el ISBN: ");
		String isbn=Utilidades.leerStringValida("ISBN");
		boolean resultadoPrestamo=MainCli.biblioteca.prestarLibro(isbn);
		String mensaje=(resultadoPrestamo)?"El libro se ha prestado correctamente.":"El libro no se ha prestado, porque estaba ya prestado o no existe.";
		System.out.println(mensaje);
		return;		
	}
	
	
	private static void devolverLibro() {
		System.out.println("---- Programa de gestion de Bibliotecas -----");
		System.out.println("---- Opcion Devolver Libro -----");
		System.out.print("Dime el ISBN: ");
		String isbn=Utilidades.leerStringValida("ISBN");
		boolean resultadoPrestamo=MainCli.biblioteca.devolverLibro(isbn);
		String mensaje=(resultadoPrestamo)?"El libro se ha devuelto correctamente.":"El libro no se ha devuelto, porque no esta prestado o no existe.";
		System.out.println(mensaje);
		return;		
	}
	
	private static void buscarLibro() {
		System.out.println("---- Programa de gestion de Bibliotecas -----");
		System.out.println("---- Opcion Buscar Libro -----");
		System.out.print("Dime el ISBN: ");
		String isbn=Utilidades.leerStringValida("ISBN");
		Libro libroBuscado=MainCli.biblioteca.buscarLibro(isbn);
		if(libroBuscado==null) {
			System.out.println("El libro que busca con ISBN: " + isbn + " no existe.");
			return;
		}
		System.out.println(libroBuscado.toString());
		return;		
	}

}

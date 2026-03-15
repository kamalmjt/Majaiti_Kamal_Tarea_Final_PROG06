import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {
	
	private ArrayList<Libro> listaLibros;
	private HashMap<String, Libro> indiceLibros;
	public Biblioteca() {

		this.listaLibros = new ArrayList<Libro>();
		this.indiceLibros = new HashMap<String, Libro>();
	}
	

	public boolean altaLibro(Libro libro) {
		String isbn=libro.getIsbn();
		if (!this.existeLibro(isbn)) {
			listaLibros.add(libro);
			indiceLibros.put(isbn, libro);
			return  true;
		}
		// Si no se puede añadir devolvermos false.
		return false;
	}
	
	
	public Libro buscarLibro(String isbn) {
		// indiceLibros.get ya devulve null por si sola.
		return indiceLibros.get(isbn);
		
	}
	
	
	public boolean existeLibro(String isbn) {
		if (this.listaLibros.size()==0) return false;
		return indiceLibros.containsKey(isbn);
	}
	
	
	public boolean estaDisponible(String isbn) {
		if (this.existeLibro(isbn)) {
			return indiceLibros.get(isbn).getDisponible();
		}
		return false;
	}
	
	
	
	public boolean prestarLibro(String isbn) {
		if(!this.existeLibro(isbn)) return false;
		if(!this.estaDisponible(isbn)) return false;
		return indiceLibros.get(isbn).prestar();
		
	}
	
	public boolean devolverLibro(String isbn) {
		if(!this.existeLibro(isbn)) return false;
		if(this.estaDisponible(isbn)) return false;
		return indiceLibros.get(isbn).devolver();
		
	}
	
	
	public String mostrarListadoLibros() {
		StringBuilder sb = new StringBuilder();
		StringBuilder linea = new StringBuilder();
		int contaLibros = 0;
		sb.append("------- Inicio del Listao de libros -------\n");
		for (Libro libro : listaLibros) {
			contaLibros =contaLibros  +1;
			linea.append("Libro Nº :" + contaLibros);
			linea.append(" | ISBN :" + libro.getIsbn());
			linea.append(" | Titulo :" + libro.getTitulo());
			linea.append(" | Autor :" + libro.getAutor());
			linea.append(" | Disponibilidad :" + libro.getDisponibleStr());
			sb.append(linea.toString()+"\n");
			// Limpiamos la linea: https://www.baeldung.com/java-clear-stringbuilder-stringbuffer
			linea.setLength(0);
		}
		sb.append("\n------- Fin del Listao de libros -------");
		return sb.toString();
		
	}
	
	public ArrayList<Libro> getLibros() {
		return this.listaLibros;
	}
	
	
}

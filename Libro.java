
public class Libro {

	private String isbn;
	private String titulo;
	private String autor;
	private boolean disponible;
	
	public Libro(String isbn, String titulo, String autor, boolean disponible) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.disponible = disponible;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean getDisponible() {
		return disponible;
	}

	public String getDisponibleStr() {
		return (this.disponible)?"Si":"No";
	}

	
	public String getBoolStrDisponible() {
		return (this.disponible)?"true":"false";
	}

	private void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	
	// Si no esta disponible lo prestamos e indicamos false o true si exito o no.
	public boolean prestar() {
		if (!this.getDisponible()) return false;
			this.setDisponible(false);
			return true;
	}
	
	public boolean devolver() {
		if (!this.getDisponible()) {
			this.setDisponible(true);
			return true;
		} 
		return false;
	
	}
	
	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", disponible=" + disponible + "]";
	}
	
	
	
	
}

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class MainGUI extends JFrame {

	public static Biblioteca biblioteca = new Biblioteca();
	public static final String csvBiblioteca = "./datosBiblioteca.csv";

	private static JTextField tfAutor = new JTextField(50);
	private static JTextField tfISBN = new JTextField(50);
	private static JTextField tfTitulo = new JTextField(50);

	// La creamos aqui para poder acceder a ella desde diferentes metodos.
	private static DefaultTableModel modeloTabla = new DefaultTableModel(new Object[][] {}, // filas iniciales vacías
			new String[] { "Título", "Autor", "ISBN", "Disponible" } // nombres de columnas
	) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false; // tabla solo lectura
		}
	};

	public static void main(String[] args) {
		MainGUI.biblioteca = new Biblioteca();
		Utilidades.leerCSVBiblioteca(MainGUI.csvBiblioteca, MainGUI.biblioteca, true);

		// Lanzamos la GUI.
		// https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainGUI.construirInterfaz();
			}
		});

	}

	public static void construirInterfaz() {
		// Creo la ventana principal.
		JFrame jframe = new JFrame("Gestión de Libros");
		;
		// Configuro el boton cerrar.
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// configuro el tamaño de ventana.
		jframe.setSize(800, 500);
		jframe.setLayout(new BorderLayout(10, 10));

		// Creo el panel de formulario con 4 elementos txt.
		// new GridLayout(filas, columnas, margen, margen)
		JPanel panelFormulario = new JPanel(new GridLayout(1, 3, 8, 8));

		JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel panelAutor = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		JPanel panelISBN = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

		// Añado el panel al formulario.
		jframe.add(panelFormulario, BorderLayout.NORTH);

		// Creamos las etiquetas del Panel Nº1 panelFormulario que contine la caja de
		// registrar libro con sus campos.

		// Creo etiquetas para las cajas de texto.
		// https://docs.oracle.com/javase/8/docs/api/javax/swing/JLabel.html
		JLabel jlTitulo = new JLabel("Titulo:");
		JLabel jlAutor = new JLabel("Autor:");
		JLabel jlIsbn = new JLabel("ISBN:");
		jlTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		jlAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		jlIsbn.setHorizontalAlignment(SwingConstants.RIGHT);

		// Campos las cajas de texto de entrada de datos JTextField (título, autor,
		// ISBN) 50 caracteres de entrada.

		tfTitulo.setPreferredSize(new Dimension(200, 50));
		tfAutor.setPreferredSize(new Dimension(200, 50));
		tfISBN.setPreferredSize(new Dimension(200, 50));

		// Agrego al panel de formulario las cajas de texto y el boton añadirLibro.

		// Agrupo cada componente con su etiqueta.
		panelTitulo.add(jlTitulo);
		panelTitulo.add(tfTitulo);
		panelAutor.add(jlAutor);
		panelAutor.add(tfAutor);
		panelISBN.add(jlIsbn);
		panelISBN.add(tfISBN);

		panelFormulario.add(panelTitulo);
		panelFormulario.add(panelAutor);
		panelFormulario.add(panelISBN);

		// Crear la tabla.
		JTable tabla = new JTable(modeloTabla);
		// Crear el scroll de la tabla.
		JScrollPane scroll = new JScrollPane(tabla);

		jframe.add(scroll, BorderLayout.CENTER);

		// fin de tabla.

		// Inicio panel inferior.
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

		// Botones del panel inferior.
		JButton btnAnadir = new JButton("Añadir");
		JButton btnMostrar = new JButton("Mostrar Todos");
		JButton btnPrestar = new JButton("Prestar");
		JButton btnDevolver = new JButton("Devolver");
		JButton btnBuscar = new JButton("Buscar por ISBN");
		JButton btnGuardar = new JButton("Guardar");

		// Configurar accion de los botones.
		btnBuscar.addActionListener(e -> accionBuscarPorISBN());
		btnMostrar.addActionListener(e -> accionRefrescarTabla());
		btnGuardar.addActionListener(e -> accionGuardar());
		btnDevolver.addActionListener(e -> accionDevolver());
		btnPrestar.addActionListener(e -> accionPrestar());
		btnAnadir.addActionListener(e -> accionAnyadir());

		panelBotones.add(btnAnadir);
		panelBotones.add(btnMostrar);
		panelBotones.add(btnPrestar);
		panelBotones.add(btnDevolver);
		panelBotones.add(btnBuscar);
		panelBotones.add(btnGuardar);

		jframe.add(panelBotones, BorderLayout.SOUTH);

		// Hacer visible
		jframe.setVisible(true);
		refrescarTabla();

	}

	public static void accionBuscarPorISBN() {

		String isbn = Utilidades.leerStringValidaGUI("Introduce el ISBN del libro que quieres buscar:", "Buscar Libro");
		if (!MainGUI.biblioteca.existeLibro(isbn)) {
			Utilidades.mensajeError("El ISBN que buscas no existe.");
			return;
		} else {
			Libro libroBuscado = MainGUI.biblioteca.buscarLibro(isbn);
			refrescarTabla(libroBuscado);
			Utilidades.mensajeInfo("El ISBN que buscas se ha encontrado y se muestra en la tabla filtrado.");

		}

	}

	public static void refrescarTabla(Libro libro) {
		modeloTabla.setRowCount(0);
		Object[] fila = { libro.getTitulo(), libro.getAutor(), libro.getIsbn(), libro.getDisponibleStr() };

		modeloTabla.addRow(fila);

	}

	public static void refrescarTabla() {
		// Vaciar tabla
		modeloTabla.setRowCount(0);
		// Recorrer biblioteca
		for (Libro l : MainGUI.biblioteca.getLibros()) {
			Object[] fila = { l.getTitulo(), l.getAutor(), l.getIsbn(), l.getDisponibleStr() };
			// añadir una fila por cada libro
			modeloTabla.addRow(fila);
		}
	}

	public static void accionRefrescarTabla() {
		refrescarTabla();
		// Mostramos GUI, Mensaje Informativo con el texto.
		Utilidades.mensajeAdaptativo(true, false, "Tabla refrescada con exito...");
		return;
	}

	public static void accionGuardar() {
		boolean resultadoSalvado = FicheroLibros.escribirFichero(MainGUI.csvBiblioteca, MainGUI.biblioteca, true);
		if (resultadoSalvado)
			Utilidades.mensajeAdaptativo(true, !resultadoSalvado, "Fichero guardado correctamente.");
	}

	public static void accionDevolver() {

		String isbn = Utilidades.leerStringValidaGUI("Introduce el ISBN del libro que quieres devolver:",
				"Devolver Libro");
		if (!MainGUI.biblioteca.existeLibro(isbn)) {
			Utilidades.mensajeError("El ISBN que quieres devolver no existe. \n Datos del ISBN: " + isbn);
			return;
		} else {
			boolean estadoDevolucion = MainGUI.biblioteca.devolverLibro(isbn);
			if (estadoDevolucion) {
				refrescarTabla();
				Utilidades.mensajeInfo("El ISBN con ISBN" + isbn
						+ " se ha marcado como devuelto y se muestra en la tabla actualizado.");
			} else {
				Utilidades.mensajeError("El Libro que intentas devolver con el ISBN: " + isbn + " no esta prestado.");
			}

		}

	}

	public static void accionPrestar() {

		String isbn = Utilidades.leerStringValidaGUI("Introduce el ISBN del libro que quieres prestar:",
				"Prestar Libro");
		if (!MainGUI.biblioteca.existeLibro(isbn)) {
			Utilidades.mensajeError("El ISBN que quieres prestar no existe. \n Datos del ISBN: " + isbn);
			return;
		} else {
			boolean estadoPrestamo = MainGUI.biblioteca.prestarLibro(isbn);
			if (estadoPrestamo) {
				refrescarTabla();
				Utilidades.mensajeInfo("El libro con ISBN " + isbn
						+ " se ha marcado como prestado y se muestra en la tabla actualizado.");
			} else {
				Utilidades.mensajeError("ERROR: El Libro que intentas prestar con el ISBN: " + isbn
						+ " ya esta prestado previamente y no se puede prestar.");
			}

		}

	}

	public static void limpiarFormulario() {

		tfTitulo.setText("");
		tfAutor.setText("");
		tfISBN.setText("");
		tfTitulo.requestFocus();
	}

	public static void accionAnyadir() {
		String titulo = tfTitulo.getText();
		String autor = tfAutor.getText();
		String isbn = tfISBN.getText();

		if (!Utilidades.validarStringNumerosYLetrasInsensibleMayus(titulo)) {
			Utilidades.mensajeError("Error, el texto del campo titulo no es valido.");
			return;
		}
		;
		if (!Utilidades.validarStringNumerosYLetrasInsensibleMayus(autor)) {
			Utilidades.mensajeError("Error, el texto del campo autor no es valido.");
			return;
		}
		;
		if (!Utilidades.validarStringNumerosYLetrasInsensibleMayus(isbn)) {
			Utilidades.mensajeError("Error, el texto del campo ISBN no es valido.");
			return;
		}
		;

		if ((MainGUI.biblioteca.existeLibro(isbn))) {
			Utilidades.mensajeError("Error, ya existe un libro con el ISBN indicado.");
			return;
		}
		;

		Libro nuevoLibro = new Libro(isbn, titulo, autor, true);
		boolean resultadoAnyadirLibro = MainGUI.biblioteca.altaLibro(nuevoLibro);

		if (resultadoAnyadirLibro) {
			Utilidades.mensajeInfo("El libro se ha añadido con exito, se actualiza y se muestra la tabla.");
			MainGUI.refrescarTabla();
			MainGUI.limpiarFormulario();
			return;
		} else {
			Utilidades.mensajeError("Error al añadir el libro, revisa la consola de excepciones.");
			return;
		}

	}
}

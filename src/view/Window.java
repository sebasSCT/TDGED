package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.staticTools.GetResources;
import model.staticTools.vars;

//Configuracion de la ventana con JFrame

public class Window extends JFrame
{

	private static final long serialVersionUID = 1L;

	private String titulo;
	private ImageIcon icon;

	public Window ( final String titulo, final DrawCanvas ds )
	{
		this.titulo = titulo;

		this.icon = new ImageIcon(GetResources.ciTranslucida(vars.icon));

		configurarVentana(ds);
	}

	private void configurarVentana ( DrawCanvas ds )
	{
		// titulo en la ventana
		setTitle(titulo);

		// Cerrar programa al cerrar la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Para no permititr que se redimensione la ventana
		setResizable(false);

		// Imagen del icono de la aplicacion
		setIconImage(icon.getImage());

		// Hara que el canvas ocupe la ventana y que la ventana sea del mismo
		// tamaño del canvas
		setLayout(new BorderLayout());
		add(ds, BorderLayout.CENTER);

		// Eliminar lo bordes de la ventana
		setUndecorated(false);

		// Dar formato y tamaño a la ventana
		pack();

		// Para establecer la ventana en el centro
		setLocationRelativeTo(null);

		// Para que sea visible
		setVisible(true);
	}

}

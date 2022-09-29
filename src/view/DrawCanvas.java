package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import com.sun.javafx.geom.Point2D;
import model.staticTools.GetResources;
import model.staticTools.vars;

public class DrawCanvas extends Canvas
{

	// Clase para configurar y actualizar la pantalla de dibujado

	// Atributos
	private static final long serialVersionUID = 1L;
	private int width, height;

	// Constructor
	public DrawCanvas ( Point2D size )
	{
		this.width = (int) size.x;
		this.height = (int) size.y;

		// Configuración del canvas
		setIgnoreRepaint(true);
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus(true);
	}

	// COntrollador de estados como parametro o controllador de juego
	public void draw ()
	{

		// Configurar la existencia de un buffer en el canvas, espacio en
		// memoria para dibujar imagenes.
		final BufferStrategy buffer = getBufferStrategy();

		// Si el buffer es diferente de null, se crea una nueva estragia de
		// buffer
		if ( buffer == null )
		{
			createBufferStrategy(3);
			return;
		}

		// Graphics, dibujar en el lugar donde queramos
		// aqui los Graphics dibujaran dentro del buffer
		final Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

		// Escalado
		if ( vars.FACTOR_ESCALADO_X != 1.0 || vars.FACTOR_ESCALADO_Y != 1.0 )
		{
			g.scale(vars.FACTOR_ESCALADO_X, vars.FACTOR_ESCALADO_Y);
		}

		// back
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		// -----------------------------------

		// DRAW AREA

		// #test
		test(g);

		// ================

		// Limpiar g
		g.dispose();

		// Mostrar el contenido del buffer
		buffer.show();

	}

	private void test ( Graphics g )
	{
		g.drawImage(GetResources.ciTranslucida("src/resources/img/map/tile_0.png"), 0, 0,
				null);
	}

	public int getWidth ()
	{
		return width;
	}

	public int getHeight ()
	{
		return height;
	}

}

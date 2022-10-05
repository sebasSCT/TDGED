package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import controller.MapController;
import model.staticTools.GetResources;
import model.staticTools.vars;

public class DrawCanvas extends Canvas
{

	// Clase para configurar y actualizar la pantalla de dibujado

	// Atributos
	private static final long serialVersionUID = 1L;
	private int width, height;

	// Constructor
	public DrawCanvas ( Point size, MapController mc )
	{
		this.width = (int) size.getX();
		this.height = (int) size.getY();

		// Configuración del canvas
		setIgnoreRepaint(true);
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus(true);

		this.mc = mc;
		this.dp = new DrawMap(mc);
		System.out.println("DrawCanvas");
	}

	// (ELIMINAR ?) (cambiar por gameCOntroller o scene COntroller)
	private MapController mc;
	private DrawMap dp;
	// ----
	private BufferStrategy buffer;
	private Graphics2D g;
	// COntrollador de estados como parametro o controllador de juego
	public void draw () // Aceptando mapa provisionalmente
	{

		// Configurar la existencia de un buffer en el canvas, espacio en
		// memoria para dibujar imagenes.
		buffer = getBufferStrategy();

		// Si el buffer es diferente de null, se crea una nueva estragia de
		// buffer
		if ( buffer == null )
		{
			createBufferStrategy(3);
			return;
		}

		// Graphics, dibujar en el lugar donde queramos
		// aqui los Graphics dibujaran dentro del buffer
		g = (Graphics2D) buffer.getDrawGraphics();

		// back
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);

		// Escalado
		g.scale(vars.FACTOR_ESCALADO_X, vars.FACTOR_ESCALADO_Y);
		// g.scale(2, 2);

		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

		// DRAW AREA===================

		/// #test
		// test(g);

		// Dibujar mapa testeo
		dp.drawMap(g);
		// ----------------------------

		///
		g.setColor(Color.yellow);
		g.drawString("FPS: " + String.valueOf(vars.FPS), 210, 210);
		g.drawString("APS: " + String.valueOf(vars.APS), 210, 220);
		///

		// ===========================

		// Limpiar g
		g.dispose();

		// Mostrar el contenido del buffer
		buffer.show();

	}

	private void test ( Graphics g )
	{
		g.drawImage(GetResources.ciTranslucida("src/resources/img/mapTileSet/tileSet_0.png"),
				0, 0, null);
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

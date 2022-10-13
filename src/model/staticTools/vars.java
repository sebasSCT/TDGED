package model.staticTools;

import java.awt.Font;
import java.awt.Point;

public class vars
{

	// Configuracion inicial
	public final static String title = "Tower Defenders";
	public final static Point size = new Point(1280, 720);
	public final static Point gameSize = new Point((int) size.x / 2, (int) size.y / 2);
	public final static int spriteSize = 16;
	public final static int ANCHO_JUEGO = (int) size.x / 2, ALTO_JUEGO = (int) size.y / 2;
	public final static int CENTRO_X_VENTANA = ANCHO_JUEGO / 2,
			CENTRO_Y_VENTANA = ALTO_JUEGO / 2;
	public final static double FACTOR_ESCALADO_X = 3;
	public final static double FACTOR_ESCALADO_Y = 3;
	public final static int MARGEN_X = ANCHO_JUEGO / 2 - spriteSize / 2;
	public final static int MARGEN_Y = ALTO_JUEGO / 2 - spriteSize / 2;
	public static int FPS, APS;
	public static double delta;
	// ==================================

	// Rutas
	/// etc
	public final static String icon = "src/resources/img/etc/img.jpeg" + ""; // provisional

	// fuente
	public final static Font font = GetResources.cargarFuentes("src/resources/font/font2.ttf",
			8);

	/// background
	public final static String getBG ( String n )
	{
		return "src/resources/img/bg/bg_" + n + ".png"; // modificar formato
	}

	/// JSON mapas
	public final static int nMaps = 3;
	public final static String getMapJSON ( String type, String n )
	{
		return "src/resources/file/map/" + type + "/mapa" + n + ".json";
	}

	/// tileset mapas
	public final static String getTileset ( String n )
	{
		return "src/resources/img/mapTileSet/tileSet_" + n + ".png";
	}

	/// tileset entidades
	public final static String getTilesetE ( String t, String n )
	{
		return "src/resources/img/entity/" + t + "/" + n + ".png";
	}
	// ============================

}

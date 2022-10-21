package model.staticTools;

import java.awt.Font;
import model.input.Keyboard;

public class vars
{

	// Configuracion inicial
	public final static String title = "Tower Defenders";

	public final static int spriteSize = 16;
	public final static int entitySpriteSize = 32;
	// tamaño de personajes

	public final static double scale = 2;
	public final static double W_SCREEN = 1280, H_SCREEN = 720;
	public final static double W_GAME = W_SCREEN / scale, H_GAME = H_SCREEN / scale;
	public final static double FACTOR_ESCALADO_X = 2; // W_SCREEN / W_GAME
	public final static double FACTOR_ESCALADO_Y = 2; // H_SCREEN / H_GAME
	public final static double MARGEN_X = W_GAME / 2 - spriteSize / 2;
	public final static double MARGEN_Y = H_GAME / 2 - spriteSize / 2;
	public final static int screenOffSet = 0;

	public static int FPS, APS;
	public static double delta;
	// ==================================

	// Rutas
	/// etc
	public final static String icon = "src/resources/img/etc/img.jpeg" + ""; // provisional

	// fuente
	public final static Font font = GetResources.cargarFuentes("src/resources/font/font2.ttf",
			5);

	/// background
	public final static String getBG ( String n )
	{
		return "src/resources/img/bg/bg_" + n + ".png"; // modificar formato
	}

	/// JSON mapas
	public final static int nMaps = 5;
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

	// Teclado

	public final static Keyboard kb = new Keyboard();

	//

	// Fisicas

	public final static int gravity = 1;

	//

}

package model.staticTools;

import com.sun.javafx.geom.Point2D;

public class vars
{

	// Configuracion inicial
	public final static String title = "Tower Defenders";
	public final static Point2D size = new Point2D(1280, 720);
	public static int ANCHO_JUEGO = (int) size.x / 2, ALTO_JUEGO = (int) size.y / 2;
	public static int CENTRO_X_VENTANA = ANCHO_JUEGO / 2, CENTRO_Y_VENTANA = ALTO_JUEGO / 2;
	public static double FACTOR_ESCALADO_X = size.x / ANCHO_JUEGO;
	public static double FACTOR_ESCALADO_Y = size.y / ALTO_JUEGO;
	// ==================================

	// Rutas
	/// etc
	public final static String icon = "src/resources/img/etc/img.jpeg"; // provisional

	/// map
	public final static String map0 = "src/resources/file/map/mapa0.json";
	// ============================

}

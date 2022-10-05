package controller;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.scene.GameMap;
import model.staticTools.GetResources;
import model.staticTools.JSONgetters;

public class MapController
{

	// Guardar mapas cargados. no recargarlos

	private GameMap mapa;
	private JSONObject global;
	private ArrayList<GameMap> mapas; // Puede ser almacenado con algo hash
										// (modificiar)
	private GameMap mapaActual;

	public MapController ()
	{
		mapa = new GameMap();
		mapas = new ArrayList<>();
		System.out.println("Mapcontroller");
	}

	public void loadMap ( String ruta )
	{
		String archivo = GetResources.leerArchivoTxt(ruta);

		global = JSONgetters.getObjectJSON(archivo);

		mapa.setWidthTiles(JSONgetters.getIntJSON(global, "width"));
		mapa.setHeightTiles(JSONgetters.getIntJSON(global, "height"));
		mapa.setPosIni(new Point(	JSONgetters.getIntJSON(global, "posiniX"),
									JSONgetters.getIntJSON(global, "posiniY")));

		JSONArray layers = JSONgetters.getArrayJSON(global.get("layers").toString());

		String[] spriteLy = null;
		for ( Object layer : layers )
		{
			Object ly = JSONgetters.getObjectJSON(layer.toString()).get("type").toString();
			if ( ly.toString().contains("tilelayer") )
			{
				Object sprites = JSONgetters.getObjectJSON(layer.toString()).get("data")
											.toString();
				sprites = sprites.toString().replace("[", "");
				sprites = sprites.toString().replace("]", "");
				spriteLy = sprites.toString().split(",");
				loadLayer(spriteLy,
						JSONgetters.getObjectJSON(layer.toString()).get("tileset").toString());
			}
		}

		mapas.add(mapa);
		mapaActual = mapa;
	}

	private void loadLayer ( String[] spriteLy, String tileSet )
	{
		SpriteSheetController ssc = new SpriteSheetController(tileSet);
		ArrayList<BufferedImage> sprites = new ArrayList<>();
		BufferedImage bi;
		for ( int i = 0; i < spriteLy.length; i++ )
		{
			// Puede ser necesario extraer firstgid del archivo JSON para el
			// (-1)

			bi = (spriteLy[i].equals("0")) ? ssc.getSs().getSprites()[5]
					: ssc.getSs().getSprites()[Integer.parseInt(spriteLy[i]) - 1];
			sprites.add(bi);
		}

		mapa.getSpriteLayers().add(sprites);
	}

	public GameMap getMapaActual ()
	{
		return mapaActual;
	}

	public void setMapaActual ( int ind )
	{
		mapaActual = mapas.get(ind);
	}

	public void impm ()
	{
		System.out.println(mapas);
	}
}

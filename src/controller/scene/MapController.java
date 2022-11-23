package controller.scene;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.logic.ColisionBox;
import model.logic.dataStructure.Duplet;
import model.logic.dataStructure.Triplet;
import model.scene.GameMap;
import model.staticTools.GetResources;
import model.staticTools.JSONgetters;
import model.staticTools.vars;

public class MapController
{

	private GameMap mapa;
	private JSONObject global;

	public MapController ()
	{
		System.out.println("Mapcontroller");
	}

	public void loadMap ( String ruta )
	{
		mapa = new GameMap();

		String archivo = GetResources.leerArchivoTxt(ruta);

		global = JSONgetters.getObjectJSON(archivo);

		// Propiedades del mapa
		mapa.setWidthTiles(JSONgetters.getIntJSON(global, "width"));
		mapa.setHeightTiles(JSONgetters.getIntJSON(global, "height"));
		mapa.setBG(vars.getBG(String.valueOf(JSONgetters.getIntJSON(global, "background"))));

		// Punto inicial de los jugadores
		mapa.setPosIni(new Point(	JSONgetters.getIntJSON(global, "posiniX"),
									JSONgetters.getIntJSON(global, "posiniY")));

		// Extraer estructuras y sus posiciones
		if ( global.get("structures") != null )
		{
			JSONArray structures = JSONgetters.getArrayJSON(
					global.get("structures").toString());
			for ( Object structure : structures )
			{
				Object type = JSONgetters.getObjectJSON(structure.toString()).get("type");
				int x = JSONgetters.getIntJSON((JSONObject) structure, "posX");
				int y = JSONgetters.getIntJSON((JSONObject) structure, "posY");
				Object direction = JSONgetters	.getObjectJSON(structure.toString())
												.get("direction");
				Object animation = JSONgetters	.getObjectJSON(structure.toString())
												.get("animation");

				mapa.getStructures()
					.add(new Triplet<String, Point, Duplet<String, String>>(type.toString(),
																			new Point(x, y),
																			new Duplet<String, String>(	direction.toString(),
																										animation.toString())));
			}
		}

		// Area de daño de la torre
		if ( global.get("towerColisions") != null )
		{
			JSONArray recs = JSONgetters.getArrayJSON(global.get("towerColisions").toString());
			for ( Object rec : recs )
			{
				int x = JSONgetters.getIntJSON((JSONObject) rec, "x");
				int y = JSONgetters.getIntJSON((JSONObject) rec, "y");
				int width = JSONgetters.getIntJSON((JSONObject) rec, "width");
				int height = JSONgetters.getIntJSON((JSONObject) rec, "height");

				mapa.getTC().add(new Rectangle(x, y, width, height));
			}
		}

		// escaleras
		if ( global.get("ladders") != null )
		{
			JSONArray recs = JSONgetters.getArrayJSON(global.get("ladders").toString());
			for ( Object rec : recs )
			{
				int x = JSONgetters.getIntJSON((JSONObject) rec, "x");
				int y = JSONgetters.getIntJSON((JSONObject) rec, "y");
				int width = JSONgetters.getIntJSON((JSONObject) rec, "width");
				int height = JSONgetters.getIntJSON((JSONObject) rec, "height");

				mapa.getLadders().add(new Rectangle(x, y, width, height));
			}
		}

		// vida de la torre
		mapa.setTowerPS(JSONgetters.getIntJSON(global, "towerps"));

		// tables
		if ( global.get("tables") != null )
		{
			JSONArray points = JSONgetters.getArrayJSON(global.get("tables").toString());
			for ( Object rec : points )
			{
				int x = JSONgetters.getIntJSON((JSONObject) rec, "x");
				int y = JSONgetters.getIntJSON((JSONObject) rec, "y");

				mapa.getTables().add(new Point(x, y));
			}
		}

		// Extraer las capas de sprites y las capas de colisiones
		JSONArray layers = JSONgetters.getArrayJSON(global.get("layers").toString());
		String[] spriteLy;
		int fg = 0;
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

				int tileset = JSONgetters.getIntJSON((JSONObject) layer, "tileset");
				JSONArray tilesets = JSONgetters.getArrayJSON(
						global.get("tilesets").toString());
				fg = JSONgetters.getIntJSON((JSONObject) tilesets.get(tileset), "firstgid");

				loadLayer(spriteLy, tileset, fg);
			}

			if ( ly.toString().contains("objectgroup") )
			{

				JSONObject c = JSONgetters.getObjectJSON(layer.toString());
				JSONArray colisions = JSONgetters.getArrayJSON(c.get("objects").toString());

				loadColisions(colisions);

			}
		}

	}

	private void loadColisions ( JSONArray colisions )
	{
		for ( Object colision : colisions )
		{
			JSONObject box = JSONgetters.getObjectJSON(colision.toString());
			int w = JSONgetters.getIntJSON(box, "width"),
					h = JSONgetters.getIntJSON(box, "height"),
					x = JSONgetters.getIntJSON(box, "x"), y = JSONgetters.getIntJSON(box, "y");

			mapa.getColisions().add(new ColisionBox(w, h, x + vars.screenOffSet, y));
		}
	}

	private void loadLayer ( String[] spriteLy, int tileSet, int firstgid )
	{
		SpriteSheetController ssc = new SpriteSheetController(String.valueOf(tileSet));
		ArrayList<BufferedImage> sprites = new ArrayList<>();
		BufferedImage bi;
		for ( int i = 0; i < spriteLy.length; i++ )
		{
			bi = (spriteLy[i].equals("0")) ? ssc.getSs().getSprites()[0]
					: ssc.getSs().getSprites()[Integer.parseInt(spriteLy[i]) - firstgid];
			sprites.add(bi);

		}

		mapa.getSpriteLayers().add(sprites);
	}

	public GameMap getMapa ()
	{
		return mapa;
	}
}

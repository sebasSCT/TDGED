package controller.scene;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.logic.ColisionBox;
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

		mapa.setWidthTiles(JSONgetters.getIntJSON(global, "width"));
		mapa.setHeightTiles(JSONgetters.getIntJSON(global, "height"));
		mapa.setPosIni(new Point(	JSONgetters.getIntJSON(global, "posiniX"),
									JSONgetters.getIntJSON(global, "posiniY")));
		mapa.setBG(vars.getBG(String.valueOf(JSONgetters.getIntJSON(global, "background"))));

		JSONArray layers = JSONgetters.getArrayJSON(global.get("layers").toString());

		String[] spriteLy;
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

			mapa.getColisions().add(new ColisionBox(w, h, x, y));
		}
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

	public GameMap getMapa ()
	{
		return mapa;
	}
}

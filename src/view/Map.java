package view;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.staticTools.GetResources;
import model.staticTools.JSONgetters;

public class Map
{

	private int widthTiles, heightTiles;
	private String ruta;
	private JSONObject global;

	public Map ( String ruta )
	{
		this.ruta = ruta;

		extraer();
	}

	private void extraer ()
	{
		String archivo = GetResources.leerArchivoTxt(ruta);

		global = JSONgetters.getObjectJSON(archivo);

		widthTiles = JSONgetters.getIntJSON(global, "width");
		heightTiles = JSONgetters.getIntJSON(global, "height");

		JSONArray layers = JSONgetters.getArrayJSON(global.get("layers").toString());
	}
}

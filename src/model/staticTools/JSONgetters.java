package model.staticTools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONgetters
{

	public static JSONObject getObjectJSON ( String codigo )
	{
		JSONParser reader = new JSONParser();
		JSONObject object = null;

		try
		{
			Object recovery = reader.parse(codigo);
			object = (JSONObject) recovery;
		}

		catch ( org.json.simple.parser.ParseException e )
		{
			e.printStackTrace();
		}

		return object;
	}

	public static JSONArray getArrayJSON ( String codigo )
	{
		JSONParser reader = new JSONParser();
		JSONArray object = null;

		try
		{
			Object recovery = reader.parse(codigo);
			object = (JSONArray) recovery;
		}

		catch ( org.json.simple.parser.ParseException e )
		{
			e.printStackTrace();
		}

		return object;
	}

	public static int getIntJSON ( JSONObject object, String key )
	{
		double d = Double.parseDouble(object.get(key).toString());
		return (int) d;
	}
}

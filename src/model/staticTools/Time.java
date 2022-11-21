package model.staticTools;

public class Time
{

	// DOES NOT WORK

	private static float time;

	public static boolean time ( float duration )
	{
		time += vars.time;

		if ( time >= duration )
		{
			time = 0;
			return true;
		}

		return false;

	}

}

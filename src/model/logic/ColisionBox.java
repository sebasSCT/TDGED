package model.logic;

import java.awt.Point;
import java.awt.Rectangle;

public class ColisionBox
{

	private Rectangle box;

	public ColisionBox ( int width, int height, int x, int y )
	{
		box = new Rectangle(x, y, width, height);
	}

	public ColisionBox ( int width, int height, Point pos )
	{
		box = new Rectangle(pos.x, pos.y, width, height);
	}

	public Rectangle getBox ()
	{
		return box;
	}

}

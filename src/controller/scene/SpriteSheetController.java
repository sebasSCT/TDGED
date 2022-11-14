package controller.scene;

import model.scene.SpriteSheet;
import model.staticTools.vars;

public class SpriteSheetController
{
	// guardar Spritesheets?

	private SpriteSheet ss;

	// Constructor para tilesets
	public SpriteSheetController ( String tileset )
	{
		ss = new SpriteSheet(vars.getTileset(tileset), vars.spriteSize);
		cargarSS();
		System.out.println("SpriteSheetController");
	}

	// Constructor para entidades
	public SpriteSheetController ( String type, String tileset )
	{
		ss = new SpriteSheet(vars.getTilesetE(type, tileset), vars.entitySpriteSize);
		cargarSS();
		System.out.println("SpriteSheetController");

	}

	// Constructor para otros
	public SpriteSheetController ( String type, String tileset, int spriteSize )
	{
		ss = new SpriteSheet(vars.getTilesetE(type, tileset), spriteSize);
		cargarSS();
		System.out.println("SpriteSheetController");

	}

	private void cargarSS ()
	{
		int posicionX, posicionY;

		for ( int y = 0; y < ss.getHeight(); y++ )
		{
			for ( int x = 0; x < ss.getWidth(); x++ )
			{
				posicionX = x * ss.getSpriteSize();
				posicionY = y * ss.getSpriteSize();

				ss.getSprites()[x + y * ss.getWidth()] = ss.getImg().getSubimage(posicionX,
						posicionY, ss.getSpriteSize(), ss.getSpriteSize());
			}
		}
	}

	public SpriteSheet getSs ()
	{
		return ss;
	}

}

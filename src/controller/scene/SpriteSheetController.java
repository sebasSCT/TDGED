package controller.scene;

import java.awt.image.BufferedImage;
import model.scene.SpriteSheet;
import model.staticTools.vars;

public class SpriteSheetController
{
	// guardar Spritesheets?

	private SpriteSheet ss;
	private int posicionX, posicionY;

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
		ss = new SpriteSheet(vars.getTilesetE(type, tileset), vars.spriteSize);
		cargarSS();
		System.out.println("SpriteSheetController");

	}

	private void cargarSS ()
	{
		BufferedImage bi;
		for ( int y = 0; y < ss.getHeight(); y++ )
		{
			for ( int x = 0; x < ss.getWidth(); x++ )
			{
				posicionX = x * ss.getSpriteSize();
				posicionY = y * ss.getSpriteSize();

				bi = ss.getImg().getSubimage(posicionX, posicionY, ss.getSpriteSize(),
						ss.getSpriteSize());
				ss.getSprites()[x + y * ss.getWidth()] = bi;
			}
		}
	}

	public SpriteSheet getSs ()
	{
		return ss;
	}

}

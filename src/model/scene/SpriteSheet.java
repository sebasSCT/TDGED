package model.scene;

import java.awt.image.BufferedImage;
import model.staticTools.GetResources;

public class SpriteSheet
{

	private int width, height;
	private int spriteSize;
	private BufferedImage[] sprites;
	private BufferedImage img;

	public SpriteSheet ( String ruta, int spriteSize )
	{
		img = GetResources.ciTranslucida(ruta);
		width = img.getWidth() / spriteSize;
		height = img.getHeight() / spriteSize;
		this.spriteSize = spriteSize;
		sprites = new BufferedImage[width * height];

	}

	public int getWidth ()
	{
		return width;
	}

	public int getHeight ()
	{
		return height;
	}

	public int getSpriteSize ()
	{
		return spriteSize;
	}

	public BufferedImage[] getSprites ()
	{
		return sprites;
	}

	public BufferedImage getImg ()
	{
		return img;
	}

}

package controller.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import controller.scene.SpriteSheetController;
import model.entities.active.Active;
import view.DrawAnimation;

public abstract class EntityController
{

	protected DrawAnimation[] da;

	public EntityController ()
	{
	}

	public void draw ( Graphics g )
	{
		for ( DrawAnimation d : da )
		{
			d.draw(g);
		}
	}

	protected void loadAnim ( String type, String tileset, Active e )
	{
		SpriteSheetController ssc = new SpriteSheetController(type, tileset);

		for ( int x = 0; x < ssc.getSs().getHeight(); x++ )
		{
			ArrayList<BufferedImage> imgs = new ArrayList<>();

			for ( int a = 0; a < ssc.getSs().getWidth(); a++ )
			{
				imgs.add(ssc.getSs().getSprites()[a + (ssc.getSs().getWidth() * x)]);
			}
			e.getAnimations().put("a" + x, imgs);
		}
	}
}

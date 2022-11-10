package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import controller.Controller;
import controller.scene.SpriteSheetController;
import model.entities.active.Active;
import model.staticTools.vars;
import view.DrawAnimation;

public abstract class EntityController implements Controller
{

	protected ArrayList<DrawAnimation> da;
	protected ArrayList<Active> ents;

	public EntityController ()
	{
		this.ents = new ArrayList<>();
		this.da = new ArrayList<>();
	}

	public void draw ( Graphics g )
	{
		for ( DrawAnimation d : da )
		{
			d.draw(g);
		}

		g.setColor(Color.red);

		if ( vars.kb.isActive('}') )
		{
			g.drawString("ShowColisionsON", 10, 280);
			drawColisions(g);
		}

	}

	public void update ()
	{
	}

	protected void animDirections ()
	{
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

	protected void loadSprite ( String tileset, Active e )
	{
		SpriteSheetController ssc = new SpriteSheetController("material", tileset);

		for ( int x = 0; x < ssc.getSs().getHeight(); x++ )
		{
			for ( int a = 0; a < ssc.getSs().getWidth(); a++ )
			{
				BufferedImage img = ssc.getSs().getSprites()[a + (ssc.getSs().getWidth() * x)];
				e.getSprites().put(x + "" + a, img);
			}
		}
	}

	public void drawColisions ( Graphics g )
	{
		g.setColor(Color.green);
		for ( Active e : ents )
		{
			g.drawRect(e.getCB().getBox().x, e.getCB().getBox().y, e.getCB().getBox().width,
					e.getCB().getBox().height);
		}
	}

	public ArrayList<Active> getEnts ()
	{
		return ents;
	}

}

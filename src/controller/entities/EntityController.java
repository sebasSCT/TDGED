package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import controller.Controller;
import controller.scene.SpriteSheetController;
import model.entities.Entity;
import model.entities.active.Active;
import model.entities.inactive.Inactive;
import model.staticTools.vars;
import view.DrawAnimation;

public abstract class EntityController implements Controller
{

	protected Hashtable<String, String> objList;
	protected SpriteSheetController ssc;

	protected ArrayList<DrawAnimation> da;

	// deprecated
	protected ArrayList<Active> ents;
	protected ArrayList<Inactive> entsI;

	protected String entType;

	public EntityController ()
	{
		this.ents = new ArrayList<>();
		this.entsI = new ArrayList<>();
		this.da = new ArrayList<>();
		objList = new Hashtable<>();
	}

	public void draw ( Graphics g )
	{
		for ( DrawAnimation d : da )
		{
			d.draw(g);
		}

		g.setColor(Color.red);

		if ( vars.kb.isActive("showCols") )
		{
			g.drawString("ShowColisionsON", 10, 280);
			drawColisions(g);
		}

	}

	public void update ()
	{
	}

	protected void loadAnim ( String tileset, Entity e )
	{
		SpriteSheetController ssc = new SpriteSheetController(entType, tileset);

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

	protected void loadAnim ( SpriteSheetController ssc, Entity e )
	{

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
		SpriteSheetController ssc = new SpriteSheetController(entType, tileset);

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
		for ( Entity e : entsI )
		{
			g.drawRect(e.getCB().getBox().x, e.getCB().getBox().y, e.getCB().getBox().width,
					e.getCB().getBox().height);
		}
		for ( Entity e : ents )
		{
			g.drawRect(e.getCB().getBox().x, e.getCB().getBox().y, e.getCB().getBox().width,
					e.getCB().getBox().height);
		}
	}

	public ArrayList<Active> getEnts ()
	{
		return ents;
	}

	public ArrayList<Inactive> getEntsI ()
	{
		return entsI;
	}

	public String getEntType ()
	{
		return entType;
	}

}

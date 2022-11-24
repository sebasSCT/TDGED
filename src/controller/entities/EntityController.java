package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import controller.Controller;
import controller.scene.SpriteSheetController;
import model.entities.Entity;
import model.entities.active.Active;
import model.entities.inactive.Inactive;
import model.staticTools.vars;
import view.DrawAnimation;

public abstract class EntityController implements Controller
{

	protected Hashtable<String, String> entList;
	protected SpriteSheetController ssc;

	protected ArrayList<DrawAnimation> da;

	// deprecated
	protected Hashtable<Integer, Active> ents;
	protected ArrayList<Inactive> entsI;

	protected String entType;

	public EntityController ()
	{
		this.ents = new Hashtable<>();
		this.entsI = new ArrayList<>();
		this.da = new ArrayList<>();
		entList = new Hashtable<>();
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
		ssc = new SpriteSheetController(entType, tileset);

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

	protected void loadAnim ( String tileset, Entity e, int size, String key )
	{
		ssc = new SpriteSheetController(entType, tileset, size);

		for ( int x = 0; x < ssc.getSs().getHeight(); x++ )
		{
			ArrayList<BufferedImage> imgs = new ArrayList<>();

			for ( int a = 0; a < ssc.getSs().getWidth(); a++ )
			{
				imgs.add(ssc.getSs().getSprites()[a + (ssc.getSs().getWidth() * x)]);
			}
			e.getAnimations().put(key + x, imgs);
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
		for ( Entry<Integer, Active> e : ents.entrySet() )
		{
			g.drawRect(e.getValue().getCB().getBox().x, e.getValue().getCB().getBox().y,
					e.getValue().getCB().getBox().width, e.getValue().getCB().getBox().height);
		}
	}

	public Hashtable<Integer, Active> getEnts ()
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

	public ArrayList<DrawAnimation> getDa ()
	{
		return da;
	}

}

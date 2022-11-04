package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import controller.scene.SpriteSheetController;
import model.entities.active.Active;
import model.entities.active.Material;
import model.logic.ColisionBox;
import view.DrawAnimation;

public class MaterialController extends ActiveEntityController
{

	private Hashtable<String, String> materials;
	private SpriteSheetController ssc;

	public MaterialController ( ArrayList<ColisionBox> cbm )
	{
		super(cbm);
		materials = new Hashtable<>();
		ssc = new SpriteSheetController("material", "materials");
		startList();

		System.out.println("Material Controller");
	}

	private void startList ()
	{
		// (id)-(peso)-(offsetX)-(offsetY)
		materials.put("cannonball", "00-3-12-24");
		materials.put("cannonball2", "01-3-9-18");
		materials.put("santy", "04-3-10-18");
	}

	public void update ()
	{
		inertia();
		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
		g.setColor(Color.green);
		for ( Active e : ents )
		{
			g.drawRect(e.getCB().getBox().x, e.getCB().getBox().y, e.getCB().getBox().width,
					e.getCB().getBox().height);
		}
	}

	float time = 0;
	boolean up = false;
	private void inertia ()
	{
		for ( Active e : ents )
		{
			if ( e.getVel() != 0 )
			{
				move(e.getDirection(), e);
				move("up", e);

				time += (float) 0.016;
				if ( time >= 0.09 )
				{
					if ( e.getVel() > 1 )
					{
						e.setVel(e.getVel() - 1);
						time = 0;
					}
					if ( time >= 0.5 )
					{
						e.setVel(0);
						time = 0;
					}
				}

				if ( colision(e, "down") )
				{
					e.setVel(0);
				}
			}
		}
	}

	public void carrying ( Point pos, int ind )
	{
		ents.get(ind).setPos(new Point(pos.x, pos.y - 30));
	}

	public void addMaterial ( String name, Point pos )
	{
		String[] data = materials.get(name).split("-");

		ents.add(new Material(data[0], pos, Double.parseDouble(
				data[1]), new Point(Integer.parseInt(data[2]), Integer.parseInt(data[3]))));

		ents.get(ents.size() - 1).setSprite(
				ssc.getSs().getSprites()[Integer.parseInt(ents.get(ents.size() - 1).getID())]);
		da.add(new DrawAnimation(ents.get(ents.size() - 1)));
	}

}

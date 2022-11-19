package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import controller.scene.SpriteSheetController;
import model.entities.active.Active;
import model.entities.active.Material;
import model.logic.ColisionBox;
import view.DrawAnimation;

public class MaterialController extends ActiveEntityController
{

	public MaterialController ( ArrayList<ColisionBox> cbm )
	{
		super(cbm);
		entType = "material";

		ssc = new SpriteSheetController(entType, "materials");
		startList();

		System.out.println("Material Controller");
	}

	private void startList ()
	{
		// (id)-(peso)-(offsetX)-(offsetY)-(idanim)
		entList.put("cannonball", "00-3-12-24-a0");
		entList.put("cannonball2", "02-3-12-23-a0");
	}

	public void update ()
	{
		// inertia(); //ta complejo
		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	private boolean border;
	public void border ( Active[] e )
	{
		for ( int i = 0; i < getEnts().size(); i++ )
		{
			border = false;
			for ( Active ee : e )
			{
				if ( getEnts().get(i).getCB().getBox().intersects(ee.getCB().getBox()) )
				{
					border = true;
					break;
				}
			}

			if ( border )
			{
				da.get(i).setAnimation(ents.get(i).getIDA(), "static",
						Integer.parseInt(ents.get(i).getID()) + 1);
			}

			else
			{
				da.get(i).setAnimation(ents.get(i).getIDA(), "static",
						Integer.parseInt(ents.get(i).getID()));
			}
		}
	}

	public void addMaterial ( String name, Point pos )
	{
		String[] data = entList.get(name).split("-");

		ents.add(new Material(data[0], pos, Double.parseDouble(
				data[1]), new Point(Integer.parseInt(data[2]), Integer.parseInt(data[3]))));

		loadAnim("materials", ents.get(ents.size() - 1));
		da.add(new DrawAnimation(ents.get(ents.size() - 1)));
		da.get(ents.size() - 1).setAnimation(data[4], "static", Integer.parseInt(data[0]));
		ents.get(ents.size() - 1).setIdanim(data[4]);
	}

	float time = 0;
	boolean up = false;
	@ SuppressWarnings ( "unused" )
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

	public void carrying ( Point pos, int ind, int offsety )
	{
		ents.get(ind).setPos(new Point(pos.x, pos.y - offsety - 5));
	}

}

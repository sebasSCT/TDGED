package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import controller.scene.SpriteSheetController;
import model.entities.active.Material;
import model.logic.ColisionBox;
import model.staticTools.vars;
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
		// (id)-(peso)-(offsetX)-(offsetY)-(idanim)-(sprite)
		entList.put("cannonball", "cannonball-3-12-24-a0-2");
		entList.put("gunpowder", "gunpowder-3-12-23-a0-0");
		entList.put("block", "block-3-11-22-a1-0");
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
	public void border ( ColisionBox[] e )
	{
		for ( int i = 0; i < getEnts().size(); i++ )
		{
			if ( ents.get(i).isHiden() )
			{
				continue;
			}

			border = false;
			for ( ColisionBox ee : e )
			{
				if ( getEnts().get(i).getCB().getBox().intersects(ee.getBox()) )
				{
					border = true;
					break;
				}
			}

			if ( border )
			{
				da.get(i).setAnimation(ents.get(i).getIDA().getA(), "static",
						Integer.parseInt(ents.get(i).getIDA().getB()) + 1);
				continue;
			}

			da.get(i).setAnimation(ents.get(i).getIDA().getA(), "static",
					Integer.parseInt(ents.get(i).getIDA().getB()));

		}
	}

	public void addMaterial ( String name, int posx, int posy )
	{
		String[] data = entList.get(name).split("-");

		ents.add(new Material(data[0], new Point(posx, posy), Double.parseDouble(
				data[1]), new Point(Integer.parseInt(data[2]), Integer.parseInt(data[3]))));

		loadAnim("materials", ents.get(ents.size() - 1));
		da.add(new DrawAnimation(ents.get(ents.size() - 1)));
		da.get(ents.size() - 1).setAnimation(data[4], "static", Integer.parseInt(data[5]));
		ents.get(ents.size() - 1).setIdanim(data[4], data[5]);

		vars.entities++;
	}

	float time = 0;
	boolean shoot = false;
	private void shootMove ( int ind, String direction )
	{
		move("left", ents.get(ind));
		// move("down", ents.get(ind));
	}

	public void carrying ( Point pos, int ind, int offsety )
	{
		ents.get(ind).setPos(new Point(pos.x, pos.y - offsety - 5));
	}

	public void hide ( int ind )
	{
		da.get(ind).setAnimation("a1", "static", 6);
		ents.get(ind).setPosTile(0, 0);
		ents.get(ind).setHiden(true);
	}

	public void delete ( int ind )
	{

		da.remove(ind);
		ents.remove(ind);

		vars.entities--;
	}

	public void shoot ( int ind, Point pos, String direction )
	{
		ents.get(ind).setPos(pos);
		da.get(ind).setAnimation(ents.get(ind).getIDA().getA(), "static",
				Integer.parseInt(ents.get(ind).getIDA().getB()));
		shootMove(ind, direction);
	}

}

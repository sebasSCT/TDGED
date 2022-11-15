package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import model.entities.inactive.Structure;
import view.DrawAnimation;

public class StructureController extends InactiveEntityController
{

	public StructureController ()
	{
		super();
		entType = "structure";

		startList();

		System.out.println("StructureController");
	}

	private void startList ()
	{
		// (id)-(offsetX)-(offsetY)-(anim)
		entList.put("cannon", "00-6-15");
	}

	public void update ()
	{
		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	public void addStructure ( String name, Point pos, String direction )
	{
		String[] data = entList.get(name).split("-");

		entsI.add(new Structure(data[0], pos, new Point(Integer.parseInt(data[1]),
														Integer.parseInt(data[2]))));
		loadAnim(name, entsI.get(entsI.size() - 1));

		da.add(new DrawAnimation(entsI.get(entsI.size() - 1)));

		switch ( direction )
		{
			case "right":
				da.get(entsI.size() - 1).setAnimation("a0", "static", 0);
				break;

			case "left":
				da.get(entsI.size() - 1).setAnimation("a0", "static", 1);
				break;
		}

	}

}

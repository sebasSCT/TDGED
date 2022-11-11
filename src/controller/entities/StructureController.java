package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import controller.scene.SpriteSheetController;
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
		// (id)-(offsetX)-(offsetY)
		objList.put("cannon", "00-6-15");
	}

	public void update ()
	{
		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	public void addStructure ( String name, Point pos )
	{
		String[] data = objList.get(name).split("-");
		ssc = new SpriteSheetController(entType, name);

		entsI.add(new Structure(data[0], pos, new Point(Integer.parseInt(data[1]),
														Integer.parseInt(data[2]))));

		entsI.get(entsI.size() - 1).setSprite(ssc.getSs().getSprites()[Integer.parseInt(
				entsI.get(entsI.size() - 1).getID())]);
		loadAnim(ssc, entsI.get(entsI.size() - 1));
		da.add(new DrawAnimation(entsI.get(entsI.size() - 1)));
		da.get(entsI.size() - 1).setAnimation("a4");
	}

}

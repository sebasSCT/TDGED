package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import model.entities.inactive.Structure;
import model.logic.dataStructure.Duplet;
import model.staticTools.vars;
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
		entList.put("cannon", "cannon-6-15");
	}

	public void update ()
	{
		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	public void action ()
	{
		for ( int i = 0; i < entsI.size(); i++ )
		{

		}
	}

	public void addStructure ( String name, Point pos, Duplet<String, String> anim )
	{
		String[] data = entList.get(name).split("-");

		entsI.add(new Structure(data[0], pos, new Point(Integer.parseInt(data[1]),
														Integer.parseInt(data[2]))));
		loadAnim(name, entsI.get(entsI.size() - 1));

		da.add(new DrawAnimation(entsI.get(entsI.size() - 1)));

		switch ( anim.getA() )
		{
			case "right":
				da.get(entsI.size() - 1).setAnimation(anim.getB(), "static", 0);
				break;

			case "left":
				da.get(entsI.size() - 1).setAnimation(anim.getB(), "static", 1);
				break;
		}

		vars.entities++;

	}

}

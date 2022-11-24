package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import model.entities.inactive.Structure;
import model.logic.ColisionBox;
import model.logic.dataStructure.Duplet;
import model.logic.dataStructure.Triplet;
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
		// (id)-(offsetX)-(offsetY)-(anim)-(type)-(material(s))
		entList.put("cannon", "cannon-6-15-weapon-cannonball");
		entList.put("cannonball_table", "cannonball_table-7-16-table-cannonball");
	}

	public void update ()
	{
		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	private Structure s;
	private boolean pressed[] =
	{ false, false };
	private float[] time = new float[2];
	public Triplet<Integer, Point, String> interact ( Duplet<String, Integer>[] carry )
	{
		for ( int a = 0; a < pressed.length; a++ )
		{
			if ( !pressed[a] )
			{
				continue;
			}
			time[a] += (float) 0.016;
			if ( time[a] >= 1 )
			{
				time[a] = 0;
				pressed[a] = false;
			}
		}

		for ( int x = 0; x < entsI.size(); x++ )
		{

			s = (Structure) entsI.get(x);

			if ( vars.kb.isPressed("interact") && entsI.get(x).getColision()[0] )
			{
				return action(s, x, carry[0], 0);
			}

			if ( vars.kb.isPressed("interact1") && entsI.get(x).getColision()[1] )
			{
				return action(s, x, carry[1], 1);
			}

		}
		return new Triplet<Integer, Point, String>(99, null, null);
	}

	private Triplet<Integer, Point, String> action (	Structure s, int x,
														Duplet<String, Integer> carry, int p )
	{
		switch ( s.getType() )
		{
			case "weapon":
				if ( !s.getCharged().getA() )
				{
					if ( carry.getA() == null )
					{
						return new Triplet<Integer, Point, String>(99, null, null);
					}

					if ( s.getMaterial()[0].equals(carry.getA()) )
					{
						pressed[p] = true;
						s.setCharged(true, carry.getB());
						return new Triplet<Integer, Point, String>(p, null, null);
					}

					return new Triplet<Integer, Point, String>(99, null, null);
				}

				if ( !pressed[p] )
				{
					s.setCooldown(true);
					return shoot(x, s);
				}

				return new Triplet<Integer, Point, String>((s.getDirection() == "left") ? 901
						: 902, null, null);

			case "table":
				if ( !pressed[p] )
				{
					pressed[p] = true;
					s.setCooldown(true);
					return new Triplet<Integer, Point, String>(500 + p, null, null);
				}
				s.setCooldown(false);
				return new Triplet<Integer, Point, String>(99, null, null);
		}

		System.err.println("Something Went Wrong...");
		System.err.println("Structure Controller: action()");
		return new Triplet<Integer, Point, String>(99, null, null);
	}

	private Triplet<Integer, Point, String> shoot ( int s, Structure st )
	{
		int r = st.getCharged().getB();
		st.setCharged(false, 9999);
		da.get(s).setAnimation(
				String.valueOf((Integer.parseInt(entsI.get(s).getIDA().getA()) + 3)), "once",
				Integer.parseInt(entsI.get(s).getIDA().getB()));
		return new Triplet<Integer, Point, String>(r + 100, st.getPos(), st.getDirection());
	}

	private boolean border;
	public void border ( ColisionBox[] CB )
	{
		for ( int i = 0; i < getEntsI().size(); i++ )
		{
			border = false;
			for ( int y = 0; y < CB.length; y++ )
			{
				if ( getEntsI().get(i).getCB().getBox().intersects(CB[y].getBox()) )
				{
					border = true;
					getEntsI().get(i).getColision()[y] = true;
					break;
				}
				getEntsI().get(i).getColision()[y] = false;
			}

			s = (Structure) entsI.get(i);

			if ( s.getCharged().getA() )
			{
				da.get(i).setAnimation(
						String.valueOf((Integer.parseInt(entsI.get(i).getIDA().getA()) + 2)),
						"static", Integer.parseInt(entsI.get(i).getIDA().getB()));
				continue;
			}

			if ( border )
			{

				da.get(i).setAnimation(
						String.valueOf((Integer.parseInt(entsI.get(i).getIDA().getA()) + 1)),
						"static", Integer.parseInt(entsI.get(i).getIDA().getB()));
				continue;
			}
			da.get(i).setAnimation(entsI.get(i).getIDA().getA(), "static",
					Integer.parseInt(entsI.get(i).getIDA().getB()));

		}
	}

	public void addStructure ( String name, Point pos, Duplet<String, String> anim )
	{
		String[] data = entList.get(name).split("-");

		entsI.add(new Structure(
								data[0], pos, new Point(Integer.parseInt(data[1]),
														Integer.parseInt(data[2])),
								data[3], data[4]));
		loadAnim(name, entsI.get(entsI.size() - 1), vars.entitySpriteSize, "");

		da.add(new DrawAnimation(entsI.get(entsI.size() - 1)));

		entsI.get(entsI.size() - 1).setDirection(anim.getA());
		switch ( anim.getA() )
		{
			case "right":
				da.get(entsI.size() - 1).setAnimation(anim.getB(), "static", 0);
				entsI.get(entsI.size() - 1).setIdanim(anim.getB(), "0");
				break;

			case "left":
				da.get(entsI.size() - 1).setAnimation(anim.getB(), "static", 1);
				entsI.get(entsI.size() - 1).setIdanim(anim.getB(), "1");
				break;
		}

		vars.entities++;

	}

}

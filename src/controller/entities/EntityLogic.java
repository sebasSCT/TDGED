package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import controller.Controller;
import model.entities.active.Character;
import model.entities.active.Material;
import model.logic.dataStructure.Triplet;
import model.scene.GameMap;
import model.staticTools.vars;

public class EntityLogic implements Controller
{

	String players;

	private CharacterController cc;
	private MaterialController mtc;
	private StructureController stc;

	public EntityLogic ( String players, GameMap map )
	{
		this.players = players;

		cc = new CharacterController(this.players, map.getPosIni(), map.getColisions());
		mtc = new MaterialController(map.getColisions());
		stc = new StructureController();

		mtc.addMaterial("cannonball2", new Point(20, 10));
		mtc.addMaterial("cannonball2", new Point(22, 10));
		mtc.addMaterial("cannonball", new Point(24, 10));

		for ( Triplet<String, Point, String> st : map.getStructures() )
		{
			stc.addStructure(st.getA(), st.getB(), st.getC());
		}
	}

	public void update ()
	{
		carryMaterial();
		stc.update();
		mtc.update();
		cc.update();
	}

	public void draw ( Graphics g )
	{
		stc.draw(g);
		mtc.draw(g);
		cc.draw(g);
	}

	private Material m;
	private boolean[] pressed = new boolean[2];
	private float[] time = new float[2];
	private Character[] p = new Character[2];
	private void carryMaterial ()
	{

		for ( int i = 0; i < p.length; i++ )
		{
			p[i] = (Character) cc.getEnts().get(i);

			if ( p[i].isCarrying() )
			{
				mtc.carrying(p[i].getPos(), p[i].getCarry(), p[i].getOffset().y);
				mtc.border(true, p[i].getCarry());
			}

			else
			{
				border(p[i]);
			}

			if ( pressed[i] )
			{
				time[i] += (float) 0.016;
				if ( time[i] >= (float) 0.3 )
				{
					pressed[i] = false;
					time[i] = 0;
				}
			}
		}

		if ( vars.kb.isPressed("interact") && !pressed[0] )
		{
			setCarry(p[0]);
			pressed[0] = true;
		}

		if ( vars.kb.isPressed("interact1") && !pressed[1] )
		{
			setCarry(p[1]);
			pressed[1] = true;
		}

	}

	private void setCarry ( Character p )
	{
		if ( !p.isCarrying() )
		{
			for ( int i = 0; i < mtc.getEnts().size(); i++ )
			{
				if ( mtc.getEnts().get(i).getCB().getBox().intersects(p.getCB().getBox()) )
				{
					m = (Material) mtc.getEnts().get(i);
					m.setCarry(true);
					p.setCarrying(true);
					p.setCarry(i);
				}
			}
			return;
		}

		m = (Material) mtc.getEnts().get(p.getCarry());
		m.setCarry(false);
		p.setCarrying(false);
	}

	private void border ( Character p )
	{
		for ( int i = 0; i < mtc.getEnts().size(); i++ )
		{
			if ( mtc.getEnts().get(i).getCB().getBox().intersects(p.getCB().getBox()) )
			{
				mtc.border(true, i);
				return;
			}
			mtc.border(false, i);
		}
	}

	public void drawColisions ( Graphics g )
	{
		stc.drawColisions(g);
		mtc.drawColisions(g);
		cc.drawColisions(g);
	}

}

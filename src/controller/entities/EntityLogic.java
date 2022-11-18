package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import controller.Controller;
import model.entities.active.Character;
import model.entities.active.Material;
import model.logic.dataStructure.Triplet;
import model.scene.GameMap;
import model.staticTools.vars;

public class EntityLogic implements Controller
{

	private String players;
	private ArrayList<Rectangle> tower;
	private int towerPS;

	private CharacterController cc;
	private EnemyController ec;
	private MaterialController mtc;
	private StructureController stc;

	public EntityLogic ( String players, GameMap map )
	{
		this.players = players;
		this.tower = map.getTC();
		this.towerPS = map.getTowerPS();

		cc = new CharacterController(this.players, map.getPosIni(), map.getColisions());
		ec = new EnemyController(map.getColisions());
		mtc = new MaterialController(map.getColisions());
		stc = new StructureController();
		mtc.addMaterial("cannonball2", new Point(15, 14));
		mtc.addMaterial("cannonball2", new Point(23, 14));

		ec.addEnemy("one", new Point(0, 19));
		ec.addEnemy("one", new Point(5, 19));
		ec.addEnemy("one", new Point(10, 19));
		ec.addEnemy("one", new Point(30, 19));
		ec.addEnemy("one", new Point(35, 19));
		ec.addEnemy("one", new Point(40, 19));

		for ( Triplet<String, Point, String> st : map.getStructures() )
		{
			stc.addStructure(st.getA(), st.getB(), st.getC());
		}

		System.out.println("Entity Logic");
	}

	public void update ()
	{
		carryMaterial();
		enemyAttack();

		stc.update();
		mtc.update();
		cc.update();
		ec.update();
	}

	public void draw ( Graphics g )
	{
		stc.draw(g);
		mtc.draw(g);
		cc.draw(g);
		ec.draw(g);

		g.setColor(Color.black);
		g.drawString("TOWERPS: " + towerPS, 300, 5);
		g.setColor(Color.red);
	}

	private void enemyAttack ()
	{
		time[2] += 0.016;

		if ( time[2] >= 1 )
		{
			for ( Rectangle r : tower )
			{
				System.out.println("damage: " + ec.attack(r));
				towerPS -= ec.attack(r);
			}
		}

		if ( time[2] >= 1 )
		{
			time[2] = 0;
		}
	}

	private Material m;
	private boolean[] pressed = new boolean[2];
	private float[] time = new float[3];
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

		g.setColor(Color.orange);
		for ( Rectangle r : tower )
		{
			g.drawRect(r.x, r.y, r.width, r.height);
		}
	}

}

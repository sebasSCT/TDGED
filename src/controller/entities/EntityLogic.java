package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import controller.Controller;
import model.entities.active.GameCharacter;
import model.entities.active.Material;
import model.logic.ColisionBox;
import model.logic.dataStructure.Duplet;
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

		cc = new CharacterController(	this.players, map.getPosIni(), map.getColisions(),
										map.getLadders());
		ec = new EnemyController(map.getColisions());
		mtc = new MaterialController(map.getColisions());
		stc = new StructureController();

		// precarga
		mtc.addMaterial("block", 20, 19);

		for ( Triplet<String, Point, Duplet<String, String>> st : map.getStructures() )
		{
			stc.addStructure(st.getA(), st.getB(), st.getC());
		}

		for ( Point p : map.getTables() )
		{
			stc.addStructure("cannonball_table", p, new Duplet<String, String>("right", "0"));
		}

		System.out.println("Entity Logic");
	}

	public void update ()
	{

		characterData();
		carryMaterial();
		enemyAttack();
		interact();
		raids();

		stc.update();
		mtc.update();
		cc.update();
		ec.update();
	}

	public void draw ( Graphics g )
	{
		stc.draw(g);
		cc.draw(g);
		mtc.draw(g);
		ec.draw(g);

		drawTowerPs(g);
	}

	private float timer;
	private boolean[] raids =
	{ true, true, true };
	private void raids ()
	{
		timer += (float) 0.016;

		if ( timer >= 2 && raids[0] )
		{
			ec.addEnemy("two", 0, 18);
			ec.addEnemy("two", 2, 18);
			ec.addEnemy("two", 4, 18);
			ec.addEnemy("two", 36, 18);
			ec.addEnemy("two", 38, 18);
			ec.addEnemy("two", 40, 18);

			raids[0] = false;
		}

		if ( timer >= 25 && raids[1] )
		{
			ec.addEnemy("two", 0, 18);
			ec.addEnemy("two", 2, 18);
			ec.addEnemy("two", 4, 18);
			ec.addEnemy("two", 36, 18);
			ec.addEnemy("two", 38, 18);
			ec.addEnemy("two", 40, 18);

			raids[1] = false;
		}

		if ( timer >= 40 && raids[2] )
		{
			ec.addEnemy("two", 0, 18);
			ec.addEnemy("two", 2, 18);
			ec.addEnemy("two", 4, 18);
			ec.addEnemy("two", 36, 18);
			ec.addEnemy("two", 38, 18);
			ec.addEnemy("two", 40, 18);

			raids[2] = false;
		}

		if ( timer >= 50 )
		{
			ec.addEnemy("two", 0, 19);
			ec.addEnemy("two", 2, 19);
			ec.addEnemy("two", 4, 19);
			ec.addEnemy("two", 36, 19);
			ec.addEnemy("two", 38, 19);
			ec.addEnemy("two", 40, 19);

			timer = 0;
			for ( int i = 0; i < raids.length; i++ )
			{
				raids[i] = true;
			}
		}

	}

	@ SuppressWarnings ( "unchecked" )
	private Duplet<String, Integer>[] carry = new Duplet[2];
	private ColisionBox[] CB = new ColisionBox[2];
	private Triplet<Integer, Point, String> ind;
	private void interact ()
	{
		stc.border(CB);

		ind = stc.interact(carry);

		if ( ind.getA() < 99 )
		{
			p[ind.getA()].setCarrying(false);
			mtc.delete(p[ind.getA()].getCarry());
			return;
		}

		if ( ind.getA() > 100 && ind.getA() < 500 )
		{

			switch ( ind.getA() )
			{
				case 101:
					ec.delEnemy("right");
					break;

				case 102:
					ec.delEnemy("left");
					break;

			}
			return;

		}

		if ( ind.getA() >= 500 )
		{
			p[ind.getA() - 500].setCarry(mtc.addMaterial("cannonball",
					p[ind.getA() - 500].getPos().x, p[ind.getA() - 500].getPos().y));
			p[ind.getA() - 500].setCarrying(true);
			mtc.carrying(p[ind.getA() - 500].getPos(), p[ind.getA() - 500].getCarry(),
					p[ind.getA() - 500].getOffset().y);
		}
	}

	private float[] time = new float[3];
	private void enemyAttack ()
	{
		time[2] += 0.016;

		if ( time[2] >= 1 )
		{
			for ( Rectangle r : tower )
			{
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
	private GameCharacter[] p = new GameCharacter[2];
	private void carryMaterial ()
	{

		for ( int i = 0; i < p.length; i++ )
		{
			p[i] = (GameCharacter) cc.getEnts().get(i);
			CB[i] = p[i].getCB();

			if ( p[i].isCarrying() )
			{
				carry[i] = new Duplet<String, Integer>(mtc	.getEnts().get(p[i].getCarry())
															.getID(),
														p[i].getCarry());
				mtc.carrying(p[i].getPos(), p[i].getCarry(), p[i].getOffset().y);
			}

			else
			{
				carry[i] = new Duplet<String, Integer>(null, 9999);
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

		mtc.border(CB);

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

	private void setCarry ( GameCharacter p )
	{
		if ( !p.isCarrying() )
		{
			for ( int i = 0; i < mtc.getMaterials(); i++ )
			{
				if ( mtc.getEnts().get(i) == null )
				{
					continue;
				}

				if ( mtc.getEnts().get(i).getCB().getBox().intersects(p.getCB().getBox()) )
				{
					m = (Material) mtc.getEnts().get(i);
					m.setCarry(true);
					p.setCarrying(true);
					p.setCarry(i);
					return;
				}
			}
			return;
		}

		m = (Material) mtc.getEnts().get(p.getCarry());
		m.setCarry(false);
		p.setCarrying(false);

	}

	private void characterData ()
	{

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

	private void drawTowerPs ( Graphics g )
	{
		g.setColor(Color.black);
		g.drawString("TOWER: " + towerPS, 300, 100);
		g.setColor(Color.white);
		g.drawString("TOWER: " + towerPS, 300, 101);
	}

}

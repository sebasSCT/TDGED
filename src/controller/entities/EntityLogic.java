package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import controller.Controller;
import model.entities.active.Character;
import model.entities.active.Material;
import model.logic.dataStructure.Pair;
import model.scene.GameMap;
import model.staticTools.vars;

public class EntityLogic implements Controller
{

	String players;
	private float time;

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
		mtc.addMaterial("santy", new Point(18, 10));
		mtc.addMaterial("cannonball", new Point(12, 10));

		for ( Pair<String, Point> st : map.getStructures() )
		{
			stc.addStructure(st.getA(), st.getB());
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

	private boolean pressed;
	private Material m;
	private Character p;
	private void carryMaterial ()
	{

		p = (Character) cc.getEnts().get(0);

		if ( p.isCarrying() )
		{
			mtc.carrying(p.getPos(), p.getCarry());
		}

		if ( pressed )
		{
			time += (float) 0.016;
			if ( time >= (float) 0.2 )
			{
				pressed = false;
			}
		}

		if ( vars.kb.isPressed('e') && !pressed )
		{
			time = 0;
			pressed = true;
			if ( !p.isCarrying() )
			{
				for ( int i = 0; i < mtc.getEnts().size(); i++ )
				{
					if ( mtc.getEnts().get(i).getCB().getBox()
							.intersects(cc.getEnts().get(0).getCB().getBox()) )
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
			// if ( p.isWalking() )
			// {
			// m.setVel(p.getVel() * 5);
			// m.setDirection(p.getDirection());
			// }
			m.setCarry(false);
			p.setCarrying(false);
		}

	}

	public void drawColisions ( Graphics g )
	{
		stc.drawColisions(g);
		mtc.drawColisions(g);
		cc.drawColisions(g);
	}

}

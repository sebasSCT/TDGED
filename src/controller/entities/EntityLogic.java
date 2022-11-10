package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import controller.Controller;
import model.entities.active.Material;
import model.entities.active.Player;
import model.scene.GameMap;
import model.staticTools.vars;

public class EntityLogic implements Controller
{

	String players;
	private float time;

	private CharacterController cc;
	private MaterialController mtc;

	public EntityLogic ( String players, GameMap map )
	{
		this.players = players;

		cc = new CharacterController(this.players, map.getPosIni(), map.getColisions());
		mtc = new MaterialController(map.getColisions());

		mtc.addMaterial("cannonball2", new Point(15, 5));
	}

	public void update ()
	{
		carryMaterial();
		mtc.update();
		cc.update();
	}

	public void draw ( Graphics g )
	{
		mtc.draw(g);
		cc.draw(g);
	}

	private boolean pressed;
	private Material m;
	private Player p;
	private void carryMaterial ()
	{

		p = (Player) cc.getEnts().get(0);

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
		mtc.drawColisions(g);
		cc.drawColisions(g);
	}

}

package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import model.entities.active.Active;
import model.entities.active.Player;
import model.logic.ColisionBox;
import model.staticTools.vars;
import view.DrawAnimation;

public class CharacterController extends ActiveEntityController
{

	public CharacterController ( String id, Point pos, ArrayList<ColisionBox> cbm )
	{
		super(cbm);

		String[] ids = id.split(":");

		for ( int i = 0; i < ids.length; i++ )
		{
			ents.add(new Player((i == 1) ? ids[1] : ids[0],
								new Point(pos.x * vars.spriteSize, pos.y * vars.spriteSize),
								100, 1, new Point(10, 18)));
			da.add(new DrawAnimation(ents.get(i)));
			da.get(i).setAnimation("a0");
			ents.get(i).setDirection("right");
		}

		for ( Active p : ents )
		{
			loadAnim("player", p.getID(), p);
		}
		System.out.println("PlayerController");
	}

	public void update ()
	{

		move();

		super.update();

		for ( Active p : ents )
		{
			gravity(p);
		}

	}

	public void draw ( Graphics g )
	{
		super.draw(g);

		g.setColor(Color.yellow);
		g.drawString("Falling: " + ents.get(0).isFalling(), 270, 210);
		g.drawString("Walking: " + ents.get(0).isWalking(), 270, 220);
		g.drawString("Direction: " + ents.get(0).getDirection(), 270, 230);

	}

	// for para mas de 2 jugadores
	private void move ()
	{
		if ( ents.size() >= 2 )
		{
			p2Keys();
		}
		p1Keys();

	}

	private void p1Keys ()
	{
		if ( vars.kb.isPressed('a') && !colision(ents.get(0), 3) )
		{
			ents.get(0).move("left");
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("left");
			return;
			// da.setAnimation("loop", 1, players[0].getAnimations().get("a1"),
			// players[0].getPos());

		}
		if ( vars.kb.isPressed('d') && !colision(ents.get(0), 2) )
		{
			ents.get(0).move("right");
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("right");
			return;
			// da.setAnimation("loop", 1, players[0].getAnimations().get("a1"),
			// players[0].getPos());

		}
		if ( vars.kb.isPressed('w') )
		{
			ents.get(0).setPos(1, 0);
			ents.get(1).setPos(1, 0);
			// da.setAnimation("loop", 1, players[0].getAnimations().get("a0"),
			// players[0].getPos());

		}
		ents.get(0).setWalking(false);
	}

	private void p2Keys ()
	{
		if ( vars.kb.isPressed('j') && !colision(ents.get(1), 3) )
		{
			ents.get(1).move("left");
			ents.get(1).setWalking(true);
			ents.get(1).setDirection("left");
			return;
			// da.setAnimation("loop", 1, players[1].getAnimations().get("a1"),
			// players[1].getPos());

		}
		if ( vars.kb.isPressed('l') && !colision(ents.get(1), 2) )
		{
			ents.get(1).move("right");
			ents.get(1).setWalking(true);
			ents.get(1).setDirection("right");
			return;
			// da.setAnimation("loop", 1, players[1].getAnimations().get("a1"),
			// players[1].getPos());

		}
		ents.get(1).setWalking(false);
	}
}

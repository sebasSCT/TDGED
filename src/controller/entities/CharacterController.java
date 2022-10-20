package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import model.entities.active.Player;
import model.logic.ColisionBox;
import model.staticTools.vars;
import view.DrawAnimation;

public class CharacterController extends ActiveEntityController
{

	private Player[] players;

	public CharacterController ( String id, Point pos, ArrayList<ColisionBox> cbm )
	{
		super(cbm);

		String[] ids = id.split(":");

		players = new Player[ids.length];
		da = new DrawAnimation[ids.length];
		for ( int i = 0; i < players.length; i++ )
		{
			players[i] = new Player((i == 1) ? ids[1] : ids[0],
									new Point(	pos.x * vars.spriteSize,
												pos.y * vars.spriteSize),
									100, 1, new Point(10, 18));
			da[i] = new DrawAnimation(players[i]);
		}

		for ( Player p : players )
		{
			loadAnim("player", p.getID(), p);

		}
		da[0].setAnimation("a0");
		da[1].setAnimation("a0");
		System.out.println("PlayerController");
	}

	public void update ()
	{
		for ( Player p : players )
		{
			gravity(p);
		}

		move();

		for ( int i = 0; i < players.length; i++ )
		{
			if ( players[i].isMoving() )
			{
				da[i].setAnimation("a1");
			}

			else
			{
				da[i].setAnimation("a0");
			}
		}

	}

	public void draw ( Graphics g )
	{
		super.draw(g);

		g.setColor(Color.green);
		g.drawString("Moving: " + players[0].isMoving(), 270, 210);
	}

	// for para mas de 2 jugadores
	private void move ()
	{
		if ( players.length >= 2 )
		{
			p2Keys();
		}
		p1Keys();

	}

	private void p1Keys ()
	{
		if ( vars.kb.isPressed('a') && !colision(players[0], 3) )
		{
			players[0].move("left");
			players[0].setMoving(true);
			// da.setAnimation("loop", 1, players[0].getAnimations().get("a1"),
			// players[0].getPos());

		}
		if ( vars.kb.isPressed('d') && !colision(players[0], 2) )
		{
			players[0].move("right");
			players[0].setMoving(true);
			// da.setAnimation("loop", 1, players[0].getAnimations().get("a1"),
			// players[0].getPos());

		}
		if ( vars.kb.isPressed('w') )
		{
			players[0].setPos(1, 0);
			// da.setAnimation("loop", 1, players[0].getAnimations().get("a0"),
			// players[0].getPos());

		}
	}

	private void p2Keys ()
	{
		if ( vars.kb.isPressed('j') && !colision(players[1], 3) )
		{
			players[1].move("left");
			players[1].setMoving(true);
			// da.setAnimation("loop", 1, players[1].getAnimations().get("a1"),
			// players[1].getPos());

		}
		if ( vars.kb.isPressed('l') && !colision(players[1], 2) )
		{
			players[1].move("right");
			players[1].setMoving(true);
			// da.setAnimation("loop", 1, players[1].getAnimations().get("a1"),
			// players[1].getPos());

		}
	}

	public Player getPlayer ( int i )
	{
		return players[i];
	}

}

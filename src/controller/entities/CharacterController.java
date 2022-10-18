package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import controller.scene.SpriteSheetController;
import model.entities.active.Player;
import model.logic.ColisionBox;
import model.staticTools.vars;

public class CharacterController extends ActiveEntityController
{

	private Player[] players;

	public CharacterController ( String id, Point pos, ArrayList<ColisionBox> cbm )
	{
		super(cbm);

		String[] ids = id.split(":");

		players = new Player[ids.length];
		for ( int i = 0; i < players.length; i++ )
		{
			players[i] = new Player((i == 1) ? ids[1] : ids[0],
									new Point(	pos.x * vars.spriteSize,
												pos.y * vars.spriteSize),
									2, 2, new Point(10, 18));
			// OFFSET para nuevos sprites y dependiendo los personajes (enum) ?

			SpriteSheetController ssc = new SpriteSheetController("player", (i == 1) ? ids[1]
					: ids[0]);

			for ( int e = 0; e < ssc.getSs().getHeight(); e++ )
			{
				ArrayList<BufferedImage> imgs = new ArrayList<>();

				for ( int a = 0; a < ssc.getSs().getWidth(); a++ )
				{
					imgs.add(ssc.getSs().getSprites()[a + (ssc.getSs().getWidth() * e)]);
				}

				players[i].getAnimations().put("a" + e, imgs);
			}
		}

		System.out.println("PlayerController");
	}

	public void update ()
	{
		for ( Player p : players )
		{
			gravity(p);
		}

		move();

		for ( Player p : players )
		{
			if ( p.isMoving() )
			{
				da.setAnimation("loop", 1, p.getAnimations().get("a0"), p.getPos());
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
			da.setAnimation("loop", 1, players[0].getAnimations().get("a0"),
					players[0].getPos());
			return;
		}
		if ( vars.kb.isPressed('d') && !colision(players[0], 2) )
		{
			players[0].move("right");
			players[0].setMoving(true);
			da.setAnimation("loop", 1, players[0].getAnimations().get("a0"),
					players[0].getPos());
			return;
		}
		if ( vars.kb.isPressed('w') )
		{
			players[0].setPos(1, 0);
			da.setAnimation("loop", 1, players[0].getAnimations().get("a0"),
					players[0].getPos());
			return;
		}
	}

	private void p2Keys ()
	{
		if ( vars.kb.isPressed('j') && !colision(players[1], 3) )
		{
			players[1].move("left");
			players[1].setMoving(true);
			da.setAnimation("loop", 1, players[1].getAnimations().get("a0"),
					players[1].getPos());
			return;
		}
		if ( vars.kb.isPressed('l') && !colision(players[1], 2) )
		{
			players[1].move("right");
			players[1].setMoving(true);
			da.setAnimation("loop", 1, players[1].getAnimations().get("a0"),
					players[1].getPos());
			return;
		}
	}

	public Player getPlayer ( int i )
	{
		return players[i];
	}

}

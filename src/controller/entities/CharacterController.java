package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import model.entities.active.Active;
import model.entities.active.Player;
import model.logic.ColisionBox;
import model.logic.LinkedListTail;
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

		////
		listIns();
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

		g.setColor(Color.black);
		g.drawString("Falling: " + ents.get(0).isFalling(), 10, 30);
		g.drawString("Walking: " + ents.get(0).isWalking(), 10, 40);
		g.drawString("Direction: " + ents.get(0).getDirection(), 10, 50);

	}

	boolean startIns = false; // prueba
	boolean complete = true;
	private void move ()
	{
		if ( ents.size() >= 2 )
		{
			p2Keys();
		}
		p1Keys();

		// Instrucciones prueba (mover)

		if ( vars.kb.isPressed('s') )
		{
			startIns = true;
		}

		if ( startIns && !ins.equals("empty") )
		{
			instructions();
			if ( time >= 1 )
			{
				if ( complete )
				{
					ins = list.unstack().toString();
					time = 0;
				}
			}

			time += (float) 0.016;
		}

	}

	LinkedListTail list = new LinkedListTail(); // prueba

	private void listIns ()
	{
		list.addNode("move:left");
		list.addNode("move:right");
		list.addNode("move:left");
		list.addNode("moveto:player1");
		ins = list.unstack().toString();
	}

	float time;
	String ins;
	private void instructions ()
	{
		String[] inspart = ins.split(":");
		switch ( inspart[0] )
		{
			case "move":
				if ( !ins.equals("empty") )
				{
					ents.get(1).move(inspart[1]);
					ents.get(1).setDirection(inspart[1]);
					return;
				}
				ents.get(1).setWalking(false);
				break;

			case "moveto":
				complete = false;
				if ( ents.get(0).getPos().x != ents.get(1).getPos().x )
				{
					if ( ents.get(0).getPos().x < ents.get(1).getPos().x )
					{
						ents.get(1).move("left");
						ents.get(1).setDirection("left");
					}

					else
					{
						ents.get(1).move("right");
						ents.get(1).setDirection("right");
					}
				}

				else
				{
					complete = true;
				}
				break;
		}

	}

	private void p1Keys ()
	{
		if ( vars.kb.isPressed('a') && !colision(ents.get(0), 3) )
		{
			ents.get(0).move("left");
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("left");
			return;

		}
		if ( vars.kb.isPressed('d') && !colision(ents.get(0), 2) )
		{
			ents.get(0).move("right");
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("right");
			return;

		}
		if ( vars.kb.isPressed('w') )
		{
			ents.get(0).setPos(19, 1);
			ents.get(1).setPos(19, 1);

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

		}
		if ( vars.kb.isPressed('l') && !colision(ents.get(1), 2) )
		{
			ents.get(1).move("right");
			ents.get(1).setWalking(true);
			ents.get(1).setDirection("right");
			return;

		}
		ents.get(1).setWalking(false);
	}
}

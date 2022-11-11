package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import model.entities.active.Active;
import model.entities.active.Character;
import model.logic.ColisionBox;
import model.logic.dataStructure.LinkedListTail;
import model.staticTools.vars;
import view.DrawAnimation;

/**
 * Controlador del Jugardor, extiende de ActiveEntityController para asignar
 * fisicas y demas.
 * 
 * @author sebas_awitvuh, Juan
 *
 */
public class CharacterController extends ActiveEntityController
{

	public CharacterController ( String id, Point pos, ArrayList<ColisionBox> cbm )
	{
		super(cbm);
		entType = "character";

		String[] ids = id.split(":");

		for ( int i = 0; i < ids.length; i++ )
		{
			ents.add(new Character(	(i == 1) ? ids[1] : ids[0], pos, 100, 1, 4,
									new Point(10, 18)));
			// Agrega las animaciones al personaje
			da.add(new DrawAnimation(ents.get(i)));
			// Setea animaciones
			da.get(i).setAnimation("a0");
			ents.get(i).setDirection("right");
		}

		for ( Active p : ents )
		{
			// Carga las animaciones
			loadAnim(p.getID(), p);
		}

		////
		listIns();
		System.out.println("CharacterController");
	}

	/**
	 * Actualiza lo referente al personaje
	 */
	public void update ()
	{
		animDirections();
		actions();

		super.update();

	}

	// Dibuja los personaje
	public void draw ( Graphics g )
	{
		Character a = (Character) ents.get(0);
		super.draw(g);

		// Debug (mover)
		g.setColor(Color.white);
		g.drawString("Falling: " + ents.get(0).isFalling(), 10, 30);
		g.drawString("Walking: " + ents.get(0).isWalking(), 10, 40);
		g.drawString("Direction: " + ents.get(0).getDirection(), 10, 50);
		g.drawString("Carrying: " + a.isCarrying() + " " + a.getCarry(), 10, 60);
		g.setColor(Color.white);
		// g.drawString("entity", ents.get(1).getPos().x + 5,
		// ents.get(1).getPos().y + 5);
		g.setColor(Color.yellow);
		// g.drawString("player", ents.get(0).getPos().x + 5,
		// ents.get(0).getPos().y + 5);
		g.drawString(
				"Pos X: " + ents.get(0).getPos().x + "  Tile: " + ents.get(0).getPos().x / 16,
				10, 80);
		g.drawString(
				"Pos Y: " + ents.get(0).getPos().y + "  Tile: " + ents.get(0).getPos().y / 16,
				10, 90);

		// g.drawRect(ents.get(0).getCB().getBox().x,
		// ents.get(0).getCB().getBox().y,
		// ents.get(0).getCB().getBox().width,
		// ents.get(0).getCB().getBox().height);

	}

	boolean startIns = false; // prueba
	boolean complete = true;
	private void actions ()
	{
		// moviemiento
		if ( ents.size() >= 2 )
		{
			p2Keys();
		}
		p1Keys();

		// Tomar Material

		// Instrucciones prueba (mover)

		if ( vars.kb.isPressed('s') )
		{
			startIns = true;
		}

		if ( startIns && !ins.equals("empty") )
		{
			instructions();
			if ( time >= 3 )
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
		list.addNode("moveto:player");
		list.addNode("follow:player");
		// list.imprimir();
		ins = list.unstack().toString();
	}

	float time;
	String ins;

	// Movimiento del segundo pj "IA".
	private void instructions ()
	{
		String[] inspart = ins.split(":");
		switch ( inspart[0] )
		{
			case "move":
				if ( !ins.equals("empty") && !colision(ents.get(1), inspart[1]) )
				{
					move(inspart[1], ents.get(1));
					ents.get(1).setDirection(inspart[1]);
					return;
				}
				ents.get(1).setWalking(false);
				break;

			case "moveto":
				complete = false;
				if ( ents.get(0).getPos().x != ents.get(1).getPos().x )
				{
					if ( ents.get(0).getPos().x < ents.get(1).getPos().x
							&& !colision(ents.get(1), "left") )
					{
						move("left", ents.get(1));
						ents.get(1).setDirection("left");
					}

					else
					{
						if ( !colision(ents.get(1), "right") )
						{
							move("right", ents.get(1));
							ents.get(1).setDirection("right");
						}
					}
				}

				else
				{
					complete = true;
				}
				break;

			case "follow":
				complete = false;

				if ( ents.get(1).getPos().x != ents.get(0).getPos().x )
				{
					if ( ents.get(1).getPos().x < ents.get(0).getPos().x - 30
							&& !colision(ents.get(1), "right") )
					{
						move("right", ents.get(1));
						ents.get(1).setDirection("right");
					}

					if ( ents.get(1).getPos().x > ents.get(0).getPos().x + 30
							&& !colision(ents.get(1), "left") )
					{
						move("left", ents.get(1));
						ents.get(1).setDirection("left");
					}
				}

				break;
		}

	}

	/**
	 * Clase que se encarga de gestionar el movimiento del personaje con las
	 * teclas del jugador 1.
	 */
	private void p1Keys ()
	{
		if ( vars.kb.isPressed('a') && !colision(ents.get(0), "left") )
		{
			move("left", ents.get(0));
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("left");
			return;

		}
		if ( vars.kb.isPressed('d') && !colision(ents.get(0), "right") )
		{
			move("right", ents.get(0));
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("right");
			return;

		}
		if ( vars.kb.isPressed('w') )
		{
			ents.get(0).setPosTile(19, 1);
			// ents.get(1).setPosTile(19, 1);

		}
		ents.get(0).setWalking(false);
	}

	/**
	 * Clase que se encarga de gestionar el movimiento del personaje con las
	 * teclas del jugador 2.
	 */
	private void p2Keys ()
	{
		if ( vars.kb.isPressed('j') && !colision(ents.get(1), "left") )
		{
			move("left", ents.get(1));
			ents.get(1).setWalking(true);
			ents.get(1).setDirection("left");
			return;

		}
		if ( vars.kb.isPressed('l') && !colision(ents.get(1), "right") )
		{
			move("right", ents.get(1));
			ents.get(1).setWalking(true);
			ents.get(1).setDirection("right");
			return;

		}
		ents.get(1).setWalking(false);
	}

}

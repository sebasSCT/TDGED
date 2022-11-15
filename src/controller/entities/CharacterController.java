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
		startList();
		entType = "character";

		String[] ids = id.split(":");
		String[][] data =
		{ entList.get(ids[0]).split("-"), entList.get(ids[1]).split("-") };

		for ( int i = 0; i < ids.length; i++ )
		{
			ents.add(new Character(	data[i][0], pos, Integer.parseInt(data[i][1]),
									Integer.parseInt(data[i][2]), Integer.parseInt(data[i][3]),
									new Point(	Integer.parseInt(data[i][4]),
												Integer.parseInt(data[i][5])),
									data[i][0]));

			// Agrega las animaciones al personaje
			da.add(new DrawAnimation(ents.get(i)));
			// Setea animaciones
			da.get(i).setAnimation("a0", "loop", 1);
			ents.get(i).setDirection("right");
		}

		// Carga las animaciones
		for ( Active p : ents )
		{
			loadAnim(p.getID(), p);
		}

		////
		listIns();
		System.out.println("CharacterController");
	}

	private void startList ()
	{
		// (id)-(ps)-(vel)-(maxVel)-(offsetX)-(offsetY)
		entList.put("santy", "santy-100-1-4-10-19");
		entList.put("juan", "juan-100-1-4-10-15");
		entList.put("beto", "beto-100-1-4-10-20");
		entList.put("sebas", "sebas-100-1-4-10-20");
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
	private Character a;
	public void draw ( Graphics g )
	{

		for ( Active e : ents )
		{
			a = (Character) e;
			g.setColor(Color.black);
			g.drawString(a.getPlayerName(), a.getPos().x + 6,
					(a.isCarrying()) ? a.getPos().y - 6 : a.getPos().y + 6);
			g.setColor(Color.orange);
			g.drawString(a.getPlayerName(), a.getPos().x + 6,
					(a.isCarrying()) ? a.getPos().y - 5 : a.getPos().y + 5);
		}

		super.draw(g);

		// Debug (mover)
		a = (Character) ents.get(0);
		g.setColor(Color.white);
		g.drawString("Falling: " + ents.get(0).isFalling(), 10, 30);
		g.drawString("Walking: " + ents.get(0).isWalking(), 10, 40);
		g.drawString("Direction: " + ents.get(0).getDirection(), 10, 50);
		g.drawString("Carrying: " + a.isCarrying() + " " + a.getCarry(), 10, 60);
		g.setColor(Color.white);
		// g.drawString("entity", ents.get(1).getPos().x + 5,
		// ents.get(1).getPos().y + 5);
		g.setColor(Color.yellow);

		g.drawString(
				"Pos X: " + ents.get(0).getPos().x + "  Tile: " + ents.get(0).getPos().x / 16,
				10, 80);
		g.drawString(
				"Pos Y: " + ents.get(0).getPos().y + "  Tile: " + ents.get(0).getPos().y / 16,
				10, 90);

		// g.drawString("player", ents.get(0).getPos().x + 5,
		// ents.get(0).getPos().y + 5);

	}

	boolean startIns = false; // prueba de instrucciones
	boolean complete = true;
	private void actions ()
	{
		// moviemiento
		if ( ents.size() >= 2 )
		{
			p2Keys();
		}
		p1Keys();

		// DEV
		if ( vars.kb.isPressed("teleport") )
		{
			ents.get(0).setPosTile(19, 1);

			if ( ents.size() >= 1 )
			{
				ents.get(1).setPosTile(19, 1);
			}

		}

		// Instrucciones prueba (mover)

		if ( vars.kb.isPressed("instructions") )
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
		if ( vars.kb.isPressed("left") && !colision(ents.get(0), "left") )
		{
			move("left", ents.get(0));
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("left");
			return;
		}
		if ( vars.kb.isPressed("right") && !colision(ents.get(0), "right") )
		{
			move("right", ents.get(0));
			ents.get(0).setWalking(true);
			ents.get(0).setDirection("right");
			return;

		}

		ents.get(0).setWalking(false);
	}

	/**
	 * Clase que se encarga de gestionar el movimiento del personaje con las
	 * teclas del jugador 2.
	 */
	private void p2Keys ()
	{
		if ( vars.kb.isPressed("left1") && !colision(ents.get(1), "left") )
		{
			move("left", ents.get(1));
			ents.get(1).setWalking(true);
			ents.get(1).setDirection("left");
			return;

		}
		if ( vars.kb.isPressed("right1") && !colision(ents.get(1), "right") )
		{
			move("right", ents.get(1));
			ents.get(1).setWalking(true);
			ents.get(1).setDirection("right");
			return;

		}
		ents.get(1).setWalking(false);
	}

}

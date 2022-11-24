package controller.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.entities.active.Active;
import model.entities.active.Enemy;
import model.logic.ColisionBox;
import model.staticTools.vars;
import view.DrawAnimation;

public class EnemyController extends ActiveEntityController
{

	public EnemyController ( ArrayList<ColisionBox> cbm )
	{
		super(cbm);
		entType = "enemy";

		startList();
		System.out.println("Enemy Controller");
	}

	private void startList ()
	{
		// (id)-(ps)-(vel)-(maxVel)-(offsetX)-(offsetY)-(damage)
		entList.put("one", "00-100-1-4-10-19-17");
		entList.put("two", "01-100-1-4-10-19-19");
	}

	public void update ()
	{
		gotower();
		anim();

		super.update();
	}

	public void draw ( Graphics g )
	{
		// showPS(g);

		super.draw(g);
	}

	public int attack ( Rectangle r )
	{
		int damage = 0;

		for ( int i = 0; i < ents.size(); i++ )
		{
			Enemy en = (Enemy) ents.get(i);

			if ( en.getCB().getBox().intersects(r) )
			{
				damage += en.getDamage();
				en.setAttacking(true);
			}

			else
			{
				if ( damage > 0 && !en.isAttacking() )
				{
					damage -= en.getDamage();
				}
			}
		}

		return damage;
	}

	private void gotower ()
	{
		for ( int i = 0; i < ents.size(); i++ )
		{
			if ( !row(ents.get(i), i) )
			{
				if ( ents.get(i).getPos().x < 300 )
				{
					move("right", ents.get(i));

					if ( !colision(ents.get(i), "right") )
					{
						da.get(i).setAnimation("a0", "loop", (float) 0.5, 3);
					}
				}

				else
				{
					move("left", ents.get(i));

					if ( !colision(ents.get(i), "left") )
					{
						da.get(i).setAnimation("a1", "loop", (float) 0.5, 3);
					}
				}
			}
		}
	}

	private boolean row ( Active e, int ind )
	{
		for ( int i = 0; i < ents.size(); i++ )
		{
			if ( i != ind )
			{
				if ( e.getCB().getBox().intersects(ents.get(i).getCB().getBox()) )
				{
					return true;
				}
			}
		}
		return false;
	}

	// provisional
	private void anim ()
	{
		for ( int i = 0; i < ents.size(); i++ )
		{
			Enemy en = (Enemy) ents.get(i);
			if ( en.isAttacking() )
			{
				switch ( en.getDirection() )
				{
					case "left":
						da.get(i).setAnimation("a2", "loop", (float) 0.4, 4);
						break;
					case "right":
						da.get(i).setAnimation("a3", "loop", (float) 0.4, 4);
						break;
				}
			}
		}
	}

	@ SuppressWarnings ( "unused" )
	private void showPS ( Graphics g )
	{
		for ( Active e : ents )
		{
			g.setColor(Color.red);
			g.drawString("" + e.getPs(), e.getPos().x + 5, e.getPos().y + 5);
			g.setColor(Color.black);
			g.drawString("" + e.getPs(), e.getPos().x + 5, e.getPos().y + 6);
		}
	}

	public void addEnemy ( String name, int posx, int posy )
	{
		String[] data = entList.get(name).split("-");

		ents.add(new Enemy(	data[0], new Point(posx, posy), Integer.parseInt(data[1]),
							Integer.parseInt(data[2]), Integer.parseInt(data[3]),
							new Point(Integer.parseInt(data[4]), Integer.parseInt(data[5])),
							Integer.parseInt(data[6])));
		loadAnim(name, ents.get(ents.size() - 1));
		ents.get(ents.size() - 1).setDirection("right");
		da.add(new DrawAnimation(ents.get(ents.size() - 1)));
		da.get(ents.size() - 1).setAnimation("a0", "loop", (float) 1, 1);

		vars.entities++;
	}

	public void delEnemy ( String direction )
	{
		Enemy en;

		for ( int x = 0; x < ents.size(); x++ )
		{

			en = (Enemy) ents.get(x);

			if ( en.getDirection() != direction && !en.isAttacking() )
			{
				continue;
			}

			ents.remove(x);
			da.remove(x);
			vars.entities--;
		}

	}

}

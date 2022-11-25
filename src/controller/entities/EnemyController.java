package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.entities.active.Enemy;
import model.logic.ColisionBox;
import model.staticTools.vars;
import view.DrawAnimation;

public class EnemyController extends ActiveEntityController
{

	private int enemies;

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
		entList.put("two", "01-100-1-4-10-19-78");
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

		for ( int i = 0; i < enemies; i++ )
		{
			if ( ents.get(i) == null )
			{
				continue;
			}

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

	private Enemy en;
	private void gotower ()
	{
		for ( int i = 0; i < enemies; i++ )
		{
			if ( ents.get(i) == null )
			{
				continue;
			}

			en = (Enemy) ents.get(i);

			if ( !row(en, i) )
			{
				if ( ents.get(i).getPos().x < 300 )
				{
					ents.get(i).setDirection("right");
					move("right", ents.get(i));

					if ( !colision(ents.get(i), "right") )
					{
						da.get(i).setAnimation("a0", "loop", (float) 0.5, 3);
					}
				}

				else
				{
					ents.get(i).setDirection("left");
					move("left", ents.get(i));

					if ( !colision(ents.get(i), "left") )
					{
						da.get(i).setAnimation("a1", "loop", (float) 0.5, 3);
					}
				}
			}
		}
	}

	private boolean row ( Enemy e, int ind )
	{
		for ( int i = 0; i < enemies; i++ )
		{
			if ( !e.isAttacking() )
			{
				return false;
			}

			{
				if ( ents.get(i) == null )
				{
					continue;
				}

				if ( i != ind )
				{
					if ( e.getCB().getBox().intersects(ents.get(i).getCB().getBox()) )
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	// provisional
	private void anim ()
	{
		for ( int i = 0; i < enemies; i++ )
		{
			if ( ents.get(i) == null )
			{
				continue;
			}

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
		// for ( Active e : ents )
		// {
		// g.setColor(Color.red);
		// g.drawString("" + e.getPs(), e.getPos().x + 5, e.getPos().y + 5);
		// g.setColor(Color.black);
		// g.drawString("" + e.getPs(), e.getPos().x + 5, e.getPos().y + 6);
		// }
	}

	public void addEnemy ( String name, int posx, int posy )
	{
		String[] data = entList.get(name).split("-");

		ents.put(enemies,
				new Enemy(	data[0], new Point(posx, posy), Integer.parseInt(data[1]),
							Integer.parseInt(data[2]), Integer.parseInt(data[3]),
							new Point(Integer.parseInt(data[4]), Integer.parseInt(data[5])),
							Integer.parseInt(data[6])));
		loadAnim(name, ents.get(enemies));
		ents.get(enemies).setDirection("right");
		da.put(enemies, new DrawAnimation(ents.get(enemies)));
		da.get(enemies).setAnimation("a0", "loop", (float) 1, 1);

		vars.entities++;
		enemies++;
	}

	public void delEnemy ( String direction )
	{
		Enemy en;

		for ( int x = 0; x < enemies; x++ )
		{

			if ( ents.get(x) == null )
			{
				continue;
			}

			en = (Enemy) ents.get(x);

			if ( !en.isAttacking() )
			{
				continue;
			}

			if ( en.getDirection() == direction )
			{
				ents.remove(x);
				da.remove(x);
				vars.entities--;
			}

		}

	}

}

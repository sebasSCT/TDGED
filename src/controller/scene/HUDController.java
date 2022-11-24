package controller.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import controller.entities.InactiveEntityController;
import model.entities.inactive.GameMenu;
import model.scene.HUD;
import model.staticTools.vars;
import view.DrawAnimation;

public class HUDController extends InactiveEntityController
{

	private int ind = 4;

	private ArrayList<HUD> huds;

	public HUDController ()
	{
		entType = "menu";

		huds = new ArrayList<>();

		startHUDs();

		System.out.println("HUDController");
	}

	private void startHUDs ()
	{
		selectPlayerHUD();

		huds.add(new HUD());
		huds.add(new HUD());
		huds.add(new HUD());
		huds.add(new HUD());
		huds.add(new HUD());
	}

	private void selectPlayerHUD ()
	{
		GameMenu gm;

		huds.add(new HUD());

		gm = new GameMenu("PSanty", new Point(2, 10));
		loadAnim("PSantySheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.put(0, new DrawAnimation(gm));
		da.get(0).setAnimation("p0", "static", 0);

		gm = new GameMenu("PSebas", new Point(10, 10));
		loadAnim("PSebasSheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.put(1, new DrawAnimation(huds.get(0).getMenus().get(1)));
		da.get(1).setAnimation("p2", "loop", (float) 1.5, 10);

		gm = new GameMenu("PJuan", new Point(18, 10));
		loadAnim("PJuanSheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.put(2, new DrawAnimation(huds.get(0).getMenus().get(2)));
		da.get(2).setAnimation("p2", "loop", (float) 1.5, 9);

		gm = new GameMenu("PBeto", new Point(26, 10));
		loadAnim("PBetoSheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.put(3, new DrawAnimation(huds.get(0).getMenus().get(3)));
		da.get(3).setAnimation("p2", "loop", (float) 1.5, 10);

	}

	private float[] time = new float[3];
	private boolean pressed[] = new boolean[2];
	private boolean[] next =
	{ false, false };
	public void update ()
	{

		for ( int i = 0; i < pressed.length; i++ )
		{
			if ( pressed[i] )
			{
				time[i] += (float) 0.016;
			}

			if ( time[i] >= 0.2 )
			{
				pressed[i] = false;
				time[i] = 0;
			}
		}

		if ( vars.kb.isPressed("a_right") && !pressed[1] )
		{
			pressed[1] = true;
			// ind = (ind <= 0) ? 0 : ind--;
			ind++;
		}

		if ( vars.kb.isPressed("a_left") && !pressed[0] )
		{
			pressed[0] = true;
			// ind = (ind >= 4) ? 3 : ind++;
			ind--;
		}

		switch ( ind )
		{
			case 0:
				next[0] = false;

				da.get(0).setAnimation("p2", "once", (float) 1, 7);
				da.get(1).setAnimation("p2", "loop", (float) 1.5, 10);
				da.get(2).setAnimation("p2", "loop", (float) 1.5, 9);
				break;

			case 1:

				if ( !next[0] )
				{
					time[2] += 0.016;
					if ( time[2] >= 0.8 )
					{
						next[0] = true;
						time[2] = 0;
					}
				}

				da.get(0).setAnimation("p0", "static", 0);
				da.get(2).setAnimation("p2", "loop", (float) 1.5, 9);
				if ( !next[0] )
				{
					da.get(1).setAnimation("p3", "once", (float) 1, 5);
					break;
				}
				da.get(1).setAnimation("p4", "loop", (float) 0.5, 4);
				break;

			case 2:
				next[0] = false;

				da.get(0).setAnimation("p0", "static", 0);
				da.get(1).setAnimation("p2", "loop", (float) 1.5, 10);
				da.get(2).setAnimation("p3", "once", (float) 1, 8);

				break;

			case 3:
				next[0] = false;

				da.get(0).setAnimation("p0", "static", 0);
				da.get(1).setAnimation("p2", "loop", (float) 1.5, 10);
				da.get(2).setAnimation("p2", "loop", (float) 1.5, 9);
				break;
		}
	}

	public void draw ( Graphics g )
	{
		super.draw(g);

		g.setColor(Color.yellow);
		g.drawString("IND ANIMACION:" + ind, 10, 180);
	}

	// da.add(new DrawAnimation(gm));

	public ArrayList<HUD> getHuds ()
	{
		return huds;
	}

}

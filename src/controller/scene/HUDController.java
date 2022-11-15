package controller.scene;

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
	}

	private void selectPlayerHUD ()
	{
		GameMenu gm;

		huds.add(new HUD());

		gm = new GameMenu("PSanty", new Point(2, 10));
		loadAnim("PSantySheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.add(new DrawAnimation(gm));
		da.get(0).setAnimation("p0", "static", 0);

		gm = new GameMenu("PSebas", new Point(10, 10));
		loadAnim("PSebasSheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.add(new DrawAnimation(huds.get(0).getMenus().get(1)));
		da.get(1).setAnimation("p2", "loop", (float) 1.5, 10);

		gm = new GameMenu("PJuan", new Point(18, 10));
		loadAnim("PJuanSheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.add(new DrawAnimation(huds.get(0).getMenus().get(2)));
		da.get(2).setAnimation("p2", "loop", (float) 1.5, 9);

		gm = new GameMenu("PBeto", new Point(26, 10));
		loadAnim("PBetoSheet", gm, vars.presentationSize, "p");
		huds.get(0).getMenus().add(gm);
		da.add(new DrawAnimation(huds.get(0).getMenus().get(3)));
		da.get(3).setAnimation("p2", "loop", (float) 1.5, 10);

	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	// da.add(new DrawAnimation(gm));

	public ArrayList<HUD> getHuds ()
	{
		return huds;
	}

}

package controller.scene;

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
	}

	private void selectPlayerHUD ()
	{
		GameMenu gm = new GameMenu("PSanty", new Point(10, 10));
		loadAnim("PSantySheet", gm, vars.presentationSize);

		huds.add(new HUD());
		huds.get(0).getMenus().add(gm);
		da.add(new DrawAnimation(huds.get(0).getMenus().get(0)));

		huds.add(new HUD());
		huds.add(new HUD());
		huds.add(new HUD());
		huds.add(new HUD());
	}

	public ArrayList<HUD> getHuds ()
	{
		return huds;
	}

}

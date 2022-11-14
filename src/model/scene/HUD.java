package model.scene;

import java.awt.Rectangle;
import java.util.ArrayList;
import model.entities.inactive.GameMenu;

public class HUD
{

	private ArrayList<Rectangle> recs;
	private ArrayList<GameMenu> menus;

	public HUD ()
	{
		recs = new ArrayList<>();
		menus = new ArrayList<>();
	}

	public ArrayList<Rectangle> getRecs ()
	{
		return recs;
	}

	public ArrayList<GameMenu> getMenus ()
	{
		return menus;
	}

}

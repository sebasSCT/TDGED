package controller.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import model.logic.ColisionBox;

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
		// (id)-(ps)-(vel)-(maxVel)-(offsetX)-(offsetY)
		entList.put("one", "00-100-1-4-10-19");
	}

	public void update ()
	{

	}

	public void draw ( Graphics g )
	{

	}

	public void addEnemy ( String name, Point pos )
	{

	}

}

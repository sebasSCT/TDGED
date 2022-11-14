package view;

import java.awt.Graphics;
import model.scene.GameScene;

public class DrawScene
{

	private GameScene s;
	private DrawMap dm;
	private DrawAnimation da;

	public DrawScene ( GameScene s )
	{
		this.s = s;
		dm = new DrawMap(s.getMap());

		if ( !s.getHud().getMenus().isEmpty() )
		{
			da = new DrawAnimation(s.getHud().getMenus().get(0));
			da.setAnimation("a2");
		}

		System.out.println("DrawScene");
	}

	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		dm.draw(g);

		if ( !s.getHud().getMenus().isEmpty() )
		{
			da.draw(g);
		}
	}

	public void drawColisions ( Graphics g )
	{
		dm.drawColisions(g);
	}
}

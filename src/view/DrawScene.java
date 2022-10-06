package view;

import java.awt.Graphics;
import model.scene.GameScene;

public class DrawScene
{

	private GameScene s;
	private DrawMap dm;

	public DrawScene ( GameScene s )
	{
		this.s = s;
		dm = new DrawMap(s.getMap());
	}

	public void draw ( final Graphics g )
	{
		dm.draw(g);
	}
}

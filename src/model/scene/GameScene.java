package model.scene;

import java.awt.image.BufferedImage;
import model.staticTools.GetResources;

public class GameScene
{

	private BufferedImage background;

	private GameMap map;

	public GameScene ( GameMap map )
	{
		this.map = map;
		background = GetResources.ciOpaca(map.getBG());
		System.out.println("GameScene");
	}

	public GameMap getMap ()
	{
		return map;
	}

	public BufferedImage getBG ()
	{
		return background;
	}

}

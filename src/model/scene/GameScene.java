package model.scene;

public class GameScene
{

	// entidades
	private GameMap map;

	public GameScene ( GameMap map )
	{
		this.map = map;
	}

	public GameMap getMap ()
	{
		return map;
	}

}

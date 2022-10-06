package controller;

import java.awt.Graphics;
import java.util.ArrayList;
import model.scene.GameScene;
import model.staticTools.vars;
import view.DrawScene;

public class SceneController
{

	// Se puede aplicar una hashtable o algo así
	private ArrayList<GameScene> scenes;
	private GameScene currentScene;

	private GameScene s;
	private MapController mc;
	private DrawScene ds;

	public SceneController ()
	{
		mc = new MapController();
		scenes = new ArrayList<>();
		loadScenes();
		ds = new DrawScene(currentScene);
	}

	private void loadScenes ()
	{
		for ( int x = 0; x < vars.nMaps; x++ )
		{
			mc.loadMap(vars.getMapJSON("test", String.valueOf(x)));
			s = new GameScene(mc.getMapa());
			scenes.add(s);
		}

		currentScene = scenes.get(0);
	}

	public void draw ( final Graphics g )
	{
		ds.draw(g);
	}

	public void setCS ( int ind )
	{
		currentScene = scenes.get(ind);
		ds = new DrawScene(currentScene);
	}

	public GameScene getCS ()
	{
		return currentScene;
	}

}

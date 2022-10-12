package controller.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import controller.entities.PlayerController;
import model.scene.GameScene;
import model.staticTools.vars;
import view.DrawScene;

public class SceneController
{

	// Se puede aplicar una hashtable o algo así
	private ArrayList<GameScene> scenes;
	private GameScene currentScene;

	private PlayerController pc;
	private GameScene s;
	private MapController mc;
	private DrawScene ds;

	public SceneController ()
	{
		mc = new MapController();
		scenes = new ArrayList<>();
		loadScenes();

		// Crear forma de elegir personaje
		pc = new PlayerController("test_guy:test_guy", currentScene.getMap().getPosIni());
		ds = new DrawScene(currentScene);
	}

	private void loadScenes ()
	{
		for ( int x = 0; x < vars.nMaps; x++ )
		{
			mc.loadMap(vars.getMapJSON("test", String.valueOf(x))); // testeo

			s = new GameScene(mc.getMapa());
			scenes.add(s);
		}

		currentScene = scenes.get(2);
	}

	public void update ()
	{
		// pc.getPlayer(0).setPos(5, 2);
	}

	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		ds.draw(g);
		g.drawImage(pc.getPlayer(0).getAnimations().get("a1").get(0),
				pc.getPlayer(0).getPos().x, pc.getPlayer(0).getPos().y, null);

		g.setColor(Color.yellow);
		g.drawRect(pc.getPlayer(0).getCB().getBox().x, pc.getPlayer(0).getCB().getBox().y,
				pc.getPlayer(0).getCB().getBox().width,
				pc.getPlayer(0).getCB().getBox().height);
	}

	public void setCS ( int ind )
	{
		currentScene = scenes.get(ind);
		ds = new DrawScene(currentScene);
		pc = new PlayerController("test_guy:test_guy", currentScene.getMap().getPosIni());
	}

	public GameScene getCS ()
	{
		return currentScene;
	}

}

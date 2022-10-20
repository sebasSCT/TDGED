package controller.scene;

import java.awt.Graphics;
import java.util.ArrayList;
import controller.entities.CharacterController;
import model.scene.GameScene;
import model.staticTools.vars;
import view.DrawScene;

public class SceneController
{

	private ArrayList<GameScene> scenes;
	private GameScene currentScene;

	private CharacterController cc;
	private GameScene s;
	private MapController mc;
	private DrawScene ds;

	public SceneController ()
	{
		mc = new MapController();
		scenes = new ArrayList<>();
		loadScenes();

		// Crear forma de elegir personaje
		cc = new CharacterController(	"test_guy:test_guy", currentScene.getMap().getPosIni(),
										currentScene.getMap().getColisions());
		ds = new DrawScene(currentScene);
		System.out.println("SceneController");
	}

	private void loadScenes ()
	{
		// no guardar escenas (?)
		for ( int x = 0; x < vars.nMaps; x++ )
		{
			mc.loadMap(vars.getMapJSON("test", String.valueOf(x))); // testeo

			s = new GameScene(mc.getMapa());
			scenes.add(s);
		}

		currentScene = scenes.get(3);
	}

	public void update ()
	{
		cc.update();
	}

	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		ds.draw(g);
		cc.draw(g);

	}

	public void setCS ( int ind )
	{
		currentScene = scenes.get(ind);
		ds = new DrawScene(currentScene);
		cc = new CharacterController(	"test_guy:test_guy", currentScene.getMap().getPosIni(),
										currentScene.getMap().getColisions());
	}

	public GameScene getCS ()
	{
		return currentScene;
	}

}

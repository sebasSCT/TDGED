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

	int x = 1;
	double cont;
	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		ds.draw(g);
		cc.draw(g);

		// test
		// Clase de dibujar jugador (Crear)
		// Animacion

		// if ( vars.kb.isPressed('d') )
		// {
		// if ( cont > 7.5 )
		// {
		// x++;
		// cont = 0;
		// }
		// if ( x == cc.getPlayer(0).getAnimations().get("a1").size() - 1 )
		// {
		// x = 1;
		// }
		//
		// for ( int i = 0; i < 2; i++ )
		// {
		// g.drawImage(cc.getPlayer(i).getAnimations().get("a1").get(x),
		// cc.getPlayer(i).getPos().x, cc.getPlayer(i).getPos().y, null);
		// }
		// cont += vars.delta;
		// }
		//
		// else
		// {
		// x = 1;
		// cont = 0;
		// for ( int i = 0; i < 2; i++ )
		// {
		// g.drawImage(cc.getPlayer(i).getAnimations().get("a1").get(0),
		// cc.getPlayer(i).getPos().x, cc.getPlayer(i).getPos().y, null);
		// }
		// }
		// if ( vars.kb.isPressed('a') )
		// {}

		// Animacion end

		// g.setColor(Color.red);
		// g.drawLine(5 * 16, 10 * 16, 13 * 16, 2 * 16); // GANCHO

		// g.setColor(Color.white);
		// // for ( Rectangle r : cc.future )
		// // {
		// Rectangle r dd= cc.future;
		// g.drawRect(r.x, r.y, r.width, r.height);
		// // }
		//
		// g.setColor(Color.magenta);
		// g.drawRect(cc.getPlayer(0).getCB().getBox().x,
		// cc.getPlayer(0).getCB().getBox().y,
		// cc.getPlayer(0).getCB().getBox().width,
		// cc.getPlayer(0).getCB().getBox().height);
		//
		// g.setColor(Color.pink);
		//
		// for ( ColisionBox cb : currentScene.getMap().getColisions() )
		// {
		// g.drawRect(cb.getBox().x, cb.getBox().y, cb.getBox().width,
		// cb.getBox().height);
		//
		// }

		// .-----------------------------------------------.
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

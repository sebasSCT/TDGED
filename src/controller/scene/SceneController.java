package controller.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import controller.entities.CharacterController;
import model.entities.active.Active;
import model.logic.ColisionBox;
import model.scene.GameScene;
import model.staticTools.vars;
import view.DrawScene;

public class SceneController
{

	// Se puede aplicar una hashtable o algo así
	private ArrayList<GameScene> scenes;
	private GameScene currentScene;

	private CharacterController pc;
	private GameScene s;
	private MapController mc;
	private DrawScene ds;

	public SceneController ()
	{
		mc = new MapController();
		scenes = new ArrayList<>();
		loadScenes();

		// Crear forma de elegir personaje
		pc = new CharacterController(	"test_guy", currentScene.getMap().getPosIni(),
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

	int i = 0;
	public void update ()
	{

		// Organizar mejor
		// pc.getPlayer(0).setPos(16 * vars.spriteSize, 5 * vars.spriteSize);
		// pc.getPlayer(0).setPos(10 * vars.spriteSize,
		// pc.getPlayer(0).getPos().y + 5 * vars.delta);

		if ( !pc.colision((Active) pc.getPlayer(0)) )
		{
			pc.getPlayer(0).fall();
		}

		if ( vars.kb.a.isPressed() )
		{
			pc.getPlayer(0).move("left");
		}
		if ( vars.kb.d.isPressed() )
		{
			pc.getPlayer(0).move("right");
		}
		// if ( !pc.colision((Active) pc.getPlayer(1)) )
		// {
		// pc.getPlayer(1).setPos(i, 9);
		// }
		i += vars.delta;

		if ( i > 200 )
		{
			// pc.getPlayer(0).setPos(10, 1);
			// pc.getPlayer(1).setPos(0, 9);
			i = 0;
		}

	}

	int x = 1;
	double cont;
	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		ds.draw(g);

		// KeyboardController.kb.keyTyped();

		// test
		// Clase de dibujar jugador (Crear)

		// Animacion

		if ( vars.kb.d.isPressed() )
		{

			if ( cont > 7.5 )
			{
				x++;
				cont = 0;
			}
			if ( x == pc.getPlayer(0).getAnimations().get("a1").size() - 1 )
			{
				x = 1;
			}

			g.drawImage(pc.getPlayer(0).getAnimations().get("a1").get(x),
					pc.getPlayer(0).getPos().x, pc.getPlayer(0).getPos().y, null);
			cont += vars.delta;
		}

		else
		{
			x = 1;
			cont = 0;
			g.drawImage(pc.getPlayer(0).getAnimations().get("a1").get(0),
					pc.getPlayer(0).getPos().x, pc.getPlayer(0).getPos().y, null);
		}
		if ( vars.kb.a.isPressed() )
		{
			// pc.getPlayer(0).move("right");
		}

		// Animacion

		g.setColor(Color.red);
		g.drawLine(5 * 16, 10 * 16, 13 * 16, 2 * 16); // GANCHO

		g.setColor(Color.magenta);
		g.drawRect(pc.getPlayer(0).getCB().getBox().x, pc.getPlayer(0).getCB().getBox().y,
				pc.getPlayer(0).getCB().getBox().width,
				pc.getPlayer(0).getCB().getBox().height);
		// g.drawRect(pc.getPlayer(1).getCB().getBox().x,
		// pc.getPlayer(1).getCB().getBox().y,
		// pc.getPlayer(1).getCB().getBox().width,
		// pc.getPlayer(1).getCB().getBox().height);

		g.setColor(Color.pink);

		for ( ColisionBox cb : currentScene.getMap().getColisions() )
		{
			g.drawRect(cb.getBox().x, cb.getBox().y, cb.getBox().width, cb.getBox().height);

		}

		g.setColor(Color.white);
		// for ( Rectangle r : pc.future )
		// {
		// g.drawRect(r.x, r.y, r.width, r.height);
		// }

		// .-----------------------------------------------.
	}

	public void setCS ( int ind )
	{
		currentScene = scenes.get(ind);
		ds = new DrawScene(currentScene);
		pc = new CharacterController(	"test_guy:test_guy", currentScene.getMap().getPosIni(),
										currentScene.getMap().getColisions());
	}

	public GameScene getCS ()
	{
		return currentScene;
	}

}

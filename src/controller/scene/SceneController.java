package controller.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import controller.entities.CharacterController;
import controller.entities.MaterialController;
import model.entities.active.Material;
import model.entities.active.Player;
import model.scene.GameScene;
import model.staticTools.vars;
import view.DrawScene;

public class SceneController
{

	// Contiene todas las escenas
	private ArrayList<GameScene> scenes;
	// Escena actual "seleccionada"
	private GameScene currentScene;
	// Instancia los controles de los personajes
	private CharacterController cc;

	private MaterialController mtc;
	// Instancia la clase de las escenas
	private GameScene s;
	// Instancia el controlador del mapa
	private MapController mc;
	// Instancia la clase del dibujado de escena
	private DrawScene ds;

	public SceneController ()
	{
		// Se crea un nuevo MapController inicializandolo en el constructor
		mc = new MapController();
		// Inicializar el ArrayList de escenas en el controlador
		scenes = new ArrayList<>();
		loadScenes();

		// Crear forma de elegir personaje asignandole la posicion inicial en el
		// mapa y agrega las colisiones del mapa
		cc = new CharacterController(	"santy:santy", currentScene.getMap().getPosIni(),
										currentScene.getMap().getColisions());

		mtc = new MaterialController(currentScene.getMap().getColisions());
		mtc.addMaterial("cannonball", new Point(19, 10));
		mtc.addMaterial("cannonball2", new Point(21, 10));
		mtc.addMaterial("santy", new Point(23, 10));

		ds = new DrawScene(currentScene);
		System.out.println("SceneController");
	}

	private void loadScenes ()
	{
		// Carga todas las escenas
		for ( int x = 0; x < vars.nMaps; x++ )
		{
			mc.loadMap(vars.getMapJSON("test", String.valueOf(x)));

			s = new GameScene(mc.getMapa());
			scenes.add(s);
		}

		// Asigna las escena numero 4
		currentScene = scenes.get(4);
	}

	// Actualiza todos los elementos de la escena para asignar la posicion de
	// todo segun corresponda
	public void update ()
	{
		cc.update();
		mtc.update();
		carryMaterial();
	}

	// Dibuja todos los elementos de la escena
	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		ds.draw(g);
		mtc.draw(g);
		cc.draw(g);
		g.setColor(Color.white);
		g.drawString(vars.kb.isPressed('e') + " " + vars.kb.isReleased('e'), 10, 100);

	}

	// clase para logica (?)
	float time;
	boolean pressed;
	private void carryMaterial ()
	{

		Player a = (Player) cc.getEnts().get(0);

		if ( a.isCarrying() )
		{
			mtc.carrying(a.getPos(), a.getCarry());
		}

		if ( pressed )
		{
			time += (float) 0.016;
			if ( time >= (float) 0.2 )
			{
				pressed = false;
			}
		}

		if ( vars.kb.isPressed('e') && !pressed )
		{
			time = 0;
			pressed = true;
			if ( !a.isCarrying() )
			{
				for ( int i = 0; i < mtc.getEnts().size(); i++ )
				{
					if ( mtc.getEnts().get(i).getCB().getBox()
							.intersects(cc.getEnts().get(0).getCB().getBox()) )
					{
						Material m = (Material) mtc.getEnts().get(i);
						m.setCarry(true);
						a.setCarrying(true);
						a.setCarry(i);
					}
				}
				return;
			}

			Material m = (Material) mtc.getEnts().get(a.getCarry());
			if ( a.isWalking() )
			{
				m.setVel(a.getVel() * 5);
				m.setDirection(a.getDirection());
			}
			m.setCarry(false);
			a.setCarrying(false);
		}

	}

	// Cambiar de escena
	public void setCS ( int ind )
	{
		currentScene = scenes.get(ind);
		ds = new DrawScene(currentScene);
		cc = new CharacterController(	"test_guy:test_guy", currentScene.getMap().getPosIni(),
										currentScene.getMap().getColisions());
	}

	// Retorna la escena
	public GameScene getCS ()
	{
		return currentScene;
	}

}

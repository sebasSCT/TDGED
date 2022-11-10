package controller.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import controller.entities.EntityLogic;
import model.scene.GameScene;
import model.staticTools.vars;
import view.DrawScene;

public class SceneController
{

	// Contiene todas las escenas
	private ArrayList<GameScene> scenes;
	// Escena actual "seleccionada"
	private GameScene currentScene;
	// Instancia la clase de las escenas
	private GameScene s;
	// Instancia el controlador del mapa
	private MapController mc;
	// Instancia la clase del dibujado de escena

	private EntityLogic el;

	private DrawScene ds;

	public SceneController ()
	{
		// Se crea un nuevo MapController inicializandolo en el constructor
		mc = new MapController();
		// Inicializar el ArrayList de escenas en el controlador
		scenes = new ArrayList<>();
		loadScenes();

		el = new EntityLogic("santy:santy", currentScene.getMap());

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

		currentScene = scenes.get(4);
	}

	// Actualiza todos los elementos de la escena para asignar la posicion de
	// todo segun corresponda
	public void update ()
	{
		if ( vars.kb.isActive('m') )
		{
			el.update();
		}
	}

	// Dibuja todos los elementos de la escena
	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		ds.draw(g);
		el.draw(g);

		// DEV
		if ( vars.kb.isActive('}') )
		{
			ds.drawColisions(g);
			el.drawColisions(g);
		}

		///
		g.setColor(Color.white);
		g.drawString(vars.kb.isPressed('e') + " " + vars.kb.isReleased('e'), 10, 100);

	}

	// Cambiar de escena
	public void setCS ( int ind )
	{
		currentScene = scenes.get(ind);
		ds = new DrawScene(currentScene);
		el = new EntityLogic("santy:santy", currentScene.getMap());
	}

	// Retorna la escena
	public GameScene getCS ()
	{
		return currentScene;
	}

}

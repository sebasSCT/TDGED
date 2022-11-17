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
	private HUDController hc;

	private EntityLogic el;

	private DrawScene ds;

	public SceneController ()
	{
		// Se crea un nuevo MapController inicializandolo en el constructor
		mc = new MapController();
		//
		hc = new HUDController();
		// Inicializar el ArrayList de escenas en el controlador
		scenes = new ArrayList<>();
		loadScenes();

		ds = new DrawScene(currentScene);

		System.out.println("SceneController");
	}

	private void loadScenes ()
	{
		// Carga todas las escenas
		for ( int x = 0; x < vars.nMaps; x++ )
		{
			mc.loadMap(vars.getMapJSON("test", String.valueOf(x)));

			s = new GameScene(mc.getMapa(), hc.getHuds().get(x));
			scenes.add(s);
		}

		currentScene = scenes.get(4);
		ds = new DrawScene(currentScene);
		el = new EntityLogic("sebas:santy", currentScene.getMap());
	}

	// Actualiza todos los elementos de la escena para asignar la posicion de
	// todo segun corresponda
	public void update ()
	{
		// hc.update();

		if ( vars.kb.isActive("pause") && el != null )
		{
			el.update();
		}

		// switch scene
		if ( vars.kb.isPressed("next") )
		{
			setCS(4);
		}
	}

	// Dibuja todos los elementos de la escena
	public void draw ( final Graphics g )
	{
		ds.draw(g);
		// hc.draw(g);

		if ( el != null )
		{
			el.draw(g);
		}

		// DEV
		if ( vars.kb.isActive("showCols") )
		{
			ds.drawColisions(g);
			el.drawColisions(g);
		}

		if ( !vars.kb.isActive("pause") && el != null )
		{
			g.setColor(Color.red);
			g.drawString("PAUSE", 300, 200);
		}

		///
		g.setColor(Color.white);
		g.drawString("Pause: " + !vars.kb.isActive("pause"), 10, 110);

	}

	// Cambiar de escena
	public void setCS ( int ind )
	{
		currentScene = scenes.get(ind);
		ds = new DrawScene(currentScene);

		if ( el == null )
		{
			el = new EntityLogic("sebas:juan", currentScene.getMap());
		}

	}

	// Retorna la escena
	public GameScene getCS ()
	{
		return currentScene;
	}

}

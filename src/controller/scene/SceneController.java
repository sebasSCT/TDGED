package controller.scene;

import java.awt.Graphics;
import java.util.ArrayList;
import controller.entities.CharacterController;
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
		cc = new CharacterController(	"santy:test_guy", currentScene.getMap().getPosIni(),
										currentScene.getMap().getColisions());
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
	}

	// Dibuja todos los elementos de la escena
	public void draw ( final Graphics g )
	{
		g.drawImage(s.getBG(), 0, 0, null);
		ds.draw(g);
		cc.draw(g);

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

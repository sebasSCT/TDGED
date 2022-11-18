package model.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.util.Map.Entry;

public class Keyboard implements KeyListener
{

	private Hashtable<String, Key> keys;
	private Hashtable<String, String> actions;

	public Keyboard ()
	{
		keys = new Hashtable<>();
		actions = new Hashtable<>();

		putKeys();
		startKeys();

		System.out.println("Keyboard");
	}

	public void putKeys ()
	{

		// Player 1
		actions.put("down", "s");
		actions.put("up", "w");
		actions.put("left", "a");
		actions.put("right", "d");
		actions.put("interact", "e");
		// *************

		// Player 2
		actions.put("down1", "5");
		actions.put("up1", "8");
		actions.put("left1", "4");
		actions.put("right1", "6");
		actions.put("interact1", "7");
		// *************

		// Menu
		actions.put("a_right", "39");
		actions.put("a_left", "37");

		// DEV
		actions.put("showCols", "}");
		actions.put("pause", "-");
		actions.put("teleport", "+");
		actions.put("instructions", "*");
		actions.put("next", "k");
		actions.put("delEnemy", "b");
		// *************
	}

	public void startKeys ()
	{

		for ( Entry<String, String> c : actions.entrySet() )
		{
			keys.put(c.getValue(), new Key());
		}

	}

	public void keyPressed ( KeyEvent e )
	{
		if ( keys.containsKey(String.valueOf(e.getKeyChar())) )
		{
			keys.get(String.valueOf(e.getKeyChar())).pressed();
		}

		if ( keys.containsKey(String.valueOf(e.getKeyCode())) )
		{
			keys.get(String.valueOf(e.getKeyCode())).pressed();
		}

	}

	public void keyReleased ( KeyEvent e )
	{
		if ( keys.containsKey(String.valueOf(e.getKeyChar())) )
		{
			keys.get(String.valueOf(e.getKeyChar())).released();
		}

		if ( keys.containsKey(String.valueOf(e.getKeyCode())) )
		{
			keys.get(String.valueOf(e.getKeyCode())).released();
		}
	}

	public void keyTyped ( KeyEvent e )
	{
	}

	public boolean isPressed ( String action )
	{
		// verify(action);
		return keys.get(actions.get(action)).isPressed();
	}

	public boolean isReleased ( String action )
	{
		// verify(action);
		return keys.get(actions.get(action)).isReleased();
	}

	public boolean isActive ( String action )
	{
		// verify(action);
		return keys.get(actions.get(action)).isActive();
	}

	@ SuppressWarnings ( "unused" )
	private void verify ( String action )
	{
		if ( !actions.containsKey(action) )
		{
			System.err.println("INVALID COMMAND ACTION! : " + action);
		}
	}

}

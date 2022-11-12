package model.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.util.Map.Entry;

public class Keyboard implements KeyListener
{

	private Hashtable<Character, Key> keys;
	private Hashtable<String, Character> actions;

	public Keyboard ()
	{
		keys = new Hashtable<>();

		actions = putKeys();
		startKeys();

		System.out.println("Keyboard");
	}

	public Hashtable<String, Character> putKeys ()
	{
		Hashtable<String, Character> k = new Hashtable<>();

		// Player 1
		k.put("down", 's');
		k.put("up", 'w');
		k.put("left", 'a');
		k.put("right", 'd');
		k.put("interact", 'e');
		// *************

		// Player 2
		k.put("down1", '5');
		k.put("up1", '8');
		k.put("left1", '4');
		k.put("right1", '6');
		k.put("interact1", '7');
		// *************

		// DEV
		k.put("showCols", '}');
		k.put("pause", '-');
		k.put("teleport", '+');
		k.put("instructions", '*');
		// *************

		return k;
	}

	public void startKeys ()
	{

		for ( Entry<String, Character> c : actions.entrySet() )
		{
			keys.put(c.getValue(), new Key());
		}

	}

	public void keyPressed ( KeyEvent e )
	{
		if ( keys.containsKey(e.getKeyChar()) )
		{
			keys.get(e.getKeyChar()).pressed();
		}

	}

	public void keyReleased ( KeyEvent e )
	{
		if ( keys.containsKey(e.getKeyChar()) )
		{
			keys.get(e.getKeyChar()).released();
		}
	}

	public void keyTyped ( KeyEvent e )
	{
	}

	public boolean isPressed ( String action )
	{
		verify(action);
		return keys.get(actions.get(action)).isPressed();
	}

	public boolean isReleased ( String action )
	{
		verify(action);
		return keys.get(actions.get(action)).isReleased();
	}

	public boolean isActive ( String action )
	{
		verify(action);
		return keys.get(actions.get(action)).isActive();
	}

	private void verify ( String action )
	{
		if ( !actions.containsKey(action) )
		{
			System.err.println("INVALID COMMAND ACTION! : " + action);
		}
	}

}

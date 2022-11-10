package model.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

public class Keyboard implements KeyListener
{

	private Hashtable<Object, Key> keys = new Hashtable<>();

	public void startKeys ()
	{
		keys.put('1', new Key());
		keys.put('2', new Key());

		keys.put('m', new Key());

		// CONTROL ARROWS (unnused)
		keys.put(KeyEvent.VK_LEFT, new Key());
		keys.put(KeyEvent.VK_RIGHT, new Key());
		keys.put(KeyEvent.VK_UP, new Key());
		keys.put(KeyEvent.VK_DOWN, new Key());

		// PLAYER 1
		keys.put('w', new Key());
		keys.put('a', new Key());
		keys.put('s', new Key());
		keys.put('d', new Key());
		keys.put('e', new Key());

		// PLAYER 2
		keys.put('j', new Key());
		keys.put('l', new Key());

		// DEV
		keys.put('}', new Key());
	}

	// Mejorar con keycode de ser necesario
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

	public boolean isPressed ( char key )
	{
		return keys.get(key).isPressed();
	}

	public boolean isReleased ( char key )
	{
		return keys.get(key).isReleased();
	}

	public boolean isActive ( char key )
	{
		return keys.get(key).isActive();
	}

	// public boolean isPressed ( String key )
	// {
	// return keys.get(code(key)).isPressed();
	// }
	//
	// public boolean isReleased ( String key )
	// {
	// return keys.get(code(key)).isReleased();
	// }
	//
	// public boolean isActive ( String key )
	// {
	// return keys.get(code(key)).isActive();
	// }
	//
	// private int code ( String key )
	// {
	// switch (key)
	// {
	// case "a_left":
	// return KeyEvent.VK_LEFT;
	//
	// case "a_right":
	// return KeyEvent.VK_LEFT;
	//
	// case "a_right":
	// return KeyEvent.VK_LEFT;
	//
	// case "a_left":
	// return KeyEvent.VK_LEFT;
	//
	// default:
	// return 0;
	// }
	// }

}

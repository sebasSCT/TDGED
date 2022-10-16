package model.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{

	public Key a = new Key();
	public Key d = new Key();

	public void keyPressed ( KeyEvent e )
	{
		// System.out.println("Pressed: " + e.getKeyCode());

		if ( e.getKeyCode() == KeyEvent.VK_A )
		{
			a.pressed();
		}
		if ( e.getKeyCode() == KeyEvent.VK_D )
		{
			d.pressed();
		}

	}

	public void keyReleased ( KeyEvent e )
	{
		// System.out.println("Released: " + e.getKeyCode());

		if ( e.getKeyCode() == KeyEvent.VK_A )
		{
			a.released();
		}
		if ( e.getKeyCode() == KeyEvent.VK_D )
		{
			d.released();
		}
	}

	public void keyTyped ( KeyEvent e )
	{
	}

}

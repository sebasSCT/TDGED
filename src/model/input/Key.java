package model.input;

public class Key
{

	private boolean pulsada = false, released = true;

	public void pressed ()
	{
		pulsada = true;
		released = false;
	}

	public void released ()
	{
		pulsada = false;
		released = true;
	}

	public boolean isPressed ()
	{
		return pulsada;
	}

	public boolean isReleased ()
	{
		return released;
	}

}

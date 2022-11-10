package model.input;

public class Key
{

	private boolean pulsada = false, released = true, active = false;

	public void pressed ()
	{
		pulsada = true;
		active = !active;
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

	public boolean isActive ()
	{
		return active;
	}

}

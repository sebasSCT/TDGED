package model.input;

public class Key
{

	private boolean pulsada = false;

	public void pressed ()
	{
		pulsada = true;
	}

	public void released ()
	{
		pulsada = false;
	}

	public boolean isPressed ()
	{
		return pulsada;
	}
}

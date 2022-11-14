package model.entities.active;

import java.awt.Point;

public class Character extends Active
{

	private String playerName;

	public Character (	String id, Point pos, double ps, double vel, double maxVel,
						Point offset, String playerName )
	{
		super(id, pos, ps, vel, maxVel, offset);
		this.playerName = playerName;
	}

	public String getPlayerName ()
	{
		return playerName;
	}

	public void setPlayerName ( String playerName )
	{
		this.playerName = playerName;
	}

}

package model.scene;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameMap
{

	private int widthTiles, heightTiles;
	private Point posIni;
	private ArrayList<ArrayList<BufferedImage>> spriteLayers;

	public GameMap ()
	{
		spriteLayers = new ArrayList<>();
		System.out.println("GameMap");
	}

	public int getWidthTiles ()
	{
		return widthTiles;
	}

	public void setWidthTiles ( int widthTiles )
	{
		this.widthTiles = widthTiles;
	}

	public int getHeightTiles ()
	{
		return heightTiles;
	}

	public void setHeightTiles ( int heightTiles )
	{
		this.heightTiles = heightTiles;
	}

	public Point getPosIni ()
	{
		return posIni;
	}

	public void setPosIni ( Point posIni )
	{
		this.posIni = posIni;
	}

	public ArrayList<ArrayList<BufferedImage>> getSpriteLayers ()
	{
		return spriteLayers;
	}

}

package model.scene;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.logic.ColisionBox;
import model.logic.dataStructure.Triplet;

public class GameMap
{

	private int widthTiles, heightTiles, towerPS;
	private Point posIni;
	private ArrayList<ArrayList<BufferedImage>> spriteLayers;
	private ArrayList<ColisionBox> colisions;
	private ArrayList<Triplet<String, Point, String>> structures;
	private ArrayList<Rectangle> towerColisions;
	private String bg;

	public GameMap ()
	{
		spriteLayers = new ArrayList<>();
		colisions = new ArrayList<>();
		structures = new ArrayList<>();
		towerColisions = new ArrayList<>();

		System.out.println("GameMap");
	}

	public String getBG ()
	{
		return bg;
	}

	public void setBG ( String bg )
	{
		this.bg = bg;
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

	public int getTowerPS ()
	{
		return towerPS;
	}

	public void setTowerPS ( int towerPS )
	{
		this.towerPS = towerPS;
	}

	public ArrayList<ArrayList<BufferedImage>> getSpriteLayers ()
	{
		return spriteLayers;
	}

	public ArrayList<ColisionBox> getColisions ()
	{
		return colisions;
	}

	public ArrayList<Triplet<String, Point, String>> getStructures ()
	{
		return structures;
	}

	public ArrayList<Rectangle> getTC ()
	{
		return towerColisions;
	}

}

package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.logic.ColisionBox;
import model.logic.dataStructure.Pair;
import model.scene.GameMap;
import model.staticTools.vars;

public class DrawMap
{

	private GameMap gm;
	private ArrayList<Pair<Point, BufferedImage>> imgs;

	public DrawMap ( GameMap gm )
	{
		this.gm = gm;
		imgs = new ArrayList<>();
		loadImages();
		System.out.println("DrawMap");
	}

	private void loadImages ()
	{
		for ( ArrayList<BufferedImage> layer : gm.getSpriteLayers() )
		{
			int y = 0, x = 0;
			for ( BufferedImage img : layer )
			{
				int px = x * vars.spriteSize + vars.screenOffSet;
				int py = y * vars.spriteSize;

				imgs.add(new Pair<Point, BufferedImage>(new Point(px, py), img));

				x++;
				y += (x == gm.getWidthTiles()) ? 1 : 0;
				x = (x == gm.getWidthTiles()) ? 0 : x;
			}
		}
	}

	public void draw ( final Graphics g )
	{
		for ( Pair<Point, BufferedImage> img : imgs )
		{
			g.drawImage(img.getB(), img.getA().x, img.getA().y, null);
		}
	}

	public void drawColisions ( Graphics g )
	{
		for ( ColisionBox cb : gm.getColisions() )
		{
			g.drawRect(cb.getBox().x, cb.getBox().y, cb.getBox().width, cb.getBox().height);
		}
	}
}

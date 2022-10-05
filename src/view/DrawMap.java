package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import controller.MapController;
import javafx.util.Pair;
import model.staticTools.vars;

public class DrawMap
{

	private MapController mc;
	private ArrayList<Pair<Point, BufferedImage>> imgs;

	public DrawMap ( MapController mc )
	{
		this.mc = mc;
		imgs = new ArrayList<>();
		loadMapa();
		System.out.println("DrawMap");
	}

	private void loadMapa ()
	{
		for ( ArrayList<BufferedImage> layer : mc.getMapaActual().getSpriteLayers() )
		{
			int y = 0, x = 0;
			for ( BufferedImage img : layer )
			{
				x++;
				y += (x == mc.getMapaActual().getWidthTiles()) ? 1 : 0;
				x = (x == mc.getMapaActual().getWidthTiles()) ? 0 : x;

				int px = x * vars.spriteSize;
				int py = y * vars.spriteSize;

				imgs.add(new Pair<Point, BufferedImage>(new Point(px, py), img));
			}
		}
	}

	public void drawMap ( final Graphics g )
	{

		for ( int i = 0; i < imgs.size(); i++ )
		{
			g.drawImage(imgs.get(i).getValue(), imgs.get(i).getKey().x, imgs.get(i).getKey().y,
					null);
		}
	}

}

package controller.entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import controller.scene.SpriteSheetController;
import model.entities.Player;
import model.staticTools.vars;

public class PlayerController
{

	private Player[] players;

	public PlayerController ( String id, Point pos )
	{
		String[] ids = id.split(":");

		players = new Player[ids.length];
		for ( int i = 0; i < players.length; i++ )
		{
			players[i] = new Player((i == 1) ? ids[1] : ids[0],
									new Point(	pos.x * vars.spriteSize,
												pos.y * vars.spriteSize),
									10, 10);
			SpriteSheetController ssc = new SpriteSheetController("player", (i == 1) ? ids[1]
					: ids[0]);

			for ( int e = 0; e < ssc.getSs().getHeight(); e++ )
			{
				ArrayList<BufferedImage> imgs = new ArrayList<>();

				for ( int a = 0; a < 5; a++ )
				{
					imgs.add(ssc.getSs().getSprites()[a]);
				}

				players[i].getAnimations().put("a" + e, imgs);
			}
		}

		System.out.println("PlayerController");
	}

	public Player getPlayer ( int i )
	{
		return players[i];
	}

}

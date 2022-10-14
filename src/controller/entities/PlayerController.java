package controller.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import controller.scene.SpriteSheetController;
import model.entities.Player;
import model.logic.ColisionBox;
import model.staticTools.vars;

public class PlayerController
{

	private Player[] players;
	private ArrayList<ColisionBox> cbm;

	public PlayerController ( String id, Point pos, ArrayList<ColisionBox> cbm )
	{
		this.cbm = cbm;

		String[] ids = id.split(":");

		players = new Player[ids.length];
		for ( int i = 0; i < players.length; i++ )
		{
			players[i] = new Player((i == 1) ? ids[1] : ids[0],
									new Point(	pos.x * vars.spriteSize,
												pos.y * vars.spriteSize),
									10, 10, new Point(14, 17));
			// OFFSET para
			// nuevos
			// sprites y
			// dependiendo
			// los
			// personajes
			// (enum) ?
			SpriteSheetController ssc = new SpriteSheetController("player", (i == 1) ? ids[1]
					: ids[0]);

			for ( int e = 0; e < ssc.getSs().getHeight(); e++ )
			{
				ArrayList<BufferedImage> imgs = new ArrayList<>();

				for ( int a = 0; a < 5; a++ )
				{
					imgs.add(ssc.getSs().getSprites()[a + (ssc.getSs().getWidth() * e)]);
				}

				players[i].getAnimations().put("a" + e, imgs);
			}
		}

		System.out.println("PlayerController");
	}

	public void update ()
	{
		colisions();
	}

	private void colisions ()
	{
		Rectangle fb[] = new Rectangle[4];

		for ( int i = 0; i < players.length; i++ )
		{
			fb[0] = new Rectangle(	players[i].getCB().getBox().x + 10,
									players[i].getCB().getBox().y,
									(int) players[i].getCB().getBox().getWidth(),
									(int) players[i].getCB().getBox().getHeight());
			fb[1] = new Rectangle(	players[i].getCB().getBox().x - 10,
									players[i].getCB().getBox().y,
									(int) players[i].getCB().getBox().getWidth(),
									(int) players[i].getCB().getBox().getHeight());
			fb[2] = new Rectangle(	players[i].getCB().getBox().x,
									players[i].getCB().getBox().y + 10,
									(int) players[i].getCB().getBox().getWidth(),
									(int) players[i].getCB().getBox().getHeight());
			fb[3] = new Rectangle(	players[i].getCB().getBox().x,
									players[i].getCB().getBox().y - 10,
									(int) players[i].getCB().getBox().getWidth(),
									(int) players[i].getCB().getBox().getHeight());

			for ( int j = 0; j < fb.length; j++ )
			{
				for ( ColisionBox cb : cbm )
				{
					if ( fb[j].intersects(cb.getBox()) )
					{
						players[i].setInColision(true);
					}

					else
					{
						players[i].setInColision(false);
					}
				}
			}

			// if ( >= cbm)
		}
	}

	public Player getPlayer ( int i )
	{
		return players[i];
	}

}

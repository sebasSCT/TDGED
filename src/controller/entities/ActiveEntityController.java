package controller.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.entities.active.Active;
import model.logic.ColisionBox;
import model.staticTools.vars;

public abstract class ActiveEntityController extends EntityController
{

	protected ArrayList<ColisionBox> cbm;

	private Rectangle future;

	public ActiveEntityController ( ArrayList<ColisionBox> cbm )
	{
		super();
		this.cbm = cbm;
	}

	public void update ()
	{
		animate();
	}

	protected void animate ()
	{
		for ( int i = 0; i < ents.size(); i++ )
		{

			if ( ents.get(i).isFalling() )
			{
				switch ( ents.get(i).getDirection() )
				{
					// Mas sprites de caida y l�gica
					case "left":
						da.get(i).setAnimation("a5");
						break;

					case "right":
						da.get(i).setAnimation("a4");
						break;

					// abajo (mismo sprite subir escaleras)
					// arriba
				}

			}

			if ( !ents.get(i).isFalling() && ents.get(i).isWalking() )
			{
				switch ( ents.get(i).getDirection() )
				{
					case "left":
						da.get(i).setAnimation("a3");
						break;

					case "right":
						da.get(i).setAnimation("a2");
						break;

					// abajo (mismo sprite subir escaleras)
					// arriba
				}
			}

			if ( !ents.get(i).isFalling() && !ents.get(i).isWalking() )
			{
				switch ( ents.get(i).getDirection() )
				{
					case "left":
						da.get(i).setAnimation("a1");
						break;

					case "right":
						da.get(i).setAnimation("a0");
						break;

					// abajo (mismo sprite subir escaleras)
					// arriba
				}
			}

		}
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	protected void gravity ( Active e )
	{
		if ( !colision(e, 0) )
		{
			e.fall();
			return;
		}
		e.setG(vars.gravity);
		e.setFalling(false);
	}

	protected boolean colision ( Active e, int side )
	{

		switch ( side )
		{
			case 0:
				// ABAJO (GRAVEDAD)
				future = new Rectangle(	e.getCB().getBox().x, e.getCB().getBox().y + e.getG(),
										e.getCB().getBox().width, e.getCB().getBox().height);
				break;
			case 1:
				// ARRIBA
				future = new Rectangle(	e.getCB().getBox().x,
										e.getCB().getBox().y - (int) e.getVel(),
										e.getCB().getBox().width, e.getCB().getBox().height);
				break;
			case 2:
				// DERECHA
				future = new Rectangle(	e.getCB().getBox().x + (int) e.getVel(),
										e.getCB().getBox().y, e.getCB().getBox().width,
										e.getCB().getBox().height);
				break;
			case 3:
				// IZQUIERDA
				future = new Rectangle(	e.getCB().getBox().x - (int) e.getVel(),
										e.getCB().getBox().y, e.getCB().getBox().width,
										e.getCB().getBox().height);
				break;
		}

		for ( ColisionBox cb : cbm )
		{
			if ( future.intersects(cb.getBox()) )
			{
				return true;
			}
		}
		return false;
	}
}

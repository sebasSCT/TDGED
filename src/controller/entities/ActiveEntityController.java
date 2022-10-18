package controller.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.entities.active.Active;
import model.logic.ColisionBox;
import view.DrawAnimation;

public abstract class ActiveEntityController
{

	protected ArrayList<ColisionBox> cbm;

	protected DrawAnimation da;

	public Rectangle future; // Para visualizar (eliminar)

	public ActiveEntityController ( ArrayList<ColisionBox> cbm )
	{
		this.cbm = cbm;
		this.da = new DrawAnimation();
	}

	public void update ()
	{
	}

	public void draw ( Graphics g )
	{
		da.draw(g);
	}

	protected void gravity ( Active e )
	{
		if ( !colision(e, 0) )
		{
			e.fall();
			return;
		}

		e.setMoving(false);
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

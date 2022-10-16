package controller.entities;

import java.awt.Rectangle;
import java.util.ArrayList;
import model.entities.active.Active;
import model.logic.ColisionBox;
import model.staticTools.vars;

public abstract class ActiveEntityController
{

	protected ArrayList<ColisionBox> cbm;

	public Rectangle[] future = new Rectangle[4];

	public ActiveEntityController ( ArrayList<ColisionBox> cbm )
	{
		this.cbm = cbm;
	}

	public boolean colision ( Active e )
	{

		/// modificar velocidad (5)
		// ABAJO (GRAVEDAD)
		future[0] = new Rectangle(	e.getCB().getBox().x, e.getCB().getBox().y + vars.gravity,
									e.getCB().getBox().width, e.getCB().getBox().height);

		// ARRIBA
		future[1] = new Rectangle(	e.getCB().getBox().x,
									e.getCB().getBox().y - (int) e.getVel(),
									e.getCB().getBox().width, e.getCB().getBox().height);

		// DERECHA
		future[2] = new Rectangle(	e.getCB().getBox().x + (int) e.getVel(),
									e.getCB().getBox().y, e.getCB().getBox().width,
									e.getCB().getBox().height);

		// IZQUIERDA
		future[3] = new Rectangle(	e.getCB().getBox().x - (int) e.getVel(),
									e.getCB().getBox().y, e.getCB().getBox().width,
									e.getCB().getBox().height);

		for ( ColisionBox cb : cbm )
		{
			for ( int i = 0; i < 4; i++ )
			{

				if ( future[i].intersects(cb.getBox()) )
				{
					// e.setInColision(true);
					return true;
				}
			}
		}
		return false;
	}
}

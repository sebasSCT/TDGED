package controller.entities;

import java.util.ArrayList;
import model.entities.active.Active;
import model.logic.ColisionBox;

public abstract class ActiveEntityController
{

	protected ArrayList<ColisionBox> cbm;

	public ActiveEntityController ( ArrayList<ColisionBox> cbm )
	{
		this.cbm = cbm;
	}

	public boolean colision ( Active e )
	{
		for ( ColisionBox cb : cbm )
		{
			if ( e.getCB().getBox().intersects(cb.getBox()) )
			{
				e.setInColision(true);
				return true;
			}
		}
		return false;
	}
}

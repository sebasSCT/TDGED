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
				da.get(i).setAnimation("a2");
			}

			if ( !ents.get(i).isFalling() && ents.get(i).isWalking() )
			{
				da.get(i).setAnimation("a1");
			}

			if ( !ents.get(i).isFalling() && !ents.get(i).isWalking() )
			{
				da.get(i).setAnimation("a0");
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

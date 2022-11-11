package controller.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import model.entities.active.Active;
import model.logic.ColisionBox;
import model.staticTools.vars;

/**
 * Clase ActiveEntityController que extiende de EntityController, esta se
 * encarga de controlar la logica de las entidades activas, como las fisicas,
 * movimiendo, direcciones, etc.
 * 
 * @author sebas_awitvuh
 *
 */
public abstract class ActiveEntityController extends EntityController
{

	// ArrayList de colisiones
	protected ArrayList<ColisionBox> cbm;

	private Rectangle future;

	public ActiveEntityController ( ArrayList<ColisionBox> cbm )
	{
		super();
		this.cbm = cbm;
	}

	public void update ()
	{
		for ( Active p : ents )
		{
			gravity(p);
		}

		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

	protected void gravity ( Active e )
	{
		if ( colision(e, "down") )
		{
			e.setAcl(1);
			e.setFalling(false);
			return;
		}
		fall(e);

	}

	private void fall ( Active e )
	{
		e.getPos().y += e.getAcl() * vars.delta;
		e.setAcl((e.getAcl() >= e.getMaxVel()) ? e.getAcl() : e.getAcl() + e.getG());
		e.getCB().setBox(e.getPos().x + e.getOffset().x, e.getPos().y + e.getOffset().y);
		e.setFalling(true);
	}

	protected void animDirections ()
	{

		for ( int i = 0; i < ents.size(); i++ )
		{
			if ( ents.get(i).isFalling() )
			{
				switch ( ents.get(i).getDirection() )
				{
					// Mas sprites de caida y lógica
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
						if ( ents.get(i).isCarrying() )
						{
							da.get(i).setAnimation("a7");
							break;
						}
						da.get(i).setAnimation("a3");
						break;

					case "right":
						if ( ents.get(i).isCarrying() )
						{
							da.get(i).setAnimation("a6");
							break;
						}
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
						if ( ents.get(i).isCarrying() )
						{
							da.get(i).setAnimation("a9");
							break;
						}
						da.get(i).setAnimation("a1");
						break;

					case "right":
						if ( ents.get(i).isCarrying() )
						{
							da.get(i).setAnimation("a8");
							break;
						}
						da.get(i).setAnimation("a0");
						break;

					// abajo (mismo sprite subir escaleras)
					// arriba
				}
			}

		}
	}

	protected void move ( String direction, Active e )
	{
		switch ( direction )
		{
			case "left":
				e.getPos().x -= e.getVel();
				e.getCB().setBox(e.getPos().x + e.getOffset().x,
						e.getPos().y + e.getOffset().y);
				e.setWalking(true);
				break;

			case "right":
				e.getPos().x += e.getVel();
				e.getCB().setBox(e.getPos().x + e.getOffset().x,
						e.getPos().y + e.getOffset().y);
				e.setWalking(true);
				break;

			case "up":
				e.getPos().y -= e.getVel();
				e.getCB().setBox(e.getPos().x + e.getOffset().x,
						e.getPos().y + e.getOffset().y);
				e.setWalking(true);
				break;

			case "down":
				e.getPos().y += e.getVel();
				e.getCB().setBox(e.getPos().x + e.getOffset().x,
						e.getPos().y + e.getOffset().y);
				e.setWalking(true);
				break;
		}
	}

	/**
	 * Colisiones segundo el movimiento
	 * 
	 * @param e
	 *            instancia de la clase Active la cual contiene datos sobre
	 *            colisione, fisicas y movimientos.
	 * @param side
	 *            un entero que representa un case con cada movimiento.
	 * @return retorna true si hay colision , false sino.
	 */
	protected boolean colision ( Active e, String side )
	{

		switch ( side )
		{
			case "down":
				// ABAJO (GRAVEDAD)
				future = new Rectangle(	e.getCB().getBox().x,
										e.getCB().getBox().y + (int) e.getAcl(),
										e.getCB().getBox().width, e.getCB().getBox().height);
				break;
			case "up":
				// ARRIBA
				future = new Rectangle(	e.getCB().getBox().x,
										e.getCB().getBox().y - (int) e.getVel(),
										e.getCB().getBox().width, e.getCB().getBox().height);
				break;
			case "right":
				// DERECHA
				future = new Rectangle(	e.getCB().getBox().x + (int) e.getVel(),
										e.getCB().getBox().y, e.getCB().getBox().width,
										e.getCB().getBox().height);
				break;
			case "left":
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

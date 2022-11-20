package model.entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import controller.scene.SpriteSheetController;
import model.logic.ColisionBox;
import model.logic.dataStructure.Duplet;
import model.staticTools.vars;

public abstract class Entity
{

	protected String id, direction;
	protected Point pos, offset;
	protected ColisionBox cb;

	protected Duplet<String, String> idAnim;
	protected Hashtable<String, ArrayList<BufferedImage>> animations;
	protected Hashtable<String, BufferedImage> sprites;
	protected SpriteSheetController ssc;

	public Entity ( String id, Point pos, Point offset )
	{
		this.id = id;
		this.pos = new Point(	pos.x * vars.spriteSize - offset.x,
								pos.y * vars.spriteSize - offset.y);
		offset.x += 2;
		this.offset = offset;
		cb = new ColisionBox(	vars.entitySpriteSize - offset.x - 12,
								vars.entitySpriteSize - offset.y - 1, this.pos.x + offset.x,
								this.pos.y + offset.y);
		animations = new Hashtable<>();
		sprites = new Hashtable<>();
		System.out.println("Entity");
	}

	public Hashtable<String, ArrayList<BufferedImage>> getAnimations ()
	{
		return animations;
	}

	public Hashtable<String, BufferedImage> getSprites ()
	{
		return sprites;
	}

	public ColisionBox getCB ()
	{
		return cb;
	}

	public Point getPos ()
	{
		return pos;
	}

	public String getID ()
	{
		return id;
	}

	public String getDirection ()
	{
		return direction;
	}

	public Point getOffset ()
	{
		return offset;
	}

	public SpriteSheetController getSSC ()
	{
		return ssc;
	}

	public Duplet<String, String> getIDA ()
	{
		return idAnim;
	}

	public void setDirection ( String direction )
	{
		this.direction = direction;
	}

	public void setPosTile ( double x, double y )
	{
		pos.setLocation(x * vars.spriteSize - offset.x, y * vars.spriteSize - offset.y);
		cb.setBox(pos.x + vars.screenOffSet - 4 + offset.x, pos.y + offset.y);
	}

	public void setPosTile ( Point pos )
	{
		pos.setLocation(pos.x * vars.spriteSize - offset.x,
				pos.y * vars.spriteSize - offset.y);
		cb.setBox(this.pos.x + vars.screenOffSet - 4 + offset.x, this.pos.y + offset.y);
	}

	public void setPos ( Point pos )
	{
		this.pos = pos;
		cb.setBox(this.pos.x + vars.screenOffSet + offset.x, this.pos.y + offset.y);
	}

	public void setPosX ( int x )
	{
		pos.x = x;
		cb.setBox(pos.x + vars.screenOffSet - 4 + offset.x, pos.y);
	}

	public void setPosY ( int y )
	{
		pos.y = y;
		cb.setBox(this.pos.x, this.pos.y + offset.y);
	}

	public void setIdanim ( String idAnim, String idAnim_ )
	{
		this.idAnim = new Duplet<String, String>(idAnim, idAnim_);
	}

}

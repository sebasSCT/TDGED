package model.entities.inactive;

import java.awt.Point;
import model.logic.dataStructure.Duplet;

public class Structure extends Inactive
{

	private String type;
	private String[] material;

	private Duplet<Boolean, Integer> charged;

	public Structure ( String id, Point pos, Point offset, String type, String material )
	{
		super(id, pos, offset);

		String[] data = material.split(":");
		this.material = data;

		this.type = type;

		this.charged = new Duplet<Boolean, Integer>(false, 99);
	}

	public Duplet<Boolean, Integer> getCharged ()
	{
		return charged;
	}

	public void setCharged ( boolean flag, int ind )
	{
		this.charged = new Duplet<Boolean, Integer>(flag, ind);
	}

	public String getType ()
	{
		return type;
	}

	public String[] getMaterial ()
	{
		return material;
	}

}

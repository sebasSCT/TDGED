package model.logic.dataStructure;

public class Duplet<A, B>
{

	private A _a;
	private B _b;

	public Duplet ( A _a, B _b )
	{
		this._a = _a;
		this._b = _b;
	}

	public A getA ()
	{
		return _a;
	}

	public B getB ()
	{
		return _b;
	}

}

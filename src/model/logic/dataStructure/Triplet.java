package model.logic.dataStructure;

public class Triplet<A, B, C>
{

	private A _a;
	private B _b;
	private C _c;

	public Triplet ( A _a, B _b, C _c )
	{
		this._a = _a;
		this._b = _b;
		this._c = _c;
	}

	public A getA ()
	{
		return _a;
	}

	public B getB ()
	{
		return _b;
	}

	public C getC ()
	{
		return _c;
	}
}

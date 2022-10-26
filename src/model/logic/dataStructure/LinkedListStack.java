package model.logic.dataStructure;

public class LinkedListStack
{

	private Node head;
	int nodes = 0;

	public LinkedListStack ()
	{
	}

	public void addNode ( Object value )
	{
		head = new Node(value, head);
		nodes++;
	}

	public void delHead ()
	{
		nodes--;
	}

	public Object unstack ()
	{
		Object r = head.getValue();
		head = head.getNext();
		nodes--;
		return r;
	}

	public int getSize ()
	{
		return nodes;
	}

	public boolean isEmpty ()
	{
		return (nodes == 0) ? true : false;
	}

	public void imprimir ()
	{
		Node rec = head;
		System.out.println("----------o-----------");
		for ( int x = 1; x <= nodes; x++ )
		{
			System.out.println(rec.getValue() + " " + x);
			rec = rec.getNext();
		}
		System.out.println("----------o-----------");
	}

}

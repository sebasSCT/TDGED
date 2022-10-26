package model.logic.dataStructure;

/**
 * Pachamama
 * 
 * @author sebas_awitvuh
 *
 */
public class LinkedListTail
{

	private Node head;
	int nodes = 0;

	public LinkedListTail ()
	{
	}

	public void addNode ( Object value )
	{
		if ( nodes == 0 )
		{
			head = new Node(value, null);
			nodes++;
			return;
		}
		if ( nodes == 1 )
		{
			head.setNext(new Node(value, null));
			nodes++;
			return;
		}

		head.setNext(addTail(value, 0, head));
		nodes++;
	}

	public Node addTail ( Object value, int i, Node aux )
	{
		if ( i == nodes - 1 )
		{
			return new Node(value, null);
		}
		aux = aux.getNext();
		aux.setNext(addTail(value, i + 1, aux));
		return aux;

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

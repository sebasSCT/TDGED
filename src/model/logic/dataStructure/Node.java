package model.logic.dataStructure;

public class Node
{

	private Object value;
	private Node next;

	public Node ( Object value, Node next )
	{
		this.value = value;
		this.next = next;
	}

	public void setNext ( Node n )
	{
		next = n;
	}

	public Object getValue ()
	{
		return value;
	}

	public Node getNext ()
	{
		return (next == null) ? new Node("empty", null) : next;
	}

}

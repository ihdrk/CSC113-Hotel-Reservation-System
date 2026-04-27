 import java.io.Serializable;
 
 public class Node implements Serializable
{   // Node class used by Linked List

    private Object data;// The data stored in this node
    private Node nextNode;// Reference to the next node in the linked list
    
    public Node(Object data)//Constructor to create a new node.
    {
        this.data = data;
        this.nextNode = null;
    }
    
    public Node(Object data, Node nextNode) 
    {
        this.data = data;
        this.nextNode = nextNode;
    }
    public Object getData() // returns data stored in node
    {
        return data;
    }
    
    public Node getNext()//returns the reference to the next node in the list.
    {
        return nextNode;
    }

    public void setNext(Node next)//Updates the reference to the next node.
    {
        this.nextNode = next;
    }
}

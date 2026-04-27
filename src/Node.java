 public class Node<T>
{   // Node class used by Linked List

    private T data;// The data stored in this node
    private Node<T>next;// Reference to the next node in the linked list
    
    public Node(T data)//Constructor to create a new node.
    {
        this.data = data;
        this.next = null;
    }

    public T getData() // returns data stored in node
    {
        return data;
    }
    
    public Node<T> getNext()//returns the reference to the next node in the list.
    {
        return next;
    }

    public void setNext(Node<T> next)//Updates the reference to the next node.
    {
        this.next = next;
    }
}

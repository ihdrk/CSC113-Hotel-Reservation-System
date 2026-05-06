import java.io.Serializable;
// implements Serializable for file saving
public class LinkedList implements Serializable
{
    private Node firstNode;// Head of the list
    private Node lastNode;// Tail of the list
    private int size;// Number of elements in the list

    public LinkedList()//Constructor: Initializes an empty linked list.
    {
        firstNode = lastNode = null;
        size = 0;
    }

    public boolean isEmpty()//Checks if the list contains any elements.
    {
        return firstNode == null;
    }

    public int getSize()//returns The current number of elements in the list
    {
        return size;
    }

     
     public void add(Object data)// Adds element to the end of the list
     {
        if(isEmpty())// If list is empty, new node becomes both first and last
        {
            firstNode = lastNode = new Node(data);
        }
        else
        {   // Assigns new node to the current end and update the lastNode pointer
            lastNode = lastNode.nextNode = new Node(data);
        }
        size++;
     }

     public Object get(int index) // Returns element at given index
     {
        if(index < 0 || index >= size) {return null;}//checks if index is within boundary
        Node current = firstNode;

        for(int i = 0; i < index; i++)// Traverse the list until the index is reached
        {
            current = current.nextNode;
        }
        return current.data;
     }

     public boolean removeAt(int index) // Removes element at given index
     {
        if(index < 0 || index >= size) {return false;}//checks if index is within boundary

        if(index == 0)// Case 1: Removing the first element
        {
            firstNode = firstNode.nextNode;
            if(firstNode == null)// If list became empty after removal
            {
                lastNode = null;
            }
            --size;
            return true;
            
        }
        // Case 2: Removing an element in the middle or end
        Node current = firstNode;
        for(int i = 0; i < index-1 ; ++i)// Move to the node right before the one we want to delete
        {current = current.nextNode;}
        if(current.nextNode == lastNode)// If we are removing the last node, update the lastNode pointer to current
        {lastNode = current;}
        
        current.nextNode = current.nextNode.nextNode;// Skip the node to be deleted by linking current to current.next.next
        size--;
        return true;

     }

     public void print()//Iterates through the list and prints each element's data
     {
        if(isEmpty())
        {
            System.out.println("The list is empty");
            return;
        }
        Node current  = firstNode;
        while(current != null)
        {
            System.out.println(current.data);
            current = current.nextNode;
        }
     }
}

import java.io.Serializable;
public class Customer extends Person implements Serializable
{   
   // LinkedList to store reservations and services instead of arrays
    private LinkedList reservations;
    private LinkedList services;

    //constructor initializes the linked list ,uses constructor of parent class (Person) with super method
    public Customer(String name , String id , String phone)
    {
        super(name,id,phone);
        reservations = new LinkedList();
        services = new LinkedList();
    }

    public boolean addReservation(Reservation r)
    {
        reservations.add(r); // Adds a reservation to the linked list
        return true; //always returns true since linked list has no fixed size limit
    }

    public boolean removeReservation(String reservationId) // Removes a reservation by searching for its specific ID
    {
        for(int i = 0 ; i < reservations.getSize(); ++i) // for loop used to traverse list
        {   Reservation r = (Reservation) reservations.get(i);
            if(r.getReservationId().equalsIgnoreCase(reservationId)) // compares IDs 
            {
                reservations.removeAt(i);
                 return true; // returning true after successful removal
            }
        }
        return false; // returns false after failure to remove
    }

    public Reservation searchReservation(String reservationId) // searches for a reservation by ID and returns the object
    {
        for(int i = 0 ; i < reservations.getSize() ; ++i)// for loop used to traverse alist
        { Reservation r = (Reservation) reservations.get(i);
            if(r.getReservationId().equals(reservationId)) // checks if ID matches
        {
            return r; //returns the full Reservation object matching the given ID
        }
         }
        return null; // returns null if reservationid is not found
    }

    // adds a service object to the customer service list
    public boolean addService(Service s)
    {
        services.add(s);
        return true;
    }

    // removes a service by its name from the services list
    public boolean removeService(String serviceName)
    {
        for(int i = 0; i < services.getSize(); i++)
        { Service s = (Service) services.get(i);
            if(s.getServiceName().equalsIgnoreCase(serviceName)) // compares service names
            {
                services.removeAt(i);
                return true;
            }
        }
        return false; // returns false if service was not found
    }

    // displays all added services for the customer
    public void displayServices()
    {
        if(services.isEmpty())
        {
            System.out.println("No services found");
            return;
        }

        for(int i = 0; i < services.getSize(); i++)
        {
            System.out.println(services.get(i)); // prints each service using toString method
        }
    }

    public double calculateBill()// calculates total bill for customer reservations and services
    {
        double total = 0;
        for(int i = 0 ; i < reservations.getSize(); ++ i) // loop through reservations 
        { Reservation r = (Reservation) reservations.get(i);
            total += r.calculateBill(); // add reservation cost to total
        }

        // adds bill of each service to total
        for(int i = 0; i < services.getSize(); ++i)// loop through services
        { Service s = (Service) services.get(i);
            total += s.calculateBill();// add service cost to total
        }

        return total;
    }

    // recursive method that calculates total bill for all services
    public double totalServicesRecursive(int index)
    {
        if(index == services.getSize()) // base case: end of list reached returns 0
            return 0.0;
        Service s = (Service) services.get(index);
        return s.calculateBill() + totalServicesRecursive(index + 1); // recursive case
    }

    public double totalSpentRecursive(int index)// Recursive method that calculates total bill for all reservations and services
    {
        if(index == reservations.getSize())// Base case: if all reservations processed, continue with services recursively
            return totalServicesRecursive(0);
        Reservation r = (Reservation) reservations.get(index);
        return r.calculateBill() + totalSpentRecursive(index+1);// Recursive case: adds current reservation bill to result of next call
    }

    // Checks if the customer has any reservations
    public boolean hasReservations()
    {
        return !reservations.isEmpty();
    }

    // Checks if the customer has any services
    public boolean hasServices()
    {
        return !services.isEmpty();
    }

   // Resets the services list to a new empty list
    public void clearServices()
    {
        services = new LinkedList();
    }

    public String getRole() // returns role (no parameter) from abstract class Person
    {
        return "Customer";
    }

    public void  displayReservations()// Loops through and prints all reservations for the customer
    {
        if(reservations.isEmpty()) // checks if list is empty
        {   
            System.out.println("No reservations Found");
            return;
        }
        for(int i = 0 ; i< reservations.getSize() ; i++) // traverses list printing objects
        {   
            System.out.println(reservations.get(i));
        }
    }

    public String toString() // returns info in string format (no parameters)
    {
        return "\nName: "+getName()+
        "\nID: "+getId()+
        "\nPhone: "+getPhone()+
        "\nRole: "+getRole()+
        "\nReservations: "+reservations.getSize()+
        "\nServices: "+services.getSize();
 
    }
 
}


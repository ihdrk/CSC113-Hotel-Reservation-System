public class Customer extends Person implements Payable
{   // private attributes only accessed directly inside class
    private Reservation[] reservations; // array of objects type Reservations
    private int numReservations;
    private static final int MAX = 10; //constant representing max number of reservations allowed

    //constructor with parameters initializing attributes ,uses constructor of parent class (Person) with super method
    public Customer(String name , String id , String phone)
    {
        super(name,id,phone);
        reservations = new Reservation[MAX]; // assigning size MAX to array of object
        numReservations = 0;

    }

    public boolean addReservation(Reservation r)
    {
        if(numReservations >=  MAX) //checks if number of reservations is less than MAX
        {return false;} // returns false if reservation is not added
        
        reservations[numReservations++] = r; // adds reservation to array of object
        return true; // returns true if reservation added successfully
    }

    public boolean removeReservation(String reservationId) //parameter is the reservationId that we want to remove
    {
        for(int i = 0 ; i < numReservations; ++i) // for loop used to traverse through array of objects
        {
            if(reservations[i].getReservationId().equals(reservationId)) // if statment used to compare Id of all reservations with Id of reservation we want to remove (from parameter)
            {
                for(int j = i; j < numReservations -1 ; j++)// for loop used to traverse through array of objects starting from index of reservation we want to remove
                {
                    reservations[j] = reservations[j+1]; // moves all values 1 index to the left replacing the deleted reservation
                }
                 reservations[numReservations-1] = null; // making the last value null so we wouldn't have 2 reservations with the same id
                 numReservations--;
                 return true; // returning true after successful removal
            }
        }
        return false; // returns false after failure to remove
    }

    public Reservation searchReservation(String reservationId) // searches through array of object till reservation id in parameter is found
    {
        for(int i = 0 ; i < numReservations ; ++i)// for loop used to traverse through array of objects
        {if(reservations[i].getReservationId().equals(reservationId)) // if statment used to compare Id of all reservations with Id of reservation we want to find (from parameter)
        {
            return reservations[i]; //returns the full Reservation object matching the given ID
        }
         }
        return null; // returns null if reservationid is not found
    }

    public double calculateBill()// calculates bill method from Interface Payable
    {
        double total = 0;
        for(int i = 0 ; i < numReservations; ++ i) // for loop traverses through array of object 
        {
            total += reservations[i].calculateBill(); // adding bill of each reservation to total (calculateBill method from Reservation class)
        }
        return total;
    }

    public double totalSpentRecursive(int index)// Recursive method that calculates total bill for all reservations
    {
        if(index == numReservations)// Base case: index reaches numReservations, returns 0
            return 0.0;
        
        return reservations[index].calculateBill() + totalSpentRecursive(index+1);// Recursive case: adds current reservation bill to result of next call
    }

    public String getRole() // returns role (no parameter) from abstract class Person
    {
        return "Customer";
    }

    public void  displayReservations()
    {
        if(numReservations != 0) // makes sure atleast 1 reservation exists
        {
            for(int i = 0 ; i< numReservations ; i++) // traverses through array of object printing each one (toString is used)
            {
                System.out.println(reservations[i]);
            }
        }
        else
            System.out.println("No reservations Found");
    }

    public String toString() // returns info in string format (no parameters)
    {
        return "\nName: "+getName()+
        "\nID: "+getId()+
        "\nPhone: "+getPhone()+
        "\nRole: "+getRole()+
        "\nReservations: "+numReservations;
 
    }


}

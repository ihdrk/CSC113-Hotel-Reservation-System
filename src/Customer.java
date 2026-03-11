public class Customer extends Person implements Payable
{
    private Reservation[] reservations;
    private int numReservations;
    private static final int MAX = 10;

    public Customer(String name , String id , String phone)
    {
        super(name,id,phone);
        reservations = new Reservation[MAX];
        numReservations = 0;

    }

    public boolean addReservation(Reservation r)
    {
        if(numReservations >=  MAX)
        {return false;}
        
        reservations[numReservations++] = r;
        return true;
    }

    public boolean removeReservation(String reservationId)
    {
        for(int i = 0 ; i < numReservations; ++i)
        {
            if(reservations[i].getReservationId().equals(reservationId))
            {
                for(int j = i; j < numReservations -1 ; j++)
                {
                    reservations[j] = reservations[j+1];
                }
                 reservations[numReservations-1] = null;
                 numReservations--;
                 return true;
            }
        }
        return false;
    }

    public Reservation searchReservation(String reservationId)
    {
        for(int i = 0 ; i < numReservations ; ++i)
        {if(reservations[i].getReservationId().equals(reservationId))
        {
            return reservations[i];
        }
         }
        return null;
    }

    public double calculateBill()
    {
        double total = 0;
        for(int i = 0 ; i < numReservations; ++ i)
        {
            total += reservations[i].calculateBill();
        }
        return total;
    }

    public double totalSpentRecursive(int index)
    {
        if(index == numReservations)
            return 0.0;
        
        return reservations[index].calculateBill() + totalSpentRecursive(index+1);
    }

    public String getRole()
    {
        return "Customer";
    }

    public void  displayReservations()
    {
        if(numReservations != 0)
        {
            for(int i = 0 ; i< numReservations ; i++)
            {
                System.out.println(reservations[i]);
            }
        }
        else
            System.out.println("No reservations Found");
    }

    public String toString()
    {
        return "\nName: "+getName()+"\nID: "+getId()+"\nPhone: "+getPhone()+"\nRole: "+getRole()+"\nReservations: "+numReservations;
 
    }


}

public class Customer extends Person implements Payable
{
    private Reservation[] reservations;
    private int numReservations;
    private Static final int MAX = 10;

    Customer(String name , String id , String phone)
    {
        super(name,id,phone);

    }

    public boolean addReservation(Reservation r)
    {
        if(numReservations >  MAX)
        {return false;}
        
        reservations[numReservations++] = r;
        return true;
    }

    public boolean removeReservation(String reservationId)
    {
        for(int i = 0 ; reservations[i].getReservationId.equals(reservationId); ++i)
        {
            for(int j = i; j < numReservations -1 ; j++)
            {
                reservations[j] = reservations[j+1];
                reservations[numReservations-1] = null;
                numReservations--;
                return true;
            }
        }
        return false;
    }

    public Reservations searchReservation(String reservationId)
    {
        for(int i = 0 ; reservations[i].getReservationId.equals(reservationId); ++i)
        {return reservations[i];}
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
        
        return reservations[index].calculateBill + totalSpentRecursive(index+1);
    }

    public String getRole()
    {
        return "Customer";
    }

    public void  displayReservations()
    {
        if(numReservations != 0)
        {
            for(int i = 0 ; i< numReservations : ++i)
            {
                reservations[i].toString();
            }
        }
        else
            System.out.println("No reservations Found");
    }

    public String ToString()
    {
        return "\nName: "+name+"\nID: "+id+"\nPhone: "+phone+"\nRole: "+getRole()+"\nReservations"+numReservations;
 
    }


}

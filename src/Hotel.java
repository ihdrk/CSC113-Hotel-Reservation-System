import java.io.Serializable;
public class Hotel implements Serializable 
{
	
    // hotel name
    private String hotelName;

    private LinkedList rooms;
    private LinkedList customers;

    private int nextReservationNumber;
    // constructor initializes hotel name, linked lists, and reservation number
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        rooms = new LinkedList();
        customers = new LinkedList();
        nextReservationNumber = 1;
    }

    // adds a room object to hotel after checking duplication
    public boolean addRoom(Room r)
    {
        if(searchRoom(r.getRoomNumber()) != null) // making sure  a room with the same number doesnt already exist
        {
            return false;
        }

        // if room is DeluxeRoom, store a copied DeluxeRoom object in  linked list
        if(r instanceof DeluxeRoom) // checking if room is of type DeluxeRoom
        {rooms.add(new DeluxeRoom((DeluxeRoom) r));}// calling copy copy constructor of DeluxeeRoom and adding the copy to rooms linked list

        // if room is StandardRoom, store a copied StandardRoom object in  linked list
        else if(r instanceof StandardRoom)// checking if room is of type StandardRoom
        { rooms.add(new StandardRoom((StandardRoom) r));}// calling copy copy constructor of StandardRoom and adding the copy to rooms  linked list

        // otherwise store a copied normal Room object
        else
        {rooms.add(new Room(r));}// calling copy copy constructor of Room and adding the copy to rooms  linked list
        return true;
    }

    // removes a room using room number from the linked list
    public boolean removeRoom(int roomNumber) {
        for (int i = 0; i < rooms.getSize(); i++) 
            {Room r = (Room) rooms.get(i);
            if (r.getRoomNumber() == roomNumber) {
                rooms.removeAt(i);
                return true;
            }
        }
        return false;
    }

    // searches for a room by room number and returns it if found
    public Room searchRoom(int roomNumber) {
        for (int i = 0; i < rooms.getSize(); i++) 
            {Room r = (Room) rooms.get(i);
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    // displays only rooms that are currently available
    public void displayAvailableRooms() {
        boolean found = false;
        for (int i = 0; i < rooms.getSize(); i++) 
            {Room r = (Room) rooms.get(i);
            if (r.isAvailable()) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms.");
        }
    }

    // adds a customer to hotel after checking duplicate id 
    public boolean addCustomer(Customer c) {
        if (searchCustomer(c.getId()) != null) {
            System.out.println("Customer already registered");
            return false;
        }

        customers.add(c);
        return true;
    }

    // searches for a customer by id and returns matching customer object
    public Customer searchCustomer(String customerId) {
        for (int i = 0; i < customers.getSize(); i++) 
            {Customer c = (Customer) customers.get(i);
            if (c.getId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }

    // creates a reservation for a customer after validating customer, room, availability, and dates
    public boolean makeReservation(String customerId, int roomNumber, int checkInDay, int checkOutDay)throws RoomUnavailableException {
        // first search for customer in customer linked list
        Customer customer = searchCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found");
            return false;
        }

        // search for requested room in room linked list
        Room room = searchRoom(roomNumber);
        if (room == null) {
            System.out.println("Room not found");
            return false;
        }

        // reservation cannot be made if room is already reserved
        if (!room.isAvailable()) {
          throw new RoomUnavailableException(roomNumber);
        }

        // make sure check-out day is after check-in day
        if (checkOutDay <= checkInDay) {
            System.out.println("Invalid dates");
            return false;
        }

        // generate unique reservation id using counter
        String reservationId = "RES-" + nextReservationNumber++;

        // create new reservation object using entered data
        Reservation res = new Reservation(reservationId, customerId, room, checkInDay, checkOutDay);

        // add created reservation to customer's reservation linked list
        if (!customer.addReservation(res)) {
            System.out.println("Customer reservation list full");
            return false;
        }

        // once reservation is successful, room becomes unavailable
        room.setAvailable(false);

        System.out.println("Reservation successful: " + res);
        return true;
    }

    // cancels a reservation for a specific customer and frees the room again
    public boolean cancelReservation(String customerId, String reservationId) {
        // search for customer first
        Customer customer = searchCustomer(customerId);
        if (customer == null) {
            return false;
        }

        // search for reservation inside this customer's reservations
        Reservation res = customer.searchReservation(reservationId);
        if (res == null) {
            System.out.println("Reservation not found");
            return false;
        }

        // get the room linked to this reservation
        Room room = res.getRoom();

        // make room available again after cancellation
        room.setAvailable(true);

        // mark reservation as inactive
        res.setActive(false);

        // remove reservation from customer's reservation linked list
        customer.removeReservation(reservationId);

        // if customer has no remaining reservations, remove all added services
        if(!customer.hasReservations())
        {
            customer.clearServices();
        }

        System.out.println("Reservation cancelled successfully.");
        return true;
    }

    // recursive method that calculates total hotel revenue from all customers
    public double totalRevenueRecursive(int index) {
        // base case: all customers processed
        if (index == customers.getSize()) {
            return 0.0;
            
        }

        // recursive case: current customer bill + remaining customers bills
        Customer c = (Customer) customers.get(index);
        return c.calculateBill() + totalRevenueRecursive(index + 1);
    }

    // returns summary information about hotel
    public String toString() {
        return "Hotel: " + hotelName + " | Rooms: " + rooms.getSize() + " | Customers: " + customers.getSize();
    }

     // Getter for hotel name
    public String getHotelName() {
        return hotelName;
    }

    // Getter for rooms linked list — used by GUI panels to display rooms
    public LinkedList getRooms() {
        return rooms;
    }

    // Getter for customers linked list — used by GUI panels to display customers
    public LinkedList getCustomers() {
        return customers;
    }
}

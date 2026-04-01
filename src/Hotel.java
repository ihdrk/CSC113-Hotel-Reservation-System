public class Hotel {
	
    // hotel name
    private String hotelName;

    // array of room objects managed by hotel
    private Room[] rooms;

    // array of customer objects managed by hotel
    private Customer[] customers;

    // current number of rooms stored in rooms array
    private int numRooms;

    // current number of customers stored in customers array
    private int numCustomers;

    // maximum number of rooms hotel can store
    private static final int MAX_ROOMS = 50;

    // maximum number of customers hotel can store
    private static final int MAX_CUSTOMERS = 100;

    // counter used to generate unique reservation ids
    private int nextReservationNumber;

    // constructor initializes hotel name, arrays, counters, and reservation number
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        rooms = new Room[MAX_ROOMS];
        customers = new Customer[MAX_CUSTOMERS];
        numRooms = 0;
        numCustomers = 0;
        nextReservationNumber = 1;
    }

    // adds a room object to hotel after checking capacity and duplication
    public boolean addRoom(Room r)
    {
        if (numRooms >= MAX_ROOMS) // making sure there is space for room in array
        {
        return false;
        }

        if(searchRoom(r.getRoomNumber()) != null) // making sure  a room with the same number doesnt already exist
        {
            return false;
        }

        // if room is DeluxeRoom, store a copied DeluxeRoom object in array
        if(r instanceof DeluxeRoom) // checking if room is of type DeluxeRoom
        {rooms[numRooms++] = new DeluxeRoom((DeluxeRoom) r); return true;}// calling copy copy constructor of DeluxeeRoom and adding the copy to rooms array

        // if room is StandardRoom, store a copied StandardRoom object in array
        else if(r instanceof StandardRoom)// checking if room is of type StandardRoom
        { rooms[numRooms++] = new StandardRoom((StandardRoom) r); return true;}// calling copy copy constructor of StandardRoom and adding the copy to rooms array

        // otherwise store a copied normal Room object
        else
        {rooms[numRooms++] = new Room(r); return true;}// calling copy copy constructor of Room and adding the copy to rooms array
    }

    // removes a room using room number by shifting elements left after deletion
    public boolean removeRoom(int roomNumber) {
        for (int i = 0; i < numRooms; i++) {
            if (rooms[i].getRoomNumber() == roomNumber) {
                for (int j = i; j < numRooms - 1; j++) {
                    rooms[j] = rooms[j + 1];
                }
                rooms[numRooms - 1] = null;
                numRooms--;
                return true;
            }
        }
        return false;
    }

    // searches for a room by room number and returns it if found
    public Room searchRoom(int roomNumber) {
        for (int i = 0; i < numRooms; i++) {
            if (rooms[i].getRoomNumber() == roomNumber) {
                return rooms[i];
            }
        }
        return null;
    }

    // displays only rooms that are currently available
    public void displayAvailableRooms() {
        boolean found = false;
        for (int i = 0; i < numRooms; i++) {
            if (rooms[i].isAvailable()) {
                System.out.println(rooms[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms.");
        }
    }

    // adds a customer to hotel after checking duplicate id and array capacity
    public boolean addCustomer(Customer c) {
        if (searchCustomer(c.getId()) != null) {
            System.out.println("Customer already registered");
            return false;
        }

        if (numCustomers >= MAX_CUSTOMERS) {
            return false;
        }

        customers[numCustomers] = c;
        numCustomers++;
        return true;
    }

    // searches for a customer by id and returns matching customer object
    public Customer searchCustomer(String customerId) {
        for (int i = 0; i < numCustomers; i++) {
            if (customers[i].getId().equals(customerId)) {
                return customers[i];
            }
        }
        return null;
    }

    // creates a reservation for a customer after validating customer, room, availability, and dates
    public boolean makeReservation(String customerId, int roomNumber, int checkInDay, int checkOutDay) {
        // first search for customer in customer array
        Customer customer = searchCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found");
            return false;
        }

        // search for requested room in room array
        Room room = searchRoom(roomNumber);
        if (room == null) {
            System.out.println("Room not found");
            return false;
        }

        // reservation cannot be made if room is already reserved
        if (!room.isAvailable()) {
            System.out.println("Room not available");
            return false;
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

        // add created reservation to customer's reservation array
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

        // remove reservation from customer's reservation array
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
        if (index == numCustomers) {
            return 0.0;
        }

        // recursive case: current customer bill + remaining customers bills
        return customers[index].calculateBill() + totalRevenueRecursive(index + 1);
    }

    // returns summary information about hotel
    public String toString() {
        return "Hotel: " + hotelName + " | Rooms: " + numRooms + " | Customers: " + numCustomers;
    }
}

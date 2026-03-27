public class Hotel {
	
    private String hotelName;
    private Room[] rooms;
    private Customer[] customers;
    private int numRooms;
    private int numCustomers;
    private static final int MAX_ROOMS = 50;
    private static final int MAX_CUSTOMERS = 100;
    private int nextReservationNumber;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        rooms = new Room[MAX_ROOMS];
        customers = new Customer[MAX_CUSTOMERS];
        numRooms = 0;
        numCustomers = 0;
        nextReservationNumber = 1;
    }

    public boolean addRoom(Room r) {
        if (numRooms >= MAX_ROOMS) {
            return false;
        }
        if (searchRoom(r.getRoomNumber()) != null) {
        return false;
        }
        rooms[numRooms] = r;
        numRooms++;
        return true;
    }

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

    public Room searchRoom(int roomNumber) {
        for (int i = 0; i < numRooms; i++) {
            if (rooms[i].getRoomNumber() == roomNumber) {
                return rooms[i];
            }
        }
        return null;
    }

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

    public Customer searchCustomer(String customerId) {
        for (int i = 0; i < numCustomers; i++) {
            if (customers[i].getId().equals(customerId)) {
                return customers[i];
            }
        }
        return null;
    }

    public boolean makeReservation(String customerId, int roomNumber, int checkInDay, int checkOutDay) {
        Customer customer = searchCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found");
            return false;
        }

        Room room = searchRoom(roomNumber);
        if (room == null) {
            System.out.println("Room not found");
            return false;
        }

        if (!room.isAvailable()) {
            System.out.println("Room not available");
            return false;
        }

        if (checkOutDay <= checkInDay) {
            System.out.println("Invalid dates");
            return false;
        }

        String reservationId = "RES-" + nextReservationNumber++;
        Reservation res = new Reservation(reservationId, customerId, room, checkInDay, checkOutDay);

        if (!customer.addReservation(res)) {
            System.out.println("Customer reservation list full");
            return false;
        }

        room.setAvailable(false);
        System.out.println("Reservation successful: " + res);
        return true;
    }

    public boolean cancelReservation(String customerId, String reservationId) {
        Customer customer = searchCustomer(customerId);
        if (customer == null) {
            return false;
        }

        Reservation res = customer.searchReservation(reservationId);
        if (res == null) {
            System.out.println("Reservation not found");
            return false;
        }

        Room room = res.getRoom();
        room.setAvailable(true);
        res.setActive(false);
        customer.removeReservation(reservationId);

        System.out.println("Reservation cancelled successfully.");
        return true;
    }

    public double totalRevenueRecursive(int index) {
        if (index == numCustomers) {
            return 0.0;
        }
        return customers[index].calculateBill() + totalRevenueRecursive(index + 1);
    }

    public String toString() {
        return "Hotel: " + hotelName + " | Rooms: " + numRooms + " | Customers: " + numCustomers;
    }
}

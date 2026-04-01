import java.util.Scanner;

// Main class that runs the hotel reservation system
// It displays the menu, takes user input, and calls the correct methods
public class HotelSystem {
    public static void main(String[] args) {
        // Scanner object used to read input from the user
        Scanner sc = new Scanner(System.in);

        // Create hotel object
        Hotel hotel = new Hotel("KSU Hotel");

        // Create some employee objects to display hotel staff
        Employee emp1 = new Employee("Ahmed Al-Rashid", "E1", "0512345678", "Receptionist", 5000);
        Employee emp2 = new Employee("Majed Al-Qahtani", "E2", "0587654321", "Manager", 9000);

        // Create room objects
        StandardRoom s1 = new StandardRoom(101, 150, 2, true);
        StandardRoom s2 = new StandardRoom(102, 120, 1, false);
        DeluxeRoom d1 = new DeluxeRoom(201, 300, 2, true, true,true, 75.0);
        DeluxeRoom d2 = new DeluxeRoom(202, 280, 2, true, false,true, 50.0);

        // Add rooms to the hotel
        hotel.addRoom(s1);
        hotel.addRoom(s2);
        hotel.addRoom(d1);
        hotel.addRoom(d2);

        // Variable controls the menu loop
        boolean running = true;

        // Main menu loop keeps running until user chooses Exit
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            // Switch used to execute the correct operation based on user choice
            switch (choice) {
                case 1:
                    // Register a new customer in the hotel system
                    registerCustomer(hotel, sc);
                    break;
                case 2:
                    // Display all available rooms
                    hotel.displayAvailableRooms();
                    break;
                case 3:
                    // Make a reservation for a customer
                    makeReservation(hotel, sc);
                    break;
                case 4:
                    // Cancel an existing reservation
                    cancelReservation(hotel, sc);
                    break;
                case 5:
                    // Display all reservations and added services for a customer
                    viewCustomerReservations(hotel, sc);
                    break;
                case 6:
                    // Display the total bill for a customer
                    viewBill(hotel, sc);
                    break;
                case 7:
                    // Add an extra hotel service such as breakfast, lunch, dinner, laundry, spa, or airport pickup
                    addServiceToCustomer(hotel, sc);
                    break;
                case 8:
                    // Remove a service from a customer using service name
                    removeServiceFromCustomer(hotel, sc);
                    break;
                case 9:
                    // Display total revenue of the hotel using recursive method
                    System.out.println("Total hotel revenue: $" + hotel.totalRevenueRecursive(0));
                    break;
                case 10:
                    // Display hotel staff information
                    System.out.println(emp1 +"\n"+ emp2);
                    break;
                case 11:
                    // Stop the loop and exit the system
                    running = false;
                    break;
                default:
                    // Runs if user enters invalid menu option
                    System.out.println("Invalid option.");
            }
        }

        // Closing message
        System.out.println("Thank you! Goodbye.");
        sc.close();
    }

    // Prints the menu options shown to the user
    public static void printMenu() {
        System.out.println(" ");
        System.out.println("===== KSU Hotel =====");
        System.out.println("1. Register as customer");
        System.out.println("2. View available rooms");
        System.out.println("3. Make a reservation");
        System.out.println("4. Cancel a reservation");
        System.out.println("5. View my reservations");
        System.out.println("6. View my bill");
        System.out.println("7. Add service");
        System.out.println("8. Remove service");
        System.out.println("9. View total hotel revenue");
        System.out.println("10. View hotel staff");
        System.out.println("11. Exit");
        System.out.print("Enter choice: ");
    }

    // Registers a new customer by taking name, ID, and phone from user
    public static void registerCustomer(Hotel hotel, Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        Customer customer = new Customer(name, id, phone);

        if (hotel.addCustomer(customer)) {
            System.out.println("Customer registered successfully.");
        } else {
            System.out.println("Failed to register customer.");
        }
    }

    // Makes a reservation by taking customer ID, room number, and stay period
    public static void makeReservation(Hotel hotel, Scanner sc) {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();

        System.out.print("Enter check-in day: ");
        int checkInDay = sc.nextInt();

        System.out.print("Enter check-out day: ");
        int checkOutDay = sc.nextInt();
        sc.nextLine();

        hotel.makeReservation(customerId, roomNumber, checkInDay, checkOutDay);
    }

    // Cancels an existing reservation using customer ID and reservation ID
    public static void cancelReservation(Hotel hotel, Scanner sc) {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        System.out.print("Enter reservation ID: ");
        String reservationId = sc.nextLine();

        if (!hotel.cancelReservation(customerId, reservationId)) {
            System.out.println("Cancellation failed.");
        }
    }

    // Displays all reservations and services for a specific customer
    public static void viewCustomerReservations(Hotel hotel, Scanner sc) {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        Customer customer = hotel.searchCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            customer.displayReservations();
            customer.displayServices();
        }
    }

    // Displays customer bill using both loop and recursion
    public static void viewBill(Hotel hotel, Scanner sc) {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        Customer customer = hotel.searchCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            System.out.println("Bill using loop: $" + customer.calculateBill());
            System.out.println("Bill using recursion: $" + customer.totalSpentRecursive(0));
        }
    }

    // Adds a service to a specific customer
    // Customer must already have at least one reservation before adding a service
    public static void addServiceToCustomer(Hotel hotel, Scanner sc) {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        // Search for customer before adding service
        Customer customer = hotel.searchCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        // customer must already have at least one reservation before adding a service
        if (!customer.hasReservations()) {
            System.out.println("Customer must have at least one reservation first.");
            return;
        }

        // Display available services
        System.out.println("Choose service:");
        System.out.println("1. Breakfast - 30");
        System.out.println("2. Lunch - 45");
        System.out.println("3. Dinner - 50");
        System.out.println("4. Laundry - 25");
        System.out.println("5. Spa - 100");
        System.out.println("6. Airport Pickup - 80");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        // Service reference that will store selected service object
        Service service = null;

        // Create the correct service object depending on user choice
        switch (choice) {
            case 1:
                service = new Service("Breakfast", 30);
                break;
            case 2:
                service = new Service("Lunch", 45);
                break;
            case 3:
                service = new Service("Dinner", 50);
                break;
            case 4:
                service = new Service("Laundry", 25);
                break;
            case 5:
                service = new Service("Spa", 100);
                break;
            case 6:
                service = new Service("Airport Pickup", 80);
                break;
            default:
                System.out.println("Invalid service.");
                return;
        }

        // Add selected service to the customer's service array
        if(customer.addService(service))
            System.out.println("Service added successfully.");
        else
            System.out.println("Failed to add service.");
    }

    // removes a selected service from a specific customer
    public static void removeServiceFromCustomer(Hotel hotel, Scanner sc)
    {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        Customer customer = hotel.searchCustomer(customerId);

        if(customer == null)
        {
            System.out.println("Customer not found.");
            return;
        }
     // customer must already have at least one service before removing one
        if(!customer.hasServices())
        {
            System.out.println("Customer has no services to remove.");
            return;
        }

        System.out.print("Enter service name to remove: ");
        String serviceName = sc.nextLine();

        if(customer.removeService(serviceName))
        {
            System.out.println("Service removed successfully.");
        }
        else
        {
            System.out.println("Service not found.");
        }
    }
}

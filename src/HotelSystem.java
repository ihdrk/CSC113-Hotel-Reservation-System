import java.util.Scanner;
import java.io.IOException;

public class HotelSystem {

    public static void main(String[] args) {

        // Scanner to read input from user
        Scanner sc = new Scanner(System.in);

        // Load saved hotel data from file
        Hotel hotel = HotelFileManager.loadHotel();

        // Staff objects used in option 10
        Employee emp1 = new Employee("Ahmed Al-Rashid", "E1", "0512345678", "Receptionist", 5000);
        Employee emp2 = new Employee("Majed Al-Qahtani", "E2", "0587654321", "Manager", 9000);

        // Controls the menu loop
        boolean running = true;

        while (running) {

            printMenu();

            // Read menu choice with input handling
            int choice = readIntInRange(sc, "Enter choice: ", 1, 11);

            switch (choice) {

                case 1:
                    registerCustomer(hotel, sc);
                    break;

                case 2:
                    hotel.displayAvailableRooms();
                    break;

                case 3:
                    try {
                        makeReservation(hotel, sc);
                    } catch (RoomUnavailableException e) {
                        System.out.println("Room unavailable: " + e.getMessage());
                    }
                    break;

                case 4:
                    cancelReservation(hotel, sc);
                    break;

                case 5:
                    viewCustomerReservations(hotel, sc);
                    break;

                case 6:
                    viewBill(hotel, sc);
                    break;

                case 7:
                    addServiceToCustomer(hotel, sc);
                    break;

                case 8:
                    removeServiceFromCustomer(hotel, sc);
                    break;

                case 9:
                    System.out.println("Total hotel revenue: $" + hotel.totalRevenueRecursive(0));
                    break;

                case 10:
                    System.out.println(emp1);
                    System.out.println(emp2);
                    break;

                case 11:
                    // Save data before exiting
                    try {
                        HotelFileManager.saveHotel(hotel);
                        System.out.println("Data saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Save failed: " + e.getMessage());
                    }

                    running = false;
                    break;
            }
        }

        System.out.println("Thank you! Goodbye.");
        sc.close();
    }

    // Prints the main menu
    public static void printMenu() {
        System.out.println();
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
        System.out.println("11. Save and Exit");
    }

    // Reads an integer and keeps asking if the user enters letters
    public static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            }

            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine();
        }
    }

    // Reads an integer within a specific range
    public static int readIntInRange(Scanner sc, String message, int min, int max) {
        while (true) {
            int value = readInt(sc, message);

            if (value >= min && value <= max) {
                return value;
            }

            System.out.println("Invalid input. Enter a number from " + min + " to " + max + ".");
        }
    }

    // Reads text and does not allow empty input
    public static String readRequiredText(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Invalid input. This field cannot be empty.");
        }
    }

    // Reads a name and does not allow numbers
    public static String readName(Scanner sc, String message) {
        while (true) {
            String name = readRequiredText(sc, message);

            if (isValidName(name)) {
                return name;
            }

            System.out.println("Invalid name. Name must contain letters and spaces only.");
        }
    }

    // Checks if name contains only letters and spaces
    public static boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            if (!Character.isLetter(ch) && ch != ' ') {
                return false;
            }
        }

        return true;
    }

    // Reads an ID and does not allow letters
    public static String readNumericId(Scanner sc, String message) {
        while (true) {
            String id = readRequiredText(sc, message);

            if (isOnlyDigits(id)) {
                return id;
            }

            System.out.println("Invalid ID. ID must contain numbers only.");
        }
    }

    // Reads a phone number and does not allow letters
    public static String readPhone(Scanner sc, String message) {
        while (true) {
            String phone = readRequiredText(sc, message);

            if (isOnlyDigits(phone)) {
                return phone;
            }

            System.out.println("Invalid phone number. Phone must contain numbers only.");
        }
    }

    // Checks if the text contains digits only
    public static boolean isOnlyDigits(String text) {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        return true;
    }

    // Registers a new customer
    public static void registerCustomer(Hotel hotel, Scanner sc) {

        String name = readName(sc, "Enter name: ");
        String id = readNumericId(sc, "Enter ID: ");
        String phone = readPhone(sc, "Enter phone: ");

        Customer customer = new Customer(name, id, phone);

        if (hotel.addCustomer(customer)) {
            System.out.println("Customer registered successfully.");
        } else {
            System.out.println("Failed to register customer. Customer ID already exists.");
        }
    }

    // Makes a reservation for a customer
    public static void makeReservation(Hotel hotel, Scanner sc) throws RoomUnavailableException {

        String customerId = readNumericId(sc, "Enter customer ID: ");

        int roomNumber = readInt(sc, "Enter room number: ");
        int checkInDay = readIntInRange(sc, "Enter check-in day: ", 1, 31);
        int checkOutDay = readIntInRange(sc, "Enter check-out day: ", 1, 31);

        // Check-out must be after check-in
        if (checkOutDay <= checkInDay) {
            System.out.println("Invalid dates. Check-out day must be after check-in day.");
            return;
        }

        boolean success = hotel.makeReservation(customerId, roomNumber, checkInDay, checkOutDay);

        if (success) {
            System.out.println("Reservation created successfully.");
        } else {
            System.out.println("Reservation failed. Check customer ID or room number.");
        }
    }

    // Cancels an existing reservation
    public static void cancelReservation(Hotel hotel, Scanner sc) {

        String customerId = readNumericId(sc, "Enter customer ID: ");
        String reservationId = readRequiredText(sc, "Enter reservation ID: ");

        if (hotel.cancelReservation(customerId, reservationId)) {
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Cancellation failed. Check customer ID and reservation ID.");
        }
    }

    // Displays reservations and services for a customer
    public static void viewCustomerReservations(Hotel hotel, Scanner sc) {

        String customerId = readNumericId(sc, "Enter customer ID: ");

        Customer customer = hotel.searchCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            customer.displayReservations();
            customer.displayServices();
        }
    }

    // Displays the bill for a customer
    public static void viewBill(Hotel hotel, Scanner sc) {

        String customerId = readNumericId(sc, "Enter customer ID: ");

        Customer customer = hotel.searchCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            System.out.println("Bill using loop: $" + customer.calculateBill());
            System.out.println("Bill using recursion: $" + customer.totalSpentRecursive(0));
        }
    }

    // Adds a service to a customer
    public static void addServiceToCustomer(Hotel hotel, Scanner sc) {

        String customerId = readNumericId(sc, "Enter customer ID: ");

        Customer customer = hotel.searchCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        // Customer must have a reservation before adding services
        if (!customer.hasReservations()) {
            System.out.println("Customer must have at least one reservation first.");
            return;
        }

        System.out.println("Choose service:");
        System.out.println("1. Breakfast - 30");
        System.out.println("2. Lunch - 45");
        System.out.println("3. Dinner - 50");
        System.out.println("4. Laundry - 25");
        System.out.println("5. Spa - 100");
        System.out.println("6. Airport Pickup - 80");

        int choice = readIntInRange(sc, "Enter choice: ", 1, 6);

        Service service = null;

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
        }

        customer.addService(service);
        System.out.println("Service added successfully.");
    }

    // Removes a service from a customer
    public static void removeServiceFromCustomer(Hotel hotel, Scanner sc) {

        String customerId = readNumericId(sc, "Enter customer ID: ");

        Customer customer = hotel.searchCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (!customer.hasServices()) {
            System.out.println("Customer has no services to remove.");
            return;
        }

        String serviceName = readName(sc, "Enter service name to remove: ");

        if (customer.removeService(serviceName)) {
            System.out.println("Service removed successfully.");
        } else {
            System.out.println("Service not found.");
        }
    }
}

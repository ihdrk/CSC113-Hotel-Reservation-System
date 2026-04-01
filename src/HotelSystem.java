import java.util.Scanner;

public class HotelSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel("Grand Palace Hotel");

        Employee emp1 = new Employee("Ahmed Al-Rashid", "E1", "0512345678", "Receptionist", 5000);
        Employee emp2 = new Employee("Majed Al-Qahtani", "E2", "0587654321", "Manager", 9000);

        StandardRoom s1 = new StandardRoom(101, 150, 2, true);
        StandardRoom s2 = new StandardRoom(102, 120, 1, false);
        DeluxeRoom d1 = new DeluxeRoom(201, 300, 2, true, true,true, 75.0);
        DeluxeRoom d2 = new DeluxeRoom(202, 280, 2, true, false,true, 50.0);

        hotel.addRoom(s1);
        hotel.addRoom(s2);
        hotel.addRoom(d1);
        hotel.addRoom(d2);

        boolean running = true;

        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerCustomer(hotel, sc);
                    break;
                case 2:
                    hotel.displayAvailableRooms();
                    break;
                case 3:
                    makeReservation(hotel, sc);
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
                    System.out.println("Total hotel revenue: $" + hotel.totalRevenueRecursive(0));
                    break;
                case 8:
                    System.out.println(emp1 +"\n"+ emp2);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Thank you! Goodbye.");
        sc.close();
    }

    public static void printMenu() {
        System.out.println(" ");
        System.out.println("===== KSU Hotel =====");
        System.out.println("1. Register as customer");
        System.out.println("2. View available rooms");
        System.out.println("3. Make a reservation");
        System.out.println("4. Cancel a reservation");
        System.out.println("5. View my reservations");
        System.out.println("6. View my bill");
        System.out.println("7. View total hotel revenue");
        System.out.println("8. View hotel staff");
        System.out.println("9. Exit");
        System.out.print("Enter choice: ");
    }

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

    public static void cancelReservation(Hotel hotel, Scanner sc) {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        System.out.print("Enter reservation ID: ");
        String reservationId = sc.nextLine();

        if (!hotel.cancelReservation(customerId, reservationId)) {
            System.out.println("Cancellation failed.");
        }
    }

    public static void viewCustomerReservations(Hotel hotel, Scanner sc) {
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();

        Customer customer = hotel.searchCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            customer.displayReservations();
        }
    }

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
}
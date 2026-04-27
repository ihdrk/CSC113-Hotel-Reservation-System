import java.io.Serializable;
// This class represents a reservation made by a customer
// It implements Payable to allow bill calculation
public class Reservation implements Payable , Serializable {

    // Unique ID for the reservation
    private String reservationId;

    // ID of the customer who made the reservation
    private String customerId;

    // The room associated with this reservation
    private Room room;

    // Check-in day (simplified as an integer)
    private int checkInDay;

    // Check-out day
    private int checkOutDay;

    // Indicates whether the reservation is currently active
    private boolean isActive;

    // Constructor to initialize a Reservation object
    public Reservation(String reservationId,
                       String customerId,
                       Room room,
                       int checkInDay,
                       int checkOutDay) {

        this.reservationId = reservationId; // Assign reservation ID
        this.customerId = customerId;       // Assign customer ID
        this.room = room;                   // Assign room reference
        this.checkInDay = checkInDay;       // Assign check-in day
        this.checkOutDay = checkOutDay;     // Assign check-out day

        // Reservation starts as active by default
        this.isActive = true;
    }

    // Returns reservation ID
    public String getReservationId() {
        return reservationId;
    }

    // Returns customer ID
    public String getCustomerId() {
        return customerId;
    }

    // Returns the room associated with this reservation
    public Room getRoom() {
        return room;
    }

    // Returns check-in day
    public int getCheckInDay() {
        return checkInDay;
    }

    // Returns check-out day
    public int getCheckOutDay() {
        return checkOutDay;
    }

    // Returns whether the reservation is active
    public boolean isActive() {
        return isActive;
    }

    // Updates reservation status (active / inactive)
    public void setActive(boolean active) {
        isActive = active;
    }

    // Calculates number of nights stayed
    public int getNumNights() {
        return checkOutDay - checkInDay;
    }

    // Calculates the total bill for the reservation
    public double calculateBill() {

        double price;

        // Polymorphism handling:
        // If the room is Deluxe, use its effective price (includes surcharge)
        if (room instanceof DeluxeRoom) {
            price = ((DeluxeRoom) room).getEffectivePrice();
        } 
        // Otherwise, use the base room price
        else {
            price = room.getPricePerNight();
        }

        // Total cost = price per night * number of nights
        return price * getNumNights();
    }

    // Returns a string representation of the reservation
    public String toString() {

        return "Reservation: " + reservationId
                + " | Customer: " + customerId
                + " | Room: " + room.getRoomNumber()
                + " | Nights: " + getNumNights()
                + " | Bill: " + calculateBill();
    }
}

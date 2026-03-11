public class Reservation implements Payable {

    private String reservationId;
    private String customerId;
    private Room room;
    private int checkInDay;
    private int checkOutDay;
    private boolean isActive;

    public Reservation(String reservationId,
                       String customerId,
                       Room room,
                       int checkInDay,
                       int checkOutDay) {

        this.reservationId = reservationId;
        this.customerId = customerId;
        this.room = room;
        this.checkInDay = checkInDay;
        this.checkOutDay = checkOutDay;
        this.isActive = true;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Room getRoom() {
        return room;
    }

    public int getCheckInDay() {
        return checkInDay;
    }

    public int getCheckOutDay() {
        return checkOutDay;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getNumNights() {
        return checkOutDay - checkInDay;
    }

    public double calculateBill() {

        double price;

        if (room instanceof DeluxeRoom) {
            price = ((DeluxeRoom) room).getEffectivePrice();
        } else {
            price = room.getPricePerNight();
        }

        return price * getNumNights();
    }

    public String toString() {

        return "Reservation: " + reservationId
                + " | Customer: " + customerId
                + " | Room: " + room.getRoomNumber()
                + " | Nights: " + getNumNights()
                + " | Bill: " + calculateBill();
    }
}


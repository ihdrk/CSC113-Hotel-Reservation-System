public class Room {

    private int roomNumber;
    private double pricePerNight;
    private boolean isAvailable;
    private String roomType;

    public Room(int roomNumber, double pricePerNight, String roomType) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getRoomType() {
        return roomType;
    }

    public String toString() {
        return "Room #" + roomNumber +
                " | Type: " + getRoomType() +
                " | Price: " + pricePerNight +
                " | Available: " + isAvailable;
    }
}

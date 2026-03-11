public class Room {

    protected int roomNumber;
    protected double pricePerNight;
    protected boolean isAvailable;
    protected String roomType;

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
                " | Type: " + roomType +
                " | Price: " + pricePerNight +
                " | Available: " + isAvailable;
    }
}

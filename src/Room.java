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

    public Room(Room other) 
    {
        this.roomNumber = other.roomNumber;
        this.pricePerNight = other.pricePerNight;
        this.roomType = other.roomType;
        this.isAvailable = other.isAvailable;
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

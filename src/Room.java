// This class represents a room in a hotel system

import java.io.Serializable;

public class Room implements Serializable{

    // Unique identifier for the room
    private int roomNumber;

    // Cost per night for the room
    private double pricePerNight;

    // Indicates whether the room is available for booking
    private boolean isAvailable;

    // Type/category of the room (e.g., Single, Double, Suite)
    private String roomType;

    // Constructor to initialize a new Room object
    public Room(int roomNumber, double pricePerNight, String roomType) {
        this.roomNumber = roomNumber;       // Assign room number
        this.pricePerNight = pricePerNight; // Assign price
        this.roomType = roomType;           // Assign room type

        // By default, a new room is available
        this.isAvailable = true;
    }

    // Copy constructor
    // Creates a new Room object as a copy of another Room
    public Room(Room other) {
        this.roomNumber = other.roomNumber;       // Copy room number
        this.pricePerNight = other.pricePerNight; // Copy price
        this.roomType = other.roomType;           // Copy type
        this.isAvailable = other.isAvailable;     // Copy availability status
    }

    // Returns the room number
    public int getRoomNumber() {
        return roomNumber;
    }

    // Returns the price per night
    public double getPricePerNight() {
        return pricePerNight;
    }

    // Returns whether the room is available
    public boolean isAvailable() {
        return isAvailable;
    }

    // Updates the availability of the room
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Returns the type of the room
    public String getRoomType() {
        return roomType;
    }

    // Returns a string representation of the Room object
    public String toString() {
        return "Room #" + roomNumber +
                " | Type: " + getRoomType() +
                " | Price: " + pricePerNight +
                " | Available: " + isAvailable;
    }
}

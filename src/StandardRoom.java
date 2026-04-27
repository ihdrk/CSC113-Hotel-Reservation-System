// This class represents a standard type of room

import java.io.Serializable;

public class StandardRoom extends Room implements Serializable{

    // Number of beds available in the room
    private int numBeds;

    // Indicates whether the room has a TV
    private boolean hasTV;

    // Constructor to initialize a StandardRoom object
    public StandardRoom(int roomNumber, double price,
                        int numBeds, boolean hasTV) {

        // Call the parent (Room) constructor and set type as "Standard"
        super(roomNumber, price, "Standard");

        this.numBeds = numBeds; // Assign number of beds
        this.hasTV = hasTV;     // Assign TV availability
    }

    // Copy constructor
    // Creates a new StandardRoom object as a copy of another StandardRoom
    public StandardRoom(StandardRoom other) {
        super(other); // Copy base Room attributes

        this.numBeds = other.numBeds; // Copy number of beds
        this.hasTV = other.hasTV;     // Copy TV availability
    }

    // Returns the number of beds in the room
    public int getNumBeds() {
        return numBeds;
    }

    // Returns whether the room has a TV
    public boolean hasTV() {
        return hasTV;
    }

    // Returns a string representation of the StandardRoom object
    // Extends the parent class (Room) toString with additional details
    public String toString() {

        return super.toString()        // Include base room details
                + " | Beds: " + numBeds
                + " | TV: " + hasTV;
    }
}

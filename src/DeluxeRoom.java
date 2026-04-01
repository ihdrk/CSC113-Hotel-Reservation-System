// This class represents a deluxe type of room
// It extends StandardRoom and adds more luxury features
public class DeluxeRoom extends StandardRoom {

    // Indicates if the room includes a jacuzzi
    private boolean hasJacuzzi;

    // Indicates if the room has an ocean view
    private boolean hasOceanView;

    // Additional cost for luxury features
    private double luxurySurcharge;

    // Constructor to initialize a DeluxeRoom object
    public DeluxeRoom(int roomNumber, double price,
                      int beds, boolean tv,
                      boolean jacuzzi, boolean ocean,
                      double surcharge) {

        // Call parent constructor (StandardRoom)
        super(roomNumber, price, beds, tv);

        this.hasJacuzzi = jacuzzi;         // Assign jacuzzi feature
        this.hasOceanView = ocean;         // Assign ocean view feature
        this.luxurySurcharge = surcharge; // Assign extra cost
    }

    // Copy constructor
    // Creates a new DeluxeRoom object as a copy of another DeluxeRoom
    public DeluxeRoom(DeluxeRoom other) {
        super(other); // Copy StandardRoom + Room attributes

        this.hasJacuzzi = other.hasJacuzzi;         // Copy jacuzzi status
        this.hasOceanView = other.hasOceanView;     // Copy ocean view status
        this.luxurySurcharge = other.luxurySurcharge; // Copy surcharge
    }

    // Returns whether the room has a jacuzzi
    public boolean hasJacuzzi() {
        return hasJacuzzi;
    }

    // Returns whether the room has an ocean view
    public boolean hasOceanView() {
        return hasOceanView;
    }

    // Returns the additional luxury surcharge
    public double getLuxurySurcharge() {
        return luxurySurcharge;
    }

    // Returns the total effective price (base price + surcharge)
    public double getEffectivePrice() {
        return getPricePerNight() + luxurySurcharge;
    }

    // Overrides the room type to always return "Deluxe"
    public String getRoomType() {
        return "Deluxe";
    }

    // Returns a string representation of the DeluxeRoom object
    // Extends the parent toString with luxury details
    public String toString() {

        return super.toString()             // Include base + standard details
                + " | Jacuzzi: " + hasJacuzzi
                + " | Ocean View: " + hasOceanView
                + " | Extra: " + luxurySurcharge;
    }
}

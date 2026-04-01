public class StandardRoom extends Room {

    private int numBeds;
    private boolean hasTV;

    public StandardRoom(int roomNumber, double price,
                        int numBeds, boolean hasTV) {

        super(roomNumber, price, "Standard");
        this.numBeds = numBeds;
        this.hasTV = hasTV;
    }

    public StandardRoom(StandardRoom other) // copy constructor used in Hotel
    {
        super(other);
        this.numBeds = other.numBeds;
        this.hasTV = other.hasTV;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public boolean hasTV() {
        return hasTV;
    }

    public String toString() {

        return super.toString()
                + " | Beds: " + numBeds
                + " | TV: " + hasTV;
    }
}

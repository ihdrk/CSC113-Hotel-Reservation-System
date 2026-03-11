public class DeluxeRoom extends StandardRoom {

    private boolean hasJacuzzi;
    private boolean hasOceanView;
    private double luxurySurcharge;

    public DeluxeRoom(int roomNumber, double price,
                      int beds, boolean tv,
                      boolean jacuzzi, boolean ocean,
                      double surcharge) {

        super(roomNumber, price, beds, tv);
        this.hasJacuzzi = jacuzzi;
        this.hasOceanView = ocean;
        this.luxurySurcharge = surcharge;
    }

    public boolean hasJacuzzi() {
        return hasJacuzzi;
    }

    public boolean hasOceanView() {
        return hasOceanView;
    }

    public double getLuxurySurcharge() {
        return luxurySurcharge;
    }

    public double getEffectivePrice() {
        return getPricePerNight() + luxurySurcharge;
    }

    public String toString() {

        return super.toString()
                + " | Jacuzzi: " + hasJacuzzi
                + " | Ocean View: " + hasOceanView
                + " | Extra: " + luxurySurcharge;
    }
}

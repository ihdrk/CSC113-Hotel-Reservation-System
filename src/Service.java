import java.io.Serializable;
// This class represents an extra hotel service added for a customer
// Examples: Breakfast, Lunch, Dinner, Laundry, Spa, Airport Pickup
// It implements Payable so each service can calculate its own cost
public class Service implements Payable , Serializable
{
    // name of the selected hotel service
    private String serviceName;

    // fixed price of the selected service
    private double price;

    // constructor with parameters initializing service name and price
    public Service(String serviceName, double price)
    {
        this.serviceName = serviceName;
        this.price = price;
    }

    // returns service name
    public String getServiceName()
    {
        return serviceName;
    }

    // returns service price
    public double getPrice()
    {
        return price;
    }

    // calculates the bill of the service
    public double calculateBill()
    {
        return price;
    }

    // returns service details in string format
    public String toString()
    {
        return "Service: " + serviceName + " | Price: " + price;
    }
}
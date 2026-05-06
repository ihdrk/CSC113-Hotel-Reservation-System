import java.io.Serializable;
public interface Payable extends Serializable// Interface defining billing behavior, implemented by Service and Reservation
{
    double calculateBill();
}

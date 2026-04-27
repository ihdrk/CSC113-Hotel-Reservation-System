public class RoomUnavailableException extends HotelException
{
    private int roomNumber;

    public RoomUnavailableException(int roomNumber)
    {
        super("Room: "+roomNumber+" is currently unavailabel.");
        this.roomNumber = roomNumber;
    }

}

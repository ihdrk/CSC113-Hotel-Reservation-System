public class RoomUnavailableException extends HotelException
{
    private int roomNumber;

    public RoomUnavailableException(int roomNumber)
    {// Create a message for the parent class
        super("Room: "+roomNumber+" is currently unavailabel.");
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber()//returns The room number associated with this error.
    {
        return roomNumber;
    }

}

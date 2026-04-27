import java.io.*;
public class HotelFileManager 
{
    private static final String FILE_PATH = "hotel_data.ser";

    public static void saveHotel(Hotel hotel)throws IOException
    {   
        ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(FILE_PATH));
        oos.writeObject(hotel);
        oos.close();
    }

    public static Hotel loadHotel()
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            Hotel hotel = (Hotel) ois.readObject();
            ois.close();
            return hotel;
        }catch(FileNotFoundException e)
        { // First run — no save file yet, return fresh hotel with default rooms
            return createDefaultHotel();
        }catch(IOException | ClassNotFoundException e)
        {// File corrupted or unreadable — start fresh
            return createDefaultHotel();
        }
    }

    private static Hotel createDefaultHotel()
    {
        Hotel hotel = new Hotel("KSU Hotel");
        hotel.addRoom(new StandardRoom(101,150,2,true));
        hotel.addRoom(new StandardRoom(102,120,1,false));
        hotel.addRoom(new DeluxeRoom(201,300,2,true,true,true,75.0));
        hotel.addRoom(new DeluxeRoom(202,280,2,true,false,true,50.0));
        return hotel;
    }
}

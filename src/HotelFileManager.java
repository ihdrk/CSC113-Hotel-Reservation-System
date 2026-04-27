import java.io.*;
public class HotelFileManager 
{
    private static final String FILE_PATH = "hotel_data.ser";// The filename where the serialized hotel data will be stored

    public static void saveHotel(Hotel hotel)throws IOException//Serializes the Hotel object and writes it to a file
    {   //throws Exception If there is an error writing to the file
        ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(FILE_PATH));// Create an output stream that writes to a file with ObjectOutputStream
        oos.writeObject(hotel);// Write the entire hotel object
        oos.close();
    }

    public static Hotel loadHotel()//Attempts to load the Hotel object from the save file.
    {//If no file exists or an error occurs, a default hotel is generated.
        try
        {// Open the file for reading
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            Hotel hotel = (Hotel) ois.readObject();// Read the object and cast it back to a Hotel
            ois.close();
            return hotel;
        }catch(FileNotFoundException e)
        { // Case: Application is running for the first time
            return createDefaultHotel();
        }catch(IOException | ClassNotFoundException e)
        {// Case: File is corrupted, incompatible, or class definition changed
            return createDefaultHotel();
        }
    }

    private static Hotel createDefaultHotel()//initialize a new Hotel with pre-configured rooms.
    {
        Hotel hotel = new Hotel("KSU Hotel");
        // Adding initial room inventory
        hotel.addRoom(new StandardRoom(101,150,2,true));
        hotel.addRoom(new StandardRoom(102,120,1,false));
        hotel.addRoom(new DeluxeRoom(201,300,2,true,true,true,75.0));
        hotel.addRoom(new DeluxeRoom(202,280,2,true,false,true,50.0));
        return hotel;
    }
}

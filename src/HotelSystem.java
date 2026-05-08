import javax.swing.SwingUtilities;

public class HotelSystem {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Hotel hotel = HotelFileManager.loadHotel();
            new LoginFrame(hotel).setVisible(true);
        });
    }

}

package Hotel;

public class Hotel {
    private String hotelID;
    private String hotelName;
    private String location;
    private double price;
    private int[] rooms = new int[30];

    public Hotel() {
        hotelName = "";
        location = "";
        hotelID = "";
        price = 0;
        for (int i = 0; i < 30; i++) {
            rooms[i] = 0;
        }
    }

    public Hotel(String line) {
        String[] fields = line.split(",");
        hotelID = fields[0];
        hotelName = fields[1];
        location = fields[2];
        price = Double.parseDouble(fields[3]);
        for (int i = 0; i < 30; i++) {
            rooms[i] = Integer.parseInt(fields[i + 4]);
        }
    }

    public String toString() {
        String line = hotelID + "," + hotelName + "," + location + "," + price;
        for (int i = 0; i < 30; i++) {
            line += "," + rooms[i];
        }
        return line;
    }

    public boolean BookRoom(int[] days) {
        for (int i = 0; i < days.length; i++) {
            if (days[i] > 30 || days[i] < 0) {
                return false;
            } else {
                if (rooms[days[i] - 1] > 0) {
                    rooms[days[i] - 1]--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean CancelRoom(int[] days) {
        for (int i = 0; i < days.length; i++) {
            if (days[i] > 30 || days[i] < 0) {
                return false;
            } else {
                rooms[days[i] - 1]++;
            }
        }
        return true;
    }

    public static void displayHotels(Hotel[] hotels) {
        System.out.println("Hotels available");
        // System.out.println("Hotel ID\tHotel Name\tLocation\tPrice");
        // do usign printf
        System.out.printf("%15s %15s %15s %15s\n", "Hotel ID", "Hotel Name", "Location", "Price");
        for (int i = 0; i < hotels.length; i++) {
            System.out.printf("%15s %15s %15s %15.2f\n", hotels[i].hotelID, hotels[i].hotelName, hotels[i].location,hotels[i].price);
        }
    }

    public void displayRooms() {
        System.out.println("Rooms available for hotel " + hotelID);
        for (int i = 0; i < 30; i++) {
            System.out.printf("Day %3d :- %d\n", i + 1, rooms[i]);
            System.out.println();
        }
    }

    public String getHotelNumber() {
        return hotelID;
    }
    public void displayHotel() {
        System.out.println("Hotel ID: " + hotelID);
        System.out.println("Hotel Name: " + hotelName);
        System.out.println("Location: " + location);
        System.out.println("Price: " + price);
    }

}

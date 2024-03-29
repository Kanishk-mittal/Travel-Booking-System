package Hotel;

public class Hotel {

    private String hotelName;
    private String location;
    private String hotelID;
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
        hotelName = fields[0];
        location = fields[1];
        hotelID = fields[2];
        price = Double.parseDouble(fields[3]);
        for (int i = 0; i < 30; i++) {
            rooms[i] = Integer.parseInt(fields[i + 4]);
        }
    }

    public String toString() {
        String line = hotelName + "," + location + "," + hotelID + "," + price;
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
        System.out.printf("%15s || %15s || %15s || %s\n", "Hotel Name", "Location", "Hotel ID", "Price");
        for (int i = 0; i < 65; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (Hotel hotel : hotels) {
            System.out.printf("%15s || %15s || %15s || %.2f\n", hotel.hotelName, hotel.location, hotel.hotelID,
                    hotel.price);
        }
    }

    public void displayRooms() {
        System.out.println("Rooms available for hotel " + hotelID);
        System.out.println("Day\tRooms");
        for (int i = 0; i < 30; i++) {
            System.out.printf("%3d\t%d\n", i + 1, rooms[i]);
            System.out.println();
        }
    }

    public String getHotelNumber() {
        return hotelID;
    }

}

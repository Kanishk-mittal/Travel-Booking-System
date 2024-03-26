package Hotel;

public class Hotel {
    // private data memers for the Hotel class to store the hotel name, location,
    // hotel id , price and an array of 30 intigers showing how many rooms
    // are avilabe on each day of month
    private String hotelName;
    private String location;
    private String hotelID;
    private double price;
    private int[] rooms = new int[30];

    // default constructor
    public Hotel() {
        hotelName = "";
        location = "";
        hotelID = "";
        price = 0;
        for (int i = 0; i < 30; i++) {
            rooms[i] = 0;
        }
    }
    // constructor that takes a single string as input and initializes the data
    // members
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

    // to strign function to get all the information about the hotelseperated by
    // comma
    public String toString() {
        String line = hotelName + "," + location + "," + hotelID + "," + price;
        for (int i = 0; i < 30; i++) {
            line += "," + rooms[i];
        }
        return line;
    }

    // function to book the room which take an array of intigers as input and book for multiple days
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
    // function to cancel the room which take an array of intigers as input and cancel for multiple days
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
}

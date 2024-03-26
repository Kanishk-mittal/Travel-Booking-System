package Flight;

public class Flight {
    // private data members for the Flight class to store the flight number, origin,
    // and destination , price and an array of 30 intigers showing how many seats
    // are avilabe on each day of month
    private String flightNumber;
    private String origin;
    private String destination;
    private double price;
    private int[] seats = new int[30];

    // default constructor to initialize the data members
    public Flight() {
        flightNumber = "";
        origin = "";
        destination = "";
        price = 0;
        for (int i = 0; i < 30; i++) {
            seats[i] = 0;
        }
    }

    // constructor that takes a single string as input and initializes the data
    // members
    public Flight(String line) {
        String[] fields = line.split(",");
        flightNumber = fields[0];
        origin = fields[1];
        destination = fields[2];
        price = Double.parseDouble(fields[3]);
        for (int i = 0; i < 30; i++) {
            seats[i] = Integer.parseInt(fields[i + 4]);
        }
    }

    // to strign fuction that will give all the information about the flight
    public String toString() {
        String line = flightNumber + "," + origin + "," + destination + "," + price;
        for (int i = 0; i < 30; i++) {
            line += "," + seats[i];
        }
        return line;
    }

    // funciton to book the seat
    public boolean BookSeat(int day) {
        if (day > 30 || day < 0) {
            return false;
        } else {
            if (seats[day - 1] > 0) {
                seats[day - 1]--;
                return true;
            } else {
                return false;
            }
        }
    }

    // function to cancel the seat
    public boolean CancelSeat(int day) {
        if (day > 30 || day < 0) {
            return false;
        } else {
            seats[day - 1]++;
            return true;
        }
    }
    // create 20 Dummy flights use random library to have random number of seats avilabe between 50 and 70
    

}

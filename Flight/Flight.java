package Flight;
import CustomExceptions.FlightNotFoud;

public class Flight {

    private String flightNumber;
    public String origin;
    private String destination;
    private double price;
    private int[] seats = new int[30];

    public Flight() {
        flightNumber = "";
        origin = "";
        destination = "";
        price = 0;
        for (int i = 0; i < 30; i++) {
            seats[i] = 0;
        }
    }

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

    public String toString() {
        String line = flightNumber + "," + origin + "," + destination + "," + price;
        for (int i = 0; i < 30; i++) {
            line += "," + seats[i];
        }
        return line;
    }

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

    public boolean CancelSeat(int day) {
        if (day > 30 || day < 0) {
            return false;
        } else {
            seats[day - 1]++;
            return true;
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getPrice() {
        return price;
    }

    public int[] getSeats() {
        return seats;
    }

    public static void displayFlights(Flight[] flights) {
        System.out.printf("%15s || %15s || %15s || %s\n", "Flight Number", "Origin", "Destination", "Price");

        for (int i = 0; i < 65; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (Flight flight : flights) {
            System.out.printf("%15s || %15s || %15s || %.2f\n", flight.getFlightNumber(), flight.getOrigin(),
                    flight.getDestination(), flight.getPrice());
        }
    }

    public void displayFlight() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);
        System.out.println("Price: " + price);
    }

    public static Flight[] bookFlight(Flight[] flights, String flightNumber, int date) throws FlightNotFoud {
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getFlightNumber().equals(flightNumber)) {
                if (flights[i].BookSeat(date)) {
                    return flights;
                } else {
                    throw new FlightNotFoud();
                }
            }
        }
        ;
        throw new FlightNotFoud();
    }

    public void displaySeats() {
        System.out.println("Seats available for flight " + flightNumber);
        for (int i = 0; i < 30; i++) {
            System.out.println("Day " + (i + 1) + ": " + seats[i]);
        }
    }

}

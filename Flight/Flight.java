package Flight;
import java.util.Scanner;

import CustomExceptions.FlightNotFoud;

public class Flight {
    // private data members for the Flight class to store the flight number, origin,
    // and destination , price and an array of 30 intigers showing how many seats
    // are avilabe on each day of month
    private String flightNumber;
    public String origin;
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
    // getter methods for all private data members
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
        //print 65 _
        for (int i = 0; i < 65; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (Flight flight : flights) {
            System.out.printf("%15s || %15s || %15s || %.2f\n", flight.getFlightNumber(),flight.getOrigin(), flight.getDestination(), flight.getPrice());
        }
    }
    // static funciton to book flight
    public static Flight[] bookFlight(Flight[] flights, String flightNumber, int date) throws FlightNotFoud{
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getFlightNumber().equals(flightNumber)) {
                if (flights[i].BookSeat(date)) {
                    return flights;
                } else {
                    throw new FlightNotFoud();
                }
            }
        };
        throw new FlightNotFoud();
    }
    public void displaySeats() {
        System.out.println("Seats available for flight " + flightNumber);
        for (int i = 0; i < 30; i++) {
            System.out.println("Day " + (i + 1) + ": " + seats[i]);
        }
    }
    public static Flight[] bookFlight(Flight[] flights, Scanner scanner) {
        Flight.displayFlights(flights);
        System.out.print("Enter the flight number you want to book: ");
        String flightNumber = scanner.next();
        boolean validFlightNumber = false;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                validFlightNumber = true;
                break;
            }
        }
        while (!validFlightNumber) {
            System.out.println("Invalid flight number. Please enter a valid flight number.");
            flightNumber = scanner.next();
            for (Flight flight : flights) {
                if (flight.getFlightNumber().equals(flightNumber)) {
                    validFlightNumber = true;
                    break;
                }
            }
        }
        // display the seats available of entered flight
        Flight flight = null;
        for (Flight f : flights) {
            if (f.getFlightNumber().equals(flightNumber)) {
                flight = f;
                break;
            }
        }
        flight.displaySeats();
        System.out.print("Enter the date you want to book: ");
        // validate the date
        int date = scanner.nextInt();
        boolean validDate = date >= 1 && date <= 30;
        while (!validDate) {
            System.out.println("Invalid date. Please enter a date between 1 and 30.");
            date = scanner.nextInt();
            validDate = date >= 1 && date <= 30;
        }
        // ask confirmation from user
        System.out.println("Are you sure you want to book flight no " + flightNumber + " on " + date + "? (yes/no)");
        String confirmation = scanner.next();
        boolean flightBooked = false;
        if (confirmation.equals("yes")) {
            try {
                Flight.bookFlight(flights, flightNumber, date);
                flightBooked = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                flightBooked = false;
            }
        }
        if (flightBooked) {
            System.out.println("Flight booked successfully");
        } else {
            System.out.println("Flight booking failed.Please Try again");
        }
        return flights;
    }
}

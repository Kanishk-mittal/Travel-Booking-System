import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import DataHandler.*;
import Flight.Flight;
import Hotel.Hotel;
import User.User;

public class Main {
    static User currentUser;
    static Flight[] flights;
    static Hotel[] hotels;
    static User[] users;

    public static void main(String[] args) {
        ReadData readData = new ReadData();
        WriteData writer = new WriteData();
        flights = readData.getFlights();
        hotels = readData.getHotels();
        users = readData.getUsers();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./welcome.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e);
        }
        final String WELCOME_MESSAGE = "=========Welcome to JK travels one Stop for all your travel needs=========";
        System.out.println(WELCOME_MESSAGE);
        if (users.length == 0) {
            System.out.println("Please create an account to continue");
            users = User.createNewUser(users, writer);
            currentUser = users[0];
        } else {

            System.out.println("1. Login");
            System.out.println("2. Create new account");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 2) {
                users = User.createNewUser(users, writer);
                currentUser = users[users.length - 1];
            } else if (choice == 1) {

                boolean loginSuccess = false;
                while (!loginSuccess) {
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    for (User user : users) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            loginSuccess = true;
                            currentUser = user;
                            break;
                        }
                    }
                    if (!loginSuccess) {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                }
            }
        }
        while (true) {

            System.out.println("1. Book a flight");
            System.out.println("2.Cancel a flight");
            System.out.println("3. Book a hotel");
            System.out.println("4. Cancel a hotel");
            System.out.println("5. See your bookings");
            System.out.println("6. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid choice. Please enter a number.");
                scanner.next();
                choice = -1;
            }
            if (choice == 1) {
                bookFlight(scanner);
            }
            if (choice == 2) {
                cancelFlight(scanner);
            }
            if (choice == 3) {
                bookHotel(scanner);
            } else if (choice == 4) {
                currentUser.cancelHotel();
            } else if (choice == 5) {
                currentUser.displayBookings();
            } else if (choice == 6) {
                break;
            } else if (choice == -1) {
                continue;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        writer.WriteHotels(hotels);
        writer.WriteFlights(flights);
        writer.WriteUsers(users);
    }

    private static void bookFlight(Scanner scanner) {
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

        Flight flight = null;
        for (Flight f : flights) {
            if (f.getFlightNumber().equals(flightNumber)) {
                flight = f;
                break;
            }
        }
        flight.displaySeats();
        System.out.print("Enter the date you want to book: ");

        int date = scanner.nextInt();
        boolean validDate = date >= 1 && date <= 30;
        while (!validDate) {
            System.out.println("Invalid date. Please enter a date between 1 and 30.");
            date = scanner.nextInt();
            validDate = date >= 1 && date <= 30;
        }

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
            users = User.bookFlight(users, currentUser.getUsername(), flightNumber, date);
        } else {
            System.out.println("Flight booking failed.Please Try again");
        }
    }

    private static void bookHotel(Scanner scanner) {
        Hotel.displayHotels(hotels);
        System.out.print("Enter the hotel number you want to book: ");
        String hotelNumber = scanner.next();
        boolean validHotelNumber = false;
        for (Hotel hotel : hotels) {
            if (hotel.getHotelNumber().equals(hotelNumber)) {
                validHotelNumber = true;
                break;
            }
        }
        while (!validHotelNumber) {
            System.out.println("Invalid hotel number. Please enter a valid hotel number.");
            hotelNumber = scanner.next();
            for (Hotel hotel : hotels) {
                if (hotel.getHotelNumber().equals(hotelNumber)) {
                    validHotelNumber = true;
                    break;
                }
            }
        }

        Hotel hotel = null;
        for (Hotel h : hotels) {
            if (h.getHotelNumber().equals(hotelNumber)) {
                hotel = h;
                break;
            }
        }
        hotel.displayRooms();
        System.out.print("Enter the date and no of days you want to book: ");

        int date = scanner.nextInt();
        int days = scanner.nextInt();
        boolean validDate = date >= 1 && date <= 30;
        while (!validDate) {
            System.out.println("Invalid date. Please enter a date between 1 and 30.");
            date = scanner.nextInt();
            validDate = date >= 1 && date <= 30;
        }
        int[] dates = new int[days];
        for (int i = 0; i < days; i++) {
            dates[i] = date + i;
        }
        System.out.println("Are you sure you want to book hotel no " + hotelNumber + " on " + date + " for " + days
                + " days ? (yes/no)");
        String confirmation = scanner.next();
        boolean hotelBooked = false;
        if (confirmation.equals("yes")) {
            try {
                hotelBooked = hotel.BookRoom(dates);
                ;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                hotelBooked = false;
            }
        }
        if (hotelBooked) {
            System.out.println("Hotel booked successfully");
            currentUser.bookHotel(hotelNumber, dates);
        } else {
            System.out.println("Hotel booking failed.Please Try again");
        }
    }

    private static void cancelFlight(Scanner scanner) {
        System.out.println("Do you really want to cancel your following flight?(yes/no)");
        currentUser.displayBookedFlight();
        String confirmation = scanner.next();
        if (confirmation.equals("yes")) {
            currentUser.cancelFlight(flights);
            System.out.println("Flight cancelled successfully");
        } else {
            System.out.println("Flight not cancelled");
        }
    }
}

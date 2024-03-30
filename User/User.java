package User;

import java.util.Arrays;
import java.util.Scanner;
import DataHandler.ReadData;
import Flight.Flight;
import Hotel.Hotel;
import DataHandler.WriteData;

public class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private String phoneNumber;
    private String bookedFlight = "-1";
    private String bookedFlightDate = "-1";
    private String bookedHotel = "-1";
    public String[] bookedHotelDate = new String[0];

    public User() {
        username = "";
        password = "";
        email = "";
        name = "";
        address = "";
        phoneNumber = "";
    }

    public User(String username, String password, String email, String name, String address, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User(String line) {
        String[] fields = line.split("\\|");
        username = fields[0];
        password = fields[1];
        email = fields[2];
        name = fields[3];
        address = fields[4];
        phoneNumber = fields[5];

        bookedFlight = fields[6];
        bookedFlightDate = fields[7];
        bookedHotel = fields[8];

        String[] bookedHotelDateArray = fields[9].substring(1, fields[9].length() - 1).split(",");
        if (bookedHotelDateArray.length == 1) {
            bookedHotelDateArray = new String[0];
        }
        bookedHotelDate = new String[bookedHotelDateArray.length];
        System.arraycopy(bookedHotelDateArray, 0, bookedHotelDate, 0, bookedHotelDateArray.length);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        String returnString = username + "|" + password + "|" + email + "|" + name + "|" + address + "|" + phoneNumber
                + "|" + bookedFlight + "|" + bookedFlightDate + "|" + bookedHotel + "|[";
        for (int i = 0; i < bookedHotelDate.length; i++) {
            returnString += bookedHotelDate[i] + ",";
        }
        returnString += "]";
        return returnString;
    }

    public static User[] createNewUser(User[] users, WriteData writer) {
        User newUser;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        newUser = new User(username, password, email, name, address, phoneNumber);

        User[] updatedUsers = Arrays.copyOf(users, users.length + 1);
        updatedUsers[users.length] = newUser;
        users = updatedUsers;
        writer.WriteUser(newUser);
        return users;
    }

    public static User[] bookFlight(User[] users, String username, String flightNumber, int date ,Flight[] flights) {
        String dateStr = String.valueOf(date);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                    user.bookedFlight = flightNumber;
                    user.bookedFlightDate = dateStr;
                    return users;
            }
        }
        return users;
    }

    public void displayBookedFlight(Flight[] flights) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(bookedFlight)) {
                flight.displayFlight();
            }
        }
    }

    public void cancelFlight(Flight[] flights) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(bookedFlight)) {
                int bookedFlightInt = Integer.parseInt(bookedFlight);
                flight.CancelSeat(bookedFlightInt);
                bookedFlight = "-1";
                bookedFlightDate = "-1";
            }
        }
        bookedFlight = "-1";
        bookedFlightDate = "-1";
    }

    public void cancelHotel() {
        for (int i = 0; i < bookedHotelDate.length; i++) {
            int bookedHotelDateInt = Integer.parseInt(bookedHotelDate[i]);
            ReadData reader = new ReadData();
            Hotel[] hotels = reader.getHotels();
            for (Hotel hotel : hotels) {
                if (hotel.getHotelNumber().equals(bookedHotel)) {
                    hotel.CancelRoom(new int[] { bookedHotelDateInt });
                    bookedHotel = "-1";
                    bookedHotelDate = new String[0];
                }
            }
        }
    }

    public void bookHotel(String hotelNumber, int[] dates) {
        this.bookedHotel = hotelNumber;
        this.bookedHotelDate = new String[dates.length];
        for (int i = 0; i < dates.length; i++) {
            this.bookedHotelDate[i] = String.valueOf(dates[i]);
        }
    }

    public void displayBookings(Flight[] flights,Hotel[] hotels) {
        if (bookedFlight != "-1") {
            System.out.println("===BOOKED FLIGHT===");
            displayBookedFlight(flights);
        }
        if (bookedHotel != "-1") {
            System.out.println("===BOOKED HOTEL===");
            displayBookedHotel(hotels);
        }
    }

    public void displayBookedHotel(Hotel[] hotels) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelNumber().equals(bookedHotel)) {
                hotel.displayHotel();
                System.out.print("Booked for the following dates: ");
                for (int i = 0; i < bookedHotelDate.length; i++) {
                    System.out.print(bookedHotelDate[i]+" ,");
                }
                System.out.println();
            }
        }
    }
}

package User;

import java.util.Arrays;
import java.util.Scanner;

import DataHandler.WriteData;

public class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private String phoneNumber;
    private String bookedFlight="-1";
    private String bookedFlightDate="-1";
    private String bookedHotel="-1";
    public String[] bookedHotelDate=new String[0];


    // default constructor to initialize the data members
    public User() {
        username = "";
        password = "";
        email = "";
        name = "";
        address = "";
        phoneNumber = "";
    }
    // parameterised constructor to initialize the data members
    public User(String username, String password, String email, String name, String address, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public User(String line) {
        String[] fields = line.split("\\|"); // Escape the '|' character
        username = fields[0];
        password = fields[1];
        email = fields[2];
        name = fields[3];
        address = fields[4];
        phoneNumber = fields[5];
        // Assuming you have additional fields like bookedFlight, bookedFlightDate, bookedHotel, bookedHotelDate
        bookedFlight = fields[6];
        bookedFlightDate = fields[7];
        bookedHotel = fields[8];
        // Extract bookedHotelDate as an array
        String[] bookedHotelDateArray = fields[9].substring(1, fields[9].length() - 1).split(","); // Remove '[' and ']' and split by ','
        if (bookedHotelDateArray.length==1)  {
            bookedHotelDateArray=new String[0];
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
        String returnString = username + "|" + password + "|" + email + "|" + name + "|" + address + "|" + phoneNumber+"|"+bookedFlight+"|"+bookedFlightDate+"|"+bookedHotel+"|[";
        for (int i = 0; i < bookedHotelDate.length; i++) {
            returnString += bookedHotelDate[i]+",";
        }
        returnString+="]";
        return returnString;
    }
    public static User[] createNewUser(User[] users, WriteData writer) {
        User newUser;
        // code to take inputs from user and make a new user object
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

        // add the user to the users array
        User[] updatedUsers = Arrays.copyOf(users, users.length + 1);
        updatedUsers[users.length] = newUser;
        users = updatedUsers;
        writer.WriteUser(newUser);
        return users;
    }
    public static User[] bookFlight(User[] users, String username, String flightNumber, String date) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.bookedFlight != "-1") {
                    user.bookedFlight = flightNumber;
                    user.bookedFlightDate = date;
                    return users;
                }
                else{
                    System.out.println("You have already booked a flight");
                    return users;
                }
            }
        }
        return users;
    }
}

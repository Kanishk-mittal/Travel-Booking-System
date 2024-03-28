import java.io.BufferedReader;
import DataHandler.WriteData;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import DataHandler.ReadData;
import Flight.Flight;
import Hotel.Hotel;
import User.User;

public class Main {
    static String currentUsername;

    public static void main(String[] args) {
        ReadData readData = new ReadData();
        WriteData writer = new WriteData();
        Flight[] flights = readData.getFlights();
        Hotel[] hotels = readData.getHotels();
        User[] users = readData.getUsers();
        // read all content of welcome.txt and print it
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
            currentUsername = users[0].getUsername();
        } else {
            // ask user two choise 1. login 2. create new account
            System.out.println("1. Login");
            System.out.println("2. Create new account");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 2) {
                users = User.createNewUser(users, writer);
                currentUsername = users[users.length - 1].getUsername();
            } else if (choice == 1) {
                // code to login and on invalid login ask user to try again
                boolean loginSuccess = false;
                while (!loginSuccess) {
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    for (User user : users) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            loginSuccess = true;
                            currentUsername = username;
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
            // code to ask user wether they want to book a flight,hotel,see their bookings
            // or exit
            System.out.println("1. Book a flight");
            System.out.println("2. Book a hotel");
            System.out.println("3. See your bookings");
            System.out.println("4. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            if (choice == 4) {
                break;
            }
            if (choice == 1) {
                flights=Flight.bookFlight(flights, scanner);
            }
        }
    }
}

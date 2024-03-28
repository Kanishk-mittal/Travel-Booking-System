package DataHandler;

import Flight.Flight;
import Hotel.Hotel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import User.User;

public class ReadData {

    private Flight[] flights;
    private Hotel[] hotels;
    private User[] users;

    public ReadData() {
        flights = ReadFlights();
        hotels = ReadHotels();
        users=ReadUsers();
    }

    private Flight[] ReadFlights() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./Data/ProgramData/Flights.csv"))) {

            int size = countLines("./Data/ProgramData/Flights.csv");
            Flight[] flights = new Flight[size];
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                flights[i] = new Flight(line);
            }
            return flights;
        } catch (FileNotFoundException e) {
            WriteData writeData = new WriteData();
            return writeData.addDummyFlights();
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e);
        }
        return new Flight[0];
    }

    private Hotel[] ReadHotels() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./Data/ProgramData/Hotels.csv"))) {
            int size = countLines("./Data/ProgramData/Hotels.csv");
            Hotel[] hotels = new Hotel[size];
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                hotels[i] = new Hotel(line);
            }
            return hotels;
        } catch (FileNotFoundException e) {
            WriteData writeData = new WriteData();
            return writeData.addDummyHotels();
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e);
        }
        return new Hotel[0];
    }
    // function to read the users from the file
    public User[] ReadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./Data/UserData/Users.csv"))) {
            int size = countLines("./Data/UserData/Users.csv");
            User[] users = new User[size];
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                users[i] = new User(line);
            }
            return users;
        } catch (FileNotFoundException e) {
            WriteData writeData = new WriteData();
            writeData.createUserFile();
            return new User[0];
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e);
        }
        return new User[0];
    }

    private int countLines(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            return lines;
        }
    }

    public Flight[] getFlights() {
        return flights;
    }

    public Hotel[] getHotels() {
        return hotels;
    }
    // getter method for users
    public User[] getUsers() {
        return users;
    }
}

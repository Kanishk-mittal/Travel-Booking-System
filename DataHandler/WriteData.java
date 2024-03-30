package DataHandler;

import java.io.FileWriter;
import java.io.IOException;
import Flight.Flight;
import Hotel.Hotel;
import User.User;

public class WriteData {
    public Flight[] addDummyFlights() {

        String csvFile = "./Data/ProgramData/Flights.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            Flight[] tempFlight = CreateDummyFlights();
            for (int i = 0; i < tempFlight.length; i++) {
                writer.append(tempFlight[i].toString() + "\n");
            }
            writer.flush();
            return tempFlight;
        } catch (IOException e2) {
            System.err.println("Error writing CSV file: " + e2.getMessage());
            return new Flight[0];
        }
    }

    public Hotel[] addDummyHotels() {

        String csvFile = "./Data/ProgramData/Hotels.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            Hotel[] tempHotel = CreateDummyHotels();
            for (int i = 0; i < tempHotel.length; i++) {
                writer.append(tempHotel[i].toString() + "\n");
            }
            writer.flush();
            return tempHotel;
        } catch (IOException e2) {
            System.err.println("Error writing CSV file: " + e2.getMessage());
            return new Hotel[0];
        }
    }

    public void createUserFile() {

        String csvFile = "./Data/UserData/Users.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {

        } catch (IOException e2) {
            System.err.println("Error creating CSV file: " + e2.getMessage());
        }
    }

    private Hotel[] CreateDummyHotels() {
        Hotel[] hotels = new Hotel[20];
        hotels[0] = new Hotel("1,Taj Hotel,Washington,100" + RandomSeats());
        hotels[1] = new Hotel("2,Marriot,London,200" + RandomSeats());
        hotels[2] = new Hotel("3,Hyatt,Paris,300" + RandomSeats());
        hotels[3] = new Hotel("4,Grand Hotel,Beijing,100" + RandomSeats());
        hotels[4] = new Hotel("5,The Ritz,Tokyo,150" + RandomSeats());
        hotels[5] = new Hotel("6,The Peninsula,Los Angeles,50" + RandomSeats());
        hotels[6] = new Hotel("7,The Four Seasons,Chicago,100" + RandomSeats());
        hotels[7] = new Hotel("8,The Plaza,New York,50" + RandomSeats());
        hotels[8] = new Hotel("9,The Oberoi,Delhi,200" + RandomSeats());
        hotels[9] = new Hotel("10,The Ritz,Moscow,150" + RandomSeats());
        hotels[10] = new Hotel("11,The Peninsula,Shanghai,100" + RandomSeats());
        hotels[11] = new Hotel("12,The Four Seasons,Seoul,200" + RandomSeats());
        hotels[12] = new Hotel("13,The Plaza,San Francisco,100" + RandomSeats());
        hotels[13] = new Hotel("14,The Oberoi,New York,50" + RandomSeats());
        hotels[14] = new Hotel("15,Taj Hotel,Washington,100" + RandomSeats());
        hotels[15] = new Hotel("16,Marriot,London,200" + RandomSeats());
        hotels[16] = new Hotel("17,Hyatt,Paris,300" + RandomSeats());
        hotels[17] = new Hotel("18,Grand Hotel,Beijing,100" + RandomSeats());
        hotels[18] = new Hotel("19,The Ritz,Tokyo,150" + RandomSeats());
        hotels[19] = new Hotel("20,The Peninsula,Los Angeles,50" + RandomSeats());
        return hotels;
    }

    private Flight[] CreateDummyFlights() {
        Flight[] flights = new Flight[20];
        flights[0] = new Flight("1,Washington,Delhi,500" + RandomSeats());
        flights[1] = new Flight("2,Londono,Moscow,200" + RandomSeats());
        flights[2] = new Flight("3,Paris,New York,300" + RandomSeats());
        flights[3] = new Flight("4,Beijing,Shanghai,100" + RandomSeats());
        flights[4] = new Flight("5,Tokyo,Seoul,150" + RandomSeats());
        flights[5] = new Flight("6,Los Angeles,San Francisco,50" + RandomSeats());
        flights[6] = new Flight("7,Chicago,New York,100" + RandomSeats());
        flights[7] = new Flight("8,New York,Washington,50" + RandomSeats());
        flights[8] = new Flight("9,Delhi,Beijing,200" + RandomSeats());
        flights[9] = new Flight("10,Moscow,Paris,150" + RandomSeats());
        flights[10] = new Flight("11,Shanghai,Tokyo,100" + RandomSeats());
        flights[11] = new Flight("12,Seoul,Los Angeles,200" + RandomSeats());
        flights[12] = new Flight("13,San Francisco,Chicago,100" + RandomSeats());
        flights[13] = new Flight("14,New York,Washington,50" + RandomSeats());
        flights[14] = new Flight("15,Washington,Delhi,500" + RandomSeats());
        flights[15] = new Flight("16,Londono,Moscow,200" + RandomSeats());
        flights[16] = new Flight("17,Paris,New York,300" + RandomSeats());
        flights[17] = new Flight("18,Beijing,Shanghai,100" + RandomSeats());
        flights[18] = new Flight("19,Tokyo,Seoul,150" + RandomSeats());
        flights[19] = new Flight("20,Los Angeles,San Francisco,50" + RandomSeats());
        return flights;
    }

    private String RandomSeats() {
        String line = "";
        for (int i = 0; i < 31; i++) {
            line += (int) (Math.random() * 20 + 50) + ",";
        }
        return line;
    }

    public void WriteUser(User user) {
        String csvFile = "./Data/UserData/Users.csv";
        try (FileWriter writer = new FileWriter(csvFile, true)) {
            writer.append(user.toString() + "\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public void WriteUsers(User[] users) {
        String csvFile = "./Data/UserData/Users.csv";
        try (FileWriter writer = new FileWriter(csvFile, false)) {
            for (int i = 0; i < users.length; i++) {
                writer.append(users[i].toString() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public void WriteFlights(Flight[] flights) {
        String csvFile = "./Data/ProgramData/Flights.csv";
        try (FileWriter writer = new FileWriter(csvFile, false)) {
            for (int i = 0; i < flights.length; i++) {
                writer.append(flights[i].toString() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public void WriteHotels(Hotel[] hotels) {
        String csvFile = "./Data/ProgramData/Hotels.csv";
        try (FileWriter writer = new FileWriter(csvFile, false)) {
            for (int i = 0; i < hotels.length; i++) {
                writer.append(hotels[i].toString() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}

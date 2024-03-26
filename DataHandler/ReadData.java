package DataHandler;

import Flight.Flight;
import Hotel.Hotel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadData {

    private Flight[] flights;
    private Hotel[] hotels;

    public ReadData() {
        flights = ReadFlights();
        hotels = ReadHotels();
    }

    private Flight[] ReadFlights() {
        try (BufferedReader reader = new BufferedReader(new FileReader("../data/ProgramData/Flights.csv"))) {

            int size = countLines("../data/ProgramData/Flights.csv");
            Flight[] flights = new Flight[size];
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                flights[i] = new Flight(line);
            }
            return flights;
        } catch (FileNotFoundException e) {
            String csvFile = "../data/ProgramData/Flights.csv";
            try (FileWriter writer = new FileWriter(csvFile)) {
                Flight[] tempFlight = CreateDummyFlights();
                for (int i = 0; i < tempFlight.length; i++) {
                    writer.append(tempFlight[i].toString() + "\n");
                }
                writer.flush();
                return tempFlight;
            } catch (IOException e2) {
                System.err.println("Error writing CSV file: " + e2.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e);
        }
        return new Flight[0];
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
        for (int i = 0; i < 30; i++) {
            line += (int) (Math.random() * 20 + 50) + ",";
        }
        return line;
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

    private Hotel[] ReadHotels() {
        try (BufferedReader reader = new BufferedReader(new FileReader("../data/ProgramData/Hotels.csv"))) {
            int size = countLines("../data/ProgramData/Hotels.csv");
            Hotel[] hotels = new Hotel[size];
            String line;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                hotels[i] = new Hotel(line);
            }
            return hotels;
        } catch (FileNotFoundException e) {
            String csvFile = "../data/ProgramData/Hotels.csv";
            try (FileWriter writer = new FileWriter(csvFile)) {
                Hotel[] tempHotel = CreateDummyHotels();
                for (int i = 0; i < tempHotel.length; i++) {
                    writer.append(tempHotel[i].toString() + "\n");
                }
                writer.flush();
                return tempHotel;
            } catch (IOException e2) {
                System.err.println("Error writing CSV file: " + e2.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e);
        }
        return new Hotel[0];
    }

    private Hotel[] CreateDummyHotels() {
        Hotel[] hotels = new Hotel[20];
        hotels[0] = new Hotel("1,Washington,500,100" + RandomSeats());
        hotels[1] = new Hotel("2,Londono,200,50" + RandomSeats());
        hotels[2] = new Hotel("3,Paris,300,100" + RandomSeats());
        hotels[3] = new Hotel("4,Beijing,100,30" + RandomSeats());
        hotels[4] = new Hotel("5,Tokyo,150,40" + RandomSeats());
        hotels[5] = new Hotel("6,Los Angeles,50,20" + RandomSeats());
        hotels[6] = new Hotel("7,Chicago,100,30" + RandomSeats());
        hotels[7] = new Hotel("8,New York,50,20" + RandomSeats());
        hotels[8] = new Hotel("9,Delhi,200,50" + RandomSeats());
        hotels[9] = new Hotel("10,Moscow,150,40" + RandomSeats());
        hotels[10] = new Hotel("11,Shanghai,100,30" + RandomSeats());
        hotels[11] = new Hotel("12,Seoul,200,50" + RandomSeats());
        hotels[12] = new Hotel("13,San Francisco,100,30" + RandomSeats());
        hotels[13] = new Hotel("14,New York,50,20" + RandomSeats());
        hotels[14] = new Hotel("15,Washington,500,100" + RandomSeats());
        hotels[15] = new Hotel("16,Londono,200,50" + RandomSeats());
        hotels[16] = new Hotel("17,Paris,300,100" + RandomSeats());
        hotels[17] = new Hotel("18,Beijing,100,30" + RandomSeats());
        hotels[18] = new Hotel("19,Tokyo,150,40" + RandomSeats());
        hotels[19] = new Hotel("20,Los Angeles,50,20" + RandomSeats());
        return hotels;
    }

    public Flight[] getFlights() {
        return flights;
    }

    public Hotel[] getHotels() {
        return hotels;
    }
}

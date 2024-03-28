package CustomExceptions;

public class FlightNotFoud extends Exception{
    // constructor to initialize the exception message
    public FlightNotFoud(){
        super("Flight Not Found");
    }
}

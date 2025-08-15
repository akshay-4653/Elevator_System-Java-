package elevator;
import java.util.Scanner;

/*
 * Main class to simulate elevator operations.
 * This class provides two modes of operation:
 * 1. Manual Mode: Allows users to input starting and destination floors.
 * 2. Automatic Mode: Simulates random elevator requests during lunch hours.
 */
public class ElevatorChallenge_MainClass {

    /*
     * Simulates automatic elevator operations by generating random floor requests.
     
     * This method simulates a busy scenario where multiple random floor requests are made,
     * and the elevator responds accordingly.
     
     
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    static void automaticElevator() throws InterruptedException {
        Elevator elevator = new Elevator();
        elevator.lunchTimeElevatorRush();
        elevator.start();
    }

    /*
     * Initiates manual elevator operations by prompting the user for floor inputs.
     
     * This method allows users to input a starting floor and a destination floor,
     * and then simulates the elevator's movement between these floors.
     
     
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    static void manualElevator() throws InterruptedException {
        Elevator elevator = new Elevator();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a starting floor from 0 - 10:");
        int start = sc.nextInt();

        System.out.println("Enter a destination floor from 0 - 10:");
        int end = sc.nextInt();

        elevator.callElevator(start, end);
        elevator.start();
    }

    /*
     * Main method to execute the elevator simulation.
     
     * This method provides options for the user to choose between manual and automatic elevator operations.
    
     
     * @param args Command-line arguments
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose operation mode:");
        System.out.println("1. Manual Elevator");
        System.out.println("2. Automatic Elevator");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                manualElevator();
                break;
            case 2:
                automaticElevator();
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }
    }
}

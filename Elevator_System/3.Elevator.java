package elevator;
import java.util.*;
/*
 * Represents an elevator system that can process floor requests and move accordingly.
 * This class manages the elevator's current floor, direction, and handles requests
 * for moving between floors.
 */
public class Elevator {
    private static final int MIN_FLOOR = 0;   // Minimum floor number
    private static final int MAX_FLOOR = 10;  // Maximum floor number
    private static int processingTime = 500;  // Time in milliseconds to process each floor movement

    private int currentFloor;               // Current floor of the elevator
    private Direction currentDirection;     // Current direction of the elevator
    private Map<Integer, List<Integer>> requestedPathsMap; // Map of requested floor paths
    private Boolean[] currentFloorDestinations;           // Array to track destinations for each floor

    /**
     * Initializes the elevator to the ground floor, sets its direction to UP,
     * and prepares the requested paths map and floor destinations.
     */
    public Elevator() {
        this.currentFloor = MIN_FLOOR;
        this.currentDirection = Direction.UP;
        this.requestedPathsMap = new HashMap<>();
        this.currentFloorDestinations = new Boolean[MAX_FLOOR + 1];
        Arrays.fill(this.currentFloorDestinations, Boolean.FALSE);
    }

    /**
     * Sets the processing time for each floor movement.
     
     * @param processingTime The time in milliseconds to process each floor movement
     */
    public void setProcessingTime(int processingTime) {
        Elevator.processingTime = processingTime;
    }

    /*
     * Starts the elevator's operation, processing floor requests and moving accordingly.
      
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    public void start() throws InterruptedException {
        currentDirection = Direction.UP;

        do {
            System.out.println("-----");
            processFloor(currentFloor);
            System.out.println("------");
        } while (currentDirection != Direction.IDLE);

        System.out.println("No one is waiting and no one is looking to anywhere");
        System.out.println("Chilling for now");
    }

    /*
     * Simulates a rush hour scenario by generating random floor requests.
     */
    public void lunchTimeElevatorRush() {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            callElevator(random.nextInt(11), random.nextInt(10) + 1);
        }
    }

    /*
     * Registers a request for the elevator to move from a start floor to a destination floor.
     
     * @param start      The starting floor
     * @param destination The destination floor
     */
    public void callElevator(int start, int destination) {
        if (isInvalidFloor(start) || isInvalidFloor(destination) || start == destination) {
            System.out.println("INVALID FLOORS. Try again");
            return;
        }
        requestedPathsMap.computeIfAbsent(start, k -> new ArrayList<>()).add(destination);
    }

    /*
     * Processes the current floor, handling boarding and unboarding of passengers.
     
     * @param floor The current floor to be processed
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    private void processFloor(int floor) throws InterruptedException {
        if (currentFloorDestinations[floor]) {
            System.out.println("UN-BOARDING at Floor: " + floor);
        }
        if (requestedPathsMap.containsKey(floor)) {
            System.out.println("BOARDING at Floor: " + floor);
            requestedPathsMap.get(floor).forEach(destinationFloor -> currentFloorDestinations[destinationFloor] = true);
            requestedPathsMap.remove(floor);
        }
        currentFloorDestinations[floor] = false;
        moveElevator();
    }

    /*
     * Moves the elevator based on the current direction and floor requests.
     
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    private void moveElevator() throws InterruptedException {
        if (!Arrays.asList(currentFloorDestinations).contains(true) && requestedPathsMap.isEmpty()) {
            currentDirection = Direction.IDLE;
            return;
        } else if (isInvalidFloor(currentFloor + 1)) {
            currentDirection = Direction.DOWN;
        } else if (isInvalidFloor(currentFloor - 1)) {
            currentDirection = Direction.UP;
        }

        switch (currentDirection) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                System.out.println("Elevator Malfunctioned");
        }
    }

    /*
     * Moves the elevator up by one floor.
     
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    private void moveUp() throws InterruptedException {
        currentFloor++;
        System.out.println("GOING UP TO " + currentFloor);
        Thread.sleep(processingTime);
    }

    /*
     * Moves the elevator down by one floor.
     
     * @throws InterruptedException If the thread is interrupted during sleep
     */
    private void moveDown() throws InterruptedException {
        currentFloor--;
        System.out.println("GOING DOWN TO " + currentFloor);
        Thread.sleep(processingTime);
    }

    /*
     * Checks if the given floor number is valid.
     
     * @param floor The floor number to be checked
     * @return True if the floor is valid, false otherwise
     */
    private boolean isInvalidFloor(int floor) {
        return floor < MIN_FLOOR || floor > MAX_FLOOR;
    }
}

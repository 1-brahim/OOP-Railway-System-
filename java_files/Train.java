import java.util.List; 
import java.util.ArrayList;
import java.util.Scanner;

public class Train  {
    private static ArrayList<Train> trainList = new ArrayList<>(); // Shared list of trains
    private String trainID;
    private String name;
    private int capacity;
    // private Route route; //association
    private boolean[] seats;

    // Constructor
    public Train(String trainID, String name, int capacity) {
        this.trainID = trainID;
        this.name = name;
        this.capacity = capacity;
        // this.route = route;
        this.seats = new boolean[capacity]; // Initialize all seats as available
    }

    // Getters and setters
    public String getTrainID() { return trainID; }
    public void setTrainID(String trainID) { this.trainID = trainID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }



	// Seat management methods
    public boolean isSeatAvailable(int seatNumber) {
        if (seatNumber < 1 || seatNumber > capacity) {
            throw new IllegalArgumentException("Invalid seat number.");
        }
        return !seats[seatNumber - 1]; // Check availability (array is 0-based)
    }

    public boolean bookSeat(int seatNumber) {
        if (isSeatAvailable(seatNumber)) {
            seats[seatNumber - 1] = true; // Mark seat as booked
			System.out.println("Seat has been booked.");
            return true;
        }
        return false; // Seat is already booked
    }

    public void cancelSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > capacity) {
            throw new IllegalArgumentException("Invalid seat number.");
        }
        seats[seatNumber - 1] = false; // Mark seat as available
		System.out.println("Seat has been cancelled.");
    }

    public int getAvailableSeatsCount() {
        int count = 0;
        for (boolean seat : seats) {
            if (!seat) count++;
        }
        return count;
    }

    public ArrayList<Integer> getAvailableSeats() {
        ArrayList<Integer> availableSeats = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                availableSeats.add(i + 1); // Add 1 to convert 0-based index to seat number
            }
        }
        return availableSeats;
    }



    // Manageable Interface Implementation
    
    // public void add() {
    //     trainList.add(this);
    //     System.out.println("Train added successfully: " + this);
    // }

	// update the train info in train list
	// public void updateInList(){
    //     for (int i = 0; i < trainList.size(); i++) { 
    //         if (trainList.get(i).getTrainID().equals(this.trainID)) {
    //             TrainManager.trainList.set(i, this);
    //             System.out.println("Train updated successfully: " + this);
    //             return;
    //         }
    //     }
    //     System.out.println("Train with ID " + this.trainID + " not found.");
    // }

    // method to just update the train details, to update the updated train in the train list, we use the train management class method's update
     public void updateTrain(Scanner scanner) {
        System.out.println("What would you like to update for train ID: " + this.trainID + "?");
        System.out.println("1. Name");
        System.out.println("2. Capacity");
        System.out.println("3. Route");
        System.out.println("4. All details"); // Option to update all at once
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                this.name = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter new capacity: ");
                this.capacity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            case 3:
                // edit it according to the route constructor
                System.out.println("Enter new route details:");
                // Example: Prompt for route details or use a route selection method.
                // You'll need to implement the route input logic based on your Route class.
                System.out.print("Enter new route ID: ");
                String routeID = scanner.nextLine();
                Route newRoute = new Route(routeID, "Start", "End"); // Replace with actual route data input
                this.route = newRoute;
                break;
            case 4:
              System.out.print("Enter new name: ");
                this.name = scanner.nextLine();
                System.out.print("Enter new capacity: ");
                this.capacity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Enter new route details:");
                System.out.print("Enter new route ID: ");
                String newRouteID = scanner.nextLine();
                Route newFullRoute = new Route(newRouteID, "Start", "End"); // Replace with actual route data input
                this.route = newFullRoute;
                break;
            default:
                System.out.println("Invalid choice.");
                return; 
        }

        // Update in trainList 
        // updateInList();
    }

    // @Override
    // public void delete() {
    //     trainList.removeIf(train -> train.getTrainID().equals(this.trainID));
    //     System.out.println("Train deleted successfully: " + this.trainID);
    // }


	

    @Override
    public String toString() {
        return "Train" +
               "trainID='" + trainID + '\'' +
               ", name='" + name + '\'' +
               ", capacity=" + capacity
               ;
    }
}
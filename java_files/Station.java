// Class: Station
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject; 
public class Station {
    private String stationID;
    private String name;
    private String location;
	private ArrayList<Station> allStations;
	

    // Constructor
    public Station(String stationID, String name, String location) {
        this.stationID = stationID;
        this.name = name;
        this.location = location;
    }

    // Getters and setters
    public String getStationID() { return stationID; }
    public void setStationID(String stationID) { this.stationID = stationID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
	
	
// Function to fetch weather using WeatherAPI.com
    

	// @Override
    // public void add(Scanner scanner) {
    //     allStations.add(this);
    //     System.out.println("Station with ID " + this.trainID + " has been added to the Stations list.");
    // }
	
	
	// public void updateInList() {
    //     for (int i = 0; i < allStations.size(); i++) { 
    //         if (allStations.get(i).getTrainID().equals(this.trainID)) {
    //             TrainManager.trainList.set(i, this);
    //             System.out.println("Train updated successfully: " + this);
    //             return;
    //         }
    //     }
    //     System.out.println("Train with ID " + this.trainID + " not found.");
    // }

    
    public void update(Scanner scanner) {
        System.out.println("What would you like to update for Station ID: " + this.stationID + "?");
        System.out.println("1. Name");
        System.out.println("2. Location");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                this.name = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter new location: ");
                this.location = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
        }
		// updateInList();
    }

   
    public void delete() {
        allStations.removeIf(station -> getStationID().equals(this.stationID));
        System.out.println("Train deleted successfully: " + this.stationID);
    }


	
    public void displayAll() {
        if (allStations.isEmpty()) {
            System.out.println("No Stations available.");
        } else {
            System.out.println("Available Stations:");
            for (Station station : allStations) {
                System.out.println(station);
            }
        }
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationID='" + stationID + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
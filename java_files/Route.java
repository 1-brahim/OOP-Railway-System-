import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Route {
    private String routeID;
    private ArrayList<Station> stations; // the stations in this route
    private double distance;
    private StationManager stationManager;


    // Constructor 
    public Route(String routeID, ArrayList<Station> stations, double distance, StationManager stationManager) {
        this.routeID = routeID;
        this.stations = stations;
        this.distance = distance;
        this.stationManager = stationManager;
    }

    // Getters and setters
    public String getRouteID() { return routeID; }
    public void setRouteID(String routeID) { this.routeID = routeID; }

    public ArrayList<Station> getStations() { return stations; }
    public void setStations(ArrayList<Station> stations) { this.stations = stations; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    // Add or remove stations
    public void addStation(Station station) {
        stations.add(station);
    }

    public void removeStation(String stationID) {
        stations.removeIf(station -> station.getStationID().equals(stationID));
    }



// create a new route
public Route createRoute(Scanner scanner) {
    // Ensure stationManager is initialized
    List<Station> allStations = stationManager.getAllStations();
    if (allStations.isEmpty()) {
        System.out.println("No stations available to create a route.");
        return null;
    }

    System.out.println("Available Stations:");
    for (int i = 0; i < allStations.size(); i++) {
        System.out.println((i + 1) + ". " + allStations.get(i).getName()); // Display station names with indices
    }

    // Prompt for route details
    System.out.print("Enter a unique Route ID: ");
    String routeID = scanner.nextLine();

    ArrayList<Station> selectedStations = new ArrayList<>();
    while (true) {
        System.out.print("Enter the number of a station to add to the route (or 0 to finish): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 0) {
            if (selectedStations.isEmpty()) {
                System.out.println("A route cannot be empty. Please add at least one station.");
            } else {
                System.out.println("Route creation completed.");
                break;
            }
        } else if (choice > 0 && choice <= allStations.size()) {
            Station selectedStation = allStations.get(choice - 1);
            if (selectedStations.contains(selectedStation)) {
                System.out.println(selectedStation.getName() + " is already in the route. Choose another station.");
            } else {
                selectedStations.add(selectedStation);
                System.out.println("Added " + selectedStation.getName() + " to the route.");
            }
        } else {
            System.out.println("Invalid station number. Please try again.");
        }
    }

    // Calculate distance (for simplicity, assume each segment is 10 km)
    double distance = (selectedStations.size() - 1) * 10.0;

    // Create the Route object using the constructor
    Route route = new Route(routeID, selectedStations, distance, stationManager);

    System.out.println("Route created successfully!");
    System.out.println("Route ID: " + route.getRouteID());
    System.out.println("Distance: " + route.getDistance() + " km");
    System.out.println("Stations in the Route:");
    for (Station station : route.getStations()) {
        System.out.println("- " + station.getName());
    }

    return route;
}


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Route ID: ").append(routeID).append("\n");
        sb.append("Distance: ").append(distance).append(" km\n");
        sb.append("Stations:\n");
        for (Station station : stations) {
            sb.append(" - ").append(station.getName()).append("\n");
        }
        return sb.toString();
    }
}

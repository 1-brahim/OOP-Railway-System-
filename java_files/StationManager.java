import java.util.List; 
import java.util.ArrayList;
class StationManager implements Manageable<Station>{
    private List<Station> stations;

    public StationManager() {
        this.stations = new ArrayList<>();
    }
    @Override
    public void add(Station station) {
        
        stations.add(station);
        System.out.println("Station added successfully: " + station);
    }
    @Override
    public void delete(Station station) {
        this.stations.remove(station);
    }


    @Override
    public void update(Station station){
        for (int i = 0; i < stations.size(); i++) { 
            if (stations.get(i).getStationID().equals(station.getStationID())) {
                stations.set(i, station);
                System.out.println("Station updated successfully: " + station);
                return;
            }
        }
        System.out.println("Station with ID " + station.getStationID() + " not found.");
    }


    public List<Station> getAllStations() {
        return stations; // Return a copy to prevent external modification
    }

    @Override
    public void displayAll() {
        if (stations.isEmpty()) {
            System.out.println("No stations available.");
        } else {
            System.out.println("Available stations:");
            for (Station train : stations) {
                System.out.println(train);
            }
        }
    }

    public Station findStationByName(String name) {
        for (Station train : stations) {
            if (train.getName().equals(name)) {
                return train;
            }
        }
        return null; 
    }




}
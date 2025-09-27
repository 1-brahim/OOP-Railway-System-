import java.util.List; 
import java.util.ArrayList;
import java.util.Scanner;
class TrainManager implements Manageable<Train>{
    private List<Train> trains;

    public TrainManager() {
        this.trains = new ArrayList<>();
    }
    @Override
    public void add(Train train) {
        
        trains.add(train);
        System.out.println("Train added successfully: " + train);
    }
    @Override
    public void delete(Train train) {
        this.trains.remove(train);
    }

// method to update the updated details of train in list
@Override
    public void update(Train train){
        for (int i = 0; i < trains.size(); i++) { 
            if (trains.get(i).getTrainID().equals(train.getTrainID())) {
                trains.set(i, train);
                System.out.println("Train updated successfully: " + train);
                return;
            }
        }
        System.out.println("Train with ID " + train.getTrainID() + " not found.");
    }


    public ArrayList<Train> getAllTrains() {
        // Return a copy to prevent external modification
            return new ArrayList<>(trains);
            }

            @Override
    public void displayAll() {       
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
        } else {
            System.out.println("Available Trains:");
            for (Train train : trains) {
                System.out.println(train);
            }
        }
    }

    public Train findTrainByName(String name) {
        for (Train train : trains) {
            if (train.getName().equals(name)) {
                return train;
            }
        }
        return null; 
    }
}
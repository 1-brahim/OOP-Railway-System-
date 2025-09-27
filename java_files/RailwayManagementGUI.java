import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RailwayManagementGUI {
    private static TrainManager trainManager = new TrainManager();
    private static StationManager stationManager = new StationManager();
    private static List<Route> routeList = new ArrayList<>();

    public static void main(String[] args) {
        // Create the main frame
        JFrame mainFrame = new JFrame("Railway Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Railway Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainFrame.add(titleLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton managerButton = new JButton("Continue as Manager");
        JButton userButton = new JButton("Continue as User");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(managerButton);
        buttonPanel.add(userButton);
        buttonPanel.add(exitButton);

        mainFrame.add(buttonPanel, BorderLayout.CENTER);

        // Footer Label
        // JLabel footerLabel = new JLabel("Powered by Swing", JLabel.CENTER);
        // footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        // mainFrame.add(footerLabel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openManagerMenu();
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUserMenu();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    mainFrame.dispose();
                }
            }
        });

        // Show the frame
        mainFrame.setVisible(true);
    }

    private static void openManagerMenu() {
        JFrame managerFrame = new JFrame("Manager Menu");
        managerFrame.setSize(400, 300);
        managerFrame.setLayout(new GridLayout(5, 1, 10, 10));

        JButton manageTrainsButton = new JButton("Manage Trains");
        JButton manageStationsButton = new JButton("Manage Stations");
        JButton manageRoutesButton = new JButton("Manage Routes");
        JButton backButton = new JButton("Back");

        managerFrame.add(manageTrainsButton);
        managerFrame.add(manageStationsButton);
        managerFrame.add(manageRoutesButton);
        managerFrame.add(backButton);

        manageTrainsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTrainManagementWindow();
            }
        });

        manageStationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStationManagementWindow();
            }
        });

        manageRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRouteManagementWindow();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerFrame.dispose();
            }
        });

        managerFrame.setVisible(true);
    }





    // train management window
    private static void openTrainManagementWindow() {
        JFrame trainFrame = new JFrame("Manage Trains");
        trainFrame.setSize(500, 400);
        trainFrame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Train Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        trainFrame.add(titleLabel, BorderLayout.NORTH);

        // Table to display trains
        String[] columns = {"Train ID", "Name", "Capacity"};
        JTable trainTable = new JTable(updateTrainTableData(), columns);
        trainFrame.add(new JScrollPane(trainTable), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton addButton = new JButton("Add Train");
        JButton updateButton = new JButton("Update Train");
        JButton deleteButton = new JButton("Delete Train");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        trainFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Add button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(trainFrame, "Enter Train ID:");
                String name = JOptionPane.showInputDialog(trainFrame, "Enter Train Name:");
                String capacityStr = JOptionPane.showInputDialog(trainFrame, "Enter Train Capacity:");

                try {
                    int capacity = Integer.parseInt(capacityStr);
                    trainManager.add(new Train(id, name, capacity));
                    JOptionPane.showMessageDialog(trainFrame, "Train added successfully!");
                    trainTable.setModel(new JTable(updateTrainTableData(), columns).getModel());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(trainFrame, "Invalid capacity. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(trainFrame, "Enter Train Name to Update:");
                Train train = trainManager.findTrainByName(name);
                if (train != null) {
                    String newName = JOptionPane.showInputDialog(trainFrame, "Enter New Train Name:", train.getName());
                    String capacityStr = JOptionPane.showInputDialog(trainFrame, "Enter New Capacity:", train.getCapacity());
                    try {
                        int capacity = Integer.parseInt(capacityStr);
                        train.setName(newName);
                        train.setCapacity(capacity);
                        trainManager.update(train);
                        JOptionPane.showMessageDialog(trainFrame, "Train updated successfully!");
                        trainTable.setModel(new JTable(updateTrainTableData(), columns).getModel());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(trainFrame, "Invalid capacity. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(trainFrame, "Train not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(trainFrame, "Enter Train Name to Delete:");
                Train train = trainManager.findTrainByName(name);
                if (train != null) {
                    trainManager.delete(train);
                    JOptionPane.showMessageDialog(trainFrame, "Train deleted successfully!");
                    trainTable.setModel(new JTable(updateTrainTableData(), columns).getModel());
                } else {
                    JOptionPane.showMessageDialog(trainFrame, "Train not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Show frame
        trainFrame.setVisible(true);
    }







    // station management window

    private static void openStationManagementWindow() {
        JFrame stationFrame = new JFrame("Manage Stations");
        stationFrame.setSize(500, 400);
        stationFrame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Station Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        stationFrame.add(titleLabel, BorderLayout.NORTH);

        // Table to display stations
        String[] columns = {"Station ID", "Name", "Location"};
        JTable stationTable = new JTable(updateStationTableData(), columns);
        stationFrame.add(new JScrollPane(stationTable), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton addButton = new JButton("Add Station");
        JButton updateButton = new JButton("Update Station");
        JButton deleteButton = new JButton("Delete Station");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        stationFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Add button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(stationFrame, "Enter Station ID:");
                String name = JOptionPane.showInputDialog(stationFrame, "Enter Station Name:");
                String location = JOptionPane.showInputDialog(stationFrame, "Enter Station Location:");

                stationManager.add(new Station(id, name, location));
                JOptionPane.showMessageDialog(stationFrame, "Station added successfully!");
                stationTable.setModel(new JTable(updateStationTableData(), columns).getModel());
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(stationFrame, "Enter Station Name to Update:");
                Station station = stationManager.findStationByName(name);
                if (station != null) {
                    String newName = JOptionPane.showInputDialog(stationFrame, "Enter New Station Name:", station.getName());
                    String location = JOptionPane.showInputDialog(stationFrame, "Enter New Location:", station.getLocation());
                    station.setName(newName);
                    station.setLocation(location);
                    stationManager.update(station);
                    JOptionPane.showMessageDialog(stationFrame, "Station updated successfully!");
                    stationTable.setModel(new JTable(updateStationTableData(), columns).getModel());
                } else {
                    JOptionPane.showMessageDialog(stationFrame, "Station not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(stationFrame, "Enter Station Name to Delete:");
                Station station = stationManager.findStationByName(name);
                if (station != null) {
                    stationManager.delete(station);
                    JOptionPane.showMessageDialog(stationFrame, "Station deleted successfully!");
                    stationTable.setModel(new JTable(updateStationTableData(), columns).getModel());
                } else {
                    JOptionPane.showMessageDialog(stationFrame, "Station not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Show frame
        stationFrame.setVisible(true);
    }
    
    private static String[][] updateStationTableData() {
        List<Station> stationList = stationManager.getAllStations();
        String[][] data = new String[stationList.size()][3];
        for (int i = 0; i < stationList.size(); i++) {
            Station station = stationList.get(i);
            data[i][0] = station.getStationID();
            data[i][1] = station.getName();
            data[i][2] = station.getLocation();
        }
        return data;
    }
    





    // route management window
    private static void openRouteManagementWindow() {
        JFrame routeFrame = new JFrame("Manage Routes");
        routeFrame.setSize(600, 500);
        routeFrame.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Route Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        routeFrame.add(titleLabel, BorderLayout.NORTH);

        // Table to display routes
        String[] columns = {"Route ID", "Distance", "Stations"};
        JTable routeTable = new JTable(updateRouteTableData(), columns);
        routeFrame.add(new JScrollPane(routeTable), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton addRouteButton = new JButton("Add Route");
        JButton viewRouteButton = new JButton("View Routes");

        buttonPanel.add(addRouteButton);
        buttonPanel.add(viewRouteButton);
        routeFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Add button functionality
        addRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Station> allStations = stationManager.getAllStations();
                if (allStations.isEmpty()) {
                    JOptionPane.showMessageDialog(routeFrame, "No stations available to create a route.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String routeID = JOptionPane.showInputDialog(routeFrame, "Enter Route ID:");
                ArrayList<Station> selectedStations = new ArrayList<>();

                while (true) {
                    String stationOptions = "Available Stations:\n";
                    for (int i = 0; i < allStations.size(); i++) {
                        stationOptions += (i + 1) + ". " + allStations.get(i).getName() + "\n";
                    }
                    stationOptions += "0. Finish\n";

                    String choiceStr = JOptionPane.showInputDialog(routeFrame, stationOptions + "Enter station number to add (0 to finish):");
                    int choice;
                    try {
                        choice = Integer.parseInt(choiceStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(routeFrame, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                    if (choice == 0) {
                        if (selectedStations.isEmpty()) {
                            JOptionPane.showMessageDialog(routeFrame, "A route must have at least one station.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            break;
                        }
                    } else if (choice > 0 && choice <= allStations.size()) {
                        Station selectedStation = allStations.get(choice - 1);
                        if (selectedStations.contains(selectedStation)) {
                            JOptionPane.showMessageDialog(routeFrame, "Station already added.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            selectedStations.add(selectedStation);
                        }
                    } else {
                        JOptionPane.showMessageDialog(routeFrame, "Invalid station number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                double distance = (selectedStations.size() - 1) * 10.0;
                Route newRoute = new Route(routeID, selectedStations, distance, stationManager);
                routeList.add(newRoute);
                JOptionPane.showMessageDialog(routeFrame, "Route added successfully!\n" + newRoute.toString());
                routeTable.setModel(new JTable(updateRouteTableData(), columns).getModel());
            }
        });

        viewRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (routeList.isEmpty()) {
                    JOptionPane.showMessageDialog(routeFrame, "No routes available.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder routesInfo = new StringBuilder("Available Routes:\n\n");
                    for (Route route : routeList) {
                        routesInfo.append(route.toString()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(routeFrame, routesInfo.toString(), "View Routes", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Show frame
        routeFrame.setVisible(true);
    }


    private static void openUserMenu() {
        JFrame userFrame = new JFrame("User Menu");
        userFrame.setSize(400, 300);
        userFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("User Booking System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        userFrame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton bookTicketButton = new JButton("Book a Ticket");
        JButton backButton = new JButton("Back");

        buttonPanel.add(bookTicketButton);
        buttonPanel.add(backButton);
        userFrame.add(buttonPanel, BorderLayout.CENTER);

        bookTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookTicket();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFrame.dispose();
            }
        });

        userFrame.setVisible(true);
    }

    private static void bookTicket() {
        if (trainManager.getAllTrains().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No trains available for booking.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String userName = JOptionPane.showInputDialog(null, "Enter your name:");
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String userEmail = JOptionPane.showInputDialog(null, "Enter your email:");
        if (userEmail == null || userEmail.isEmpty() || !userEmail.contains("@")) {
            JOptionPane.showMessageDialog(null, "Invalid email.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        User user = new User(userName, userEmail);
    
        List<Train> trains = trainManager.getAllTrains();
        StringBuilder trainOptions = new StringBuilder("Available Trains:\n");
        for (int i = 0; i < trains.size(); i++) {
            trainOptions.append(i + 1).append(". ").append(trains.get(i).getName()).append(" (ID: ").append(trains.get(i).getTrainID()).append(")\n");
        }
    
        String choiceStr = JOptionPane.showInputDialog(null, trainOptions.toString() + "Select a train by number:");
        int trainChoice;
        try {
            trainChoice = Integer.parseInt(choiceStr);
            if (trainChoice < 1 || trainChoice > trains.size()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid train choice.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        Train selectedTrain = trains.get(trainChoice - 1);
        JOptionPane.showMessageDialog(null, "You selected: " + selectedTrain.getName());
    
        String seatNumberStr = JOptionPane.showInputDialog(null, "Enter seat number to book:");
        int seatNumber;
        try {
            seatNumber = Integer.parseInt(seatNumberStr);
            if (!selectedTrain.isSeatAvailable(seatNumber)) {
                JOptionPane.showMessageDialog(null, "Seat not available.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid seat number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Payment Options
        String[] paymentMethods = {"Credit Card", "Debit Card", "PayPal"};
        String paymentChoice = (String) JOptionPane.showInputDialog(null, "Select a payment method:", 
            "Payment", JOptionPane.QUESTION_MESSAGE, null, paymentMethods, paymentMethods[0]);
    
        if (paymentChoice == null) {
            JOptionPane.showMessageDialog(null, "Payment canceled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Simulate payment processing
        JOptionPane.showMessageDialog(null, "Processing payment via " + paymentChoice + "...", "Payment", JOptionPane.INFORMATION_MESSAGE);
    
        // Assume payment is successful
        Booking booking = new Booking(user, selectedTrain, seatNumber, new Scanner(System.in));
        if (booking.getTicket() != null) {
            JOptionPane.showMessageDialog(null, "Payment successful! Booking confirmed.", "Success", JOptionPane.INFORMATION_MESSAGE);
    
            // Display ticket details
            Ticket ticket = booking.getTicket();
            StringBuilder ticketDetails = new StringBuilder("Ticket Details:\n");
            ticketDetails.append("Name: ").append(user.getName()).append("\n");
            ticketDetails.append("Email: ").append(user.getEmail()).append("\n");
            ticketDetails.append("Train: ").append(selectedTrain.getName()).append("\n");
            ticketDetails.append("Train ID: ").append(selectedTrain.getTrainID()).append("\n");
            ticketDetails.append("Seat Number: ").append(seatNumber).append("\n");
            ticketDetails.append("Payment Method: ").append(paymentChoice).append("\n");
    
            JOptionPane.showMessageDialog(null, ticketDetails.toString(), "Ticket Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Booking failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private static String[][] updateTrainTableData() {
        var trainList = trainManager.getAllTrains();
        String[][] data = new String[trainList.size()][3];
        for (int i = 0; i < trainList.size(); i++) {
            Train train = trainList.get(i);
            data[i][0] = train.getTrainID();
            data[i][1] = train.getName();
            data[i][2] = String.valueOf(train.getCapacity());
        }
        return data;
    }


    private static String[][] updateRouteTableData() {
        String[][] data = new String[routeList.size()][3];
        for (int i = 0; i < routeList.size(); i++) {
            Route route = routeList.get(i);
            data[i][0] = route.getRouteID();
            data[i][1] = String.valueOf(route.getDistance());
            StringBuilder stations = new StringBuilder();
            for (Station station : route.getStations()) {
                stations.append(station.getName()).append(", ");
            }
            data[i][2] = stations.length() > 0 ? stations.substring(0, stations.length() - 2) : "";
        }
        return data;
    }
}

public class Ticket {
    private String ticketID;
    private Booking booking; // Composition relationship
    private Train train;
    private int seatNumber;

    public Ticket(Booking booking, Train train, int seatNumber) {
        this.ticketID = "TCK-" + System.currentTimeMillis();
        this.booking = booking;
        this.train = train;
        this.seatNumber = seatNumber;
    }

   

    public String getTicketID() {
        return ticketID;
    }

    public Booking getBooking() {
        return booking;
    }

    public Train getTrain() {
        return train;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public ticket displayTicketDetails() {
        System.out.println("Ticket ID: " + ticketID);
        System.out.println("Booking ID: " + booking.getBookingID());
        System.out.println("Train: " + train.getName() + " (" + train.getTrainID() + ")");
        System.out.println("Seat Number: " + seatNumber);
        return ticket; 
    }
}

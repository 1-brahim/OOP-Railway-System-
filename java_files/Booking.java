import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Booking {
    private String bookingID;
    private User user;
    private Train train;
    private LocalDateTime bookingTime;
    private Ticket ticket;

    public Booking(User user, Train train, int seatNumber, Scanner scanner) {
    this.bookingID = "BKG-" + System.currentTimeMillis();
    this.user = user;
    this.train = train;
    this.bookingTime = LocalDateTime.now();
    ticket = new Ticket(this, train, seatNumber);

    // Prompt user for payment
    System.out.println("Please make a payment to confirm your booking.");
    boolean paymentSuccessful = processPayment(scanner);

    if (paymentSuccessful) {
        // Check seat availability before creating a ticket
        if (train.isSeatAvailable(seatNumber)) {
            train.bookSeat(seatNumber); // Book the seat in the train
            this.ticket = new Ticket(this, train, seatNumber); // Initialize the ticket
            System.out.println("Booking successful. Ticket generated.");
        } else {
            System.out.println("Seat not available. Booking failed.");
            this.ticket = null; // No ticket generated if booking fails
        }
    } else {
        System.out.println("Payment failed. Booking not completed.");
        this.ticket = null; // No ticket generated if payment fails
    }
}

// Payment Processing Method
private boolean processPayment(Scanner scanner) {
    System.out.println("Choose Payment Method:");
    System.out.println("1. Credit Card");
    System.out.println("2. PayPal");
    System.out.print("Enter your choice: ");
    int paymentChoice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (paymentChoice) {
        case 1 -> {
            System.out.print("Enter Credit Card Number: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Enter Card Holder Name: ");
            String cardHolderName = scanner.nextLine();
            

            CreditCardPayment creditCardPayment = new CreditCardPayment(cardNumber, cardHolderName);
            try {
                creditCardPayment.processPayment();
                System.out.println("Credit Card Payment successful!");
                return true;
            } catch (Exception e) {
                System.out.println("Payment processing failed: " + e.getMessage());
                return false;
            }
        }
        case 2 -> {
            System.out.print("Enter PayPal Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter PayPal Password: ");
            String password = scanner.nextLine();

            PayPalPayment payPalPayment = new PayPalPayment(email, password);
            try {
                payPalPayment.processPayment();
                System.out.println("PayPal Payment successful!");
                return true;
            } catch (Exception e) {
                System.out.println("Payment processing failed: " + e.getMessage());
                return false;
            }
        }
        default -> {
            System.out.println("Invalid payment method. Please try again.");
            return false;
        }
    }
}

    public String getBookingID() {
        return bookingID;
    }

    public Ticket getTicket() {
        return ticket;
    }



    public void displayBookingDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Booking ID: " + bookingID);
        System.out.println("User: " + user.getName());
        System.out.println("Train: " + train.getName());
        System.out.println("Booking Time: " + bookingTime.format(formatter));
        ticket.displayTicketDetails(); 
    }

    public void cancelBooking() {
        if (ticket != null) {
            train.cancelSeat(ticket.getSeatNumber());
            System.out.println("Booking canceled. Seat is now available.");
            this.ticket = null; // Remove ticket as booking is canceled
        } else {
            System.out.println("No active booking to cancel.");
        }
    }
}







   
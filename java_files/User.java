import java.util.ArrayList;

public class User extends Person {
    private ArrayList<Booking> bookings;
    private ArrayList<Payment> payments;

    public User(String name, String email) {
        super(name, email);
        this.bookings = new ArrayList<>();
        this.payments = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            bookings.forEach(Booking::displayBookingDetails);
        }
    }

    public Booking getBookingByID(String bookingID) {
        for (Booking booking : bookings) {
            if (booking.getBookingID().equals(bookingID)) {
                return booking;
            }
        }
        return null;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public void viewAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
        } else {
            payments.forEach(Payment::displayPaymentDetails);
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Passenger Name: " + name);
        System.out.println("Passenger Email: " + email);
    }
}

class CreditCardPayment implements Payment {
    private String cardNumber;
    private String cardHolderName;
    // private Date expiryDate;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        // this.expiryDate = expiryDate;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment for card: " + cardNumber);
    }
}

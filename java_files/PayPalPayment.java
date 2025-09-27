class PayPalPayment implements Payment {
    private String email;
    private String password;

    public PayPalPayment(String email , String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing PayPal payment for email: " + email);
    }
}
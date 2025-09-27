public class Manager extends Person {
    private String username;
    private String password;

    public Manager(String name, String email, String username, String password) {
        super(name, email);
        this.username = username;
        this.password = password;
    }

    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getName() {
        return super.getName();
    }
}

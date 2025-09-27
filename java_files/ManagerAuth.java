import java.util.HashMap;

public class ManagerAuth {
    private static HashMap<String, Manager> managers;

    // Static block to add default manager
    
    public ManagerAuth() {
        managers = new HashMap<>();

    }

    static {
        managers.put("admin", new Manager("Admin", "admin@example.com", "admin", "admin123"));
    }
    public static boolean createAccount(String name, String email, String username, String password) {
        if (managers.containsKey(username)) {
            System.out.println("Username already exists. Try another.");
            return false;
        }
        managers.put(username, new Manager(name, email, username, password));
        System.out.println("Account created successfully!");
        return true;
    }





//   this is the boolean return function
    public boolean managerLogin(String username, String password) {
        Manager manager = managers.get(username);

        if (manager == null) {
            System.out.println("Invalid username or password.");
            return false;
        }

        if (manager.validatePassword(password)) {
            System.out.println("Login successful! Welcome, " + manager.getName() + "!");
            return true;
        }

        System.out.println("Invalid username or password.");
        return false;
    }




    public static boolean isManagerExists(String username) {
        return managers.containsKey(username);
    }
}

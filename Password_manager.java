import java.util.Scanner;
import java.util.ArrayList;

public class Password_manager {
    String site, username, password;
    Password_manager(String s, String u, String p) {
        site = s;
        username = u;
        password = p;
    }

    public static void add(ArrayList<Password_manager> entries, Scanner scanner) {
        System.out.print("Enter site name: ");
        String site = scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        Password_manager newEntry = new Password_manager(site, username, pass);
        entries.add(newEntry);

        System.out.println("Entry added successfully!\n");
    }

    public static void view(ArrayList<Password_manager> entries) {
        if (entries.isEmpty()) {
            System.out.println("No entries found.\n");
            return;
        }
        System.out.println("\n----------|Stored Passwords|----------");
        for (int i = 0; i < entries.size(); i++) {
            Password_manager p = entries.get(i);
            System.out.println((i + 1) + ". Site: " + p.site);
            System.out.println("   Username: " + p.username);
            System.out.println("   Password: " + p.password);
            System.out.println("-------------------------------------");
        }
        System.out.println();
    }

    public static void search(ArrayList<Password_manager> entries, Scanner scanner) {
        System.out.print("Enter site or username to search: ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Password_manager p : entries) {
            if (p.site.toLowerCase().contains(query) || p.username.toLowerCase().contains(query)) {
                System.out.println("\nMatch found!");
                System.out.println("Site: " + p.site);
                System.out.println("Username: " + p.username);
                System.out.println("Password: " + p.password);
                System.out.println("----------------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching entry found.\n");
        }
    }

    public static void delete(ArrayList<Password_manager> entries, Scanner scanner) {
        if (entries.isEmpty()) {
            System.out.println("No entries to delete.\n");
            return;
        }
        System.out.println("\nSites saved:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i).site);
        }
        System.out.print("\nEnter the number of the site to delete: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > entries.size()) {
            System.out.println("Invalid choice.\n");
        } else {
            scanner.nextLine();
            System.out.println("Are you sure you want to delete?(Y/N)");
            String sure = scanner.nextLine();
            if(sure.toLowerCase().equals("y") || sure.toLowerCase().equals("yes")) {
                Password_manager removed = entries.remove(choice - 1);
                System.out.println("Entry for '" + removed.site + " deleted successfully.\n");
            }
        }
    }


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Password_manager> entries = new ArrayList<Password_manager>();
        int password = 1234, attempts = 3;
        System.out.println("----------|Password Manager|----------");
        while(true) {
            System.out.print("Login Details:\nEnter Password to manage passwords: ");
            int input_password = scanner.nextInt();
            if(input_password == password) {
                System.out.println("Correct Password!\nWelcome User\n");
                break;
            } else {
                attempts--;
                if(attempts == 0) {
                    System.out.println("Too many incorrect attempts...\nExiting program...");
                    System.exit(0);
                }
                System.out.println("Wrong password. " + attempts + " attempts left...");
            }
        }
        while(true) {
            System.out.print("1. Add Entry\n2. View All Entries\n3. Search\n4. Delete Entry\n5. Exit\nChoice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1) {
                add(entries, scanner);
            } else if (choice == 2) {
                view(entries);
            } else if (choice == 3) {
                search(entries, scanner);
            } else if (choice == 4) {
                delete(entries, scanner);
            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Enter a valid choice...");
            }
        }
        scanner.close();
    }
}

import java.util.List;
import java.util.Scanner;

public class MainView {

    /**
     * @return
     */
    public int mainMenu() {
        // TODO Auto-generated method stub
        System.out.println("1. Add a Sport\n2 Add an Athlete\n3 Search Athlete\n4 List Athletes by Sport\n5 List Sports\n6 Sortir --> conn.close()");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        return option;

    }
    // Form to add a new sport
    public sport SportForm() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sport name: ");
       while (sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid name: ");
            sc.nextLine();
        }
        return new sport(sc.nextLine(),0);
    }

    // Form to add a new athlete
    public athlete AthleteForm() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter athlete name: ");
      while (sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid name: ");
            sc.nextLine();
        }
        String name = sc.nextLine();

        System.out.print("Enter sport ID: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid Sport ID: ");
            sc.nextLine();
        }
        int sportId = sc.nextInt();
        return new athlete(name,sportId,0);
    }

    // Form to search for an athlete by name
    public String searchAthleteForm() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter part of the athlete's name: ");
        while (!sc.hasNextLine()) {
            System.out.println("Invalid input. Please enter a valid name: ");
            sc.nextLine();
        }
        return sc.nextLine();
    }

    // Prompt to ask for a sport ID
    public int askSport() {

        System.out.print("Enter the Sport ID: ");
        Scanner sc = new Scanner(System.in);
        try {
            return sc.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid Sport ID.");
            sc.nextLine();
            return -1;
        }
    }

    // Display a list of sports
    public void SportsList(List<sport> sports) {
        System.out.println("=== List of Sports ===");
        for (sport sport : sports) {
            System.out.println(sport);
        }
    }

    // Display a list of athletes
    public void AthletesList(List<athlete> athletes) {
        System.out.println("=== List of Athletes ===");
        for (athlete athlete : athletes) {
            System.out.println(athlete);
        }
    }



}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Lab work No.5
 * Variant 19: Art Exhibition (outer class) with Painting (inner class).
 * Demonstrates nested classes and search functionality.
 */
public class ArtExhibition {

    // --- Fields of the outer class ---
    private String name;
    private String location;
    private String dateTime;
    private List<Painting> paintings;

    // -------------------------------------------------------
    // INNER CLASS
    // -------------------------------------------------------
    public class Painting {
        private String title;
        private String author;
        private String direction; // e.g. Impressionism, Realism, etc.

        public Painting(String title, String author, String direction) {
            this.title = title;
            this.author = author;
            this.direction = direction;
        }

        public String getTitle()     { return title; }
        public String getAuthor()    { return author; }
        public String getDirection() { return direction; }

        // Inner class can access outer class field directly
        public void printInfo() {
            System.out.println("  Painting    : " + title);
            System.out.println("  Author      : " + author);
            System.out.println("  Direction   : " + direction);
            System.out.println("  Exhibition  : " + name); // outer class field
        }
    }
    // -------------------------------------------------------

    // Constructor of the outer class
    public ArtExhibition(String name, String location, String dateTime) {
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
        this.paintings = new ArrayList<>();
    }

    // Add a painting to the exhibition
    public void addPainting(Painting p) {
        paintings.add(p);
        System.out.println("[INFO] Painting \"" + p.getTitle() + "\" added to exhibition.");
    }

    // Print all exhibition info
    public void printExhibitionInfo() {
        System.out.println("\n========================================");
        System.out.println("  EXHIBITION : " + name);
        System.out.println("  Location   : " + location);
        System.out.println("  Date/Time  : " + dateTime);
        System.out.println("  Paintings  : " + paintings.size());
        System.out.println("========================================");
        for (int i = 0; i < paintings.size(); i++) {
            System.out.println("  [" + (i + 1) + "]");
            paintings.get(i).printInfo();
        }
    }

    // Search paintings by author
    public void searchByAuthor(String author) {
        System.out.println("\n--- Search by author: \"" + author + "\" ---");
        boolean found = false;
        for (Painting p : paintings) {
            if (p.getAuthor().equalsIgnoreCase(author)) {
                p.printInfo();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("[RESULT] No paintings found by author \"" + author + "\".");
        }
    }

    // Search paintings by direction
    public void searchByDirection(String direction) {
        System.out.println("\n--- Search by direction: \"" + direction + "\" ---");
        boolean found = false;
        for (Painting p : paintings) {
            if (p.getDirection().equalsIgnoreCase(direction)) {
                p.printInfo();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("[RESULT] No paintings found with direction \"" + direction + "\".");
        }
    }

    // Search paintings by title
    public void searchByTitle(String title) {
        System.out.println("\n--- Search by title: \"" + title + "\" ---");
        boolean found = false;
        for (Painting p : paintings) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                p.printInfo();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("[RESULT] No painting found with title \"" + title + "\".");
        }
    }

    // -------------------------------------------------------
    // MAIN
    // -------------------------------------------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Lab work No.5 — Nested Classes ===");
        System.out.println("Art Exhibition with inner class Painting\n");

        // --- Create exhibition ---
        System.out.println("-- Enter exhibition details --");
        System.out.print("Exhibition name   : ");
        String exName = sc.nextLine();
        System.out.print("Location          : ");
        String exLocation = sc.nextLine();
        System.out.print("Date and time     : ");
        String exDateTime = sc.nextLine();

        ArtExhibition exhibition = new ArtExhibition(exName, exLocation, exDateTime);
        System.out.println("[INFO] Exhibition \"" + exName + "\" created.\n");

        // --- Add paintings ---
        System.out.print("How many paintings to add? ");
        int count = Integer.parseInt(sc.nextLine().trim());

        for (int i = 1; i <= count; i++) {
            System.out.println("\n-- Painting #" + i + " --");
            System.out.print("Title     : ");
            String title = sc.nextLine();
            System.out.print("Author    : ");
            String author = sc.nextLine();
            System.out.print("Direction : ");
            String direction = sc.nextLine();

            // Creating inner class object through outer class instance
            Painting painting = exhibition.new Painting(title, author, direction);
            exhibition.addPainting(painting);
        }

        // --- Print all info ---
        exhibition.printExhibitionInfo();

        // --- Search menu ---
        boolean running = true;
        while (running) {
            System.out.println("\n========== SEARCH MENU ==========");
            System.out.println("  1. Search by author");
            System.out.println("  2. Search by direction");
            System.out.println("  3. Search by title");
            System.out.println("  0. Exit");
            System.out.print("Your choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter author name: ");
                    exhibition.searchByAuthor(sc.nextLine());
                    break;
                case "2":
                    System.out.print("Enter direction: ");
                    exhibition.searchByDirection(sc.nextLine());
                    break;
                case "3":
                    System.out.print("Enter title: ");
                    exhibition.searchByTitle(sc.nextLine());
                    break;
                case "0":
                    running = false;
                    System.out.println("[INFO] Program finished.");
                    break;
                default:
                    System.out.println("[WARNING] Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}

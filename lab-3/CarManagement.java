import java.util.Scanner;
import java.util.Calendar;
import java.util.InputMismatchException;

public class CarManagement {
    public static void main(String[] args) {
        System.out.println("Developer: Smal Margarita");
        Scanner scanner = new Scanner(System.in);
        Car[] cars = new Car[5];

        System.out.println("\n--- Entering data for 5 cars ---");
        for (int i = 0; i < cars.length; i++) {
            System.out.println("\nCar #" + (i + 1));
            cars[i] = createCarWithValidation(scanner, i + 1);
        }

        System.out.println("\n--- All Entered Cars ---");
        printTableHead();
        for (Car car : cars) {
            System.out.println(car);
        }
        printTableBorder();

        // Виклик методу, який виконує всі 3 пункти пошуку
        performSearches(scanner, cars);
        scanner.close();
    }

    private static Car createCarWithValidation(Scanner sc, int id) {
        while (true) {
            try {
                System.out.print("Brand: "); String brand = sc.next();
                System.out.print("Model: "); String model = sc.next();
                System.out.print("Year: "); int year = sc.nextInt();
                System.out.print("Color: "); String color = sc.next();
                System.out.print("Reg Number: "); String reg = sc.next();
                System.out.print("Price: "); double price = sc.nextDouble();

                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                
                if (year < 1886 || year > currentYear) {
                    throw new Exception("Invalid year! Must be between 1886 and " + currentYear);
                }
                if (price < 0) {
                    throw new Exception("Price cannot be negative!");
                }

                return new Car(id, brand, model, year, color, reg, price);

            } catch (InputMismatchException e) {
                // Відловлюємо помилку - ввели текст замість числа
                System.out.println("Type Error: Expected a number. (Use comma ',' for decimals). Try again.");
                sc.nextLine(); // Очищуємо зламаний ввід
            } catch (Exception e) {
                // Відловлюємо наші власні помилки (рік, ціна)
                System.out.println("Error: " + e.getMessage() + " Try again.");
                sc.nextLine(); 
            }
        }
    }

    private static void performSearches(Scanner sc, Car[] cars) {
        // ЗАВДАННЯ 1: Список автомобілів заданої марки
        System.out.print("\n--- 1. Search by Brand ---\nEnter brand: ");
        String searchBrand = sc.next();
        boolean found1 = false;
        printTableHead();
        for (Car c : cars) {
            if (c.getBrand().equalsIgnoreCase(searchBrand)) {
                System.out.println(c);
                found1 = true;
            }
        }
        if (!found1) {
            System.out.println("No cars found for brand: " + searchBrand);
        } else {
            printTableBorder();
        }

        // ЗАВДАННЯ 2: Автомобілі заданої моделі, які експлуатуються більше N років 
        System.out.print("\n--- 2. Search by Model and Years of Exploitation ---\nEnter model: ");
        String searchModel = sc.next();
        System.out.print("More than how many years in use (N): ");
        int nYears = 0;
        try {
            nYears = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number. Setting N to 0.");
            sc.nextLine();
        }
        
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        boolean found2 = false;
        printTableHead();
        for (Car c : cars) {
            // Рахуємо роки експлуатації: Поточний рік (2026) - Рік випуску
            if (c.getModel().equalsIgnoreCase(searchModel) && (currentYear - c.getYear()) > nYears) {
                System.out.println(c);
                found2 = true;
            }
        }
        if (!found2) {
            System.out.println("No '" + searchModel + "' cars older than " + nYears + " years found.");
        } else {
            printTableBorder();
        }

        // ЗАВДАННЯ 3: Автомобілі заданого року випуску, ціна яких більше заданої 
        System.out.print("\n--- 3. Search by Year and Minimum Price ---\nEnter year: ");
        int searchYear = sc.nextInt();
        System.out.print("Enter minimum price: ");
        double minPrice = sc.nextDouble();

        boolean found3 = false;
        printTableHead();
        for (Car c : cars) {
            if (c.getYear() == searchYear && c.getPrice() > minPrice) {
                System.out.println(c);
                found3 = true;
            }
        }
        if (!found3) {
            System.out.println("No cars from " + searchYear + " more expensive than " + minPrice + " found.");
        } else {
            printTableBorder();
        }
    }

    private static void printTableHead() {
        printTableBorder();
        System.out.println("| ID  | Brand        | Model        | Year  | Color      | Reg Num    | Price      |");
        printTableBorder();
    }

    private static void printTableBorder() {
        System.out.println("-".repeat(84));
    }
}
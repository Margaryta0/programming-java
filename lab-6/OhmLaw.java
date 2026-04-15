import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Lab work No.6
 * Variant 19: Create two lists of current (A) and voltage (V) measurements
 * (size >= 10), then calculate average resistance using Ohm's Law: R = U / I
 */
public class OhmLaw {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Lab work No.6 — Java Collections Framework ===");
        System.out.println("Ohm's Law: R = U / I  (Resistance = Voltage / Current)\n");

        // --- Input size ---
        int size = 0;
        while (size < 10) {
            System.out.print("Enter number of measurements (min 10): ");
            try {
                size = Integer.parseInt(sc.nextLine().trim());
                if (size < 10) {
                    System.out.println("[WARNING] Size must be at least 10. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid number. Try again.");
            }
        }

        // --- Two ArrayList collections ---
        List<Double> currentList  = new ArrayList<>();  // Amperes (I)
        List<Double> voltageList  = new ArrayList<>();  // Volts   (U)

        // --- Input current values ---
        System.out.println("\n-- Enter current values (Amperes) --");
        for (int i = 1; i <= size; i++) {
            while (true) {
                System.out.print("  I[" + i + "] = ");
                try {
                    double val = Double.parseDouble(sc.nextLine().trim());
                    if (val == 0) {
                        System.out.println("[WARNING] Current cannot be zero (division by zero). Try again.");
                    } else {
                        currentList.add(val);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Invalid number. Try again.");
                }
            }
        }
        System.out.println("[INFO] Current list filled: " + currentList.size() + " values.");

        // --- Input voltage values ---
        System.out.println("\n-- Enter voltage values (Volts) --");
        for (int i = 1; i <= size; i++) {
            while (true) {
                System.out.print("  U[" + i + "] = ");
                try {
                    double val = Double.parseDouble(sc.nextLine().trim());
                    voltageList.add(val);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Invalid number. Try again.");
                }
            }
        }
        System.out.println("[INFO] Voltage list filled: " + voltageList.size() + " values.");

        // --- Calculate resistance for each pair ---
        List<Double> resistanceList = new ArrayList<>();

        System.out.println("\n-- Calculating resistance R = U / I for each pair --");
        System.out.printf("  %-6s %-12s %-12s %-12s%n", "Pair", "I (A)", "U (V)", "R (Ohm)");
        System.out.println("  " + "-".repeat(44));

        for (int i = 0; i < size; i++) {
            double I = currentList.get(i);
            double U = voltageList.get(i);
            double R = U / I;
            resistanceList.add(R);
            System.out.printf("  %-6d %-12.4f %-12.4f %-12.4f%n", (i + 1), I, U, R);
        }

        // --- Calculate average resistance ---
        double sum = 0;
        for (double r : resistanceList) {
            sum += r;
        }
        double averageR = sum / resistanceList.size();

        // --- Additional stats using Collections ---
        double maxR = Collections.max(resistanceList);
        double minR = Collections.min(resistanceList);

        // --- Print results ---
        System.out.println("\n========== RESULTS ==========");
        System.out.printf("  Total pairs         : %d%n", size);
        System.out.printf("  Min resistance      : %.4f Ohm%n", minR);
        System.out.printf("  Max resistance      : %.4f Ohm%n", maxR);
        System.out.printf("  Average resistance  : %.4f Ohm%n", averageR);
        System.out.println("==============================");

        // --- Demonstrate collection operations ---
        System.out.println("\n-- Collection operations demo --");

        // Sort resistance list
        List<Double> sortedResistance = new ArrayList<>(resistanceList);
        Collections.sort(sortedResistance);
        System.out.println("[INFO] Resistance values sorted (ascending):");
        System.out.println("       " + sortedResistance);

        // Find index of max resistance in original list
        int maxIndex = resistanceList.indexOf(maxR);
        System.out.println("[INFO] Highest resistance R = " + maxR +
                " Ohm is at pair #" + (maxIndex + 1));

        // Remove the pair with highest resistance
        currentList.remove(maxIndex);
        voltageList.remove(maxIndex);
        resistanceList.remove(maxIndex);
        System.out.println("[INFO] Pair #" + (maxIndex + 1) +
                " (highest resistance) removed from all lists.");
        System.out.println("[INFO] Remaining pairs: " + resistanceList.size());

        // Recalculate average after removal
        double newSum = 0;
        for (double r : resistanceList) newSum += r;
        double newAvg = newSum / resistanceList.size();
        System.out.printf("[INFO] New average resistance after removal: %.4f Ohm%n", newAvg);

        System.out.println("\n[INFO] Program finished.");
        sc.close();
    }
}

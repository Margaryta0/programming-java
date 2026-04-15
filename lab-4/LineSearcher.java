import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Lab work No.4
 * Variant 19: Find and write to another file the line numbers
 * that start with a given substring.
 */
public class LineSearcher {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("=== Lab work No.4 ===");
        System.out.println("Search for lines starting with a given substring\n");

        // Input path to the source file
        System.out.print("Enter path to input file: ");
        String inputPath = console.nextLine().trim();

        // Input the search substring
        System.out.print("Enter substring to search: ");
        String substring = console.nextLine();

        // Input path to the output file
        System.out.print("Enter path to output file: ");
        String outputPath = console.nextLine().trim();

        System.out.println("\n--- Processing started ---");

        // Check if input file exists
        File inputFile = new File(inputPath);
        if (!inputFile.exists()) {
            System.out.println("[ERROR] File not found: " + inputPath);
            console.close();
            return;
        }
        if (!inputFile.isFile()) {
            System.out.println("[ERROR] The given path is not a file: " + inputPath);
            console.close();
            return;
        }

        System.out.println("[INFO] Opened input file: " + inputFile.getAbsolutePath());
        System.out.println("[INFO] Search substring: \"" + substring + "\"");

        List<Integer> matchingLines = new ArrayList<>();
        int totalLines = 0;

        // Read input file and search for matching lines
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println("[READ] Line " + lineNumber + ": " + line);

                if (line.startsWith(substring)) {
                    matchingLines.add(lineNumber);
                    System.out.println("       ^^^ Line " + lineNumber +
                            " STARTS WITH \"" + substring + "\" -- added to results");
                }
            }
            totalLines = lineNumber;

        } catch (IOException e) {
            System.out.println("[ERROR] Could not read file: " + e.getMessage());
            console.close();
            return;
        }

        System.out.println("\n[INFO] Total lines read: " + totalLines);
        System.out.println("[INFO] Matching lines found: " + matchingLines.size());

        // Write results to output file
        File outputFile = new File(outputPath);

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {

            writer.write("Search results for substring: \"" + substring + "\"");
            writer.newLine();
            writer.write("Input file: " + inputFile.getAbsolutePath());
            writer.newLine();
            writer.write("Total lines in file: " + totalLines);
            writer.newLine();
            writer.write("Matching lines found: " + matchingLines.size());
            writer.newLine();
            writer.write("----------------------------------------");
            writer.newLine();

            if (matchingLines.isEmpty()) {
                writer.write("No lines starting with the given substring were found.");
                writer.newLine();
                System.out.println("\n[RESULT] No matching lines found.");
            } else {
                writer.write("Line numbers:");
                writer.newLine();
                for (int num : matchingLines) {
                    writer.write("  " + num);
                    writer.newLine();
                }
                System.out.println("\n[RESULT] Matching line numbers: " + matchingLines);
            }

            System.out.println("[INFO] Results written to: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("[ERROR] Could not write results: " + e.getMessage());
            console.close();
            return;
        }

        System.out.println("--- Processing complete ---");
        console.close();
    }
}

import java.util.Random;

public class TextDataProcessing {
    public static void main(String[] args) {
        System.out.println("Developer: Smal Margarita");
        System.out.println("----------------------------------------------------------------");

        Random random = new Random();
        int attempts = 10;
        String allGenerated = "";
        String matchedNumbers = "";

        for (int i = 0; i < attempts; i++) {
            int number = random.nextInt(90000) + 10000;
            String numberStr = String.valueOf(number);

            allGenerated += numberStr + " ";

            if (hasExactlyThreeIdenticalDigits(numberStr)) {
                matchedNumbers += numberStr + " ";
            }
        }

        System.out.println("All generated numbers:");
        System.out.println(allGenerated);

        System.out.println("\nNumbers with exactly three identical digits:");
        System.out.println(matchedNumbers.isEmpty() ? "No matches found." : matchedNumbers);
        
        System.out.println("----------------------------------------------------------------");
    }

    public static boolean hasExactlyThreeIdenticalDigits(String str) {
        for (char c = '0'; c <= '9'; c++) {
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == c) {
                    count++;
                }
            }
            if (count == 3) return true;
        }
        return false;
    }
}

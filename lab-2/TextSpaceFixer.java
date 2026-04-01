
public class TextSpaceFixer {
    public static void main(String[] args) {
        System.out.println("Developer: Smal M.");
        System.out.println("Assignment: Remove extra spaces (leave only one between words)");
        System.out.println("----------------------------------------------------------------");

        String originalText = "Java    is  a   powerful      language.\n" +
                              "It   has many    libraries    for   text   processing.\n" +
                              "Let's    fix    the   spacing    here!";

        System.out.println("1) ORIGINAL TEXT:");
        System.out.println(originalText);
        System.out.println("----------------------------------------------------------------");


        String processedText = originalText.trim().replaceAll("\\s+", " ");

        System.out.println("2) PROCESSED TEXT:");
        System.out.println(processedText);
        
        System.out.println("----------------------------------------------------------------");
    }
}

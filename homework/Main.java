package homework;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start program...");

        startDelayedTask("1", 1000); 
        startDelayedTask("2", 2000); 
        startDelayedTask("3", 3000); 
    }

    private static void startDelayedTask(String message, int delayMillis) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(delayMillis);
                System.out.println(message);
            } catch (InterruptedException e) {
                System.out.println("Потік було перервано!");
                Thread.currentThread().interrupt();
            }
        });
        
        thread.start();
    }
}
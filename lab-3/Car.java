public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private String regNumber;
    private double price;

    public Car(int id, String brand, String model, int year, String color, String regNumber, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.regNumber = regNumber;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("| %-3d | %-12s | %-12s | %-5d | %-10s | %-10s | %-10.2f |", 
                id, brand, model, year, color, regNumber, price);
    }
}

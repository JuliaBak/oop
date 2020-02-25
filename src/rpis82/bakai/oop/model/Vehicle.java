package rpis82.bakai.oop.model;

public class Vehicle {
    int gosNumber;
    String producer, model;

    public Vehicle()
    {

    }
    public Vehicle(int gosNumber, String producer, String model)
    {
        this.gosNumber = gosNumber;
        this.producer = producer;
        this.model = model;
    }

    public int getGosNumber() {
        return gosNumber;
    }

    public void setGosNumber(int gosNumber) {
        this.gosNumber = gosNumber;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public static void main(String[] args) {

        Vehicle vehicle1 = new Vehicle(432, "Toyota", "Corolla");
        Vehicle vehicle2 = new Vehicle(574, "BMW", "X6");
        Vehicle vehicle3 = new Vehicle(762, "Ferrari", "California");

        System.out.println(vehicle1.gosNumber);
        System.out.println(vehicle1.model);
        System.out.println(vehicle1.producer);
    }
}

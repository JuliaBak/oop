package rpis82.bakai.oop.model;

public class Vehicle {
    String   registrationNumber, manufacturer, model;

    public Vehicle()
    {
        this.model = ""; //по умолчанпию - пустые строки
        this.registrationNumber = "";
        this.manufacturer = "";
    }
    public Vehicle(String registrationNumber, String manufacturer, String model)
    {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String  getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
     }
    public static void main(String[] args) {

        Vehicle vehicle1 = new Vehicle("432", "Toyota", "Corolla");
        Vehicle vehicle2 = new Vehicle("574", "BMW", "X6");
        Vehicle vehicle3 = new Vehicle("762", "Ferrari", "California");

        System.out.println(vehicle1.registrationNumber);
        System.out.println(vehicle1.model);
        System.out.println(vehicle1.manufacturer);

       System.out.println(vehicle2.getRegistrationNumber());
       System.out.println(vehicle3.getManufacturer());
        vehicle3.setModel("Toyota");
        System.out.println(vehicle3.model);
    }
}
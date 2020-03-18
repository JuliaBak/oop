package rpis82.bakai.oop.model;

public class Vehicle {
    String   registrationNumber, manufacturer, model;
   public static Vehicle EMPTY_VEHICLE = new Vehicle(" ", " ", "");

    public Vehicle()
    {
        this.model = Vehicle.EMPTY_VEHICLE.model;
        this.registrationNumber = EMPTY_VEHICLE.registrationNumber;
        this.manufacturer = EMPTY_VEHICLE.manufacturer;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }


}



package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.VehiclesTypes;

import java.util.Objects;

public final class Vehicle {

    public static final Vehicle EMPTY_VEHICLE = new Vehicle(" ", " ", " ", VehiclesTypes.NONE);

    private final String registrationNumber, manufacturer, model;
    private final VehiclesTypes type;


    public Vehicle() {
        this.manufacturer = EMPTY_VEHICLE.manufacturer;
        this.model = EMPTY_VEHICLE.model;
        this.registrationNumber = EMPTY_VEHICLE.registrationNumber;
        this.type = EMPTY_VEHICLE.type;//added in Lab3
    }

    public Vehicle(String registrationNumber, String manufacturer, String model, VehiclesTypes vehicleType) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = vehicleType; //added in LAB3
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public VehiclesTypes getType() {
        return type;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", vehicleType=" + type +
                '}';
    }

}




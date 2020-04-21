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

    //Lab4
    @Override
    public String toString() {
        if (type == VehiclesTypes.NONE) {
            return "NONE";
        } else {
            return String.format("<%s> <%s> (<%s>) regNumber: <%s>",
                    this.manufacturer,
                    this.model,
                    this.type,
                    this.registrationNumber);
        }
    }

    @Override ///*вычисляет хэш-код как произведение хэш-кодов всех атрибутов класса */
    public int hashCode() {
        return Objects.hash(manufacturer, model, registrationNumber, type);
    }


}




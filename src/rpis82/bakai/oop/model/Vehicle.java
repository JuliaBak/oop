package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.VehiclesTypes;
public final class Vehicle { //made Vehicle final? added type

    public static Vehicle EMPTY_VEHICLE = new Vehicle();

    private final String registrationNumber, manufacturer, model;
    private final VehiclesTypes type;

    public Vehicle() {
        this("", "", "", VehiclesTypes.NONE);
    }

    public Vehicle(String registrationNumber, String manufacturer, String model, VehiclesTypes vehicleType) {
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = vehicleType;
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




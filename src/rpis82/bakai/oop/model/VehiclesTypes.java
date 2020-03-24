package rpis82.bakai.oop.model;

public enum VehiclesTypes {
    NONE("NONE"),
    CAR("CAR"),
    CROSSOVER("CROSSOVER"),
    MOTOR_BIKE("MOTOR_BIKE"),
    SUV("SUV"),
    TRUCK("TRUCK"),
    OTHER("OTHER");

    private String vehicleType;

    VehiclesTypes(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public String toString() {
        return "Vehicle Type: " + this.vehicleType;
    }
}
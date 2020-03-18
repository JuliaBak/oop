package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.Space;

public class OwnedSpace implements Space {
    private Person person;
    private Vehicle vehicle;

    public OwnedSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean isEmpty() {
        return this.vehicle == null || this.vehicle.getRegistrationNumber().equals("");
    }
}

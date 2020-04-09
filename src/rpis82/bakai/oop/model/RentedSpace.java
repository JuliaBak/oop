package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;

public class RentedSpace extends AbstractSpace implements Space {

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person,vehicle);
    }

    public RentedSpace() {
        super();
    }
}
/* RentedSpace Class Lab2 //lab 1 - Space Class
package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;

public class RentedSpace implements Space {

    private Person person;
    private Vehicle vehicle;

    public RentedSpace(Person person, Vehicle vehicle) {
        this.person = person;
        this.vehicle = vehicle;
    }

    public RentedSpace() {
        this(Person.EMPTY_PERSON, new Vehicle());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isEmpty() {
        return this.vehicle == null || this.vehicle.getRegistrationNumber().isEmpty();
    }

    @Override
    public String toString() {
        return "Space{" +
                "person=" + person +
                ", vehicle=" + vehicle +
                '}';
    }
}

 */
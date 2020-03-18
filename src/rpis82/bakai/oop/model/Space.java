package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.Person;
import rpis82.bakai.oop.model.Vehicle;
public interface Space {
    Person getPerson();

    void setPerson(Person person);
    Vehicle getVehicle();

    void setVehicle(Vehicle vehicle);
    boolean isEmpty();
}

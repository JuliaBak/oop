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

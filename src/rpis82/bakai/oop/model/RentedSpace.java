package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;
import java.lang.Cloneable;

public class RentedSpace extends AbstractSpace implements Space, Cloneable{

    public RentedSpace(Person person, Vehicle vehicle) {
        super(person,vehicle);
    }

    public RentedSpace() {
        super();
    }

    //Lab4
    @Override //переопределение класса toString, по формату, lab4
    public String toString() {
        return String.format("Tenant: <%s> TC: <%s>", getPerson().toString(), getVehicle().toString());    }

    @Override
    public int hashCode() {
        return 53*super.hashCode();
    }

    public RentedSpace clone() throws CloneNotSupportedException{
        RentedSpace clone = (RentedSpace) super.clone();
        Person clonedPerson = this.getPerson().clone();
        Vehicle clonedVehicle = this.getVehicle().clone();
        clone.setPerson(clonedPerson);
        clone.setVehicle(clonedVehicle);
        return clone;
    }
}

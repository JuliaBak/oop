package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Space;

import java.time.LocalDate;

public class OwnedSpace extends AbstractSpace implements Space, Cloneable{

    public OwnedSpace(Person person, Vehicle vehicle) {
        super(person, vehicle, LocalDate.now());
    }

    public OwnedSpace(Person person){
        super(person, LocalDate.now());
    }

    public OwnedSpace(){
        super();
    }

    //Lab4
    @Override
    public String toString() {
        return String.format("Owner: <%s> TC: <%s>",getPerson().toString(),getVehicle().toString());
    }

    @Override
    public int hashCode() {
        return 71 * super.hashCode();
    }

    public OwnedSpace clone() throws CloneNotSupportedException{

        OwnedSpace clone = (OwnedSpace) super.clone();
        Person clonedPerson = this.getPerson().clone();
        Vehicle clonedVehicle = this.getVehicle().clone();
        clone.setPerson(clonedPerson);
        clone.setVehicle(clonedVehicle);

        return clone;
    }
}

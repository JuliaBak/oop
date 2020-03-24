package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Space;

public class OwnedSpace extends AbstractSpace {

    public OwnedSpace(Person person, Vehicle vehicle) {
        super(person,vehicle);
    }

    public OwnedSpace(Person person){
        super(person);
    }

    public OwnedSpace(){
        super();
    }
}

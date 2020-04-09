package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Space;

public class OwnedSpace extends AbstractSpace implements Space{
//вызываются конструктора суперкласса
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
/* OwnedSpace Class Lab2

package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Space;

public class OwnedSpace implements Space { // реализует интерфейс Space
    private Person person; //с содержит ссылки на владельца (Person) и ТС (Vehicle)
    private Vehicle vehicle;

    public OwnedSpace(Person person, Vehicle vehicle) {//конструктор, принимающий два параметра – ссылки на ТС и владельца
        this.person = person;
        this.vehicle = vehicle;
    }

    @Override
    public Person getPerson() {
        return person;
    }//возвращающий ссылку на владельца

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }//устанавливающий новую ссылку на владельца

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }//возвращающий ссылку на ТС

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }//устанавливающий новую ссылку на ТС

    @Override//определяющий является ли это место пустым
    public boolean isEmpty() {
        return this.vehicle == null || this.vehicle.getRegistrationNumber().isEmpty();
    }
}

 */
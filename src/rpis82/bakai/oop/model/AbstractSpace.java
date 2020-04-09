package rpis82.bakai.oop.model;
import  rpis82.bakai.oop.model.interfaces.Space;

public abstract class AbstractSpace implements Space { //реализует интерфейс Space
    private Person person;
    private Vehicle vehicle;


    protected AbstractSpace(){
        this(Person.EMPTY_PERSON,Vehicle.EMPTY_VEHICLE);
    }
    //Защищенный (protected) конструктор
    protected AbstractSpace(Person person,Vehicle vehicle){
        this.person = person;
        this.vehicle = vehicle;
    }

//принимающий один параметр – ссылку на Person. ТС инициализируется как
//Vehicle.EMPTY_VEHICLE
    protected AbstractSpace(Person person){
        this(person,Vehicle.EMPTY_VEHICLE);
    }

    //реализуует методы класса Space
    @Override
    public Person getPerson() {
        return this.person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override

    //проверять, является ли ссылка на объект типа Vehicle равной ссылке
    //Vehicle.EMPTY_VEHICLE или тип ТС равен VehiclesTypes.NONE.
    public boolean isEmpty() {
        return (this.vehicle == Vehicle.EMPTY_VEHICLE) || (this.vehicle.getType() == VehiclesTypes.NONE);
    }

}

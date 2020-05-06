package rpis82.bakai.oop.model;
import  rpis82.bakai.oop.model.interfaces.Space;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class AbstractSpace implements Space, Cloneable { //реализует интерфейс Space
    private Person person;
    private Vehicle vehicle;
    private LocalDate rentalStartDate;


    protected AbstractSpace(){
        this(Person.EMPTY_PERSON, Vehicle.EMPTY_VEHICLE, LocalDate.now());
    }

    protected AbstractSpace(Person person, LocalDate rentalStartDate){
        this.person = person;
        this.vehicle = Vehicle.EMPTY_VEHICLE;
        this.rentalStartDate = isDateAcceptable(rentalStartDate);
    }

    protected AbstractSpace(Person person,Vehicle vehicle, LocalDate rentalStartDate){
        this.person = person;
        this.vehicle = vehicle;
        this.rentalStartDate = isDateAcceptable(rentalStartDate);
    }
    private LocalDate isDateAcceptable(LocalDate rentalStartDate) {
        Objects.requireNonNull(rentalStartDate, "Rental Start Date is null");
        if (rentalStartDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Rental Start Date cannot be after now");
        }
        return rentalStartDate;
    }

    //реализуует методы класса Space
    @Override
    public Person getPerson(){
        return this.person;
    }

    @Override
    public void setPerson(Person person)  throws NullPointerException {
        this.person = Objects.requireNonNull(person, "Person is null");
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public void setVehicle(Vehicle vehicle) throws  NullPointerException {
        this.vehicle = Objects.requireNonNull(vehicle, "Vehicle is null");
    }

    @Override

    //проверять, является ли ссылка на объект типа Vehicle равной ссылке
    //Vehicle.EMPTY_VEHICLE или тип ТС равен VehiclesTypes.NONE.
    public boolean isEmpty() {
        return (this.vehicle == Vehicle.EMPTY_VEHICLE) || (this.vehicle.getType() == VehiclesTypes.NONE);
    }

    //Lab4
    @Override
    public String toString()
    {
        return String.format("<%s> TC: <%s>, Date <%s>", this.person.toString(), this.vehicle.toString(), this.rentalStartDate);
    }

    @Override
    public int hashCode()
    {

        return person.hashCode() & vehicle.hashCode()  & rentalStartDate.hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractSpace abstractObj = (AbstractSpace) obj;
        return
                this.person.equals(abstractObj.person) &&
                        this.vehicle.equals(abstractObj.vehicle) &&
                        this.rentalStartDate.equals(abstractObj.rentalStartDate);
    }

    @Override
    public AbstractSpace clone() throws CloneNotSupportedException {
        AbstractSpace clone = (AbstractSpace) super.clone();
        Person clonedPerson = this.getPerson().clone();
        Vehicle clonedVehicle = this.getVehicle().clone();
        clone.setPerson(clonedPerson);
        clone.setVehicle(clonedVehicle);
        return clone;
    }


    //Lab 5
    @Override
    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    @Override
    public void setRentalStartDate(LocalDate rentalStartDate) throws  NullPointerException{
        this.rentalStartDate = Objects.requireNonNull(rentalStartDate, "Rental Start Date is null");
    }

    public Period getRentalPeriod() {
        return Period.between(this.rentalStartDate, LocalDate.now());
    }
}

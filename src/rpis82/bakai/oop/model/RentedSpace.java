package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;
import java.lang.Cloneable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class RentedSpace extends AbstractSpace implements Space, Cloneable{

    private LocalDate rentalEndDate;

    //changed to Lab5
    public RentedSpace()
    {
        this(Person.EMPTY_PERSON, Vehicle.EMPTY_VEHICLE, LocalDate.now(), LocalDate.now().plusDays(1));
    }

    public RentedSpace(Person person, LocalDate rentalStartDate, LocalDate rentalEndDate ) {
        this(    Objects.requireNonNull(person, "Person is null"),
                Vehicle.EMPTY_VEHICLE,
                Objects.requireNonNull(rentalStartDate, "Rental Start Date is null"),
                Objects.requireNonNull(rentalEndDate, "Rental End Date is null"));
    }

    public RentedSpace(Person person, Vehicle vehicle,  LocalDate rentalStartDate, LocalDate rentalEndDate ) {
        super(  Objects.requireNonNull(person, "Person is null"),
                Objects.requireNonNull(vehicle, "Vehicle is null"),
                Objects.requireNonNull(rentalStartDate, "Rental Start Date is null"));
        this.rentalEndDate = Objects.requireNonNull(rentalEndDate, " Rental End Date is mull");;
    }


    //Lab4
    @Override //переопределение класса toString, по формату, lab4
    public String toString() {
        return String.format("Tenant: <%s> TC: <%s> Rental Start Date: <%s> Rental End Date: <%s>" , getPerson().toString(), getVehicle().toString(), getRentalStartDate().toString(), getRentalEndDate().toString());    }

    @Override
    public int hashCode() {
        return 53 * super.hashCode();
    }

    public RentedSpace clone() throws CloneNotSupportedException{
        RentedSpace clone = (RentedSpace) super.clone();
        Person clonedPerson = this.getPerson().clone();
        Vehicle clonedVehicle = this.getVehicle().clone();
        clone.setPerson(clonedPerson);
        clone.setVehicle(clonedVehicle);
        return clone;
    }

    //Lab 5
    //возвращающий дату конца аренды парковочного места
    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    //изменяющий дату конца аренды парковочного места
    public void setRentEndDate(LocalDate rentalEndDate) throws NullPointerException
    {
        this.rentalEndDate = Objects.requireNonNull(rentalEndDate, "Rent End Date is null");
    }

    //возвращающий период (java.time.Period) времени соответствующий отрезку времени
    //начиная с текущего дня и заканчивая датой конца аренды парковочного места

    Period getPeriodBeforeEnd() {
        return Period.between(LocalDate.now(), rentalEndDate);
    }
}

package rpis82.bakai.oop.model.interfaces;
import rpis82.bakai.oop.model.Person;
import rpis82.bakai.oop.model.Vehicle;

import java.time.LocalDate;
import java.time.Period;

public interface Space {
    Person getPerson(); //возвращающий ссылку на владельца

    void setPerson(Person person);//устанавливающий новую ссылку на владельца

    Vehicle getVehicle();//возвращающий ссылку на ТС

    void setVehicle(Vehicle vehicle);//устанавливающий новую ссылку на ТС

    boolean isEmpty();//определяющий является ли это место пустым

    String toString();

    int hashCode();

    boolean equals(Object obj);

    //Lab 5
    //Возвращающий дату начала аренды или владения парковочным местом
    LocalDate getRentalStartDate(); //rental start date

    //Изменяющий дату начала аренды или владения парковочным местом
    void setRentalStartDate(LocalDate localDate) throws NullPointerException;

    //Возвращающий период аренды или владения парковочным местом (с даты начала аренды
    //или владения до текущей даты)
    Period getRentalPeriod();


}

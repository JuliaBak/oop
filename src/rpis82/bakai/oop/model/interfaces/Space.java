package rpis82.bakai.oop.model.interfaces;
import rpis82.bakai.oop.model.Person;
import rpis82.bakai.oop.model.Vehicle;
public interface Space {
    Person getPerson(); //возвращающий ссылку на владельца

    void setPerson(Person person);//устанавливающий новую ссылку на владельца

    Vehicle getVehicle();//возвращающий ссылку на ТС

    void setVehicle(Vehicle vehicle);//устанавливающий новую ссылку на ТС

    boolean isEmpty();//определяющий является ли это место пустым

    String toString();

    int hashCode();

    boolean equals(Object obj);

}

package rpis82.bakai.oop.model.interfaces;
import rpis82.bakai.oop.model.NoRentedSpaceException;
import rpis82.bakai.oop.model.Person;
import  rpis82.bakai.oop.model.Vehicle;
import rpis82.bakai.oop.model.VehiclesTypes;

import java.time.LocalDate;

public interface Floor {

    boolean addSpace(Space space); //метод добавляющий парковочное место

    boolean addSpaceByIndex(int index, Space space); //добавляющий парковочное место в заданное место

    Space getSpaceByIndex(int index);//возвращающий ссылку на экземпляр класса Space по его номеру в массиве

    Space getSpaceByRegNumber(String registrationNumber);
    //возвращающий ссылку на экземпляр класса Space, с которым связанно транспортное
//средство с определенным гос. номером
    boolean hasSpaceByRegNumber(String registrationNumber);//определяющий, есть ли на этаже парковочное место, связанное с транспортным средством
    // с определенным гос. номером

    Space setSpaceByIndex(int index, Space space); //изменяющий ссылку на экземпляр класса Space по его номеру в массиве

    Space removeByIndex(int index);//удаляющий парковочное место из массива по его номеру

    Space removeByRegNumber(String registrationNumber);//удаляющий парковочное место из массива по гос. номеру автомобиля

    int getCapacity();//возвращающий общее число парковочных мест на этаже

    Space[] getSpaces();//возвращающий массив парковочных мест на этаже

    Vehicle[] getVehicles();// возвращающий массив транспортных средств на этаже

    int getVehiclesNumber(); //возвращающий кол-во транспортных средств на этаже

    void shift();

    //Lab 3
    Space[] getEmptySpaces(); //возвращающий массив не занятых парковочных мест

    Space[] getSpacesByVehicleType(VehiclesTypes vehicleTypes); //возвращающий массив парковочных мест с ТС заданного типа

    //Lab 4
    String toString();

    int hashCode();

    boolean equals(Object o);

    //удаляющий парковочное место из списка
    boolean isSpaceRemoved(Space space);

    //возвращающий индекс первого вхождения объекта Space
    int indexOfSpace(Space space);

    // возвращающий число парковочных мест, связанных с заданным человеком
    int spacesNumberByPerson(Person person);

    //Lab 5
    //возвращающий ближайшую дату конца аренды парковочного места
    LocalDate getClosestRentalEndDate() throws NoRentedSpaceException;

    //возвращающий ссылку на парковочное место с ближайшей датой конца аренды парковочного места
    Space getSpaceByClosestRentalEndDate() throws NoRentedSpaceException;

}


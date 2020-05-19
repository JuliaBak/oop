package rpis82.bakai.oop.model.interfaces;
import rpis82.bakai.oop.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Floor  extends Comparable<Floor>, Iterable<Space>, Collection<Space> {


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


    int getVehiclesNumber(); //возвращающий кол-во транспортных средств на этаже

    void shift();

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

    //lab6
    default  boolean isRegNumberFormatOK(String registrationNumber){

        Pattern pattern = Pattern.compile("[ABEKMHOPCTYX][0-9]{3}[ABEKMHOPCTYX]{2}[0-9]{2,3}");
        Matcher matcherReg = pattern.matcher(registrationNumber);
        return matcherReg.matches();
    }

    //Changed to Lab7
    Collection<Vehicle> getVehicles();// возвращающий массив транспортных средств на этаже

    //Lab 3, Changed to Lab7
    Deque<Space> getEmptySpaces(); //возвращающий массив не занятых парковочных мест

    //Changed to Lab7
    List<Space>  getSpacesByVehicleType(VehiclesTypes vehicleTypes); //возвращающий массив парковочных мест с ТС заданного типа


}


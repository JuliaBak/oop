package rpis82.bakai.oop.model.interfaces;
import  rpis82.bakai.oop.model.Vehicle;
import rpis82.bakai.oop.model.VehiclesTypes;

public interface Floor {

    boolean add(Space space); //метод добавляющий парковочное место

    boolean add(int index,Space space); //добавляющий парковочное место в заданное место

    Space get(int index);//возвращающий ссылку на экземпляр класса Space по его номеру в массиве

    Space get(String registrationNumber);
//возвращающий ссылку на экземпляр класса Space, с которым связанно транспортное
//средство с определенным гос. номером
    boolean hasSpace(String registrationNumber);//определяющий, есть ли на этаже парковочное место, связанное с транспортным средством
   // с определенным гос. номером

    Space set(int index,Space space); //изменяющий ссылку на экземпляр класса Space по его номеру в массиве

    Space remove(int index);//удаляющий парковочное место из массива по его номеру

    Space remove(String registrationNumber);//удаляющий парковочное место из массива по гос. номеру автомобиля

    int getCapacity();//возвращающий общее число парковочных мест на этаже

    Space[] getSpaces();//возвращающий массив парковочных мест на этаже

    Vehicle[] getVehicles();// возвращающий массив транспортных средств на этаже

    int getVehicleAmount(); //возвращающий кол-во транспортных средств на этаже

    void moveArray();

    Space[] getSpaces(VehiclesTypes vehicleTypes); //возвращающий массив парковочных мест с ТС заданного типа

    Space[] getEmptySpaces(); //возвращающий массив не занятых парковочных мест
}

/* Lab 2
package rpis82.bakai.oop.model.interfaces;
import  rpis82.bakai.oop.model.Vehicle;
public interface Floor {//публичный интерфейс Floor – этаж парковки

    boolean add(Space space);//добавляющий парковочное место  (принимает ссылку на экземпляр класса
   // Space

    boolean add(int index,Space space);//добавляющий парковочное место в заданное место

    Space get(int index);//возвращающий ссылку на экземпляр класса Space по его номеру в массиве

    Space get(String registrationNumber);//возвращающий ссылку на экземпляр класса Space, с которым связанно транспортное
   // средство с определенным гос. номером.

    boolean hasSpace(String registrationNumber);//определяющий, есть ли на этаже парковочное место, связанное с транспортным средством
  //  с определенным гос. номером

    Space set(int index,Space space);//изменяющий ссылку на экземпляр класса Space по его номеру в массиве

    Space remove(int index);//удаляющий парковочное место из массива по его номеру

    Space remove(String registrationNumber);//удаляющий парковочное место из массива по гос. номеру автомобиля

    int getSpacesAmount();// возвращающий общее число парковочных мест на этаже

    Space[] getSpaces();//возвращающий массив парковочных мест на этаже

    Vehicle[] getVehicles();//возвращающий массив транспортных средств на этаже

    int getVehicleAmount();//возвращающий кол-во транспортных средств на этаже

    void moveArray();
}

 */
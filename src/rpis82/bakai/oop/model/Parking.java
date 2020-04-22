package rpis82.bakai.oop.model;
 import rpis82.bakai.oop.model.interfaces.Space;
 import rpis82.bakai.oop.model.interfaces.Floor;


 import java.util.Arrays;
 import java.util.Objects;

public class Parking{

    private Floor[] floors;
    private int capacity;

    public Parking(int floorsNumber) {
        this.floors = new Floor[floorsNumber];
    }


    public Parking(Floor[] floors) {
        this.floors = new Floor[floors.length];
        System.arraycopy(floors, 0, this.floors, 0 , floors.length);
        this.capacity = this.floors.length;
    }

    public boolean addLastFloor(Floor floor) {
        if (isEnough()) {
            this.floors[this.capacity] = floor;
        } else {
            int lastElement = this.capacity;
            increaseArraySize();
            this.floors[lastElement] = floor;
        }
        this.capacity++;
        return true;
    }

    private boolean isEnough()
    {
       return  (this.capacity < this.floors.length && this.floors[this.capacity] == null);
    }

    public boolean addFloorByIndex(int index, Floor floor) {
        if (this.floors.length > index && index > 0) {
            shiftArray(index);
            this.floors[index] = floor;
            this.capacity++;
            return true;
        }
        return false;
    }

    private void shiftArray(int index) {
        if (this.capacity + 1 >= this.floors.length){
            increaseArraySize();
        }
        for (int i = this.capacity; i > index; i--)
        {
            Floor floor = this.floors[i];
            this.floors[i] = this.floors[i-1];
            this.floors[i-1] = floor;
        }
    }

    private void increaseArraySize() {
        Floor[] doubleFloors = new Floor[this.floors.length * 2];
        int amount = 0;
        for (int index = 0; index < floors.length; index++) {
            if (floors[index] != null) {
                doubleFloors[amount++] = floors[index];

            }
        }
        this.floors = doubleFloors;
        this.capacity = amount;
    }

    //возвращающий ссылку на экземпляр класса по его номеру в массиве
    public Floor get(int index) {
        return this.floors[index];
    }

    //изменяющий ссылку на экземпляр класса  по его номеру в массиве.
    //Принимает в качестве параметров номер и ссылку на экземпляр класса OwnersFloor
    public Floor setFloorByIndex(int index, Floor floor) {
        Floor changedFloor = this.floors[index];
        this.floors[index] = floor;
        return changedFloor;
    }

    //удаляющий этаж из массива по его номеру
    public Floor removeFloorByIndex(int index) {
        Floor removedFloor = this.floors[index];
        this.floors[index] = null;
        moveArray();
        return removedFloor;
    }

    private void moveArray() { //тестануть
        Arrays.stream(floors).filter(Objects::nonNull).toArray();
    }

    //возвращающий число этажей
    public int getCapacity() {
        return capacity;
    }

    //возвращающий массив этажей
    public Floor[] getFloors() {
        return floors;
    }


    public Floor[] getSortedByFloorsSize() {
        Floor[] sortedFloors = this.floors;
        for (int i = 0; i < sortedFloors.length - 1; i++) {
            for (int j = 0; j < i; j++) {
                if (sortedFloors[j].getCapacity() > sortedFloors[j + 1].getCapacity()) {
                    Floor ownersFloor = sortedFloors[j];
                    sortedFloors[j] = sortedFloors[j + 1];
                    sortedFloors[j + 1] = ownersFloor;
                }
            }
        }
        return sortedFloors;
    }


    //возвращающий массив всех, связанных с парковочными местами, транспортных средств
    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[getVehicleAmount()];
        int number = 0;
        for (int index = 0; index < floors.length; index++) {
            Space[] spaces = floors[index].getSpaces();
            for (int i = 0; i < spaces.length; i++) {
                if (isEmptySpace(spaces[i])) {
                    vehicles[number++] = spaces[i].getVehicle();
                }
            }
        }
        return vehicles;
    }

    public int getVehicleAmount() {
        int amount = 0;
        for (int i = 0; i < floors.length - 1; i++) {
            amount += floors[i].getVehiclesNumber();
        }
        return amount;
    }



    private boolean isEmptySpace(Space space)
    {
        return  (space != null) && (!space.isEmpty());
    }

    //возвращающий ссылку на экземпляр класса Space, с которым связанно транспортное
    //средство с определенным гос. номером. В качестве параметра принимает строку – гос. номер.
    public Space getSpaceByRegNumber(String registrationNumber) {
        for (int index = 0; index < floors.length; index++) {
            Space[] spaces = floors[index].getSpaces();
                  for (int i = 0; i < spaces.length; i++) {
                     if (equalsTo(spaces[i], registrationNumber))
                     {
                         return spaces[i];
                     }
            }
        }
        return null;
    }

    private boolean equalsTo(Space space,String registrationNumber)
    {
      return   space.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    //удаляющий парковочное место, с которым связанно транспортное средство с
    //определенным гос. номером.
    public Space removeSpaceByRegNumber(String registrationNumber) {
        for (int index = 0; index < floors.length - 1; index++) {
            Space[] spaces = floors[index].getSpaces();
            for (int i = 0; i < spaces.length; i++) {
                if (equalsTo(spaces[i], registrationNumber)) {
                    Space removedSpace = spaces[i];
                    floors[index].setSpaceByIndex(i, null);
                    floors[index].shift();
                    return removedSpace;
                }
            }
        }
        return null;
    }


    //изменяющий ссылку на экземпляр класса Space с которым связанно транспортное средство
    //с определенным гос. номером.
    public Space setSpaceByRegNumber(Space space, String registrationNumber) {
        Space removedSpace;
        Space[] spaces;
        for (int index = 0; index < floors.length - 1; index++) {
            spaces = floors[index].getSpaces();
            for (int i = 0; i < spaces.length; i++) {
                if (equalsTo(spaces[i], registrationNumber)) {
                    removedSpace = spaces[i];
                    floors[index].setSpaceByIndex(i, space);
                    return removedSpace;
                }
            }
        }
        return null;
    }


    //LAB 3
    //возвращающий общее число не занятых парковочных мест
    public int getEmptySpacesAmount(){
        int amount = 0;
        for (int index = 0; index < floors.length - 1; index++){
            amount += floors[index].getEmptySpaces().length;
        }
        return amount;
    }

    //возвращающий общее число ТС заданного типа
    public int getVehiclesAmountByType(VehiclesTypes type){
        int amount = 0;
        for (int index = 0; index < floors.length - 1; index++)
        {
            amount += floors[index].getSpacesByVehicleType(type).length;
        }
        return amount;
    }


    @Override
    public String toString() {
        return "Parking{" +
                "floors=" + Arrays.toString(floors) +
                ", capacity=" + capacity +
                '}';
    }
}

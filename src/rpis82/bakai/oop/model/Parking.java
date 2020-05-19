package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Space;
import rpis82.bakai.oop.model.interfaces.Floor;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class Parking implements Iterable<Floor> {

    private Floor[] floors;
    private int capacity;

    //принимающий один параметр – число этажей, инициализирующий массив
    //соответствующим числом элементов
    public Parking(int floorsNumber) {
        this.floors = new Floor[floorsNumber];
    }

    //В этом конструкторе происходит копирование элементов в новый массив, и ссылка на него записывается в атрибут
    public Parking(Floor[] floors) {
        this.floors = new Floor[floors.length];
        System.arraycopy(floors, 0, this.floors, 0 , floors.length);
        this.capacity = this.floors.length;
    }


    //добавляющий этаж в конец массива
    public boolean addLastFloor(Floor floor) throws NullPointerException {

        Objects.requireNonNull(floor, "Floor is null");

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

    //добавляющий этаж в заданное место
    public boolean addFloorByIndex(int index, Floor floor) throws IndexOutOfBoundsException, NullPointerException {

        if(index >=  this.capacity |  index < 0 )
            throw  new IndexOutOfBoundsException("THere's no such index");

        Objects.requireNonNull(floor, "Floor is null");

        if (this.floors.length > index && index > 0) {
            shiftArray(index);
            this.floors[index] = floor;
            this.capacity++;
            return true;
        }
        return false;
    }

    private void shiftArray(int index)
    {
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
        for (int i = 0; i < this.floors.length; i++ ) {
            if (floors[i] != null) {
                doubleFloors[amount++] = floors[i];

            }
        }
        this.floors = doubleFloors;
        this.capacity = amount;
    }

    //возвращающий ссылку на экземпляр класса по его номеру в массиве
    public Floor getFloorByIndex(int index) throws IndexOutOfBoundsException {

        if ( index >=  this.floors.length | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        return this.floors[index];
    }

    //изменяющий ссылку на экземпляр класса  по его номеру в массиве.
    //Принимает в качестве параметров номер и ссылку на экземпляр класса OwnersFloor
    public Floor setFloorByIndex(int index, Floor floor) throws IndexOutOfBoundsException, NullPointerException{

        if ( index >=  this.floors.length | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        Objects.requireNonNull(floor, "Floor is null");

        Floor changedFloor = this.floors[index];
        this.floors[index] = floor;
        return changedFloor;
    }

    //удаляющий этаж из массива по его номеру
    public Floor removeFloorByIndex(int index) throws IndexOutOfBoundsException {

        if ( index > this.floors.length | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

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


    public int getVehicleAmount() {
        int amount = 0;
        for (Floor floor : this.floors) {
            amount += floor.getVehiclesNumber();
        }
        return amount;
    }


    private boolean isEmptySpace(Space space)
    {
        return  (space != null) && (!space.isEmpty());
    }

    //возвращающий ссылку на экземпляр класса Space, с которым связанно транспортное
    //средство с определенным гос. номером. В качестве параметра принимает строку – гос. номер.
    public Space getSpaceByRegNumber(String registrationNumber) throws NullPointerException, RegistrationNumberFormatException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("RegNumber has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");


        for (Floor floor : floors) {
            Space[] spaces = (Space[]) floor.toArray();
            for (Space space : spaces) {
                if (equalsTo(space, registrationNumber)) {
                    return space;
                }
            }
        }
        throw new NoSuchElementException("There's no such space");
    }

     private boolean isRegNumberFormatOK(String registrationNumber){

        Pattern pattern = Pattern.compile("[ABEKMHOPCTYX][0-9]{3}[ABEKMHOPCTYX]{2}[0-9]{2,3}");
        Matcher matcherReg = pattern.matcher(registrationNumber);
        return matcherReg.matches();
    }

    private boolean equalsTo(Space space,String registrationNumber)
    {
        return   space.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    //удаляющий парковочное место, с которым связанно транспортное средство с
    //определенным гос. номером.
    public Space removeSpaceByRegNumber(String registrationNumber) throws RegistrationNumberFormatException,  NullPointerException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("RegNumber has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");


        for (int index = 0; index < this.floors.length; index++ ) {
            Space[] spaces = (Space[]) floors[index].toArray();
            for (int i = 0; i < spaces.length; i++) {
                if (equalsTo(spaces[i], registrationNumber)) {
                    Space removedSpace = spaces[i];
                    floors[index].setSpaceByIndex(i, null);
                    floors[index].shift();
                    return removedSpace;
                }
            }
        }
        throw new NoSuchElementException("There's no such space");
    }


    //изменяющий ссылку на экземпляр класса Space с которым связанно транспортное средство
    //с определенным гос. номером.
    public Space setSpaceByRegNumber(Space space, String registrationNumber) throws RegistrationNumberFormatException, NullPointerException, NoSuchElementException{

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("RegNumber has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");

        Objects.requireNonNull(space, "Space is null");

        Space removedSpace;
        Space[] spaces;
        for (int index = 0; index < this.floors.length; index++ ) {
            spaces = (Space[]) floors[index].toArray();
            for (int i = 0; i < spaces.length; i++) {
                if (equalsTo(spaces[i], registrationNumber)) {

                    removedSpace = spaces[i];
                    floors[index].setSpaceByIndex(i, space);
                    return removedSpace;
                }
            }
        }
        throw new NoSuchElementException("There's no such place");
    }


    //LAB 3
    //возвращающий общее число не занятых парковочных мест
    public int getEmptySpacesAmount(){
        int amount = 0;
        for (Floor floor : this.floors) {
            amount += floor.getEmptySpaces().size();
        }
        return amount;
    }

    //возвращающий общее число ТС заданного типа
    public int getVehiclesAmountByType(VehiclesTypes type) throws NullPointerException{

        Objects.requireNonNull(type, "Vehicle Type is null");

        int amount = 0;
        for (Floor floor : this.floors) {
            amount += floor.getSpacesByVehicleType(type).size();
        }
        return amount;
    }

    //Lab4
    @Override
    public String toString() {
        StringBuilder builtString = new StringBuilder("Floors (");
        builtString.append(this.capacity).append(" total):\n");
        for (Floor floor : this.floors) {
            builtString.append(floor.toString()).append("\n");
        }
        return builtString.toString();
    }


    //Lab6

    private class SpaceIterator implements Iterator<Floor>{

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < capacity;
        }

        @Override
        public Floor next() throws  NoSuchElementException{
            if (!hasNext()) {
                throw new NoSuchElementException("There isn't any space left");
            } else {
                return floors[index++];
            }
        }
    }

    @Override
    public Iterator<Floor> iterator() {
        return new SpaceIterator();
    }


    //Lab7


    //Changed to Lab7
    public List<Floor> getSortedByFloorsSize() {

        Floor[] sortedFloors = this.floors;
        Arrays.sort(sortedFloors);
        return new ArrayList<>(Arrays.asList(sortedFloors));

    }

    //Changed to Lab7
    public Collection<Vehicle> getVehicles() {

        Collection<Vehicle> vehicles = new ArrayList<>();

        for (Floor floor : this.floors) {
            Space[] spaces = (Space[]) floor.toArray();

            for (Space space : spaces) {
                if (isEmptySpace(space)) {
                    vehicles.add(space.getVehicle());
                }
            }
        }
        return vehicles;

    }


    //Changed to Lab7
    public Set<Floor>  getFloorsWithPerson(Person person) throws NullPointerException{

        Objects.requireNonNull(person, "Person is null");

        Set<Floor> floorsWithPer = new HashSet<>();

        for (Floor floor:this.floors){
            for (int index = 0; index < floor.size(); index++){
                if (floor.getSpaceByIndex(index).getPerson().equals(person)){
                    floorsWithPer.add(floor);
                    break;
                }
            }
        }
        return floorsWithPer;
    }
}

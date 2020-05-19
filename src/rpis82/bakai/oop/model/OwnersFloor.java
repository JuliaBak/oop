package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Floor;
import rpis82.bakai.oop.model.interfaces.Space;

import java.time.LocalDate;
import java.util.*;
import java.lang.Object;

public class OwnersFloor implements Floor, Cloneable {

    private Space[] spaces;
    private int capacity = 0;
    private final int spacesNumber = 16;

    public OwnersFloor() { this.spaces = new Space[spacesNumber]; }

    public OwnersFloor(int capacity) {
        this.spaces = new Space[capacity];
    }

    public OwnersFloor(Space[] spaces) {
        copyFrom(spaces);
        this.capacity = spaces.length;
    }

    public  Space[]  copyFrom(Space[] spaces)
    {
        Space[] newSpaces = new Space[spaces.length];
        System.arraycopy(spaces, 0 , newSpaces, 0, spaces.length);
        return newSpaces;
    }

    //changed to Lab7
    @Override
    public boolean add(Space space) throws NullPointerException {

        Objects.requireNonNull(space, "Space is null");

        if (!isEnoughCapacity()) {
            grow();
        }
        this.spaces[this.capacity++] = space;
        return true;
    }

    //Lab7
    @Override
    public int size() {
        return this.capacity;
    }

    @Override
    public boolean isEmpty() {
        return this.capacity == 0;
    }

    //Lab7
    @Override
    public Object[] toArray() {
        return copyFrom(spaces);
    }

    private boolean isEnoughCapacity()
    {
        return this.capacity < this.spaces.length && this.spaces[this.capacity] == null;
    }

    private void grow() {
        Space[] newSpaces = new Space[this.spaces.length * 2];
        System.arraycopy(spaces, 0, newSpaces, 0, spaces.length);
        this.spaces = newSpaces;
        this.capacity = newSpaces.length;
    }


    public boolean addSpaceByIndex(int index, Space space) throws IndexOutOfBoundsException, NullPointerException{

        if ( index >=  this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        Objects.requireNonNull(space, "Space is null");

        if (this.spaces.length > index && index > 0) {
            shiftArrayByIndex(index);
            this.spaces[index] = space;
            this.capacity++;
            return true;
        }
        return false;
    }

    private void shiftArrayByIndex(int index) {

        if (this.capacity + 1 >= this.spaces.length){
            grow();
        }
        for (int i = this.capacity; i > index; i--){
            Space space = this.spaces[i];
            this.spaces[i] = this.spaces[i-1];
            this.spaces[i-1] = space;
        }
    }

    @Override //возвращающий ссылку на экземпляр класса Space по его номеру в массиве
    public Space getSpaceByIndex(int index) throws IndexOutOfBoundsException {

        if ( index >=  this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        return this.spaces[index];
    }


    @Override //возвращающий ссылку на экземпляр класса Space, с которым связанно тс с определенным гос. номером.
    public Space getSpaceByRegNumber(String registrationNumber) throws RegistrationNumberFormatException, NullPointerException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");


        Iterator<Space> iteratorSpaces = new SpaceIterator();

        while (iteratorSpaces.hasNext()) {
            Space space = iteratorSpaces.next();

            if (equalsToRegNumber(space, registrationNumber)) {
                return space;
            }
        }
        throw  new NoSuchElementException("THere's no space with such RegNumber");

    }

    public boolean isRegNumberFormatOK(String registrationNumber)
    {
      return  Floor.super.isRegNumberFormatOK(registrationNumber);
    }


    public boolean equalsToRegNumber(Space space, String registrationNumber)
    {
        return space.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    @Override  //определяющий, есть ли на этаже парковочное место, связанное с тс с определенным гос. номером.
    public boolean hasSpaceByRegNumber(String registrationNumber) throws RegistrationNumberFormatException, NullPointerException {


        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");

        Iterator<Space> iteratorSpaces = new SpaceIterator();

        while (iteratorSpaces.hasNext()) {
            Space space = iteratorSpaces.next();

            if ((space != null) &&  equalsToRegNumber(space, registrationNumber)) {
                return true;
            }
        }
        return false;
    }


    public Space setSpaceByIndex(int index, Space space) throws IndexOutOfBoundsException, NullPointerException{

        if ( index >=  this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        Objects.requireNonNull(space, "Person is null");

        Space setSpace = this.spaces[index];
        this.spaces[index] = space;
        return setSpace;
    }

    @Override
    public Space removeByIndex(int index) throws IndexOutOfBoundsException {

        if ( index >=  this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        Space removedSpace = this.spaces[index];
        this.spaces[index] = null;
        shift();
        return removedSpace;
    }

    @Override //удаляющий парковочное место из массива по гос. номеру автомобиля
    public Space removeByRegNumber(String registrationNumber) throws RegistrationNumberFormatException, NullPointerException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");

        Iterator<Space> iteratorSpaces = new SpaceIterator();
        int index = 0;
        while (iteratorSpaces.hasNext()) {
            Space space = iteratorSpaces.next();

            if (equalsToRegNumber(space, registrationNumber)) {
                return removeByIndex(index);
            }
            index++;
        }
        throw new NoSuchElementException("THere's no space with such RegNumber");

    }


    public void shift() {
        for (int i = 0; i < spaces.length; i++)  {
            if (spaces[i] != null) {
                this.spaces[i++] = spaces[i];
            }
        }
        this.capacity = this.spaces.length;
    }



    private   boolean isEmptySpace(Space space)
    {
        return  (space != null) ;
    }

    // Changed to Lab7
    @Override //возвращающий массив транспортных средств на этаже
    public List<Vehicle> getVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();
        for (Space space : this.spaces) {
            if ((space != null) && (!space.isEmpty())) {
                vehicles.add(space.getVehicle());
            }
        }
        return vehicles;
    }

    public int getVehiclesNumber(){
        int number = 0;
        for (Space space : spaces) {
            if (isEmptySpace(space)) {
                number++;
            }
        }
        return number;
    }

    //Changed to lab 7, Lab3
    @Override //возвращающий массив парковочных мест с ТС заданного типа
    public List<Space> getSpacesByVehicleType(VehiclesTypes vehicleType) throws NullPointerException {

        Objects.requireNonNull(vehicleType,"Type is null");

        List<Space> spaces = new ArrayList<>();
        for (Space space: this.spaces){
            if (space.getVehicle().getType().equals(vehicleType)){
                spaces.add(space);
            }
        }
        return spaces;

    }

    private boolean equalsToType(Space space, VehiclesTypes vehicleType)
    {
        return space.getVehicle().getType().equals(vehicleType);
    }

    //Lab7
    @Override //возвращающий массив не занятых парковочных мест
    public Deque<Space> getEmptySpaces() {

        Deque<Space> emptySpaces = new LinkedList<>();

        for (Space space : this.spaces) {
            if (space.isEmpty()) {
                emptySpaces.add(space);
            }
        }
        return emptySpaces;
    }

    //Lab 4
    @Override
    public String toString() {
        StringBuilder builtString = new StringBuilder("Spaces:\n");
        for(int i = 0; i < this.capacity; i++)
        {
            builtString.append(this.spaces[i].toString()).append("\n");
        }
        return builtString.toString();
    }

    @Override
    public int hashCode() {

        int resultedHash = 0;
        for (int i = 0; i < this.capacity; i++)
        {
            resultedHash ^= spaces[i].hashCode();
        }
        return 71 * (resultedHash & Objects.hashCode(capacity));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OwnersFloor ownersFloor = (OwnersFloor) obj;
        return capacity == ownersFloor.capacity &&
                Arrays.equals(spaces, ownersFloor.spaces);
    }

    public OwnersFloor clone() throws CloneNotSupportedException{
        OwnersFloor clone = (OwnersFloor) super.clone();
        clone.spaces = (Space[]) this.toArray().clone();
        return clone;
    }

    @Override
    public boolean isSpaceRemoved(Space space) throws NullPointerException{

        Objects.requireNonNull(space, "Space is null");

        for (int i = 0; i < this.spaces.length; i++){
            if (this.spaces[i].equals(space)){
                removeByIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOfSpace(Space space) throws NullPointerException{

        Objects.requireNonNull(space, "Space is null");

        for (int index = 0; index< this.spaces.length; index++){
            if (this.spaces[index].equals(space)){
                return index;
            }
        }
        return 0;
    }

    @Override
    public int spacesNumberByPerson(Person person) throws NullPointerException{
        Objects.requireNonNull(person, "Person is null");

        int number = 0;
        for (Space space : this.spaces) {
            if (space.getPerson().equals(person)) {
                number++;
            }
        }
        return number;
    }

    //Lab 5
    @Override
    public LocalDate getClosestRentalEndDate() throws NoRentedSpaceException {

        LocalDate closestDate = null;
        for (Space space : this.spaces) {
            closestDate = ((RentedSpace) space).getRentalEndDate();
        }
        //проверка есть ли rented
            if (closestDate == null){
                throw new NoRentedSpaceException("Rental End Date is null, there's no Closest Rental Date");
            }

        for (Space space : this.spaces) {

            if (((RentedSpace) space).getRentalEndDate().isBefore(closestDate)) {
                closestDate = ((RentedSpace) space).getRentalEndDate();
            }
        }
        return closestDate;
    }

    @Override
    public Space getSpaceByClosestRentalEndDate() throws NoRentedSpaceException {

        RentedSpace closestDateSpace = null;

        for (Space space : this.spaces) {
            closestDateSpace = (RentedSpace) space;
        }
            if (closestDateSpace == null){
                throw new NoRentedSpaceException("Space is null, there's no Closest Rental Date Space");
            }

        for (Space space : this.spaces) {
            if (((RentedSpace) space).getRentalEndDate().isBefore(closestDateSpace.getRentalEndDate())) {
                closestDateSpace = (RentedSpace) space;
            }
        }
        return closestDateSpace;
    }

    //Lab6

    @Override
    public int compareTo(Floor floor)
    {
        return this.capacity - floor.size();
    }


    //внутренний приватный класс SpaceIterator
    private class SpaceIterator implements Iterator<Space> {

        int index = 0;

        //метод next() итератора должен выбрасывать NoSuchElementException
        @Override
        public Space next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("There isn't any space left");
            } else {
                return spaces[index++];
            }
        }

        @Override
        public boolean hasNext() {
            return index < capacity;
        }
    }

    //реализовать метод Iterator<Space> iterator(), который возвращает экземпляр созданного в предыдущем пункте класса
    @Override
    public Iterator<Space> iterator() {
        return new SpaceIterator();
    }


    @Override
    public boolean contains(Object spaceObject) {

        Objects.requireNonNull(spaceObject, "Space is null");

            for (Space space : this.spaces) {
                if (spaceObject.equals(space)) {
                    return true;
                }
            }

        return false;
    }

    //Lab7
    @Override
    public boolean remove(Object spaceObject) {
        Objects.requireNonNull(spaceObject, "Space is null");

        for (int i = 0; i < this.spaces.length; i++) {
            if (this.spaces[i].equals(spaceObject)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    //Lab7
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {

                if (!contains(element)){
                    return false;
                }
        }
        return true;
    }

    //Lab7
    @Override
    public boolean addAll(Collection<? extends Space> collection) {
        for (Space space : collection) {
            add(space);
        }
        return true;
    }


    //Lab7
    @Override
    public boolean removeAll(Collection<?> collection) {

        int number = this.capacity;
        for (Object spaceObject:collection){
                remove(spaceObject);

        }
        return number > this.capacity;
    }

    //Lab7
    @Override
    public boolean retainAll(Collection<?> collection) {

        int amount = this.capacity;

        for (Space space: this.spaces){
            if (space != null){
                if (!collection.contains(space)){
                    remove(space);
                }
            }
        }
        return amount > this.capacity;
    }

    //Lab7
    @Override
    public void clear()  {

        this.spaces = null;
    }

    //Lab7
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


}

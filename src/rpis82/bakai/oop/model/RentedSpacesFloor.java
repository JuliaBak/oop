package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;
import rpis82.bakai.oop.model.interfaces.Floor;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RentedSpacesFloor implements Floor, Cloneable {
    private Node head; //ссылка на голову списка
    private int capacity; //число элеметов в списке

    public RentedSpacesFloor() { //без параметров – инициализирует head с пустыми ссылками (null) next, previous и value

        this.head = new Node(null, null, null);
        this.head.next = this.head.previous = this.head;
    }

    public RentedSpacesFloor(Space[] spaces) { //массив счетов по интерфейсной ссылке (Space[]). В конструкторе происходит создание
        //  списка и заполнение его элементов ссылками из массива
        this();
        addAllSpaces(spaces);
    }

    private boolean addAllSpaces(Space[] spaces) {

        if (spaces != null) {
            for (int i = 0; i < spaces.length; i++ ) {
                addSpace(spaces[i]);
            }
        }
        return true;
    }

    @Override
    public boolean addSpace(Space space) throws NullPointerException {

        Objects.requireNonNull(space, "Space is null");
        addLastSpace(space);
        return true;
    }

    private void addLastSpace(Space space) throws NullPointerException{

        Objects.requireNonNull(space, "Space is null");
            Node last = new Node(this.head, this.head.previous, space);
            this.head.previous = this.head.previous.next = last;
            this.capacity++;

    }

    @Override
    public boolean addSpaceByIndex(int index, Space space) throws IndexOutOfBoundsException, NullPointerException {

        Objects.requireNonNull(space, "Space is null");

        if(index > this.capacity |  index < 0 )
            throw  new IndexOutOfBoundsException("THere's no such index");

        addByIndex(index, space);
        return true;
    }


    private void addByIndex(int index, Space space) throws IndexOutOfBoundsException, NullPointerException { //добавляющий узел в заданную позицию в списке

        Objects.requireNonNull(space, "Space is null");

        if(index > this.capacity |  index < 0 )
            throw  new IndexOutOfBoundsException("THere's no such index");

        if (index == 0) {
            addFirstSpace(space);
        } else if (index == this.capacity) {
            addLastSpace(space);
        } else {
            Node node = getNodeByIndex(index);
            if (node != null) {
                Node setNode = new Node(node, node.previous, space);
                node.previous = node.previous.next = setNode;
                this.capacity++;
            }
        }

    }

    public void addFirstSpace(Space value) throws NullPointerException {////метод добавялющий узел в начало списка

        Objects.requireNonNull(value, "Space is null");

        Node first = new Node(this.head.next, this.head, value);
        this.head.next = this.head.next.previous = first;
        this.capacity++;

    }

    private Node getNodeByIndex(int index) throws IndexOutOfBoundsException{ //возвращающий ссылку на узел по его номеру в списке

        if(index >=  this.capacity |  index < 0 )
            throw  new IndexOutOfBoundsException("THere's no such index");

        int number = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (number == index) {
                return node;
            }
            number++;
        }
        return null;
    }

    @Override
    public Space getSpaceByIndex(int index) throws  IndexOutOfBoundsException{

        if(index >=  this.capacity |  index <= 0 )
            throw  new IndexOutOfBoundsException("THere's no such index");

        Node node = getNodeByIndex(index);
        if (node != null) {
            return node.value;
        } else   return null;
    }

    @Override
    public Space removeByIndex(int index)  throws IndexOutOfBoundsException{ //удаляющий узел по его номеру в списке

        if(index >=  this.capacity |  index <= 0 )
            throw  new IndexOutOfBoundsException("THere's no such index");

        Space space = null;
        Node removedNode = getNodeByIndex(index);
        if (removedNode != null) {
            space = removedNode.value;
            removedNode.next.previous = removedNode.previous;
            removedNode.previous.next = removedNode.next;
            this.capacity--;
        }
        return space;
    }

    @Override //− изменяющий узел с заданным номером
    public Space setSpaceByIndex(int index, Space value) throws IndexOutOfBoundsException, NullPointerException{

        if(index >=  this.capacity |  index < 0 )
            throw  new IndexOutOfBoundsException("THere's no such index");

        Objects.requireNonNull(value, "Space is null");

        Space space = null;
        Node node = getNodeByIndex(index);
            if (node != null) {
                space = node.value;
                node.value = value;
            }

        return space;
    }

    @Override
    //возвращающий ссылку на экземпляр класса Space, с которым связанно транспортное
    // средство с определенным гос. номером
    //Changed in lab6
    public Space getSpaceByRegNumber(String registrationNumber) throws NullPointerException, NoSuchElementException,  RegistrationNumberFormatException{


        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");}

        Objects.requireNonNull(registrationNumber, "RegNumber is null");

        Iterator<Space> iteratorSpaces = new SpaceIterator();

        while (iteratorSpaces.hasNext()) {
            Space space = iteratorSpaces.next();

            if (equalsToRegNumber(space, registrationNumber)) {
                return space;
            }
        }
        throw  new NoSuchElementException("THere's no space with such RegNumber");


    }

    public boolean isRegNumberFormatOK(String registrationNumber){
        return Floor.super.isRegNumberFormatOK(registrationNumber);

    }


    @Override
    //определяющий, есть ли на этаже парковочное место, связанное с транспортным средством
    //с определенным гос. номером
    public boolean hasSpaceByRegNumber(String registrationNumber) throws RegistrationNumberFormatException , NullPointerException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");}

        Objects.requireNonNull(registrationNumber, "RegNumber is null");

        Iterator<Space> iteratorSpaces = new SpaceIterator();

        while (iteratorSpaces.hasNext()) {
            Space space = iteratorSpaces.next();

            if (equalsToRegNumber(space, registrationNumber)) {
                return true;
            }
        }
        throw  new NoSuchElementException("THere's no space with such RegNumber");
    }


    @Override
    public Space removeByRegNumber(String registrationNumber) throws RegistrationNumberFormatException, NullPointerException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");}

        Objects.requireNonNull(registrationNumber, "RegNumber is null");

        int index = 0;

        Iterator<Space> iteratorSpaces = new SpaceIterator();

        while (iteratorSpaces.hasNext()) {
            Space space = iteratorSpaces.next();

            if (equalsToRegNumber(space, registrationNumber)) {
                return removeByIndex(index);
            }
            index++;
        }
        throw new NoSuchElementException("THere's no space with such RegNumber");
    }


    private boolean equalsToRegNumber(Space space, String registrationNumber)
    {
        return   space.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public Space[] getSpaces() {

          Space[] spaces = new Space[this.capacity];
        int index = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            spaces[index++] = node.value;
        }
        return spaces;
    }

    @Override
    public Vehicle[] getVehicles() {
        Space[] spaces = getSpaces();
        Vehicle[] vehicles = new Vehicle[spaces.length];
        int number = 0;
        for (Space space : spaces) {
            vehicles[number++] = space.getVehicle();
        }
        return vehicles;
    }

    @Override
    public int getVehiclesNumber() {
        return getSpaces().length;
    }

    //Lab 3
    @Override //возвращающий массив парковочных мест с ТС заданного типа
    public Space[] getSpacesByVehicleType(VehiclesTypes vehicleTypes) throws NullPointerException{

        Objects.requireNonNull(vehicleTypes, "Vehicle Type is null");

        Space[] resultSpaces = new Space[this.capacity];
        int number = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.value.getVehicle().getType().equals(vehicleTypes)) {
                resultSpaces[number++] = node.value;
            }
        }
        return resultSpaces;
    }

    @Override // возвращающий массив не занятых парковочных мест
    public Space[] getEmptySpaces() {
        Space[] emptySpaces = new Space[this.capacity];
        int number = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.value.isEmpty()) {
                emptySpaces[number++] = node.value;
            }
        }
        return emptySpaces;
    }

    @Override
    public void shift() { }

    //Lab4
    @Override
    public String toString() {

        StringBuilder builtString = new StringBuilder("Rented spaces:\n");
        for (int index = 0; index < this.capacity; index++)
        {
            Node node = getNodeByIndex(index);
            if (node != null){
                builtString.append(node.value.toString()).append("\n");
            }
        }
        return builtString.toString();
    }

    @Override
    public int hashCode() {
        int resultedHash = 0;
        for (int i = 0; i < this.capacity; i++)
        {
            Node node = getNodeByIndex(i);
            if (node != null){

                resultedHash ^= node.value.hashCode();
            }
        }
        return 53 * ( this.capacity & resultedHash);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RentedSpacesFloor rentedFloor = (RentedSpacesFloor) obj;
        return capacity == rentedFloor.capacity && this.head.equals(rentedFloor.head) ;
    }

    public RentedSpacesFloor clone() throws CloneNotSupportedException{
        RentedSpacesFloor clone = (RentedSpacesFloor) super.clone();
        Space[] clonedSpaces = this.getSpaces().clone();
        clone.addAllSpaces(clonedSpaces);
        return clone;
    }


    @Override
    public boolean isSpaceRemoved(Space space) throws NullPointerException {

        Objects.requireNonNull(space, "Space is null");

        int index = 0;
        for (Node node = this.head.next; node != this.head; node = node.next){
            if (node.value.equals(space)){
                removeByIndex(index);
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public int indexOfSpace(Space space) throws NullPointerException {

        Objects.requireNonNull(space, "Space is null");

        int index = 0;
        for (Node node = this.head.next; node != this.head; node = node.next){
            if (node.value.equals(space)){
                return index;
            }
            index++;
        }
        return 0;
    }

    @Override
    public int spacesNumberByPerson(Person person) throws NullPointerException{

        Objects.requireNonNull(person, "Person is null");

        int number = 0;
        for (Node node = this.head.next; node != this.head; node = node.next){
            if (node.value.getPerson().equals(person)){
                number++;
            }
        }
        return number;
    }


    //Lab 5

    @Override
    public LocalDate getClosestRentalEndDate() throws NoRentedSpaceException {

        LocalDate closestDate = null;
        for (Node node = this.head.next; node != this.head; node = node.next) {
                closestDate = ((RentedSpace) node.value).getRentalEndDate();

        }
        if (closestDate == null) {
            throw new NoRentedSpaceException("Rental End Date is null, there's no Closest Rental Date");
        }
          for (Node node = this.head.next; node != this.head; node = node.next) {
                if (((RentedSpace) node.value).getRentalEndDate().isBefore(closestDate)) {
                    closestDate = ((RentedSpace) node.value).getRentalEndDate();

            }
         }
        return closestDate;
    }

    @Override
    public Space getSpaceByClosestRentalEndDate() throws NoRentedSpaceException {

        RentedSpace closestRentalEndDateSpace = null;
        for (Node node = this.head.next; node != this.head; node = node.next) {
                closestRentalEndDateSpace = (RentedSpace) node.value;
        }
            if (closestRentalEndDateSpace == null) {
                throw new NoRentedSpaceException("Space is null, there's no Closest Rental Date Space");
            }
        for (Node node = this.head.next; node != this.head; node = node.next) {
                if (((RentedSpace) node.value).getRentalEndDate().isBefore(closestRentalEndDateSpace.getRentalEndDate())) {
                    closestRentalEndDateSpace = (RentedSpace) node.value;
                }
        }
        return closestRentalEndDateSpace;
    }

    //Lab6

    @Override
    public int compareTo(Floor floor) {
        return this.capacity - floor.getCapacity();
    }

    private class SpaceIterator implements Iterator<Space> {

        private Node nextItem = head.next;

        @Override
        public boolean hasNext() {
            return nextItem != head;
        }

        @Override
        public Space next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There isn't any space left");
            } else {
                Space value = nextItem.value;
                nextItem = nextItem.next;
                return value;
            }
        }
    }

    public Iterator<Space> iterator()
    {
        return new SpaceIterator();
    }

}



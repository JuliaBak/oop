package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;
import rpis82.bakai.oop.model.interfaces.Floor;

import java.time.LocalDate;
import java.util.*;
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
        addAll(Arrays.asList(spaces));
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

    public void addFirstSpace(Space space) throws NullPointerException {////метод добавялющий узел в начало списка

        Objects.requireNonNull(space, "Space is null");

        Node first = new Node(this.head.next, this.head, space);
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
    public int getVehiclesNumber() {
        return toArray().length;
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
        Space[] clonedSpaces = this.toArray().clone();
        clone.addAll(Arrays.asList(clonedSpaces));
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
        return this.capacity - floor.size();
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

    //Lab7
    @Override
    public boolean contains(Object object) {

        Objects.requireNonNull(object, "Space is null");

            Iterator<Space> iterator = new SpaceIterator();

            while (iterator.hasNext()) {
                Space space = iterator.next();
                if (object.equals(space)) {
                    return true;
                }
            }

        return false;
    }




    @Override
    public boolean remove(Object object) {
        Objects.requireNonNull(object, "Space is null");

        while (iterator().hasNext()) {
            Space space = iterator().next();

            if (space.equals(object)) {
                remove(space);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {

        for (Object object : collection) {
                if (!contains(object)){
                    return false;
                }

        }
        return true;
    }


    @Override
    public boolean removeAll(Collection<?> collection) {

        int previousNumber = this.capacity;
        for (Object object: collection){
                remove(object);
        }
        return previousNumber > this.capacity;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {

        int previousNumber = this.capacity;
        Iterator<Space> spaceIterator = new SpaceIterator();

        while (spaceIterator.hasNext()){
            Space space = spaceIterator.next();

            if (space != null){
                if (!collection.contains(space)){
                    remove(space);
                }
            }
        }
        return previousNumber > this.capacity;
    }

    @Override
    public void clear() {
        this.head = new Node(null, null, null);
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
    public boolean addAll(Collection<? extends Space> collection) {

        for (Space space : collection) {
            add(space);
        }
        return true;
    }

    //Lab7
    @Override
    public boolean add(Space space) {

        Objects.requireNonNull(space, "Space is null");
        addLastSpace(space);
        return true;
    }


    @Override
    public Space[] toArray() {

        Space[] spaces = new Space[this.capacity];
        int index = 0;

        for (Node node = this.head.next; node != this.head; node = node.next) {
            spaces[index++] = node.value;
        }
        return spaces;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        return Arrays.copyOf(array, array.length);
    }


    @Override //Changed to Lab7
    public Collection<Vehicle> getVehicles() {

        List<Space> spaces = new ArrayList<>(Arrays.asList(toArray()));
        Collection<Vehicle> vehicles = new ArrayList<>();

        for (Space space : spaces) {
            vehicles.add(space.getVehicle());
        }
        return vehicles;
    }

    //Lab 3, Changed to Lab7
    @Override //возвращающий массив парковочных мест с ТС заданного типа
    public  List<Space>  getSpacesByVehicleType(VehiclesTypes vehicleTypes) throws NullPointerException{

        Objects.requireNonNull(vehicleTypes, "VehicleType is null");

        List<Space> resultedSpaces = new ArrayList<>();

        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.value.getVehicle().getType().equals(vehicleTypes)) {
                resultedSpaces.add(node.value);
            }
        }
        return resultedSpaces;
    }


    @Override // возвращающий массив не занятых парковочных мест
    public Deque<Space> getEmptySpaces() {

        Deque<Space> emptySpaces = new LinkedList<>();

        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.value.isEmpty()) {
                emptySpaces.add(node.value);
            }
        }
        return emptySpaces;
    }
}



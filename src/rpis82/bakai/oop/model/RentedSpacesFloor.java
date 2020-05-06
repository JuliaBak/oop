package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;
import rpis82.bakai.oop.model.interfaces.Floor;

import java.util.Arrays;
import java.util.Objects;


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
    public boolean addSpace(Space space) {
        addLastSpace(space);
        return true;
    }

    private void addLastSpace(Space value) {
        if (value != null) {
            Node last = new Node(this.head, this.head.previous, value);
            this.head.previous = this.head.previous.next = last;
            this.capacity++;
        }
    }

    @Override
    public boolean addSpaceByIndex(int index, Space value) {
        addByIndex(index, value);
        return true;
    }


    private void addByIndex(int index, Space value) { //добавляющий узел в заданную позицию в списке
        if (value != null) {
            if (index == 0) {
                addFirstSpace(value);
            } else if (index == this.capacity) {
                addLastSpace(value);
            } else {
                Node node = getNodeByIndex(index);
                if (node != null) {
                    Node setNode = new Node(node, node.previous, value);
                    node.previous = node.previous.next = setNode;
                    this.capacity++;
                }
            }
        }
    }

    public void addFirstSpace(Space value) {////метод добавялющий узел в начало списка
        if (value != null) {
            Node first = new Node(this.head.next, this.head, value);
            this.head.next = this.head.next.previous = first;
            this.capacity++;
        }
    }

    private Node getNodeByIndex(int index) { //возвращающий ссылку на узел по его номеру в списке

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
    public Space getSpaceByIndex(int index) {
        Node node = getNodeByIndex(index);
        if (node != null) {
            return node.value;
        } else return null;
    }

    @Override
    public Space removeByIndex(int index) { //удаляющий узел по его номеру в списке

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
    public Space setSpaceByIndex(int index, Space value) {
        Space space = null;
        if (value != null) {
            Node node = getNodeByIndex(index);
            if (node != null) {
                space = node.value;
                node.value = value;
            }
        }
        return space;
    }

    @Override
    //возвращающий ссылку на экземпляр класса Space, с которым связанно транспортное
   // средство с определенным гос. номером
    public Space getSpaceByRegNumber(String registrationNumber) {
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (equalsTo(node, registrationNumber)) {
                return node.value;
            }
        }
        return null;
    }

    private boolean equalsTo(Node node, String registrationNumber)
    {
      return   node.value.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    @Override
    //определяющий, есть ли на этаже парковочное место, связанное с транспортным средством
    //с определенным гос. номером
    public boolean hasSpaceByRegNumber(String registrationNumber) {
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (equalsTo(node, registrationNumber)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Space removeByRegNumber(String registrationNumber) {
        int index = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (equalsTo(node, registrationNumber)) {
                return removeByIndex(index);
            }
            index++;
        }
        return null;
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
        for (int index = 0; index < spaces.length; index++) {
            vehicles[number++] = spaces[index].getVehicle();
        }
        return vehicles;
    }

    @Override
    public int getVehiclesNumber() {
        return getSpaces().length;
    }

    //Lab 3
    @Override //возвращающий массив парковочных мест с ТС заданного типа
    public Space[] getSpacesByVehicleType(VehiclesTypes vehicleTypes) {
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
    public boolean isSpaceRemoved(Space space) {
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
    public int indexOfSpace(Space space) {
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
    public int spacesNumberByPerson(Person person) {
        int number = 0;
        for (Node node = this.head.next; node != this.head; node = node.next){
            if (node.value.getPerson().equals(person)){
                number++;
            }
        }
        return number;
    }
}



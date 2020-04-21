package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;
import rpis82.bakai.oop.model.interfaces.Floor;


public class RentedSpacesFloor implements Floor {
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
            for (Space space : spaces) {
                addSpace(space);
            }
        }
        return true;
    }

    @Override
    public boolean addSpace(Space space) {
        addLastSpace(space);
        return true;
    }


    private void addLastSpace(Space value) {//метод добавялющий узел в конце списка
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
        Vehicle[] array = new Vehicle[spaces.length];
        int number = 0;
        for (Space space : spaces) {
            array[number++] = space.getVehicle();
        }
        return array;
    }

    @Override
    public int getVehiclesNumber() {
        return getSpaces().length;
    }

    //Lab 3
    @Override //возвращающий массив парковочных мест с ТС заданного типа
    public Space[] getSpacesByVehicleType(VehiclesTypes vehicleTypes) {
        Space[] array = new Space[this.capacity];
        int number = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.value.getVehicle().getType().equals(vehicleTypes)) {
                array[number++] = node.value;
            }
        }
        return array;
    }

    @Override // возвращающий массив не занятых парковочных мест
    public Space[] getEmptySpaces() {
        Space[] arrayOfSpaces = new Space[this.capacity];
        int number = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.value.isEmpty()) {
                arrayOfSpaces[number++] = node.value;
            }
        }
        return arrayOfSpaces;
    }

    @Override
    public void shift() { }



}



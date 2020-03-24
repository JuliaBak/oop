package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Space;
import rpis82.bakai.oop.model.interfaces.Floor;
public class RentedSpacesFloor implements Floor{
    private Node head;
    private int size;

    public RentedSpacesFloor() {
        this.head = new Node(null, null, null);
        this.head.following = this.head.previous = this.head;
    }

    public RentedSpacesFloor(Space[] spaces) {
        this();
        addAll(spaces);
    }


    @Override
    public boolean add(Space space) {
        addLast(space);
        return true;
    }

    @Override
    public boolean add(int index, Space value) {
        addByIndex(index, value);
        return true;
    }

    @Override
    public Space get(int index) {
        Node node = getNodeByIndex(index);
        if (node != null) {
            return node.value;
        } else return null;
    }

    @Override
    public Space get(String registrationNumber) {
        for (Node node = this.head.following; node != this.head; node = node.following) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean hasSpace(String registrationNumber) {
        for (Node node = this.head.following; node != this.head; node = node.following) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Space set(int index, Space value) {
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
    public Space remove(int index) {
        Space element = null;
        Node delete = getNodeByIndex(index);
        if (delete != null) {
            element = delete.value;
            delete.following.previous = delete.previous;
            delete.previous.following = delete.following;
            this.size--;
        }
        return element;
    }

    @Override
    public Space remove(String registrationNumber) {
        int index = 0;
        for (Node node = this.head.following; node != this.head; node = node.following) {
            if (node.value.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return remove(index);
            }
            index++;
        }
        return null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Space[] getSpaces() {
        Space[] array = new Space[this.size];
        int index = 0;
        for (Node node = this.head.following; node != this.head; node = node.following) {
            array[index++] = node.value;
        }
        return array;
    }

    @Override
    public Vehicle[] getVehicles() {
        Space[] spaces = getSpaces();
        Vehicle[] array = new Vehicle[spaces.length];
        int i = 0;
        for (Space space : spaces) {
            array[i++] = space.getVehicle();
        }
        return array;
    }

    @Override
    public int getVehicleAmount() {
        return getSpaces().length;
    }

    @Override
    public void moveArray() {

    }

    public void addFirst(Space value) {
        if (value != null) {
            Node first = new Node(this.head.following, this.head, value);
            this.head.following = this.head.following.previous = first;
            this.size++;
        }
    }

    public void addLast(Space value) {
        if (value != null) {
            Node last = new Node(this.head, this.head.previous, value);
            this.head.previous = this.head.previous.following = last;
            this.size++;
        }
    }

    public boolean addAll(Space[] spaces) {
        if (spaces != null) {
            for (Space space : spaces) {
                add(space);
            }
        }
        return true;
    }

    public void addByIndex(int index, Space value) {
        if (value != null) {
            if (index == 0) {
                addFirst(value);
            } else if (index == this.size) {
                addLast(value);
            } else {
                Node node = getNodeByIndex(index);
                if (node != null) {
                    Node newNode = new Node(node, node.previous, value);
                    node.previous = node.previous.following = newNode;
                    this.size++;
                }
            }
        }
    }

    private Node getNodeByIndex(int index) {
        int count = 0;
        for (Node node = this.head.following; node != this.head; node = node.following) {
            if (count == index) {
                return node;
            }
            count++;
        }
        return null;
    }


    private boolean checkIndex(int index) {
        return index >= 0 && index < this.size;
    }
}


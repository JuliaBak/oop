package rpis82.bakai.oop.model;
 import rpis82.bakai.oop.model.interfaces.Space;
 import rpis82.bakai.oop.model.interfaces.Floor;


 import java.util.Arrays;

public class Parking{

    private Floor[] floors;
    private int capacity;

    public Parking(int arraySize) {
        this.floors = new Floor[arraySize];
    }

    public Parking(Floor[] array) {
        this.floors = new Floor[array.length];
        int amount = 0;
        for (Floor floor : array) {
            if (floor != null) {
                this.floors[amount] = floor;
                amount++;
            }
        }
        this.capacity = amount;
    }

    public boolean add(Floor floor) {
        if (this.capacity < this.floors.length && this.floors[this.capacity] == null) {
            this.floors[this.capacity] = floor;
        } else {
            int index = this.capacity;
            increaseArraySize();
            this.floors[index] = floor;
        }
        this.capacity++;
        return true;
    }

    public boolean add(int index, Floor floor) {
        if (this.floors.length > index && index > 0) {
            shiftArray(index);
            this.floors[index] = floor;
            this.capacity++;
            return true;
        }
        return false;
    }

    public Floor get(int index) {
        return this.floors[index];
    }

    public Floor set(int index, Floor floor) {
        Floor lastFloor = this.floors[index];
        this.floors[index] = floor;
        return lastFloor;
    }

    public Floor remove(int index) {
        Floor lastFloor = this.floors[index];
        this.floors[index] = null;
        moveArray();
        return lastFloor;
    }

    public int getCapacity() {
        return capacity;
    }

    public Floor[] getFloors() {
        return floors;
    }
//возвращающий общее число не занятых парковочных мест
    public int getEmptySpacesAmount(){
        int amount = 0;
        for (Floor floor:this.floors){
            amount += floor.getEmptySpaces().length;
        }
        return amount;
    }
//возвращающий общее число ТС заданного типа
    public int getVehiclesAmountByType(VehiclesTypes type){
        int amount = 0;
        for (Floor floor:this.floors){
            amount += floor.getSpaces(type).length;
        }
        return amount;
    }
    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[getVehicleAmount()];
        int k = 0;
        for (Floor floor : this.floors) {
            for (Space space : floor.getSpaces()) {
                if ((space != null) && (!space.isEmpty())) {
                    vehicles[k] = space.getVehicle();
                    k++;
                }
            }
        }
        return vehicles;
    }

    public Floor[] getSortedByFloorsSize() {
        Floor[] array = this.floors;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j].getCapacity() > array[j + 1].getCapacity()) {
                    Floor ownersFloor = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = ownersFloor;
                }
            }
        }
        return array;
    }

    public Space getSpace(String registrationNumber) {
        for (Floor floor : floors) {
            for (Space space : floor.getSpaces()) {
                if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space;
                }
            }
        }
        return null;
    }

    public Space removeSpace(String registrationNumber) {
        for (Floor ownersFloor : floors) {
            Space[] array = ownersFloor.getSpaces();
            for (int i = 0; i < array.length; i++) {
                if (array[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    Space deletedSpace = array[i];
                    ownersFloor.set(i, null);
                    ownersFloor.moveArray();
                    return deletedSpace;
                }
            }
        }
        return null;
    }

    public Space setSpace(String registrationNumber,Space space) {
        for (Floor ownersFloor : this.floors) {
            Space[] array = ownersFloor.getSpaces();
            for (int i = 0; i < array.length; i++) {
                if (array[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    Space lastSpace = array[i];
                    ownersFloor.set(i, space);
                    return lastSpace;
                }
            }
        }
        return null;
    }

    public int getEmptyVehiclesNumber(){
        int amount = 0;
        for (Floor floor:this.floors){
            amount += floor.getEmptySpaces().length;
        }
        return amount;
    }

    public int getVehicleNumber(VehiclesTypes type){
        int amount = 0;
        for (Floor floor:this.floors){
            amount += floor.getSpaces(type).length;
        }
        return amount;
    }

    public int getVehicleAmount() {
        int amount = 0;
        for (Floor ownersFloor : this.floors) {
            amount += ownersFloor.getVehicleAmount();
        }
        return amount;
    }

    private void increaseArraySize() {
        Floor[] newArray = new Floor[this.floors.length * 2];
        int amount = 0;
        for (Floor floor : floors) {
            if (floor != null) {
                newArray[amount] = floor;
                amount++;
            }
        }
        this.floors = newArray;
        this.capacity = amount;
    }

    public void moveArray() {
        Floor[] newArray = new Floor[this.floors.length];
        int k = 0;
        for (Floor floor : this.floors) {
            if (floor != null) {
                newArray[k] = floor;
                k++;
            }
        }
        this.floors = newArray;
        this.capacity = k;
    }


    private void shiftArray(int start){
        if (this.capacity + 1 >= this.floors.length){
            increaseArraySize();
        }
        for (int i = this.capacity; i>start; i--){
            Floor floor = this.floors[i];
            this.floors[i] = this.floors[i-1];
            this.floors[i-1] = floor;
        }
    }

    @Override
    public String toString() {
        return "Parking{" +
                "floors=" + Arrays.toString(floors) +
                ", size=" + capacity +
                '}';
    }
}
/* Parking Class Lab2
package rpis82.bakai.oop.model;
 import rpis82.bakai.oop.model.interfaces.Space;
 import rpis82.bakai.oop.model.interfaces.Floor;

 import java.util.Arrays;

public class Parking{

    private Floor[] floors;
    private int size;

    public Parking(int arraySize) {
        this.floors = new Floor[arraySize];
    }

    public Parking(Floor[] array) {
        this.floors = new Floor[array.length];
        int amount = 0;
        for (Floor floor : array) {
            if (floor != null) {
                this.floors[amount] = floor;
                amount++;
            }
        }
        this.size = amount;
    }

    public boolean add(Floor floor) {
        if (this.size < this.floors.length && this.floors[this.size] == null) {
            this.floors[this.size] = floor;
        } else {
            int index = this.size;
            increaseArraySize();
            this.floors[index] = floor;
        }
        this.size++;
        return true;
    }

    public boolean add(int index, Floor floor) {
        if (this.floors.length > index && index > 0) {
            shiftArray(index);
            this.floors[index] = floor;
            this.size++;
            return true;
        }
        return false;
    }

    public Floor get(int index) {
        return this.floors[index];
    }

    public Floor set(int index, Floor floor) {
        Floor lastFloor = this.floors[index];
        this.floors[index] = floor;
        return lastFloor;
    }

    public Floor remove(int index) {
        Floor lastFloor = this.floors[index];
        this.floors[index] = null;
        moveArray();
        return lastFloor;
    }

    public int getSize() {
        return size;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[getVehicleAmount()];
        int k = 0;
        for (Floor floor : this.floors) {
            for (Space space : floor.getSpaces()) {
                if ((space != null) && (!space.isEmpty())) {
                    vehicles[k] = space.getVehicle();
                    k++;
                }
            }
        }
        return vehicles;
    }

    public Floor[] getSortedBySizeFloors() {
        Floor[] array = this.floors;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j].getSpacesAmount() > array[j + 1].getSpacesAmount()) {
                    Floor ownersFloor = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = ownersFloor;
                }
            }
        }
        return array;
    }

    public Space getSpace(String registrationNumber) {
        for (Floor floor : floors) {
            for (Space space : floor.getSpaces()) {
                if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space;
                }
            }
        }
        return null;
    }

    public Space removeSpace(String registrationNumber) {
        for (Floor ownersFloor : floors) {
            Space[] array = ownersFloor.getSpaces();
            for (int i = 0; i < array.length; i++) {
                if (array[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    Space deletedSpace = array[i];
                    ownersFloor.set(i, null);
                    ownersFloor.moveArray();
                    return deletedSpace;
                }
            }
        }
        return null;
    }

    public Space setSpace(String registrationNumber,Space space) {
        for (Floor ownersFloor : this.floors) {
            Space[] array = ownersFloor.getSpaces();
            for (int i = 0; i < array.length; i++) {
                if (array[i].getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    Space lastSpace = array[i];
                    ownersFloor.set(i, space);
                    return lastSpace;
                }
            }
        }
        return null;
    }

    public int getVehicleAmount() {
        int amount = 0;
        for (Floor ownersFloor : this.floors) {
            amount += ownersFloor.getVehicleAmount();
        }
        return amount;
    }

    private void increaseArraySize() {
        Floor[] newArray = new Floor[this.floors.length * 2];
        int amount = 0;
        for (Floor floor : floors) {
            if (floor != null) {
                newArray[amount] = floor;
                amount++;
            }
        }
        this.floors = newArray;
        this.size = amount;
    }

    public void moveArray() {
        Floor[] newArray = new Floor[this.floors.length];
        int k = 0;
        for (Floor floor : this.floors) {
            if (floor != null) {
                newArray[k] = floor;
                k++;
            }
        }
        this.floors = newArray;
        this.size = k;
    }


    private void shiftArray(int start){
        if (this.size + 1 >= this.floors.length){
            increaseArraySize();
        }
        for (int i = this.size;i>start;i--){
            Floor floor = this.floors[i];
            this.floors[i] = this.floors[i-1];
            this.floors[i-1] = floor;
        }
    }

    @Override
    public String toString() {
        return "Parking{" +
                "floors=" + Arrays.toString(floors) +
                ", size=" + size +
                '}';
    }
}

 */
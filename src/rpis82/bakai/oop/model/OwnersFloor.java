package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Floor;
import rpis82.bakai.oop.model.interfaces.Space;

import java.util.Arrays;

public class OwnersFloor implements Floor {

    private Space[] spaces;
    private int capacity;

    public OwnersFloor() {
        this.spaces = new Space[16];
        this.capacity = 0;
    }

    public OwnersFloor(int arraySize) {
        this.spaces = new Space[arraySize];
        this.capacity = 0;
    }

    public OwnersFloor(Space[] spaces) {
        this.spaces = new Space[spaces.length];
        int amount = 0;
        for (Space space : spaces) {
            if (space != null) {
                this.spaces[amount] = space;
                amount++;
            }
        }
        this.capacity = amount;
    }

    public boolean add(Space space) {
        if (this.capacity < this.spaces.length && this.spaces[this.capacity] == null) {
            this.spaces[this.capacity] = space;
        } else {
            int index = this.capacity;
            increaseArraySize();
            this.spaces[index] = space;
        }
        this.capacity++;
        return true;
    }

    public boolean add(int index, Space space) {
        if (this.spaces.length > index && index > 0) {
            shiftArray(index);
            this.spaces[index] = space;
            this.capacity++;
            return true;
        }
        return false;
    }

    @Override
    public Space get(int index) {
        return this.spaces[index];
    }

    @Override
    public Space get(String registrationNumber) {
        for (Space space : this.spaces) {
            if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return space;
            }
        }
        return null;
    }

    @Override
    public boolean hasSpace(String registrationNumber) {
        for (Space space : this.spaces) {
            if ((space != null) && (space.getVehicle().getRegistrationNumber().equals(registrationNumber))) {
                return true;
            }
        }
        return false;
    }

    public Space set(int index, Space space) {
        Space lastSpace = this.spaces[index];
        this.spaces[index] = space;
        return lastSpace;
    }

    @Override
    public Space remove(int id) {
        Space lastSpace = this.spaces[id];
        this.spaces[id] = null;
        moveArray();
        return lastSpace;
    }

    @Override
    public Space remove(String governmentNumber) {
        for (int i = 0; i<this.capacity; i++) {
            if (this.spaces[i].getVehicle().getRegistrationNumber().equals(governmentNumber)) {
                Space lastSpace = this.spaces[i];
                this.spaces[i] = null;
                moveArray();
                return lastSpace;
            }
        }
        return null;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public Space[] getSpaces() {
        Space[] result = new RentedSpace[this.capacity];
        int k = 0;
        for (Space space:this.spaces){
            if (space != null){
                result[k] = space;
                k++;
            }
        }
        return result;
    }

    @Override
    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[getVehicleAmount()];
        int k = 0;
        for (Space space : this.spaces) {
            if ((space != null) && (!space.isEmpty())){
                vehicles[k] = space.getVehicle();
                k++;
            }
        }
        return vehicles;
    }

    public int getVehicleAmount(){
        int amount = 0;
        for (Space space: this.spaces){
            if ((space != null) && (!space.isEmpty())){
                amount++;
            }
        }
        return amount;
    }

    @Override
    public Space[] getSpaces(VehiclesTypes vehicleTypes) {
        Space[] array = new Space[this.capacity];
        int k = 0;
        for (Space space: this.spaces){
            if (space.getVehicle().getType().equals(vehicleTypes)){
                array[k++] = space;
            }
        }
        return array;
    }

    @Override
    public Space[] getEmptySpaces() {
        Space[] array = new Space[this.capacity];
        int k = 0;
        for (Space space: this.spaces){
            if (space.isEmpty()){
                array[k++] = space;
            }
        }
        return array;
    }

    public void moveArray() {
        Space[] newArray = new Space[this.spaces.length];
        int k = 0;
        for (Space space : this.spaces) {
            if (space != null) {
                newArray[k] = space;
                k++;
            }
        }
        this.spaces = newArray;
        this.capacity = k;
    }

    private void increaseArraySize() {
        Space[] newArray = new Space[this.spaces.length * 2];
        int amount = 0;
        for (Space space : spaces) {
            if (space != null) {
                newArray[amount] = space;
                amount++;
            }
        }
        this.spaces = newArray;
        this.capacity = amount;
    }

    private void shiftArray(int start){
        if (this.capacity + 1 >= this.spaces.length){
            increaseArraySize();
        }
        for (int i = this.capacity; i>start; i--){
            Space space = this.spaces[i];
            this.spaces[i] = this.spaces[i-1];
            this.spaces[i-1] = space;
        }
    }

    @Override
    public String toString() {
        return "OwnersFloor{" +
                "spaces=" + Arrays.toString(spaces) +
                ", size=" + capacity +
                "}\n ";
    }
}

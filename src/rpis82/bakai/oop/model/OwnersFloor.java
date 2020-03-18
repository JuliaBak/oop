package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Floor;
import rpis82.bakai.oop.model.interfaces.Space;

public class OwnersFloor implements Floor {

        private Space[] spaces;
        private int size;

        public OwnersFloor() {
            this.spaces = new Space[16];
            this.size = 0;
        }

        public OwnersFloor(int arrayCapacity) {
            this.spaces = new Space[arrayCapacity];
            this.size = 0;
        }

        public OwnersFloor(RentedSpace[] spaces) {
            this.spaces = new Space[spaces.length];
            int number = 0;
            for (Space space : spaces) {
                if (space != null) {
                    this.spaces[number] = space;
                    number++;
                }
            }
            this.size = number;
        }

        public boolean addSpace(Space space) {
            if (this.size < this.spaces.length && this.spaces[this.size] == null) { // если размер массива недостатточно большой - уыеличиваем
                this.spaces[this.size] = space;
            } else {
                int newAmount = this.size;
                increaseArraySize();
                this.spaces[newAmount] = space;
            }
            this.size++;
            return true;
        }

    private void increaseArraySize() {
        Space[] newArray = new Space[this.spaces.length * 2];
        int number = 0;
        for (Space space : spaces) {
            if (space != null) {
                newArray[number] = space;
                number++;
            }
        }
        this.spaces = newArray;
        this.size = number;
    }

        public boolean addSpace(int number, Space space) {
            if (this.spaces.length > number && number > 0) {
                this.spaces[number] = space;
                this.size++;
                return true;
            }
            return false;
        }


        public Space getSpace(String registrationNumber) {
            for (Space space : this.spaces) {
                if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space;
                }
            }
            return null;
        }

        public boolean haveSpace(String registrationNumber) {
            for (Space space : this.spaces) {
                if ((space != null) && (space.getVehicle().getRegistrationNumber().equals(registrationNumber))) {
                    return true;
                }
            }
            return false;
        }

        public Space set(int index, RentedSpace space) {
            Space setSpace = this.spaces[index];
            this.spaces[index] = space;
            return setSpace; // изменяет ссылку класса по его номеру в массиве
        }

    @Override
    public boolean add(Space space) {
        return false;
    }

    @Override
    public boolean add(int index, Space space) {
        return false;
    }

    @Override
    public Space get(int index) {
        return null;
    }

    @Override
    public Space get(String registrationNumber) {
        return null;
    }

    @Override
    public boolean hasSpace(String registrationNumber) {
        return false;
    }

    @Override
    public Space set(int index, Space space) {
        return null;
    }

    public Space remove(int number) {
            Space setSpace = this.spaces[number];
            this.spaces[number] = null;
            moveArray();
            return setSpace;
        }

        public Space remove(String governmentNumber) {
            for (int i = 0;i<this.size;i++) {
                if (this.spaces[i].getVehicle().getRegistrationNumber().equals(governmentNumber)) {
                    Space lastSpace = this.spaces[i];
                    this.spaces[i] = null;
                    moveArray();
                    return lastSpace;
                }
            }
            return null;
        }

        public void moveArray() {
            Space[] newArray = new Space[this.spaces.length];
            int capacity = 0;
            for (Space space : this.spaces) {
                if (space != null) {
                    newArray[capacity] = space;
                    capacity++;
                }
            }
            this.spaces = newArray;
            this.size = capacity;
        }

        public int getSize() {
            return this.size;
        }

        public Sapce[] getSpaces() {
            Space[] resultArray = new Space[this.size];
            int number = 0;
            for (Space space:this.spaces){
                if (space != null){
                    resultArray[number] = space;
                    number++;
                }
            }
            return resultArray;
        }

        public Vehicle[] getVehicles() {
            Vehicle[] vehicles = new Vehicle[getVehicleAmount()];
            int number = 0;
            for (Space space : this.spaces) {
                if ((space != null) && (!space.isEmpty())){
                    vehicles[number] = space.getVehicle();
                    number++;
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

        public Space getSpace(int capacity) {
            return this.spaces[capacity];
        }

    }


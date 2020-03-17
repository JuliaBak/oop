package rpis82.bakai.oop.model;

public class Parking {

        private OwnersFloor[] floors;
        private int size;

        public Parking(int arraySize) {
            this.floors = new OwnersFloor[arraySize];
        }

        public Parking(OwnersFloor[] array) {
            this.floors = new OwnersFloor[array.length];
            int amount = 0;
            for (OwnersFloor ownersFloor : array) {
                if (ownersFloor != null) {
                    this.floors[amount] = ownersFloor;
                    amount++;
                }
            }
            this.size = amount;
        }

        public boolean add(OwnersFloor floor) {
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

        public boolean add(int index, OwnersFloor floor) {
            if (this.floors.length > index && index > 0) {
                this.floors[index] = floor;
                this.size++;
                return true;
            }
            return false;
        }

        public OwnersFloor get(int index) {
            return this.floors[index];
        }

        public OwnersFloor set(int index, OwnersFloor floor) {
            OwnersFloor lastFloor = this.floors[index];
            this.floors[index] = floor;
            return lastFloor;
        }

        public OwnersFloor remove(int index) {
            OwnersFloor lastFloor = this.floors[index];
            this.floors[index] = null;
            moveArray();
            return lastFloor;
        }

        public int getSize() {
            return size;
        }

        public OwnersFloor[] getFloors() {
            return floors;
        }

        public Vehicle[] getVehicles() {
            Vehicle[] vehicles = new Vehicle[getVehicleAmount()];
            int k = 0;
            for (OwnersFloor floor : this.floors) {
                for (Space space : floor.getSpaces()) {
                    if ((space != null) && (!space.isEmpty())) {
                        vehicles[k] = space.getVehicle();
                        k++;
                    }
                }
            }
            return vehicles;
        }

        public OwnersFloor[] getSortedBySizeFloors() {
            OwnersFloor[] array = this.floors;
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < i; j++) {
                    if (array[j].getSize() > array[j + 1].getSize()) {
                        OwnersFloor ownersFloor = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = ownersFloor;
                    }
                }
            }
            return array;
        }

        public Space getSpace(String registrationNumber) {
            for (OwnersFloor ownersFloor : floors) {
                for (Space space : ownersFloor.getSpaces()) {
                    if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                        return space;
                    }
                }
            }
            return null;
        }

        public Space removeSpace(String registrationNumber) {
            for (OwnersFloor ownersFloor : floors) {
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

        public Space setSpace(String registrationNumber, Space space) {
            for (OwnersFloor ownersFloor : this.floors) {
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
            for (OwnersFloor ownersFloor : this.floors) {
                amount += ownersFloor.getVehicleAmount();
            }
            return amount;
        }

        private void increaseArraySize() {
            OwnersFloor[] newArray = new OwnersFloor[this.floors.length * 2];
            int amount = 0;
            for (OwnersFloor floor : floors) {
                if (floor != null) {
                    newArray[amount] = floor;
                    amount++;
                }
            }
            this.floors = newArray;
            this.size = amount;
        }

        private void moveArray() {
            OwnersFloor[] newArray = new OwnersFloor[this.floors.length];
            int k = 0;
            for (OwnersFloor floor : this.floors) {
                if (floor != null) {
                    newArray[k] = floor;
                    k++;
                }
            }
            this.floors = newArray;
            this.size = k;
        }

    }


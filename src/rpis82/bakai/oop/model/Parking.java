package rpis82.bakai.oop.model;

public class Parking {

        private OwnersFloor[] floors;
        private int capacity;

        public Parking(int arraySize) {
            this.floors = new OwnersFloor[arraySize];
        } // принимает число этажей

        public Parking(OwnersFloor[] array) {
            this.floors = new OwnersFloor[array.length];
            int amount = 0;
            for (OwnersFloor ownersFloor : array) {
                if (ownersFloor != null) {
                    this.floors[amount] = ownersFloor;
                    amount++;
                }
            }
            this.capacity = amount;
        }

        public boolean add(OwnersFloor floor) {
            if (this.capacity < this.floors.length && this.floors[this.capacity] == null) {
                this.floors[this.capacity] = floor;
            } else {
                int size = this.capacity;
                increaseArraySize();
                this.floors[size] = floor;
            }
            this.capacity++;
            return true;
        } // добавдяющий элемент

        public boolean add(int number, OwnersFloor floor) {
            if (this.floors.length > number && number > 0) {
                this.floors[number] = floor;
                this.capacity++;
                return true;
            }
            return false;
        }

        public OwnersFloor get(int number) {//возвращающий ссылку на экземпляр класса OwnersFloor по его номеру в массиве
            return this.floors[number];
        }

        public OwnersFloor set(int number, OwnersFloor floor) {//изменяющий ссылку на экземпляр класса OwnersFloor по его номеру в массиве
            OwnersFloor lastFloor = this.floors[number];
            this.floors[number] = floor;
            return lastFloor;
        }

        public OwnersFloor remove(int number) {//удаляющий этаж из массива по его номеру
            OwnersFloor lastFloor = this.floors[number];
            this.floors[number] = null;
            moveArray();
            return lastFloor;
        }

        public int getCapacity() { //возвращающий число этажей
            return capacity;
        }

        public OwnersFloor[] getFloors() {//возвращающий массив этажей
            return floors;
        }

        public Vehicle[] getVehicles() {
            Vehicle[] vehicles = new Vehicle[getVehicleAmount()];
            int number = 0;
            for (OwnersFloor floor : this.floors) {
                for (Space space : floor.getSpaces()) {
                    if ((space != null) && (!space.isEmpty())) {
                        vehicles[number] = space.getVehicle();
                        number++;
                    }
                }
            }
            return vehicles;
        }

        public OwnersFloor[] getSortedBySizeFloors() { //возвращающий массив этажей, отсортированный по возрастанию числа парковочных мест на этаже
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
            this.capacity = amount;
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
            this.capacity = k;
        }

    }


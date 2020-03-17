package rpis82.bakai.oop.model;

public class OwnersFloor {

        private Space[] spaces;
        private int size;

        public OwnersFloor() {
            this.spaces = new Space[16];
            this.size = 0;
        }

        public OwnersFloor(int arraySize) {
            this.spaces = new Space[arraySize];
            this.size = 0;
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
            this.size = amount;
        }

        public boolean add(Space space) {
            if (this.size < this.spaces.length && this.spaces[this.size] == null) {
                this.spaces[this.size] = space;
            } else {
                int index = this.size;
                increaseArraySize();
                this.spaces[index] = space;
            }
            this.size++;
            return true;
        }

        public boolean add(int index, Space space) {
            if (this.spaces.length > index && index > 0) {
                this.spaces[index] = space;
                this.size++;
                return true;
            }
            return false;
        }

        public Space get(int index) {
            return this.spaces[index];
        }

        public Space get(String registrationNumber) {
            for (Space space : this.spaces) {
                if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space;
                }
            }
            return null;
        }

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

        public Space remove(int id) {
            Space lastSpace = this.spaces[id];
            this.spaces[id] = null;
            moveArray();
            return lastSpace;
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

        public int getSize() {
            return this.size;
        }

        public Space[] getSpaces() {
            Space[] result = new Space[this.size];
            int k = 0;
            for (Space space:this.spaces){
                if (space != null){
                    result[k] = space;
                    k++;
                }
            }
            return result;
        }

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
            this.size = k;
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
            this.size = amount;
        }

    }


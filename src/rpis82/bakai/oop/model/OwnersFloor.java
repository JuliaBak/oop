package rpis82.bakai.oop.model;

public class OwnersFloor {

        private Space[] spaces;
        private int size;

        public OwnersFloor() { //конструктор инициирующий массив из 16 элеметов
            this.spaces = new Space[16];
            this.size = 0;
        }

        public OwnersFloor(int arrayCapacity) { //принимающий целое число - емкость массива
            this.spaces = new Space[arrayCapacity];
            this.size = 0;
        }

        public OwnersFloor(Space[] spaces) { //принимающий массив парковочных мест, копирование элеметов в новый массив
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

        public boolean addSpace(Space space) { //метод добавляющий парковочное место в конец массива
            if (this.size < this.spaces.length && this.spaces[this.size] == null) { // если размер массива недостатточно большой - уыеличиваем
                this.spaces[this.size] = space;
            } else {
                int newAmount = this.size;
                increaseArraySize(); //размер массива увеличивается,если не хватает места под парковку
                this.spaces[newAmount] = space;
            }
            this.size++;
            return true; // влзвращает true после выполнения операции
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

        public boolean addSpace(int number, Space space) { //добавляющий место в определенное пространство
            if (this.spaces.length > number && number > 0) {// принимает номер  и ссылку на объект
                this.spaces[number] = space;
                this.size++;
                return true; // возвращает true после выполнения операции
            }
            return false;
        }


        public Space getSpace(String registrationNumber) { // параметр -госю номер
            for (Space space : this.spaces) {
                if (space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space; // вовращает ссылку на экземпляр класса space по его регистр. номеру
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
            return false; // вовращает true если экземпляр есть в массиве
        }

        public Space set(int index, Space space) {
            Space setSpace = this.spaces[index];
            this.spaces[index] = space;
            return setSpace; // изменяет ссылку класса по его номеру в массиве
        }

        public Space remove(int number) {
            Space setSpace = this.spaces[number];
            this.spaces[number] = null;
            moveArray();
            return setSpace; // удалить парковочное место по его номеру
        }

        public Space remove(String governmentNumber) { //удаляет место по его гос номеру
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

        public void moveArray() {  // метод сдвига массива
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

        public Space[] getSpaces() {
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
        } // число транспортных средств

        public Space getSpace(int capacity) {
            return this.spaces[capacity];// вовращает массив мест на папрковке  с аднным значением
        }

    }


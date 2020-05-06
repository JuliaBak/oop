package rpis82.bakai.oop.model;
import rpis82.bakai.oop.model.interfaces.Floor;
import rpis82.bakai.oop.model.interfaces.Space;

import java.time.LocalDate;
import java.util.Arrays;
import java.lang.Object;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OwnersFloor implements Floor, Cloneable {

    private Space[] spaces;
    private int capacity = 0;
    private final int spacesNumber = 16;

    public OwnersFloor() { this.spaces = new Space[spacesNumber]; }

    public OwnersFloor(int capacity) {
        this.spaces = new Space[capacity];
    }

    public OwnersFloor(Space[] spaces) {
        copyFrom(spaces);
        this.capacity = spaces.length;
    }

    public  Space[]  copyFrom(Space[] spaces)
    {
        Space[] newSpaces = new Space[spaces.length];
        System.arraycopy(spaces, 0 , newSpaces, 0, spaces.length);
        return newSpaces;
    }

    public boolean addSpace(Space space) throws NullPointerException{

        Objects.requireNonNull(space, "Space is null");

        if (!isEnoughCapacity()) {
            grow();
        }
        this.spaces[this.capacity++] = space;
        return true;
    }

    private boolean isEnoughCapacity()
    {
        return this.capacity < this.spaces.length && this.spaces[this.capacity] == null;
    }

    private void grow() {
        Space[] newSpaces = new Space[this.spaces.length * 2];
        System.arraycopy(spaces, 0, newSpaces, 0, spaces.length);
        this.spaces = newSpaces;
        this.capacity = newSpaces.length;
    }

    public boolean addSpaceByIndex(int index, Space space) throws IndexOutOfBoundsException, NullPointerException{

        if ( index > this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        Objects.requireNonNull(space, "Space is null");

        if (this.spaces.length > index && index > 0) {
            shiftArrayByIndex(index);
            this.spaces[index] = space;
            this.capacity++;
            return true;
        }
        return false;
    }

    private void shiftArrayByIndex(int index) {

        if (this.capacity + 1 >= this.spaces.length){
            grow();
        }
        for (int i = this.capacity; i > index; i--){
            Space space = this.spaces[i];
            this.spaces[i] = this.spaces[i-1];
            this.spaces[i-1] = space;
        }
    }

    @Override //возвращающий ссылку на экземпляр класса Space по его номеру в массиве
    public Space getSpaceByIndex(int index) throws IndexOutOfBoundsException {

        if ( index > this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        return this.spaces[index];
    }

    @Override //возвращающий ссылку на экземпляр класса Space, с которым связанно тс с определенным гос. номером.
    public Space getSpaceByRegNumber(String registrationNumber) throws RegistrationNumberFormatException, NullPointerException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");


        for (int index = 0; index < spaces.length; index++) {
            if (equalsToRegNumber(spaces[index], registrationNumber)) {
                return spaces[index];
            }
        }
        throw new NoSuchElementException("There's no such space");
    }

    public boolean isRegNumberFormatOK(String text){
        Pattern pattern = Pattern.compile("[ABEKMHOPCTYX][0-9]{3}[ABEKMHOPCTYX]{2}[0-9]{2,3}");
        Matcher matcherReg = pattern.matcher(text);
        return matcherReg.matches();
    }

    public boolean equalsToRegNumber(Space space, String registrationNumber)
    {
        return space.getVehicle().getRegistrationNumber().equals(registrationNumber);
    }

    @Override  //определяющий, есть ли на этаже парковочное место, связанное с тс с определенным гос. номером.
    public boolean hasSpaceByRegNumber(String registrationNumber) throws RegistrationNumberFormatException, NullPointerException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");

        for (int index = 0; index < spaces.length; index++) {
            if ((spaces[index] != null) && equalsToRegNumber(spaces[index], registrationNumber)) {
                return true;
            }
        }
        return false;
    }

    //изменяющий ссылку на экземпляр класса Space по его номеру в массиве
    /*
    Принимает в качестве параметров номер и ссылку на экземпляр класса Space. Возвращает ссылку, которую заменили
     */
    public Space setSpaceByIndex(int index, Space space) throws IndexOutOfBoundsException, NullPointerException{

        if ( index > this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        Objects.requireNonNull(space, "Person is null");

        Space setSpace = this.spaces[index];
        this.spaces[index] = space;
        return setSpace;
    }

    @Override
    /*
    удаляющий парковочное место из массива по его номеру
     Возвращает удаленную из массива ссылку на экземпляр класса Space
     */
    public Space removeByIndex(int index) throws IndexOutOfBoundsException {

        if ( index > this.capacity | index < 0) throw new IndexOutOfBoundsException("Index isn't acceptable");

        Space removedSpace = this.spaces[index];
        this.spaces[index] = null;
        shift();
        return removedSpace;
    }

    @Override //удаляющий парковочное место из массива по гос. номеру автомобиля
    public Space removeByRegNumber(String registrationNumber) throws RegistrationNumberFormatException, NullPointerException, NoSuchElementException {

        if (!isRegNumberFormatOK(registrationNumber)){
            throw new RegistrationNumberFormatException("Reg Number has wrong format");
        }
        Objects.requireNonNull(registrationNumber,"Reg Number is null");

        Space removedSpace;
        for (int index = 0; index < this.capacity; index++) {
            if (equalsToRegNumber(spaces[index], registrationNumber)) {
                removedSpace = this.spaces[index];
                this.spaces[index] = null;
                shift();
                return removedSpace;
            }
        }
        throw new NoSuchElementException("There's no such space");
    }


    public void shift() {
        for (int i = 0; i < spaces.length; i++)  {
            if (spaces[i] != null) {
                this.spaces[i++] = spaces[i];
            }
        }
        this.capacity = this.spaces.length;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    } //возвращающий общее число парковочных мест на этаже

    @Override //возвращающий массив парковочных мест на этаже
    public Space[] getSpaces() {
        return copyFrom(spaces);
    }

    private   boolean isEmptySpaces(Space space)
    {
        return  (space != null) ;
    }

    @Override //возвращающий массив транспортных средств на этаже
    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[getVehiclesNumber()];
        int number = 0;
        for (int index = 0; index < spaces.length; index++) {
            if ((spaces[index] != null) && (spaces[index].isEmpty())){
                vehicles[number] = spaces[index].getVehicle();
                number++;
            }
        }
        return vehicles;
    }

    public int getVehiclesNumber(){
        int number = 0;
        for (int index = 0; index < spaces.length; index++){
            if (isEmptySpaces(spaces[index])){
                number++;
            }
        }
        return number;
    }

    //Lab 3
    @Override //возвращающий массив парковочных мест с ТС заданного типа
    public Space[] getSpacesByVehicleType(VehiclesTypes vehicleType) throws NullPointerException {

        Objects.requireNonNull(vehicleType, "Vehicle Type is null");

        Space[] spaces = new Space[this.capacity];
        int number = 0;
        for (int index = 0; index < spaces.length; index++){
            if (equalsToType(spaces[index], vehicleType)){
                spaces[number++] = spaces[index];
            }
        }
        return spaces;
    }

    private boolean equalsToType(Space space, VehiclesTypes vehicleType)
    {
        return space.getVehicle().getType().equals(vehicleType);
    }

    @Override //возвращающий массив не занятых парковочных мест
    public Space[] getEmptySpaces() {
        Space[] spaces = new Space[this.capacity];
        int number = 0;
        for (int index = 0; index < spaces.length; index++){
            if (spaces[index].isEmpty()){
                spaces[number++] = spaces[index];
            }
        }
        return spaces;
    }

    //Lab 4
    @Override
    public String toString() {
        StringBuilder builtString = new StringBuilder("Spaces:\n");
        for(int i = 0; i < this.capacity; i++)
        {
            builtString.append(this.spaces[i].toString()).append("\n");
        }
        return builtString.toString();
    }

    @Override
    public int hashCode() {

        int resultedHash = 0;
        for (int i = 0; i < this.capacity; i++)
        {
            resultedHash ^= spaces[i].hashCode();
        }
        return 71 * (resultedHash & Objects.hashCode(capacity));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OwnersFloor ownersFloor = (OwnersFloor) obj;
        return capacity == ownersFloor.capacity &&
                Arrays.equals(spaces, ownersFloor.spaces);
    }

    public OwnersFloor clone() throws CloneNotSupportedException{
        OwnersFloor clone = (OwnersFloor) super.clone();
        clone.spaces = this.getSpaces().clone();
        return clone;
    }

    @Override
    public boolean isSpaceRemoved(Space space) throws NullPointerException{

        Objects.requireNonNull(space, "Space is null");

        for (int i = 0; i < this.spaces.length; i++){
            if (this.spaces[i].equals(space)){
                removeByIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOfSpace(Space space) throws NullPointerException{

        Objects.requireNonNull(space, "Space is null");

        for (int index = 0; index< this.spaces.length; index++){
            if (this.spaces[index].equals(space)){
                return index;
            }
        }
        return 0;
    }

    @Override
    public int spacesNumberByPerson(Person person) throws NullPointerException{
        Objects.requireNonNull(person, "Person is null");

        int number = 0;
        for (int index = 0; index< this.spaces.length; index++) {
            if (spaces[index].getPerson().equals(person)) {
                number++;
            }
        }
        return number;
    }

    //Lab 5
    @Override
    public LocalDate getClosestRentalEndDate() throws NoRentedSpaceException {

        LocalDate closestDate = null;
        for(int index = 0; index < this.spaces.length; index++) {
                closestDate = ((RentedSpace) spaces[index]).getRentalEndDate();
        }
        //проверка есть ли rented
            if (closestDate == null){
                throw new NoRentedSpaceException("Rental End Date is null, there's no Closest Rental Date");
            }

        for(int index = 0; index < this.spaces.length; index++){

                if (((RentedSpace) spaces[index]).getRentalEndDate().isBefore(closestDate)){
                    closestDate = ((RentedSpace) spaces[index]).getRentalEndDate();
            }
        }
        return closestDate;
    }

    @Override
    public Space getSpaceByClosestRentalEndDate() throws NoRentedSpaceException {

        RentedSpace closestDateSpace = null;

        for(int index = 0; index < this.spaces.length; index++){
                closestDateSpace = (RentedSpace) spaces[index];
        }
            if (closestDateSpace == null){
                throw new NoRentedSpaceException("Space is null, there's no Closest Rental Date Space");
            }

        for(int index = 0; index < this.spaces.length; index++){
                if (((RentedSpace) spaces[index]).getRentalEndDate().isBefore(closestDateSpace.getRentalEndDate())){
                    closestDateSpace = (RentedSpace) spaces[index];
            }
        }
        return closestDateSpace;
    }
}

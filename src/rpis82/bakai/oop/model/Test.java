package rpis82.bakai.oop.model;

import rpis82.bakai.oop.model.interfaces.Floor;
import rpis82.bakai.oop.model.interfaces.Space;

import java.time.LocalDate;
import java.util.Arrays;


public class Test implements Cloneable {

    public static void main(String[] args)  {
        lab6tests();
      //  lab5tests();
        // lab4tests();
        // lab2tests();
        // lab3test();
    }


    public static void  lab6tests()
    {

    }

    public static void  lab5tests()
    {
        Person person1 = new Person("Ivan", "Ivanov");
        Person person2 = new Person("Eva", "Gtt");

        Vehicle vehicle1 = new Vehicle("A654BM64", "Toyota", "Camry",VehiclesTypes.CROSSOVER);
        Vehicle vehicle2 = new Vehicle("M354AF23", "Toyota", "Camry",VehiclesTypes.CROSSOVER);


        System.out.println(person1.toString());
        // System.out.println(person2.toString());

        System.out.println(vehicle1.toString());

        OwnedSpace ownedSpace1 = new OwnedSpace(person1, vehicle1);
        System.out.println(ownedSpace1.toString());

        RentedSpace rentedSpace1 = new RentedSpace(person1, vehicle1, LocalDate.now(), LocalDate.of(2021, 10, 21));
        System.out.println(rentedSpace1.toString());

        Space space1 = new RentedSpace(person1, vehicle1, LocalDate.now(), LocalDate.of(2022, 2,13));
        Space space2 = new RentedSpace(person2, vehicle2, LocalDate.now(), LocalDate.of(2024, 7,1));

        System.out.println(rentedSpace1.getPeriodBeforeEnd());
        System.out.println(rentedSpace1.getRentalPeriod());

        rentedSpace1.setRentEndDate(LocalDate.of(2031, 4,10));
        System.out.println(rentedSpace1.getRentalEndDate());

        System.out.println(space1.toString());

        Space[] spaces1 = new Space[2];
        spaces1[0] = space1;
        spaces1[1] = space2;

        RentedSpacesFloor rentedSpacesFloor1 = new RentedSpacesFloor(spaces1);

        System.out.println(rentedSpacesFloor1.toString());

        try {
            System.out.println( rentedSpacesFloor1.getClosestRentalEndDate());
        } catch (NoRentedSpaceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println( rentedSpacesFloor1.getSpaceByClosestRentalEndDate());
        } catch (NoRentedSpaceException e) {
            e.printStackTrace();
        }

        OwnersFloor ownersFloor1 = new OwnersFloor(spaces1);

        try {
            System.out.println(ownersFloor1.getClosestRentalEndDate());
        } catch (NoRentedSpaceException e) {
            e.printStackTrace();
        }
    }


  /*  public static void lab4tests() throws CloneNotSupportedException {
        Person person1 = new Person("Ivan", "Ivanov");
        Person person2 = new Person("Petr", "Petrov");
        Person person3 = new Person("Willy", "Free");
        Person clonedPerson;

        Vehicle vehicle1 = new Vehicle("876", "Toyota", "Camry",VehiclesTypes.NONE);
        Vehicle vehicle2 = new Vehicle("264", "BMW", "X6",VehiclesTypes.OTHER);
        Vehicle vehicle3 = new Vehicle("432", "Lada", "XRay", VehiclesTypes.CROSSOVER);

        Vehicle clonedVehicle = vehicle2.clone();
        clonedPerson = person1.clone();

        System.out.println(person1.toString());
        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());

        System.out.println(clonedPerson.toString());
        System.out.println(clonedPerson.getSurname());
        System.out.println(clonedVehicle.toString());

        OwnedSpace ownedSpace1 = new OwnedSpace(clonedPerson, clonedVehicle);
        System.out.println(ownedSpace1.toString());

        OwnedSpace ownedSpace2 = ownedSpace1.clone();
        System.out.println(ownedSpace2.toString());
        ownedSpace2.setVehicle(vehicle1);
        System.out.println(ownedSpace2.toString());

        RentedSpace rentedSpace1 = new RentedSpace(person2, vehicle1);
        RentedSpace rentedSpaceClone = rentedSpace1.clone();

        System.out.println(rentedSpaceClone.toString());

        Space space1 = new RentedSpace(person1, vehicle1);
        Space space2 = new RentedSpace(person2, vehicle2);

        Space[] spaces1 = new Space[2];
        spaces1[0] = space1;
        spaces1[1] = space2;

        RentedSpacesFloor rentedSpacesFloor1 = new RentedSpacesFloor(spaces1);

        System.out.println("Hash:");

        System.out.println("Person Hash = " + person1.hashCode()); //Person
        System.out.println("Person2 Hash = " + person2.hashCode()); //Person
        System.out.println("Person3 Hash = " + person3.hashCode()); //Person

        System.out.println("Vehicle Hash = " +  vehicle3.hashCode()); //Vehicle
        System.out.println("Vehicle1 Hash = " +  vehicle1.hashCode()); //Vehicle

        System.out.println( "ownedSpace Hash = " + ownedSpace1.hashCode()); //ownedSpace

        System.out.println("rentedSpace Hash = " + rentedSpaceClone.hashCode()); //rentedSpace

        System.out.println("rentedSpacesFloor Hash = " + rentedSpacesFloor1.hashCode()); //rentedSpacesFloor


        Space spaceOne = new RentedSpace(person1, vehicle1);
        Space spaceTwo = new RentedSpace(person2, vehicle2);

        System.out.println() ;

        Space[] spaces = new Space[2];
        spaces[0] = spaceOne;
        spaces[1] = spaceTwo;

        OwnersFloor ownersFloor1 = new OwnersFloor(spaces);
        OwnersFloor ownersFloorClone = ownersFloor1.clone();

        Floor floor1 = new OwnersFloor(4);
        floor1.addSpaceByIndex(0, spaceOne);
        floor1.addSpaceByIndex(1, spaceTwo);

        Floor floor2 = new OwnersFloor(3);
        floor2.addSpaceByIndex(1, spaceTwo);

        Parking parkingOne = new Parking(4);
        parkingOne.addFloorByIndex(0, floor1);
        parkingOne.addFloorByIndex(1, floor2);

        System.out.println(Arrays.toString(parkingOne.getFloorsWithPerson(person1)));


        System.out.println(ownersFloor1.indexOfSpace(spaceOne));
        System.out.println(ownersFloorClone.toString());

    }



    public static void lab3test() {
        Person person1 = new Person("Duck", "Ducky");
        Person person2 = new Person("Ven", "Tori");

        Vehicle vehicle1 = new Vehicle("876", "Toyota", "Camry",VehiclesTypes.NONE);
        Vehicle vehicle2 = new Vehicle("264", "BMW", "X6",VehiclesTypes.NONE);

        OwnersFloor floorOwner = new OwnersFloor();

        Space space1 =  new RentedSpace(person1, vehicle1);
        Space space2 =  new RentedSpace(person2, vehicle2);

        System.out.println(space1.getVehicle());

        Floor floor1 = new OwnersFloor(4);
        floor1.addSpace(space1);

        Floor floor2 = new OwnersFloor(3);
        floor2.addSpace(space2);

        System.out.println(Arrays.toString(floor1.getSpaces()));
        System.out.println(Arrays.toString(floor1.getEmptySpaces()));

        Parking parkingSpace = new Parking(4);
        parkingSpace.addLastFloor(floor1);
        parkingSpace.addLastFloor(floor2);


        System.out.println(parkingSpace.get(1));


        System.out.println(Arrays.toString(parkingSpace.getFloors()));
        parkingSpace.addFloorByIndex(1, floor2);
        System.out.println(parkingSpace.get(1));

    }



    public static void lab2tests() {
        Person person1 = new Person("Frick", "Jason");
        Person person2 = new Person("Two", "One");

        Vehicle vehicle1 = new Vehicle("876", "Lada", "XRay", VehiclesTypes.NONE);
        Vehicle vehicle2 = new Vehicle("264", "BMW", "X6", VehiclesTypes.NONE);

        Space spaceOne = new RentedSpace(person1, vehicle1);
        Space spaceTwo = new RentedSpace(person2, vehicle2);
        Space emptySpace = new RentedSpace();

        Space[] spaces = new Space[3]; //инициализация мест в массиве
        spaces[0] = spaceOne;
        spaces[1] = spaceTwo;
        spaces[2] = emptySpace;

        OwnedSpace ownedSpace1 = new OwnedSpace(person1, vehicle1);
        System.out.println(ownedSpace1.getPerson());
        System.out.println(ownedSpace1.isEmpty());
        System.out.println(ownedSpace1.getVehicle());

        OwnersFloor floorOwner1 = new OwnersFloor();
        floorOwner1.addSpace(spaceOne);
        floorOwner1.addSpace(spaceTwo);
        System.out.println(floorOwner1.getCapacity());

        floorOwner1.addSpaceByIndex(0, spaceOne);

        System.out.println(floorOwner1.getSpaceByIndex(0));
        System.out.println(Arrays.toString(floorOwner1.getSpaces()));
        System.out.println(Arrays.toString(floorOwner1.getVehicles()));



        RentedSpacesFloor rentedSpacesFloor = new RentedSpacesFloor(spaces);
        System.out.println(Arrays.toString(rentedSpacesFloor.getSpaces()));
        System.out.println(Arrays.toString(rentedSpacesFloor.getVehicles()));

        Vehicle[] vehiclesArray = rentedSpacesFloor.getVehicles();

        for (Vehicle space : vehiclesArray) {
            System.out.println(space.toString());
        }

    }

        /*public static void lab1tests(){
            Person person1 = new Person("Grey","Dorian");
            Person person2 = new Person("Grace","Rick");

            System.out.println(person1.getName());
            System.out.println(person2.getSurname());

            Vehicle vehicle1 = new Vehicle("657","Toyota","Camry");
            Vehicle vehicle2 = new Vehicle("342","Bentley","Bentayga");

            System.out.println(vehicle1.getRegistrationNumber());
            System.out.println(vehicle1.getModel());
            System.out.println(vehicle2.getManufacturer());

            Vehicle emptyVehicle = new Vehicle();
            Person emptyPerson = Person.UNKNOWN_PERSON;

            System.out.println(emptyVehicle.getRegistrationNumber());

            Space space1 = new Space(person1,vehicle1);
            Space space2 = new Space(person2,vehicle2);

            System.out.println(space1.getVehicle());
            System.out.println (space2.getPerson());

            Space emptySpaceLot = new Space(emptyPerson, emptyVehicle) {
            };

            OwnersFloor floor1 = new OwnersFloor(2);
            floor1.addSpace(space1);
            floor1.addSpace(space2);

           System.out.println(floor1.remove(1));
            System.out.println(floor1.getVehicleAmount());
            System.out.println(floor1.getSize());

            floor1.addSpace(emptySpaceLot);
            floor1.addSpace(emptySpaceLot);

            OwnersFloor floor2 = new OwnersFloor();

            System.out.println(floor2.addSpace(1, space1));
            floor2.addSpace(space2);
            floor2.addSpace(space2);

            floor2.addSpace(emptySpaceLot);

            Parking parking = new Parking(3);
            parking.add(floor1);
            parking.add(floor2);

            OwnersFloor[] sortedOwners = parking.getSortedBySizeFloors();
            System.out.println(sortedOwners[1].getSize());

        }
        */


}




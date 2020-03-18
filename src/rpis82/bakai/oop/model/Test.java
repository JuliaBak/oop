package rpis82.bakai.oop.model;

public class Test {

        public static void main (String[] args) {
            lab1tests();
        }

        public static void lab1tests(){
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

            RentedSpace rentedSpace1 = new RentedSpace(person1,vehicle1);
            RentedSpace rentedSpace2 = new RentedSpace(person2,vehicle2);

            System.out.println(rentedSpace1.getVehicle());
            System.out.println (rentedSpace2.getPerson());

            RentedSpace emptyRentedSpace = new RentedSpace(emptyPerson,emptyVehicle);

            OwnersFloor floor1 = new OwnersFloor(2);
            floor1.addSpace(rentedSpace1);
            floor1.addSpace(rentedSpace2);

           System.out.println(floor1.remove(1));
            System.out.println(floor1.getVehicleAmount());
            System.out.println(floor1.getSize());

            floor1.addSpace(emptyRentedSpace);
            floor1.addSpace(emptyRentedSpace);

            OwnersFloor floor2 = new OwnersFloor();

            System.out.println(floor2.addSpace(1, rentedSpace1));
            floor2.addSpace(rentedSpace2);
            floor2.addSpace(rentedSpace2);

            floor2.addSpace(emptyRentedSpace);

            Parking parking = new Parking(3);
            parking.add(floor1);
            parking.add(floor2);

            OwnersFloor[] sortedOwners = parking.getSortedBySizeFloors();
            System.out.println(sortedOwners[1].getSize());

        }

        public static void lab2tests(){

        }
    }

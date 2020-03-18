package rpis82.bakai.oop.model;

public class Test {

        public static void main (String[] args) {
            lab1tests();
        }

        public static void lab1tests(){
            Person person1 = new Person("Grey","Dorian");
            Person person2 = new Person("McAwan","Rick");

            System.out.println(person1.getName());
            System.out.println(person2.getSurname());

            Vehicle vehicle1 = new Vehicle("657","Toyota","Camry");
            Vehicle vehicle2 = new Vehicle("342","Bentley","Bentayga");

            System.out.println(vehicle1.getRegistrationNumber());
            System.out.println(vehicle1.getModel());
            System.out.println(vehicle2.getManufacturer());

            Vehicle emptyVehical = new Vehicle();
            Person emptyPerson = Person.UNKNOWN_PERSON;

            System.out.println(emptyVehical.getRegistrationNumber());

            Space space1 = new Space(person1,vehicle1);
            Space space2 = new Space(person2,vehicle2);

            System.out.println(space1.getVehicle());
            System.out.println (space2.getPerson());

            Space emptySpace = new Space(emptyPerson,emptyVehical);

            OwnersFloor floor1 = new OwnersFloor(2);
            floor1.addSpace(space1);
            floor1.addSpace(space2);

            System.out.println(floor1.getVehicleAmount());
            System.out.println(floor1.getSize());

            floor1.addSpace(emptySpace);
            floor1.addSpace(emptySpace);

            OwnersFloor floor2 = new OwnersFloor();

            System.out.println(floor2.addSpace(1, space1));
            floor2.addSpace(space2);
            floor2.addSpace(space2);

            floor2.addSpace(emptySpace);

            Parking parking = new Parking(3);
            parking.add(floor1);
            parking.add(floor2);

            OwnersFloor[] sortedOwners = parking.getSortedBySizeFloors();
            System.out.println(sortedOwners[1].getSize());
        }
    }

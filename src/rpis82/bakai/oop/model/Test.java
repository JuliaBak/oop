package rpis82.bakai.oop.model;

public class Test {

        public static void main (String[] args) {
            lab1tests();
        }

        public static void lab1tests(){
            Person person1 = new Person("Grey","Dorian");
            Person person2 = new Person("McAwan","Rick");
            Person person3 = new Person("Grace","Billy");

            Vehicle testVehicle1 = new Vehicle("657","Toyota","Camry");
            Vehicle testVehicle2 = new Vehicle("342","Bentley","Bentayga");
            Vehicle testVehicle3 = new Vehicle("835","BMW","Second");

            Vehicle emptyVechical = new Vehicle();
            Person emptyPerson = Person.UNKNOWN_PERSON;

            Space testSpace1 = new Space(person1,testVehicle1);
            Space testSpace2 = new Space(person2,testVehicle2);
            Space testSpace3 = new Space(person3,testVehicle3);
            Space emptySpace = new Space(emptyPerson,emptyVechical);

            OwnersFloor testFloor1 = new OwnersFloor(3);
            testFloor1.add(testSpace1);
            testFloor1.add(testSpace2);
            testFloor1.add(testSpace3);
            testFloor1.add(emptySpace);
            testFloor1.add(emptySpace);

            OwnersFloor testFloor2 = new OwnersFloor();
            testFloor2.add(testSpace2);
            testFloor2.add(testSpace2);
            testFloor2.add(testSpace3);
            testFloor2.add(emptySpace);

            Parking parking = new Parking(4);
            parking.add(testFloor1);
            parking.add(testFloor2);


            OwnersFloor[] sortedOwners = parking.getSortedBySizeFloors();
            System.out.println(sortedOwners[1].getSize());
        }
    }

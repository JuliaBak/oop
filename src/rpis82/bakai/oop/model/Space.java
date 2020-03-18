package rpis82.bakai.oop.model;

public class Space {
    public Person person;
    public Vehicle vehicle;
    public Space()
    {
        this.person.Surname = Person.UNKNOWN_PERSON.Surname;
        this.person.Name = Person.UNKNOWN_PERSON.Name;
        this.vehicle.manufacturer = Vehicle.EMPTY_VEHICLE.manufacturer;
        this.vehicle.registrationNumber = Vehicle.EMPTY_VEHICLE.registrationNumber;
        this.vehicle.model = Vehicle.EMPTY_VEHICLE.model;
    }
    public Space(Person person, Vehicle vehicle)
    {
    this.vehicle = vehicle;
    this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public boolean isEmpty()
    {
        if(this.vehicle == null || this.vehicle.registrationNumber == null)
        {
            System.out.println(false);
        }

        return false;
    }
    public static void main(String[] args)
    {
     Space space = new Space();
    }
}

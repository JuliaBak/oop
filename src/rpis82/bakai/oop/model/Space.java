package rpis82.bakai.oop.model;

public class Space {
    public Person person; //protected?
    public Vehicle vehicle;
    public Space ()
    {
        this.person.Surname = person.UNKNOWN_PERSON.Surname;
        this.person.Name = person.UNKNOWN_PERSON.Name;
        this.vehicle.manufacturer = "";
        this.vehicle.registrationNumber = "";
        this.vehicle.model = "";
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
            System.out.println(false); //изменить на просто false
        }

        return false;
    }
    public static void main(String[] args)
    {
     Space space = new Space();
    }
}

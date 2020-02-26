package rpis82.bakai.oop.model;

public class Space {
    protected Person person; //protected?
    protected Vehicle vehicle;
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
    public void isEmpty()
    {
        if(this.vehicle.equals(null) || this.vehicle.registrationNumber.equals(null) )
        {
            System.out.println(false); //изменить на просто false
        }

    }
    public static void main(String[] args) {



    }
}

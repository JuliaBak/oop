package rpis82.bakai.oop.model;

public final class Person {
   public final String Surname, Name;
    public static Person UNKNOWN_PERSON = new Person(" ", " ");
    public Person(String Surname, String Name)
    {
        this.Name = Name;
        this.Surname = Surname;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + Surname + '\'' +
                ", firstName='" + Name + '\'' +
                '}';
    }

}

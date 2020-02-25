package rpis82.bakai.oop.model;

public class Person {
    String Surname, Name;

    public Person(String Surname, String Name)
    {
        this.Name = Name;
        this.Surname = Surname;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getSurname() {
        return Surname;
    }
    public static void main(String[] args) {
        Person person1 = new Person("Bakai", "Julia");
        System.out.println(person1.Name);
        System.out.println(person1.Surname);
    }
}

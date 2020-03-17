package rpis82.bakai.oop.model;

public class Person {
   public String Surname, Name;
    public static Person UNKNOWN_PERSON = new Person(" ", " ");
    public Person(String Surname, String Name)
    {
        this.Name = Name;
        this.Surname = Surname;
    }
  /*  public void setName(String name) {
        Name = name;
    }
    */

    public String getName() {
        return Name;
    }

  /*  public void setSurname(String surname) {
        Surname = surname;
    }
    */

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
    public static void main(String[] args) {

        Person person1 = new Person("Bakai", "Julia");
        Person person2 = new Person("Ivanov", "Sergey");
        Person person3 = new Person("Kirov", "Vasia");
        System.out.println(person1.Name);
        System.out.println(person1.Surname);
    }
}

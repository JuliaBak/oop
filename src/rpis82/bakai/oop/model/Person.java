package rpis82.bakai.oop.model;

public final class Person {
   public final String surname, name;
    public static Person EMPTY_PERSON = new Person(" ", " ");

    public Person(String surname, String name)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Surname='" + surname + '\'' +
                ", Name='" + name + '\'' +
                '}';
    }

}

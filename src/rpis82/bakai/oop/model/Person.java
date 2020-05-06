package rpis82.bakai.oop.model;

import java.util.Objects;

public final class Person implements Cloneable {
    public final String surname, name;
    public static Person EMPTY_PERSON = new Person(" ", " ");

    public Person  (String surname, String name) throws NullPointerException
    {
        this.name =  Objects.requireNonNull(name, " Name is null");
        this.surname = Objects.requireNonNull(surname, " Surname is null");;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    //Lab4
    @Override //переопределение класса toString, по формату, lab4
    public String toString() {
        return String.format("<%s> <%s>", this.surname, this.name);

    }

    @Override
    public int hashCode()
    {
        return surname.hashCode() & name.hashCode();
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return
                this.name.equals(person.name) &&
                        this.surname.equals(person.surname) ;
    }

    @Override
    public Person clone() throws CloneNotSupportedException
    {
        return (Person) super.clone();
    }

}

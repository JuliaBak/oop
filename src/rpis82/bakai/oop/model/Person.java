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

    //Lab4
    @Override //переопределение класса toString, по формату, lab4
    public String toString() {
            return String.format("<%s> <%s>", this.surname, this.name);

    }

    @Override
    public int hashCode() /*вычисляет хэш-код как произведение хэш-кодов всех атрибутов
    класса */
    {
        return surname.hashCode()*name.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return //проверяем эквивалентность всех атрибутов
                this.name.equals(person.name) &&
                        this.surname.equals(person.surname) ;
    }

    @Override
    public Person clone() throws CloneNotSupportedException
    //Возвращает «клон» объекта
    {
        return (Person) super.clone();
    }

}

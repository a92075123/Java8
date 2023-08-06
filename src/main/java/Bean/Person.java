package Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@EqualsAndHashCode
public class Person implements Comparable<Person>{
    private String name;
    private int age;
    private List<Book> book;

    public Person() {
    }

    public Person(String name, int age, List<Book> book) {
        this.name = name;
        this.age = age;
        this.book= book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", book=" + book +
                '}';
    }

        @Override
        public int compareTo(Person person) {
            return person.getAge()-this.getAge();
        }
}

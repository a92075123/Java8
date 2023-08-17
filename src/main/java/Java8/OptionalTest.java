package Java8;

import Bean.Book;
import Bean.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static Java8.StreamData.getPersonListDemo;

public class OptionalTest {
    public static void main(String[] args) {

        Optional<Person> person = getPersonOptional();

//      Optional<Person> person1 = Optional.ofNullable(person);
//      person1.ifPresent(person2 -> System.out.println(person2.getName()));


//        optionalMap();
//        optionalFilter();
//        OptionalOrElseGet(person);
//        OptionalOrElseThrow(person);

    }
//  數據轉換，下面的例子是轉換成陣列裡面的book的陣列
    private static void optionalMap() {
        Optional<Person> personOptional = getPersonOptional();
        Optional<List<Book>> books = personOptional.map(person -> person.getBook());
        books.ifPresent(books1 -> System.out.println(books1));
    }

    /*
    Filter用來篩選條件
     */
    private static void optionalFilter() {

        Optional<Person> persons = getPersonOptional();

        Optional<Person> personOptional = persons.filter(person -> person.getAge()>88);

        persons.filter(person -> person.getAge()>58).ifPresent(person -> System.out.println(person.getName()));


    }

    /*
     假設Optional.ofNullable的值為空或是null的時候，person.orElseThrow方法會返回自己設定的異常訊息
   */
    private static void OptionalOrElseThrow(Optional<Person> person) {
        try {
            person.orElseThrow((Supplier<Throwable>) () -> new RuntimeException("數據null"));

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }
    /*
    假設Optional.ofNullable的值為空或是null的時候，person.orElseGet方法會自動返回自己設定的初始值
    Ex:Optional.ofNullable有值:Person{name='David', age=26, book=null}
       Optional.ofNullable沒值:Person{name='Tim', age=30, book=null}
     */
    public static void OptionalOrElseGet(Optional<Person> person) {
        Person person1 = person.orElseGet(() -> new Person("Tim",30,null) );
        System.out.println(person1);
    }

    /*
    Optional<?>自動處理異常空值不會出現執行錯誤，比較推薦第一個寫法，直接讓她是Optional<?>型態，就不用做多餘的轉換型態
     */
    public static Optional<Person> getPersonOptional(){

        Person person = new Person("David", 26, null);

        List<Book> book1 = new ArrayList<>();
        book1.add(new Book(1L,"Java Programming","Science",4));
        person.setBook(book1);

        return Optional.ofNullable(person);
    }
    public static Person getPerson(){
        Person person = new Person("David",26,null);
        return person;
    }
}

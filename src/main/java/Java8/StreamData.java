package Java8;

import Bean.Book;
import Bean.Person;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamData {

    public static void main(String[] args) {

        List<Person> persons = getPersonListDemo();
        Stream<Person> stream = persons.stream();
        sortStream();
//        distinctStream();
//        mapStream();
//
//        filterStream();
//        createMapStream();
//        createStream();
//        test01(persons);


    }
    /*
     如果是用空參的sorted()方法的話，要在Bean裡面 implements Comparable<T> 才能使用，不然會出現無法轉換成Comparable類的錯誤

     比數字大小
     升序
     @Override
        public int compareTo(Person person) {
            return this.getAge()-person.getAge();
        }
     降序
     @Override
        public int compareTo(Person person) {
            return person.getAge()-this.getAge();
        }
     */
    private static void sortStream() {
        List<Person> personListDemo = getPersonListDemo();
        personListDemo.stream().distinct().sorted().forEach(person -> System.out.println(person.getAge()));

    }

    private static void distinctStream() {
        List<Person> personListDemo = getPersonListDemo();
        personListDemo.stream().distinct().map(person -> person.getName()).forEach(System.out::println);

    }

    /*
        取出集合想要的元素
     */
    private static void mapStream() {
        List<Person> personListDemo = getPersonListDemo();
//      personListDemo.stream().map(person -> person.getName()).forEach(s -> System.out.println(s));
        personListDemo.stream().filter(person -> person.getAge() > 18)
                .map(person -> person.getName())
                .forEach(s -> System.out.println(s));
    }


    private static void filterStream() {
        List<Person> personListDemo = getPersonListDemo();
        //輸出名字長度大於3
        personListDemo.stream().filter(person -> person.getName().length() > 3)
                .forEach(person -> System.out.println(person.getName()));

    }

    private static void createMapStream() {
        Map<String, Integer> map = new HashMap<>();
        map.put("David", 18);
        map.put("leo", 20);
        map.put("tim", 25);

//      Entry泛型就是把map裡面的key:value封裝起來
//      Stream流的元素都是entry的類型
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Stream<Map.Entry<String, Integer>> stream = entries.stream();
        stream.filter(entry -> entry.getValue() > 18).forEach(System.out::println);
    }

    //創建stream流的方法
    private static void createStream() {
        Integer arr[] = {1, 2, 3, 4, 5};
//        IntStream stream = Arrays.stream(arr);
//        stream.distinct().filter(i -> i>2).forEach(System.out::println);
//        Stream<Integer> arr1 = Stream.of(arr);


    }

    public static void test01(List<Person> persons) {
        persons.stream().distinct().filter(person -> person.getAge() < 30)
                .forEach(person -> System.out.println(person.getName()));
    }

    public static List<Person> getPersonListDemo() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Java Programming");
        book1.setCategory("Programming");
        book1.setScore(4);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Introduction to Algorithms");
        book2.setCategory("Computer Science");
        book2.setScore(5);

        Book book3 = new Book();
        book3.setId(3L);
        book3.setName("The Great Gatsby");
        book3.setCategory("Fiction");
        book3.setScore(4);

        Book book4 = new Book();
        book4.setId(4L);
        book4.setName("Principles of Physics");
        book4.setCategory("Science");
        book4.setScore(5);

        // 创建 Person 实例并设置书籍列表
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        List<Book> books2 = new ArrayList<>();
        books2.add(book3);
        books2.add(book4);

        List<Book> books3 = new ArrayList<>();
        books3.add(book1);
        books3.add(book3);

        Person person = new Person("Alice", 25, books);
        Person person2 = new Person("David", 18, books3);
        Person person3 = new Person("Bob", 30, books2);
        Person person4 = new Person("Bob", 30, books2);

        List<Person> personList = new ArrayList<>(Arrays.asList(person, person2, person3, person4));

        return personList;
    }
}

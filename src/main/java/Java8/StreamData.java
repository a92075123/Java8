package Java8;

import Bean.Book;
import Bean.Person;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 當下的stream使用過(結束操作)一次後，就不能再重複使用了
 * 如果要再使用的話，就在創造新的stream
 * EX:persons.stream
 */
public class StreamData {

    public static void main(String[] args) {

        List<Person> persons = getPersonListDemo();
        Stream<Person> stream = persons.stream();




        negateStream();
//         andStream();
//        reduceStream();
//        findFirstStream();
//        findAnyStream();
//        noneMatchStream();
//        allMatchStream();
//        anyMatchStream();
//        collectStream();
//        countStream();
//        flatMapStream2();
//        flatMapStream();
//        skipStream();
//        limitStream();
//        sortStream();
//        distinctStream();
//        mapStream();
//        filterStream();
//        createMapStream();
//        createStream();
//        test01(persons);


    }
    /*
    .negate取相反的結果，下面為例，下面要取大於25的人，但因為有.negate所以相反，所以取得到小於25的人
     */
    private static void negateStream() {
        List<Person> persons = getPersonListDemo();
        persons.stream().filter(((Predicate<Person>) person -> person.getAge() > 25).negate()).forEach(person -> System.out.println(person.getName()+person.getAge()));


    }

    private static void andStream() {
        List<Person> persons = getPersonListDemo();
        persons.stream().filter(((Predicate<Person>) person -> person.getAge() > 26).and(person -> person.getName().length()>1)).forEach(person -> System.out.println(person.getName()));


    }

    private static void reduceStream() {

        /*
        reduce對初始值(identity)做業務邏輯，integer就是初始值，integer2陣列中的每一個元素
        每取一次就元素就會與integer值做業務邏輯，以下面為範例就是一直相加，每取得到一個元素
        integer就會跟integer2相加，並且回傳新的integer，在和下一個新的integer2元素相加，所以數字會越來越大
        */
        List<Person> persons = getPersonListDemo();
        Integer reduce = persons.stream().map(person -> person.getAge())
                .reduce(10, (integer, integer2) -> integer + integer2);

        Integer reduceAgeMin = persons.stream().map(person -> person.getAge())
                .reduce(Integer.MAX_VALUE, (integer, integer2) -> integer > integer2 ? integer2 : integer);


        /*
        這裡只有對reduce只傳一個值，但跟傳兩個值是一樣的，差在兩個參數是我們自己手動設定初始值，另一個一個參數他是把
        陣列裡的元素第一個值自動當作初始值
         */
        Optional<Integer> reduceAgeMax = persons.stream().map(person -> person.getAge()).reduce((integer, integer2) -> integer>integer2?integer:integer2);
        reduceAgeMax.ifPresent(integer -> System.out.println(integer));
    }


    //取得第一個項目
    private static void findFirstStream() {
        List<Person> persons = getPersonListDemo();

        Optional<Person> first = persons.stream().sorted((o1, o2) -> o1.getAge() - o2.getAge()).findFirst();
        first.ifPresent(person -> System.out.println(person));

    }

    //任意獲得一個達成條件的值 Optional拿來處理空值或NULL
    private static void findAnyStream() {
        List<Person> persons = getPersonListDemo();
        Optional<Person> any = persons.stream().filter(person -> person.getAge()>10).findAny();

        any.ifPresent(person -> System.out.println(person.getName()));
    }

    //判斷條件，只要全部都不達成條件就是true，要不然有一個達成就是false
    private static void noneMatchStream() {
        List<Person> persons = getPersonListDemo();
        boolean b = persons.stream().noneMatch(person -> person.getAge() > 50);
        System.out.println(b);
    }

    //判斷條件，只要有一個沒有達成就返回false不然全部符合就返回true
    private static void allMatchStream() {
        List<Person> persons = getPersonListDemo();
        boolean b = persons.stream().allMatch(person -> person.getAge() > 10);
        System.out.println(b);
    }

    //判斷條件，只要有一個達成就返回true不然就false
    private static void anyMatchStream() {
        List<Person> persons = getPersonListDemo();
        boolean b = persons.stream().anyMatch(person -> person.getAge() > 50);
        System.out.println(b);
    }

    private static void collectStream() {
        List<Person> persons = getPersonListDemo();
        //轉換List集合
        List<String> personsList = persons.stream()
                .map(person -> person.getName())
                .collect(Collectors.toList());

        //轉換set集合
        Set<Book> bookSet = persons.stream().flatMap(person -> person.getBook().stream())
                .collect(Collectors.toSet());

        /*轉換map集合
            分別要new 兩個方法，一個是key一個是value，下面舉例get.Name是key，getBook是value
         */
        Map<String,List<Book>> collect;
        collect = persons.stream()
                .distinct()
                .collect(Collectors.toMap(person -> person.getName(), person -> person.getBook()));
    }

    private static void countStream() {
        List<Person> persons = getPersonListDemo();
        long count = persons.stream()
                        .flatMap(person -> person.getBook()
                        .stream())
                        .distinct()
                        .count();
        System.out.println(count);
    }


    //取出陣列裡面的陣列所有值，並且把陣列取出來的值都一起塞到另一個stream裡面。
    private static void flatMapStream2() {

        List<Person> persons = getPersonListDemo();
        persons.stream().flatMap(person -> person.getBook().stream())
                        .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                        .distinct().forEach(s -> System.out.println(s));



    }
    private static void flatMapStream() {
        List<Person> persons = getPersonListDemo();
        persons.stream()
                .flatMap(person -> person.getBook().stream())
                .distinct()
                .forEach(o -> System.out.println(o.getName()));

    }

    //skip跳過元素，比較特別是第一個就是1，而不是0
    private static void skipStream() {
        List<Person> persons =getPersonListDemo();
        persons.stream().distinct().sorted((person,p1)->(p1.getAge()-person.getAge())).skip(2).forEach(
                person -> System.out.println(person.getName()+"==="+person.getAge())
        );
    }
    //limit取得多少筆資料 Ex:2筆就是兩個
    private static void limitStream() {
        List<Person> persons = getPersonListDemo();
        persons.stream().distinct().sorted((person, t1) -> t1.getAge() - person.getAge()).limit(1).forEach(person -> System.out.println(person.getName()+"="+person.getAge()));
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

//      Entry泛型就是把map裡面的各個物件用key:value封裝起來形成一個集合
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
        book1.setCategory("Science");
        book1.setScore(4);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Introduction to Algorithms");
        book2.setCategory("Fiction");
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

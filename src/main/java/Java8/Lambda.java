package Java8;

import java.sql.SQLOutput;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;


/*
    lambda守則
    它必須是一個interface介面，並且她只有一個抽象方法才能用lambda
    不管方法名稱，只管帶進來的參數就好
 */
public class Lambda {
    public static void main(String[] args) {





//        new Thread(()->{System.out.println("1");}).start();

//        int i = calculateNum((i12, i1) -> i12 + i1);
//                printNum(i13 -> i13 %2==0);
//        Integer result = typeConver(s -> Integer.valueOf(s));
//        String  resultString = typeConver(ss -> ss+"日");
//        foreachArr(i -> System.out.println(i));



    }

    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a,b);
    }

    public static void printNum (IntPredicate predicate){
        int[] arr = {1,2,3,4,5,6};
        for (int i :arr) {
            if(!predicate.test(i)){
                System.out.println(i);
            }
        }

    }
    public static  <R> R typeConver(Function<String,R> function){
        String str ="1235";
        R result = function.apply(str);
        return result;
    }
    public static void foreachArr(IntConsumer consumer){
        int[] arr ={1,2,3,4,5,6,7,8,9,10};
        for (int i :arr) {
            consumer.accept(i);
        }

    }
}

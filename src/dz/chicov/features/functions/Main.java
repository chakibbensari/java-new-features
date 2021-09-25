package dz.chicov.features.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        // instead of :
        for(Integer i : list){
            System.out.println(i);
        }
        // use of forEach function available on List interface (>= 1.8)
        // forEach takes a Consumer<T> object
        // creation of an anonymous implementation of Consumer<Integer> :
        Consumer<Integer> c = new Consumer<Integer>(){
            @Override
            public void accept(Integer i){
                System.out.println(i);
            }
        };
        list.forEach(c);

        // much better with lambda expression
        // lambda expression is used to replace implementation of interfaces with only one method
        // no need to create a Consumer<T> :
        list.forEach(i -> System.out.println(i)); // *o*

        // with method reference we can do ::
        list.forEach(System.out::println);
    }
}

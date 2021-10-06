package dz.chicov.features.functions;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

import dz.chicov.features.interfaces.Carre;

class Animal {
	public void print() {
		System.out.println("animal");
	}
}

class Pet extends Animal {
	public void print() {
		System.out.println("Pet");
	}
}

class Dog extends Pet {
	public void print() {
		System.out.println("Dog");
	}
}

class Cat extends Pet {
	public void print() {
		System.out.println("Cat");
	}
}

class Wrapper<T extends Animal> {
	T value;

	public Wrapper(T t) {
		value = t;
	}

	public void show(Wrapper<? super T> arg) {
		arg.value.print();
	}
}

public class Main {

	public static void main(String[] args) {

		Dog dog = new Dog();
		Animal a = new Animal();

		Wrapper<Pet> w = new Wrapper<>(dog);
		w.show(w);

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		// instead of :
		for (Integer i : list) {
			System.out.println(i);
		}
		// use of forEach function available on List interface (>= 1.8)
		// forEach takes a Consumer<T> object
		// creation of an anonymous implementation of Consumer<Integer> :
		Consumer<Integer> c = new Consumer<Integer>() {
			@Override
			public void accept(Integer i) {
				System.out.println(i);
			}
		};
		list.forEach(c);

		// much better with lambda expression
		// lambda expression is used to replace implementation of interfaces with only
		// one method
		// no need to create a Consumer<T> :
		list.forEach(i -> System.out.println(i)); // *o*

		// with method reference we can do :
		//
		list.forEach(System.out::println);

		// it was possible to do this before java 8.0 -_-
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("New thread created");
			}
		}).start();
//			@Override
//			public void run() {
//				System.out.println("New thread created");
//			}
//		}).start();

		// with java 8 :
		new Thread(() -> {
			System.out.println("New thread created");
		});

		// this is possible. see class Carre

		Carre carre = x -> x * 4;
		System.out.println(carre.getPermietre(5.5)); // prints 22

		// Chaining Consumers
		Consumer<Integer> printAgeConsumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer age) {
				System.out.println("Age is " + age);
			}
		};

		//call the method
		printAgeConsumer.accept(23);// Age is 23

		//using lambda
		Consumer<Integer> printAgeWithLamda = (age) -> System.out.println("Lamdda : age is " + age);

		//will work similar as printAgeConsumer
		printAgeWithLamda.accept(223);// Lamda : age is 223

		//chaining with andThen(Consumer)
		printAgeConsumer // 1st
				.andThen(printAgeWithLamda)// 2nd
				.andThen(age -> System.out.println("How old is he ? " + age))// 3rd
				.accept(23);// this value will be given to each consumer
		

		// The Function<T, R> interface, provide the apply abstract method to apply transformations on an Input T  and Returns Object of type R
		// it is possible to chain : function1.then(function2).apply(5)
		// pay iattention, the result of the first function must be the type of second function Input (ex: Integer -> Double and Double -> String)
		
		Function<Integer, Double> func1 = (Integer i) ->  i * 1.5;
		Function<Double, String> func2 = (Double i) ->  String.valueOf(i);
		System.out.println(func1.andThen(func2).apply(5)); // prints 7.5 as String and not as Integer or double
		
		// getting the number of core available on CPU (the number of excuted thread shoud be lte than number of cors)
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		// Creates a Thred pool with number of Thread equals ton the number of available CPU cors
		ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		// we execute any number of task (Runnable/Thread) we want with service.execute(new Task()) and the executor will affect tasks by using un blocking 
		// threadsafe tasks queue
		
		// Callable interface is the same as Runnable interface, it has the call methode which is executed in parallel Thread
		// The difference is that it returns a value, this value should be wrapped in a Future<T> because it's unpredictble to know 
		// when the value is available in the main thread
		// in the main thread, the value is fetch by using the Future get method ==> it is a blocking method !
	}
	
	
	
}

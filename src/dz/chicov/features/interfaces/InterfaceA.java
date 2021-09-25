package dz.chicov.features.interfaces;

public interface InterfaceA {

    // by default, this is a public abstract method
    void display();

    // in java 8, we can define a method !
    default void show(){
        System.out.println("Show this amazing new feature");
    }

    // in java 8, it's possible to define a static method in an interface
    static int count(){
        return 0;
    }

}

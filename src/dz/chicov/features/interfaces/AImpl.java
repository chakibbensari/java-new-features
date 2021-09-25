package dz.chicov.features.interfaces;

public class AImpl implements InterfaceA, InterfaceB {


    @Override
    public void display() {
        System.out.println("Hello A is working");
    }

    @Override
    public void show() {
        System.out.println("This method is prior to method declared as default in InterfaceA. And i must implement it here");
    }
}

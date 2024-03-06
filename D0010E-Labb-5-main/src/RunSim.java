import EventsPackage.*;
import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import StatePackage.CustomerFIFO;
import StatePackage.ShopState;

public class RunSim {
    public static void main(String[] args){
        int openCheckout = 2;
        int maxCustomer = 5;
        double openingHours = 10;
        int seed = 1234;
        int lambda = 1;
        double[] pickup = {0.5, 1.0};
        double[] pay = {2.0, 3.0};
/*
        int openCheckout = 2;
        int maxCustomer = 7;
        double openingHours = 8;
        int seed = 13;
        int lambda = 3;
        double[] pickup = {0.6, 0.9};
        double[] pay = {0.35, 0.6};
*/
        EventQueue eq = new EventQueue();
        CustomerFIFO cq = new CustomerFIFO();
        ShopState shopState = new ShopState(openCheckout, maxCustomer, openingHours, seed, lambda, pickup, pay, cq);
        Event start = new StartEvent(shopState, cq, eq);
        eq.add(start);
        Event close = new CloseEvent(shopState.getClosingTime(), shopState);
        eq.add(close);
        Event stop = new StopEvent(shopState);
        eq.add(stop);

        SpecSimulator sim = new SpecSimulator(eq, shopState);

        //Optimize
        //Optimize op = new Optimize(openCheckout, maxCustomer, openingHours, seed, lambda, pickup, pay);


    }
}

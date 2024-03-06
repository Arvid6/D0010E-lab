import java.util.Random;

import EventsPackage.*;
import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import StatePackage.*;

/**
 * This class optimizes the shop by finding the optimal parameters.
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */
public class Optimize {

    private int openCheckout;
    private int maxCustomer;
    private double closingTime;
    private int seed;
    private int lambda;
    private double[] pickup;
    private double[] pay;
    private SpecSimulator sim;
    private int optimalCheckouts = 1;
    private int optimalMissedCustomers;
    private ShopState shopState;
    private CustomerFIFO cq;
    private EventQueue eq;

    /**
     * The constructor
     * Sets the parameters and runs the three different methods to find the optimal amount of checkouts.
     *
     * @param openCheckout Amount of checkouts
     * @param maxCustomer  Maximum customers allowed
     * @param closingTime  Time when shop closes
     * @param seed         Seed
     * @param lambda       Average customers per time unit
     * @param pickup       Max and min pickup times
     * @param pay          Max and min pay time
     */
    public Optimize(int openCheckout, int maxCustomer, double closingTime, int seed, int lambda, double[] pickup, double[] pay) {
        this.openCheckout = openCheckout;
        this.maxCustomer = maxCustomer;
        this.closingTime = closingTime;
        this.seed = seed;
        this.lambda = lambda;
        this.pickup = pickup;
        this.pay = pay;
        this.cq = new CustomerFIFO();
        this.eq = new EventQueue();


        OneTime(openCheckout, maxCustomer, closingTime, seed, lambda, pickup, pay);
        OpCheckout(seed);
        Validate(seed);
    }

    /**
     * The first method runs the simulator with given parameters.
     *
     * @param openCheckout Amount of checkouts
     * @param maxCustomer  Maximum customers allowed
     * @param closingTime  Time when shop closes
     * @param seed         Seed
     * @param lambda       Average customers per time unit
     * @param pickup       Max and min pickup times
     * @param pay          Max and min pay time
     * @return Final state
     */
    public SpecSimulator OneTime(int openCheckout, int maxCustomer, double closingTime, int seed, int lambda, double[] pickup, double[] pay) { //TODO kör som vanligt fast utan utskrifter
        this.shopState = new ShopState(openCheckout, maxCustomer, closingTime, seed, lambda, pickup, pay, this.cq);
        shopState.setOptimize();
        Event start = new StartEvent(shopState, cq, eq);
        eq.add(start);
        Event close = new CloseEvent(shopState.getClosingTime(), shopState);
        eq.add(close);
        Event stop = new StopEvent(shopState);
        eq.add(stop);
        return new SpecSimulator(this.eq, this.shopState); //Ska retunera sluttillstånd

    }

    /**
     * This method runs the simulation but with different amounts of checkouts, where checkouts is less than maxCustomer, and finds the optimal amount of checkouts
     *
     * @param seed Given seed
     * @return The optimal amount of checkouts
     */


    public int[] OpCheckout(int seed) {
        int optimalCheckouts = 1;
        int optimalMissedCustomers = 0;

        for (int i = this.maxCustomer; i >= 1; i--) {

            this.sim = OneTime(i, this.maxCustomer, this.closingTime, seed, this.lambda, this.pickup, this.pay);
           // System.out.println(i);
            if (i == this.maxCustomer) {
                optimalCheckouts = i;
                optimalMissedCustomers = this.shopState.getCustomerMissed();
            } else {
                if (this.shopState.getCustomerMissed() <= optimalMissedCustomers) {
                    optimalCheckouts = i;
                    optimalMissedCustomers = this.shopState.getCustomerMissed();

                } else {
                    break;
                }
            }
        }

        int[] optimal = {optimalCheckouts, optimalMissedCustomers};
        return optimal;
    }

    /**
     * This method checks if the optimal amount of checkouts found in OpCheckout is reliable by running it with 100 different seeds.
     *
     * @param f Seed
     */
    public void Validate(int f) {
        Random random = new Random(f);
        int counter = 0;
        int thisSeed = random.nextInt();
        while (counter != 100) {
            int nextSeed = random.nextInt();

            if (OpCheckout(thisSeed)[1] > OpCheckout(nextSeed)[1]) {
                counter = 0;
                thisSeed = nextSeed;
            }else if(OpCheckout(thisSeed)[1] == OpCheckout(nextSeed)[1]){
                if(OpCheckout(thisSeed)[0] < OpCheckout(nextSeed)[0]){
                    thisSeed = nextSeed;
                    counter = 0;
                }
            }
            else {
                counter++;
            }
        }
        int[] temp = OpCheckout(thisSeed);
        System.out.println("Checkouts: " + temp[0] + " - Missed customers: " + temp[1]);
    }
}


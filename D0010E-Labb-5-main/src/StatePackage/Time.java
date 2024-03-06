package StatePackage;

import random.*;

/**
 * This class randomizes the times for arrival, pickup and pay.
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */
public class Time {

    private int lambda;
    private int seed;
    private double[] pickupTime;
    private double[] payTime;
    private ExponentialRandomStream arrivalTime;
    private UniformRandomStream pickupTimeInterval;
    private UniformRandomStream payTimeInterval;

    /**
     * The constructor
     * This sets all the arguments inputted and randomizes the times.
     *
     * @param random The seed
     * @param lambda The average number of customers per time unit
     * @param pickup The minimum and maximum time it takes to perform a pickup event
     * @param pay    The minimum and maximum time it takes to perform a pay event
     */
    public Time(int random, int lambda, double[] pickup, double[] pay) {
        this.seed = random;
        this.lambda = lambda;
        this.pickupTime = pickup;
        this.payTime = pay;
        arrivalTime = new ExponentialRandomStream(lambda, this.seed);
        pickupTimeInterval = new UniformRandomStream(pickupTime[0], pickupTime[1], seed);
        payTimeInterval = new UniformRandomStream(payTime[0], payTime[1], seed);

    }

    /**
     * The constructor
     * This sets all the arguments inputted and randomizes the times.
     *
     * @param lambda The average number of customers per time unit
     * @param pickup The minimum and maximum time it takes to perform a pickup event
     * @param pay    The minimum and maximum time it takes to perform a pay event
     */
    public Time(int lambda, double[] pickup, double[] pay) {
        this.lambda = lambda;
        this.pickupTime = pickup;
        this.payTime = pay;
        arrivalTime = new ExponentialRandomStream(lambda);
        pickupTimeInterval = new UniformRandomStream(pickupTime[0], pickupTime[1]);
        payTimeInterval = new UniformRandomStream(payTime[0], payTime[1]);

    }

    /**
     * This gets the next randomized time for arrival events
     *
     * @return The next arrival time
     */
    public double getArrival() {
        return arrivalTime.next();
    }

    /**
     * This gets the next randomized time for pickup events
     *
     * @return The next pickup time
     */
    public double getPickup() {
        return pickupTimeInterval.next();
    }

    /**
     * This gets the next randomized time for pay events
     *
     * @return The next pay time
     */
    public double getPay() {
        return payTimeInterval.next();
    }


    /**
     * Returns lambda
     *
     * @return lambda
     */
    public double getLambda(){
        return lambda;
    }
    /**
     * Returns pickupTime
     *
     * @return pickupTime
     */
    public double[] getPickupLim(){
        return pickupTime;
    }
    /**
     * Returns paytime
     *
     * @return payTime
     */
    public double[] getPayLim(){
        return payTime;
    }

    /**
     * Returns seed
     * @return seed
     */
    public int getSeed(){
        return seed;
    }

}

package StatePackage;

import GeneralClasses.State;

import java.util.ArrayList;

/**
 * This class keeps track of all the specific variables of the simulation, eg. how many checkouts are open, how many customers are shopping etc.
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class ShopState extends State {

    private int customerShopping;
    private int checkoutEmpty;
    private int checkoutBusy;
    private int customerMissed;
    private int customerShopped;
    private float checkoutEmptyTime;
    private float checkoutBusyTime;
    private final int openCheckout;
    private final int maxCustomer;
    private int totalCustomers;
    private boolean shopOpen;
    private int random;
    private final double closingTime;
    private final Time time;
    private int totalQueue;
    private String currentEvent;
    private String currentID;
    private CustomerFIFO cq;
    private boolean optimize = false;

    /**
     * The constructor
     * This sets all the arguments inputted and creates objects.
     *
     * @param openCheckout Amount of checkouts
     * @param maxCustomer  Maximum customers allowed
     * @param closingTime  Time when shop closes
     * @param seed         Seed
     * @param lambda       Average customers per time unit
     * @param pickup       Max and min pickup times
     * @param pay          Max and min pay time
     */

    public ShopState(int openCheckout, int maxCustomer, double closingTime, int seed, int lambda, double[] pickup, double[] pay, CustomerFIFO cQue) {
        this.shopOpen = true;
        this.openCheckout = openCheckout;
        this.checkoutEmpty = openCheckout;
        this.maxCustomer = maxCustomer;
        this.closingTime = closingTime;
        this.random = seed;
        this.time = new Time(seed, lambda, pickup, pay);
        this.cq = cQue;
    }

    /**
     * The constructor
     * This sets all the arguments inputted and creates objects.
     *
     * @param openCheckout Amount of checkouts
     * @param maxCustomer  Maximum customers allowed
     * @param closingTime  Time when shop closes
     * @param lambda       Average customers per time unit
     * @param pickup       Max and min pickup times
     * @param pay          Max and min pay time
     */

    public ShopState(int openCheckout, int maxCustomer, double closingTime, int lambda, double[] pickup, double[] pay, CustomerFIFO cQue) {
        this.shopOpen = true;
        this.openCheckout = openCheckout;
        this.checkoutEmpty = openCheckout;
        this.maxCustomer = maxCustomer;
        this.closingTime = closingTime;
        this.time = new Time(lambda, pickup, pay);
        this.cq =cQue;
    }

    //-------------------GETTERS------------------------------

    /**
     * This returns the amount of customers shopping
     *
     * @return Customers shopping
     */
    public int getCustomerShopping() {
        return customerShopping;
    }

    /**
     * This returns the amount of empty checkouts
     *
     * @return Empty checkouts
     */
    public int getCheckoutEmpty() {
        return checkoutEmpty;
    }

    /**
     * Returns lambda
     *
     * @return lambda
     */
    public double getLambda(){
        return time.getLambda();
    }
    /**
     * Returns pickupTime
     *
     * @return payPickupLim
     */
    public double[] getPickupLim(){
        return time.getPickupLim();
    }
    /**
     * Returns paytime
     *
     * @return payTimeLim
     */
    public double[] getPayLim(){
        return time.getPayLim();
    }

    /**
     * returns seed
     *
     * @return getSeed
     */
    public int getSeed(){
        return time.getSeed();
    }

    /**
     * This returns the amount of busy checkouts
     *
     * @return Busy checkouts
     */
    public int getCheckoutBusy() {
        return checkoutBusy;
    }

    /**
     * This returns the amount of customers missed
     *
     * @return Missed customers
     */
    public int getCustomerMissed() {
        return customerMissed;
    }

    /**
     * This returns the amount of customers who shopped
     *
     * @return Customers shopped
     */
    public int getCustomerShopped() {
        return customerShopped;
    }

    /**
     * This returns the time that checkouts have been empty.
     *
     * @return Time checkouts are empty
     */
    public float getCheckoutEmptyTime() {
        return checkoutEmptyTime;
    }

    /**
     * This returns the time that checkouts have been busy.
     *
     * @return Time checkouts are busy
     */

    public float getCheckoutBusyTime() {
        return checkoutBusyTime;
    }

    /**
     * This returns the amount of checkouts that are open
     *
     * @return Open checkouts
     */
    public int getOpenCheckout() {
        return openCheckout;
    }

    /**
     * This returns the maximum amount of customers allowed
     *
     * @return Max customers
     */
    public int getMaxCustomer() {
        return maxCustomer;
    }

    /**
     * This returns the closing time.
     *
     * @return Closing time
     */
    public double getClosingTime() {
        return closingTime;
    }

    /**
     * This returns the state of the shop, if it is open or not.
     *
     * @return Shop state
     */
    public boolean getShopState() {
        return shopOpen;
    }

    /**
     * This returns the seed
     *
     * @return Seed
     */
    public int getRandom() {
        return random;
    }

    /**
     * This returns the current time from State.java
     *
     * @return Current time
     */
    public double getTime() {
        return getCurrentTime();
    }

    /**
     * This returns the time for an arrival event
     *
     * @return Arrival time
     */
    public double getArrivalTime() {
        return time.getArrival();
    }

    /**
     * This returns the time for a pickup event
     *
     * @return Pickup time
     */
    public double getPickupTime() {
        return time.getPickup();
    }

    /**
     * This returns the time for a pay event
     *
     * @return Pay time
     */
    public double getPayTime() {
        return time.getPay();
    }

    /**
     * This returns the size of the queue from CustomerQueue.java
     *
     * @return Size of customer queue
     */
    public int getQueueSize() {
        return cq.getLength();
    }

    /**
     * Gets total amount of people in the queue
     *
     * @return total queue size
     */
    public int getTotalQueue(){
        return  totalQueue;
    }

    /**
     * This returns the customer queue from CustomerQueue.java
     *
     * @return Customer queue
     */
    public ArrayList<Customer> getQueue() {
        return cq.getcustomerFIFO();
    }

    /**
     * Gets the next event in line
     *
     * @return Next event in line
     */
    public String getEvent() {
        return currentEvent;
    }

    /**
     * Gets the ID of the current event
     *
     * @return current event ID
     */
    public String getID() {
        return currentID;
    }

    /**
     * Gets the total amount of customers
     *
     * @return total customers
     */
    public int getTotCustomer() {
        return totalCustomers;
    }

    /**
     * returns true if optimze is running
     *
     * @return optimze
     */
    public boolean getOptimize(){
        return optimize;
    }

    //-------------------------SETTERS---------------------

    /**
     * This changes the amount customers that are currently shopping
     *
     * @param delta Customer change
     */
    public void setCustomerShopping(int delta) {
        this.customerShopping += delta;
    }

    /**
     * This changes the amount of checkouts that are empty
     *
     * @param delta Checkout empty
     */
    public void setCheckoutEmpty(int delta) {
        this.checkoutEmpty += delta;
    }

    /**
     * This changes the amount of checkouts that are in use
     *
     * @param delta Checkouts in use
     */
    public void setCheckoutBusy(int delta) {
        this.checkoutBusy += delta;
    }

    /**
     * This changes the amount of customers that have been missed by 1
     */
    public void setCustomerMissed() {
        this.customerMissed++;
    }

    /**
     * This changes the amount of customers that have shopped by 1
     */
    public void setCustomerShopped() {
        this.customerShopped++;
    }

    /**
     * This changes the amount of time a checkout has been empty
     *
     * @param delta Time empty
     */
    public void setCheckoutEmptyTime(Double delta) {
        this.checkoutEmptyTime += delta;
    }

    /**
     * This changes the state of the shop to closed
     */
    public void closeShop() {
        this.shopOpen = false;
    }

    /**
     * This sets the current event
     *
     * @param s evetn type
     */
    public void setEvent(String s) {
        this.currentEvent = s;
    }

    /**
     * This sets the event ID
     *
     * @param x ID
     */
    public void setEventID(String x) {
        this.currentID = x;
    }

    /**
     * This changes the total amount of customers
     */
    public void setTotCustomer() {
        this.totalCustomers++;
    }

    /**
     * Adds one new customer to the total of queued customers
     */
    public void setTotalQueue(){
        totalQueue++;
    }

    /**
     * Updates the total time queued
     *
     * @param s time added
     */
    public void setQueueTime(double s){
        checkoutBusyTime+= s;
    }

    /**
     * sets optimize to true if optimize is running
     */
    public void setOptimize(){
        optimize = true;
    }

    /**
     * Notifies observers of a change
     */
    public void act(){
        setChanged();
        notifyObservers();
    }
}
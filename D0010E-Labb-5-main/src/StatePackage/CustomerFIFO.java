package StatePackage;

import java.util.ArrayList;

/**
 * This class keeps track of all customers currently in line
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */


public class CustomerFIFO{

    private ArrayList<Customer> customerFIFO = new ArrayList<Customer>();

    /**
     * Adds customer to the FIFO list customerFIFO
      * @param customer
     */
    public void addToQueue(Customer customer){
        customerFIFO.add(customer);
    }

    /**
     * Removes the first element from customerFIFO
     */
    public void removeFirst(){
        customerFIFO.remove(customerFIFO.get(0));
    }

    /**
     * Returns the length of the customerFIFO
     * @return length of customerFIFO
     */
    public int getLength(){
        return customerFIFO.size();
    }

    /**
     * Fetches the first element in customerFIFO
     * @return the first element in customerFIFO
     */
    public Customer getFirst(){
    	return customerFIFO.get(0);
    }

    /**
     * Fetches the entire Queue
     * @return The ArrayList customerFIFO
     */
    public ArrayList<Customer> getcustomerFIFO(){
        return this.customerFIFO;
    }
}

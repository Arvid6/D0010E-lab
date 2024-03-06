package StatePackage;

/**
 * This class optimizes the shop by finding the optimal parameters.
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class Customer {
    private int id;

    /**
     * Creates a new customer
     *
     * @param st object of ShopState
     */

    public Customer(ShopState st){
        id = st.getTotCustomer();
    }

    /**
     * Fetch ID form customer
     * @return gets customer ID
     */
    public int getID(){
        return this.id;
    }


}

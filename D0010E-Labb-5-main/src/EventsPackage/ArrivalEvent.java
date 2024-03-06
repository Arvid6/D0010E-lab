package EventsPackage;
import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import GeneralClasses.State;
import StatePackage.*;

/**
 * Represents and carries out a arrival event
 * 
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class ArrivalEvent extends Event {
	private ShopState st;
	private Customer ct;
	private CustomerFIFO cq;
	private EventQueue eq;
	
	/**
     * The constructor
     *
     * @param time, The time the event is set to happen
     * @param st, Object for ShopState class
     * @param ct, Object for Customer class, represents the current customer 
     * @param cq, Object for CustomerQueue class
     * @param eq, Object for EventQueue class
     */

public ArrivalEvent(double time, ShopState st, Customer ct, CustomerFIFO cq, EventQueue eq) {
		super("Arrival", time);
		this.st = st;
		this.ct = ct;
		this.cq = cq;
		this.eq = eq;
	}

	/**
	 * Runs the event
	 * 
	 * @param s, Object of the class state
	 */
		
	public void run(State s){
		st.setEventID(Integer.toString(ct.getID()));
		if(st.getShopState()){
			st.setTotCustomer();
			Customer newcustomer = new Customer(st);
			Event arrival = new ArrivalEvent(this.getTime()+st.getArrivalTime(), st, newcustomer, cq, eq);
			eq.add(arrival);
		}
		if(st.getCustomerShopping() < st.getMaxCustomer() && st.getShopState()){
			st.setCustomerShopping(1);
			Event pickup = new PickupEvent(this.getTime()+st.getPickupTime(), st, ct, cq, eq);
			eq.add(pickup);
		}
		else if(st.getShopState()){
			st.setCustomerMissed();
		}
			
		}

}


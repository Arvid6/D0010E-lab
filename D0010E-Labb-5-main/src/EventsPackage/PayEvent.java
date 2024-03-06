package EventsPackage;
import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import GeneralClasses.State;
import StatePackage.*;

/**
 * Represents and carries out a pay event
 * 
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class PayEvent extends Event {
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
     * @param cq, Object for customerFIFO class
     * @param eq, Object for EventQueue class
     */

public PayEvent(double time, ShopState st, Customer ct, CustomerFIFO cq, EventQueue eq) {
		super("PayEvent", time);
		this.st = st;
		this.ct = ct;
		this.cq = cq;
		this.eq = eq;
	}

	/**
	 * Runs the event
	 * 
	 * @param s, Object of the class state
	 *
	 */
	
	public void run(State s){
		st.setEventID(Integer.toString(ct.getID()));
		st.setCustomerShopping(-1);
		st.setCustomerShopped();
		st.setCheckoutBusy(-1);
		st.setCheckoutEmpty(1);
		
		if(cq.getLength() > 0) {
			st.setCheckoutEmpty(-1);
			st.setCheckoutBusy(1);
			Event pay = new PayEvent(this.getTime()+st.getPayTime(), st, cq.getFirst(), cq, eq);
			eq.add(pay);
			cq.removeFirst();
		}
	}
}

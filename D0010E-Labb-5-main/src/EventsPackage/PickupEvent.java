package EventsPackage;
import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import GeneralClasses.State;
import StatePackage.*;

/**
 * Represents and carries out a pickup event
 */

public class PickupEvent extends Event {
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

public PickupEvent(double time, ShopState st, Customer ct, CustomerFIFO cq, EventQueue eq) {
		super("Pickup", time);
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
		if(st.getCheckoutEmpty() > 0) {
			Event pay = new PayEvent(this.getTime()+ st.getPayTime(), st, ct, cq, eq);
			st.setCheckoutEmpty(-1);
			st.setCheckoutBusy(1);
			eq.add(pay);
		}
		else {
			st.setTotalQueue();
			cq.addToQueue(this.ct);
		}
	}

}

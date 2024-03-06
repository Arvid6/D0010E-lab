package EventsPackage;
import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import GeneralClasses.State;
import StatePackage.*;

/**
 * Represents and carries out a start event
 * 
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class StartEvent extends Event {
	private ShopState st;
	private CustomerFIFO cq;
	private EventQueue eq;
	
	/**
     * The constructor
     *
     * @param st, Object for ShopState class
     * @param cq, Object for CustomerQueue class
     * @param eq, Object for EventQueue class
     */

public StartEvent(ShopState st, CustomerFIFO cq, EventQueue eq){
		super("Start", 0.0);
		this.st = st;
		this.cq = cq;
		this.eq = eq;
	}

	/**
	 * Runs the event
	 * 
	 * @param s, Object of the class state
	 *
	 */

	public void run(State s) {
		Customer customer = new Customer(st);
		Event arrival = new ArrivalEvent(st.getTime()+st.getArrivalTime(), st, customer, cq, eq);
		eq.add(arrival);
		st.setEventID("---");
	}
}

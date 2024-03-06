package EventsPackage;

import GeneralClasses.Event;
import GeneralClasses.State;
import StatePackage.*;


/**
 * Represents a stop event
 * 
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class StopEvent extends Event {
	private ShopState st;
	/**
	 * The constructor
	 *
	 * @param st, Object for ShopState class
	 */
	public StopEvent(ShopState st) {
		super("StopEv", 999.9);
		this.st = st;
		st.setEventID("---");
	}

	/**
	 * runs state
	 * @param s, Object of the class state
	 */
	public void run(State s){
		s.stop();
		st.setEventID("---");
	}
}

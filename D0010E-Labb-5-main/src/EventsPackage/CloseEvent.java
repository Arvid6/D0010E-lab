package EventsPackage;
import GeneralClasses.Event;
import GeneralClasses.State;
import StatePackage.*;

/**
 * Represents and carries out a close event
 * 
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */


public class CloseEvent extends Event {
	private ShopState st;
	/**
     * The constructor
     * 
     * @param time, The time the event is set to happen
     * @param st, Object for ShopState class
     */

public CloseEvent(double time, ShopState st) {
		super("Close", time);
		this.st = st;
	}

	/**
	 * Runs the event
	 * 
	 * @param s, Object of the class state
	 *
	 */
	
	public void run(State s){
		st.closeShop();
		st.setEventID("---");
	}
	
}
	

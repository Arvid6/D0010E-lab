package GeneralClasses;

/**
 * Represents and carries out an event, parent class for all the other events
 * 
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class Event {
		private String eventType;
		private double timeset;
		
	/**
	 * The constructor
	 *
	 * @param event, The type of event
	 * @param time, The time the event is set to happen
	 */
	public Event(String event, double time) {
		eventType = event;
		timeset = time;//Tiden då den ska hända
	}
	/**
	 * Runs the event
	 * 
	 * @param s, Object of the class state
	 *
	 */
	
	public void run(State s){
		if(eventType == "Stop") {
			s.stop();
		}
	}
	
	/**
	 * Gets the time of an event
	 * 
	 * @return the time
	 */
	
	public double getTime() {
		return this.timeset;
	}
	
	/**
	 * Gets the type of event
	 * 
	 * @return event type;
	 */
	
	public String getType() {
		return this.eventType;
	}
}

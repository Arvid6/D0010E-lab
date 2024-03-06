
package GeneralClasses;

import java.util.Observable;

import GeneralClasses.Event;

/**
 * This class contains the emergency break and keeps track of the current time.
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class State extends Observable{

    private boolean emergencyStop;
    private double currentTime;
    private double hiddenTime;

    /**
     * The constructor
     * This sets the emergency break to false and sets the time to 0.
     */

    public State() {
        emergencyStop = false;
        this.currentTime = 0.0f;
    }

    /**
     * This gets the state of the emergency break.
     *
     * @return Emergency break state
     */
    public boolean getBreak() {
        return emergencyStop;
    }

    /**
     * This gets the current time
     *
     * @return Current time
     */
    public double getCurrentTime() {
        return currentTime;
    }

    /**
     * This gets the current time
     *
     * @return Current time
     */
    public double getHiddenTime() {
        return hiddenTime;
    }

    /**
     * This updates the current time by increasing it with 0.01 time units.
     */
    public void updateTime(double t) {
            this.currentTime = t;
    }

    /**
     * This updates the current time by increasing it with 0.01 time units.
     */

    public void updateHiddenTime(double t){
            this.hiddenTime = t;
    }

    /**
     * This sets the emergency break to true
     */
    public void stop() {
        emergencyStop = true;
        //Utskrift?
    }

    /**
     * This returns the type of event
     *
     * @param event The event
     * @return The type of event
     */
    public String getEventType(Event event) {
        return event.getType();
    }

}

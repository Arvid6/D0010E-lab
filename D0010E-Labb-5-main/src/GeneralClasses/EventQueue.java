package GeneralClasses;

import GeneralClasses.Event;
import GeneralClasses.FIFO;

/**
 * This class adds and handles all the events that are put in the queue.
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class EventQueue extends FIFO {

    /**
     * Checks if the queue is empty.
     * @return True or false.
     */
    public boolean isEmpty() { 
        return super.isEmpty();
    }
    /**
     * Adds events to the queue.
     */

    public void add(Event item) {
        super.add(item);
    }
    /**
     * Checks which events in the queue that has the least time.
     * 
     * @return The earliest event.
     */

    public Event first() {
        Event earliestEvent = null;
        int index = 0;
        for (int i = 0; i < size(); i++) {
            Event event = queue.get(i);
            if (i == 0 || event.getTime() < earliestEvent.getTime()) {
                earliestEvent = event;
                index = i;
            }
        }
        return earliestEvent;

    }

    /**
     * Removes the event with the least time.
     */

    public void remove() {
        Event earliestEvent = null;
        int index = 0;
        for (int i = 0; i < size(); i++) {
            Event event = queue.get(i);
            if (i == 0 || event.getTime() < earliestEvent.getTime()) {
                earliestEvent = event;
                index = i;
            }
        }
        queue.remove(index);
    }
    /**
     * Returns one event
     *
     * @return event
     */
    public Event getEvent(int i){
        return queue.get(i);
    }
}

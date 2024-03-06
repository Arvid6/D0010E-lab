package GeneralClasses;

import GeneralClasses.Event;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class contains a FIFO-list.
 * 
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */

public class FIFO {
    protected ArrayList<Event> queue = new ArrayList<Event>();
    // private int maximumSize = 0;

    public int size() {
        return queue.size();
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return True or false.
     */

    public boolean isEmpty() {
        if (queue.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks which events in the queue that that has the least time and deletes it.
     * 
     * @return The earliest event.
     * @throws NoSuchElementException
     */

    public Event first() throws NoSuchElementException {

        if (queue.size() == 0) {
            throw new NoSuchElementException();
        }
        return queue.get(0);
    }

    /**
     * Adds events to the queue.
     * 
     * @param item
     */

    public void add(Event item) {
        queue.add(item);

    }
}

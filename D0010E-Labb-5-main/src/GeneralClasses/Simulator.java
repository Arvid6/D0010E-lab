package GeneralClasses;

import GeneralClasses.Event;
import GeneralClasses.EventQueue;
import GeneralClasses.State;

import java.util.Observable;

/**
 * This class is the general simulator
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */
public class Simulator extends Observable {
    private EventQueue queue;
    private State state;

    public Simulator(EventQueue queue, State state){
        this.state = state;
        this.queue = queue;
    }
    public void run(){
        Event event;
        while(!state.getBreak()){
            try {
                event = queue.first();
                event.run(state);
                queue.remove();
            }
            catch(NullPointerException e) {
                break;
            }
        }
    }
}


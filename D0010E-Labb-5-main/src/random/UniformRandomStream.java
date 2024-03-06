
package random;

import java.util.Random;

/**
 * This is the class responsible for randomizing uniformed distributed times.
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */
public class UniformRandomStream {

    private Random rand;
    private double lower, width;

    /**
     * The constructor
     * Makes an object with randomized times that are uniformed distributed
     *
     * @param lower Lower limit
     * @param upper Upper limit
     * @param seed  Seed
     */
    public UniformRandomStream(double lower, double upper, long seed) {
        rand = new Random(seed);
        this.lower = lower;
        this.width = upper - lower;
    }

    /**
     * The constructor
     * Makes an object with randomized times that are uniformed distributed
     *
     * @param lower Lower limit
     * @param upper Upper limit
     */
    public UniformRandomStream(double lower, double upper) {
        rand = new Random();
        this.lower = lower;
        this.width = upper - lower;
    }

    /**
     * Returns the next randomized time
     *
     * @return The next time
     */
    public double next() {
        return lower + rand.nextDouble() * width;
    }
}


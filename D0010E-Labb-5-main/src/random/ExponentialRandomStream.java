
package random;

import java.util.Random;

/**
 * This is the class responsible for randomizing exponentially distributed times.
 *
 * @author Arvid Falkman, Felix Martensson, Johnny Lam and Jonathan Lindholm
 */
public class ExponentialRandomStream {

    private Random rand;
    private double lambda;

    /**
     * The constructor
     * Makes an object with randomized times that are exponentially distributed
     *
     * @param lambda Average customers per time unit
     * @param seed   Seed
     */
    public ExponentialRandomStream(double lambda, long seed) {
        rand = new Random(seed);
        this.lambda = lambda;
    }

    /**
     * The constructor
     * Makes an object with randomized times that are exponentially distributed
     *
     * @param lambda
     */
    public ExponentialRandomStream(double lambda) {
        rand = new Random();
        this.lambda = lambda;
    }

    /**
     * Returns the next randomized time
     *
     * @return The next time
     */
    public double next() {
        return -Math.log(rand.nextDouble()) / lambda;
    }
}


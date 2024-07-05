import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utilities that could be used with RSA cryptosystems.
 *
 * @author Elijah Paulman
 *
 */
public final class CryptoUtilities {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CryptoUtilities() {
    }

    /**
     * Useful constant, not a magic number: 3.
     */
    private static final int THREE = 3;

    /**
     * Pseudo-random number generator.
     */
    private static final Random GENERATOR = new Random1L();

    /**
     * Returns a random number uniformly distributed in the interval [0, n].
     *
     * @param n
     *            top end of interval
     * @return random number in interval
     * @requires n > 0
     * @ensures <pre>
     * randomNumber = [a random number uniformly distributed in [0, n]]
     * </pre>
     */
    public static NaturalNumber randomNumber(NaturalNumber n) {
        assert !n.isZero() : "Violation of: n > 0";
        final int base = 10;
        NaturalNumber result;
        int d = n.divideBy10();
        if (n.isZero()) {
            /*
             * Incoming n has only one digit and it is d, so generate a random
             * number uniformly distributed in [0, d]
             */
            int x = (int) ((d + 1) * GENERATOR.nextDouble());
            result = new NaturalNumber2(x);
            n.multiplyBy10(d);
        } else {
            /*
             * Incoming n has more than one digit, so generate a random number
             * (NaturalNumber) uniformly distributed in [0, n], and another
             * (int) uniformly distributed in [0, 9] (i.e., a random digit)
             */
            result = randomNumber(n);
            int lastDigit = (int) (base * GENERATOR.nextDouble());
            result.multiplyBy10(lastDigit);
            n.multiplyBy10(d);
            if (result.compareTo(n) > 0) {
                /*
                 * In this case, we need to try again because generated number
                 * is greater than n; the recursive call's argument is not
                 * "smaller" than the incoming value of n, but this recursive
                 * call has no more than a 90% chance of being made (and for
                 * large n, far less than that), so the probability of
                 * termination is 1
                 */
                result = randomNumber(n);
            }
        }
        return result;
    }

    /**
     * Finds the greatest common divisor of n and m.
     *
     * @param n
     *            one number
     * @param m
     *            the other number
     * @updates n
     * @clears m
     * @ensures n = [greatest common divisor of #n and #m]
     */
    public static void reduceToGCD(NaturalNumber n, NaturalNumber m) {

        /*
         * Use Euclid's algorithm; in pseudocode: if m = 0 then GCD(n, m) = n
         * else GCD(n, m) = GCD(m, n mod m)
         */

        // TODO - fill in body
        //checks if m is zero
        if (!m.isZero()) {
            // Creates NaturalNumber for modulus
            NaturalNumber modulus = n.divide(m);
            // Copes modulus to n
            n.copyFrom(modulus);
            // Calls reduceToGCD recursively until m is zero
            reduceToGCD(m, modulus);
            // Transfers value of m to n and clears m
            n.transferFrom(m);
        }
    }

    /**
     * Reports whether n is even.
     *
     * @param n
     *            the number to be checked
     * @return true iff n is even
     * @ensures isEven = (n mod 2 = 0)
     */
    public static boolean isEven(NaturalNumber n) {

        // TODO - fill in body
        // Initializes boolean for isEven to false
        boolean even = false;
        // Creates new object temp with same value as n
        NaturalNumber temp = new NaturalNumber2(n);
        // NaturalNumber for 2
        NaturalNumber two = new NaturalNumber2(2);
        // Divides temp by 2 and checks for remainder
        if (temp.divide(two).isZero()) {
            // If there is no remainder when dividing by 2, n is even
            even = true;
        }
        // Returns result
        return even;
    }

    /**
     * Updates n to its p-th power modulo m.
     *
     * @param n
     *            number to be raised to a power
     * @param p
     *            the power
     * @param m
     *            the modulus
     * @updates n
     * @requires m > 1
     * @ensures n = #n ^ (p) mod m
     */
    public static void powerMod(NaturalNumber n, NaturalNumber p,
            NaturalNumber m) {
        assert m.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: m > 1";

        /*
         * Use the fast-powering algorithm as previously discussed in class,
         * with the additional feature that every multiplication is followed
         * immediately by "reducing the result modulo m"
         */

        // TODO - fill in body
        // Initialize NaturalNumbers for 1 and 2
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        // Creates new NaturalNumber objects with same values as p and n
        NaturalNumber copyP = new NaturalNumber2(p);
        NaturalNumber originalN = new NaturalNumber2(n);

        // Checks if power is 0
        if (copyP.isZero()) {
            // Anything to 0 power is 1 - copies 1 to n
            n.copyFrom(one);
            // Checks is power is even
        } else if (isEven(copyP)) {
            // Divides power by 2
            copyP.divide(two);
            // Calls powerMod recursively until p is 0
            powerMod(n, copyP, m);
            // Creates new object with current value of n
            NaturalNumber copyN = new NaturalNumber2(n);
            // Multiplies n by copyN
            n.multiply(copyN);
            // Transfers remainder of d divided by m to n
            n.transferFrom(n.divide(m));
            // If power is odd
        } else {
            // Divides copyP by 2
            copyP.divide(two);
            // Calls powerMod recursively until p is 0
            powerMod(n, copyP, m);
            // Creates new object with current value of n
            NaturalNumber copyN = new NaturalNumber2(n);
            // Multiples n by copyN
            n.multiply(copyN);
            // Multiplies n by originalN
            n.multiply(originalN);
            // Transfers remainder of d divided by m to n
            n.transferFrom(n.divide(m));
        }
    }

    /**
     * Reports whether w is a "witness" that n is composite, in the sense that
     * either it is a square root of 1 (mod n), or it fails to satisfy the
     * criterion for primality from Fermat's theorem.
     *
     * @param w
     *            witness candidate
     * @param n
     *            number being checked
     * @return true iff w is a "witness" that n is composite
     * @requires n > 2 and 1 < w < n - 1
     * @ensures <pre>
     * isWitnessToCompositeness =
     *     (w ^ 2 mod n = 1)  or  (w ^ (n-1) mod n /= 1)
     * </pre>
     */
    public static boolean isWitnessToCompositeness(NaturalNumber w,
            NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(2)) > 0 : "Violation of: n > 2";
        assert (new NaturalNumber2(1)).compareTo(w) < 0 : "Violation of: 1 < w";
        n.decrement();
        assert w.compareTo(n) < 0 : "Violation of: w < n - 1";
        n.increment();

        // TODO - fill in body
        // Initializes NaturalNumber for 1 and 2
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber one = new NaturalNumber2(1);
        // Creates new objects with same values as w and n
        NaturalNumber copyW = new NaturalNumber2(w);
        NaturalNumber copyN = new NaturalNumber2(n);

        // copyN = #copyN - 1
        copyN.decrement();
        // Calls powerMod for copyW ^ two modulo n
        powerMod(copyW, two, n);
        // Calls powerMod for w ^ copyN modulo n
        powerMod(w, copyN, n);

        // Returns true if w is not 1 or copyW is 1
        return (!w.equals(one) || copyW.equals(one));
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime1 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime1(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";
        boolean isPrime;
        if (n.compareTo(new NaturalNumber2(THREE)) <= 0) {
            /*
             * 2 and 3 are primes
             */
            isPrime = true;
        } else if (isEven(n)) {
            /*
             * evens are composite
             */
            isPrime = false;
        } else {
            /*
             * odd n >= 5: simply check whether 2 is a witness that n is
             * composite (which works surprisingly well :-)
             */
            isPrime = !isWitnessToCompositeness(new NaturalNumber2(2), n);
        }
        return isPrime;
    }

    /**
     * Reports whether n is a prime; may be wrong with "low" probability.
     *
     * @param n
     *            number to be checked
     * @return true means n is very likely prime; false means n is definitely
     *         composite
     * @requires n > 1
     * @ensures <pre>
     * isPrime2 = [n is a prime number, with small probability of error
     *         if it is reported to be prime, and no chance of error if it is
     *         reported to be composite]
     * </pre>
     */
    public static boolean isPrime2(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use the ability to generate random numbers (provided by the
         * randomNumber method above) to generate several witness candidates --
         * say, 10 to 50 candidates -- guessing that n is prime only if none of
         * these candidates is a witness to n being composite (based on fact #3
         * as described in the project description); use the code for isPrime1
         * as a guide for how to do this, and pay attention to the requires
         * clause of isWitnessToCompositeness
         */

        // TODO - fill in body
        // Initializes boolean for isPrime to true
        boolean isPrime = true;
        // Creates int for 50
        final int fifty = 50;
        // Creates NaturalNumbers for 1 and 3
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber three = new NaturalNumber2(THREE);
        // Creates new object copyN with same value as n
        NaturalNumber copyN = new NaturalNumber2(n);
        // Decrements copyN
        copyN.decrement();

        // Checks if n is less/greater than or equal to 3
        if (n.compareTo(three) <= 0) {
            // If n is less than 3, isPrime is true
            isPrime = true;
        } else if (isEven(n)) {
            // If n is even, isPrime is false
            isPrime = false;
        } else {
            /*
             * Uses 50 candidates to check if any candidates are a witness to n
             * being composite
             */
            for (int i = 1; i < fifty; i++) {
                // New NaturalNumber for randomGuess
                NaturalNumber randomGuess = randomNumber(copyN);
                /*
                 * While randomGuess is less than or equal to 1 or greater than
                 * or equal to copyN
                 */
                while ((randomGuess.compareTo(one) <= 0)
                        || (randomGuess.compareTo(copyN) >= 0)) {
                    randomGuess = randomNumber(copyN);
                }
                // Reports whether randomGuess is a "witness" that n is composite
                if (isWitnessToCompositeness(randomGuess, n)) {
                    // If composite, isPrime is false
                    isPrime = false;
                }
            }
        }
        // Returns if prime or not (high probability)
        return isPrime;
    }

    /**
     * Generates a likely prime number at least as large as some given number.
     *
     * @param n
     *            minimum value of likely prime
     * @updates n
     * @requires n > 1
     * @ensures n >= #n and [n is very likely a prime number]
     */
    public static void generateNextLikelyPrime(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(1)) > 0 : "Violation of: n > 1";

        /*
         * Use isPrime2 to check numbers, starting at n and increasing through
         * the odd numbers only (why?), until n is likely prime
         */

        // TODO - fill in body
        // If number is even, increments to odd number (even num always composite)
        if (isEven(n)) {
            // Increments n to be odd
            n.increment();
        }
        // While n is not prime according to isPrime2
        while (!isPrime2(n)) {
            // Adds 2 to n until prime number is found
            n.increment();
            n.increment();
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Sanity check of randomNumber method -- just so everyone can see how
         * it might be "tested"
         */
        final int testValue = 17;
        final int testSamples = 100000;
        NaturalNumber test = new NaturalNumber2(testValue);
        int[] count = new int[testValue + 1];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < testSamples; i++) {
            NaturalNumber rn = randomNumber(test);
            assert rn.compareTo(test) <= 0 : "Help!";
            count[rn.toInt()]++;
        }
        for (int i = 0; i < count.length; i++) {
            out.println("count[" + i + "] = " + count[i]);
        }
        out.println("  expected value = "
                + (double) testSamples / (double) (testValue + 1));

        /*
         * Check user-supplied numbers for primality, and if a number is not
         * prime, find the next likely prime after it
         */
        while (true) {
            out.print("n = ");
            NaturalNumber n = new NaturalNumber2(in.nextLine());
            if (n.compareTo(new NaturalNumber2(2)) < 0) {
                out.println("Bye!");
                break;
            } else {
                if (isPrime1(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime1.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime1.");
                }
                if (isPrime2(n)) {
                    out.println(n + " is probably a prime number"
                            + " according to isPrime2.");
                } else {
                    out.println(n + " is a composite number"
                            + " according to isPrime2.");
                    generateNextLikelyPrime(n);
                    out.println("  next likely prime is " + n);
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}

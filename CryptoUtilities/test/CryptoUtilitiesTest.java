import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Elijah Paulman
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    /**
     * Tests reduceToGCD method with 0 and 0.
     */
    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Tests reduceToGCD with 30 and 21(routine case).
     */
    @Test
    public void testReduceToGCD_30_21() {
        final int thirty = 30;
        final int three = 3;
        final int twentyOne = 21;
        NaturalNumber n = new NaturalNumber2(thirty);
        NaturalNumber nExpected = new NaturalNumber2(three);
        NaturalNumber m = new NaturalNumber2(twentyOne);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Tests reduceToGCD with 100 and 100(same number).
     */
    @Test
    public void testReduceToGCD_100_100() {
        final int oneHundred = 100;
        NaturalNumber n = new NaturalNumber2(oneHundred);
        NaturalNumber nExpected = new NaturalNumber2(oneHundred);
        NaturalNumber m = new NaturalNumber2(oneHundred);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Tests reduceToGCD with 555 and 5(similar numbers).
     */
    @Test
    public void testReduceToGCD_555_5() {
        final int fiveFiveFive = 555;
        final int five = 5;
        NaturalNumber n = new NaturalNumber2(fiveFiveFive);
        NaturalNumber nExpected = new NaturalNumber2(five);
        NaturalNumber m = new NaturalNumber2(five);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /**
     * Tests reduceToGCD with 128 and 1024(larger num divisible by smaller).
     */
    @Test
    public void testReduceToGCD_128_1024() {
        final int oneTwoEight = 128;
        final int oneZeroTwoFour = 1024;
        NaturalNumber n = new NaturalNumber2(oneTwoEight);
        NaturalNumber nExpected = new NaturalNumber2(oneTwoEight);
        NaturalNumber m = new NaturalNumber2(oneZeroTwoFour);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    /**
     * Tests isEven with 0 (minimum case).
     */
    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * Tests isEven with 1 (small num).
     */
    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Tests isEven with 2 (small even num).
     */
    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /**
     * Tests isEven with 5 (odd num).
     */
    @Test
    public void testIsEven_5() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /**
     * Tests isEven with max val(challenging case).
     */
    @Test
    public void testIsEven_maxVal() {
        final int max = Integer.MAX_VALUE - 1;
        final int expected = 2147483646;
        NaturalNumber n = new NaturalNumber2(max);
        NaturalNumber nExpected = new NaturalNumber2(expected);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    /**
     * Tests powerMod with 0, 0, and 2(min vals).
     */
    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Tests powerMod with 17, 18, and 19.
     */
    @Test
    public void testPowerMod_17_18_19() {
        final int seventeen = 17;
        final int eighteen = 18;
        final int nineteen = 19;

        NaturalNumber n = new NaturalNumber2(seventeen);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(eighteen);
        NaturalNumber pExpected = new NaturalNumber2(eighteen);
        NaturalNumber m = new NaturalNumber2(nineteen);
        NaturalNumber mExpected = new NaturalNumber2(nineteen);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Tests powerMod with 7, 7, and 7(same num).
     */
    @Test
    public void testPowerMod_7_7_7() {
        final int seven = 7;
        NaturalNumber n = new NaturalNumber2(seven);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(seven);
        NaturalNumber pExpected = new NaturalNumber2(seven);
        NaturalNumber m = new NaturalNumber2(seven);
        NaturalNumber mExpected = new NaturalNumber2(seven);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /**
     * Tests powerMod with max val(challenging case).
     */
    @Test
    public void testPowerMod_maxVal() {
        final int max = Integer.MAX_VALUE;
        final int intMax = 2147483647;
        NaturalNumber n = new NaturalNumber2(max);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(max);
        NaturalNumber mExpected = new NaturalNumber2(intMax);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    /**
     * Tests isWitnessToCompositeness with 4 (small composite) and 2.
     */
    @Test
    public void compositeWitness_4_2() {
        final int four = 4;
        NaturalNumber n = new NaturalNumber2(four);
        NaturalNumber w = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /**
     * Tests isWitnessToCompositeness with 7 (small prime) and 2.
     */
    @Test
    public void compositeWitness_7_2() {
        final int seven = 7;
        NaturalNumber n = new NaturalNumber2(seven);
        NaturalNumber w = new NaturalNumber2(2);
        assertTrue(!CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /**
     * Tests isWitnessToCompositeness with 997 (large prime) and 2.
     */
    @Test
    public void compositeWitness_997_2() {
        final int nineNineSeven = 997;
        NaturalNumber n = new NaturalNumber2(nineNineSeven);
        NaturalNumber w = new NaturalNumber2(2);
        assertTrue(!CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /**
     * Tests isWitnessToCompositeness with 100 and 2.
     */
    @Test
    public void compositeWitness_100_30() {
        final int oneHundred = 100;
        final int thirty = 30;
        NaturalNumber n = new NaturalNumber2(oneHundred);
        NaturalNumber w = new NaturalNumber2(thirty);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /*
     * Tests of isPrime1
     */
    /**
     * Tests isPrime1 with 2(small composite).
     */
    @Test
    public void isPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isPrime1(n));
    }

    /**
     * Tests isPrime1 with 3(small prime).
     */
    @Test
    public void isPrime1_3() {
        final int three = 3;
        NaturalNumber n = new NaturalNumber2(three);
        assertTrue(CryptoUtilities.isPrime1(n));
    }

    /**
     * Tests isPrime1 with 15 (routine composite).
     */
    @Test
    public void isPrime1_15() {
        final int fifteen = 15;
        NaturalNumber n = new NaturalNumber2(fifteen);
        assertTrue(!CryptoUtilities.isPrime1(n));
    }

    /**
     * Tests isPrime1 with 99(routine composite).
     */
    @Test
    public void isPrime1_99() {
        final int ninetyNine = 99;
        NaturalNumber n = new NaturalNumber2(ninetyNine);
        assertTrue(!CryptoUtilities.isPrime1(n));
    }

    /**
     * Tests isPrime1 with max val(challenging case).
     */
    @Test
    public void isPrime1_maxVal() {
        final int max = Integer.MAX_VALUE;
        NaturalNumber n = new NaturalNumber2(max);
        assertTrue(CryptoUtilities.isPrime1(n));
    }

    /*
     * Tests of isPrime2
     */

    /**
     * Tests isPrime2 with 2(small composite).
     */
    @Test
    public void isPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    /**
     * Tests isPrime2 with 3(small prime).
     */
    @Test
    public void isPrime2_3() {
        final int three = 3;
        NaturalNumber n = new NaturalNumber2(three);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    /**
     * Tests isPrime2 with 15(routine composite).
     */
    @Test
    public void isPrime2_15() {
        final int fifteen = 15;
        NaturalNumber n = new NaturalNumber2(fifteen);
        assertTrue(!CryptoUtilities.isPrime2(n));
    }

    /**
     * Tests isPrime2 with 99(routine composite).
     */
    @Test
    public void isPrime2_99() {
        final int ninetyNine = 99;
        NaturalNumber n = new NaturalNumber2(ninetyNine);
        assertTrue(!CryptoUtilities.isPrime2(n));
    }

    /**
     * Tests isPrime2 with max val(challening case).
     */
    @Test
    public void isPrime2_maxVal() {
        final int max = Integer.MAX_VALUE;
        NaturalNumber n = new NaturalNumber2(max);
        assertTrue(CryptoUtilities.isPrime2(n));
    }

    /*
     * Tests of generateNextLikelyToPrime
     */

    /**
     * Tests generateNextLikelyToPrime with 4(small composite next to prime).
     */
    @Test
    public void testGenerateNextLikelyToPrime_4() {
        final int four = 4;
        NaturalNumber n = new NaturalNumber2(four);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("5", n.toString());
    }

    /**
     * Tests generateNextLikelyToPrime with 9(routine case).
     */
    @Test
    public void testGenerateNextLikelyToPrime_9() {
        final int nine = 9;
        NaturalNumber n = new NaturalNumber2(nine);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("11", n.toString());
    }

    /**
     * Tests generateNextLikelyToPrime with 996(large composite next to prime).
     */
    @Test
    public void testGenerateNextLikelyToPrime_996() {
        final int nineNineSix = 996;
        NaturalNumber n = new NaturalNumber2(nineNineSix);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("997", n.toString());
    }

    /**
     * Tests generateNextLikelyToPrime with max val(challenging case).
     */
    @Test
    public void testGenerateNextLikelyToPrime_maxVal() {
        final int max = Integer.MAX_VALUE - 1;
        NaturalNumber n = new NaturalNumber2(max);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("2147483647", n.toString());
    }
}

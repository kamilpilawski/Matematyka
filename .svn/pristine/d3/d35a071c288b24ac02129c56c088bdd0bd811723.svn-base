package pl.pilawski.projektQuickStart;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumberOperations {
    NumberOperations() {
        super();
    }

    /**
     * This method is checking if given value is prime number.
     * This method is using Wilson's theorem.
     * Number n is prime if:
     * (n-1)!= -1 mod n 
     * or 
     * (n-1)!+1 mod n = 0
     * @param value
     * @return
     */
    public static boolean isPrime(int value) {
        BigInteger fct = factorial(value - 1);
        if ((fct.add(BigInteger.ONE)).mod(BigInteger.valueOf(value)).equals(BigInteger.ZERO)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Recursive factorial, parameter can not be negative number.
     * @param value parameter.
     * @return int factorial of parameter.
     */
    public static BigInteger factorial(int value) {
        BigInteger bigIntValue = BigInteger.valueOf(value);
        if (value < 0) {
            return BigInteger.ZERO;
        }
        if (value == 0 || value == 1) {
            return BigInteger.ONE;
        } else {
            return bigIntValue.multiply(factorial(value - 1));
        }
    }

    /**
     * Method returns list with coprime integers for given as parameter ring.
     * This numbers are invertible for that ring.
     * @param ring/body (alphabet lenght)
     * @return List<Integer> list of coprime integers.
     */

    public static List<Integer> getCoprimeIntegers(int ring) {
        List<Integer> coprimeIntegers = new ArrayList<>();
        for (int i = 1; i <= ring; i++) {
            if (gcd(i, ring) == 1) {
                coprimeIntegers.add(i);
            }
        }
        return coprimeIntegers;
    }

    /**
     * Method returns pairs of  invertible elements (units) at given ring.
     * @param ring
     * @return String with pairs of  invertible elements.
     */
    public static String writeUnitsPairs(int ring) {
        String result = "";
        for (int i = 1; i <= ring; i++) {
            for (int j = i; j <= ring; j++) {
                if ((j * i) % ring == 1) {
                    result += i + "-" + j + " ";
                }
            }
        }
        return result;
    }

    /**
     * Method returns true if variable is invertible element of given ring.
     * @param ring
     * @param variable
     * @return boolean true if variable is invertible element of given ring.
     */
    public static boolean isInvertible(int ring, int variable) {
        for (int i = 1; i <= ring; i++) {
            if ((variable * i) % ring == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method returns inverted number using Java.Math.
     * @param ring
     * @param varialbe
     * @return BigInteger inverted number.
     */
    public static int getInvertible(int ring, int varialbe) {
        return new BigInteger(String.valueOf(varialbe)).modInverse(new BigInteger(String.valueOf(ring))).intValue();
    }

    /**
     * Method returns amount of invertible elements (coprime numbers) for given ring.
     * @param ring
     * @return int amount
     */
    public static int amountOfUnits(int ring) {
        int amount = 0;
        for (int i = 1; i <= ring; i++) {
            if (gcd(i, ring) == 1) {
                amount++;
            }
        }
        return amount;
    }

    /**
     * Computes the greatest common divisor of p and q using Euclid's algorithm.
     * @param p
     * @param q
     * @return int greatest common divisor
     */
    public static int gcd(int p, int q) {
        if (q == 0)
            return p;
        else
            return gcd(q, p % q);
    }

    /**
     * Method returns prime factorization of given as param value.
     * @param value int value.
     * @param resultList should be null.
     * @return List<Integer> list of prime numbers.
     */
    public static List<Integer> primeFactorization(int value, List<Integer> resultList) {
        if (null == resultList) {
            resultList = new ArrayList<>();
        }
        if (isPrime(value)) {
            resultList.add(value);
            return resultList;
        } else {
            for (int prime : getAllPrimesBelow(value / 2)) {
                if (value % prime == 0) {
                    value /= prime;
                    resultList.add(prime);
                }
            }
        }
        return primeFactorization(value, resultList);
    }

    /**
     * Method returns array of prime numbers smaller then given as parameter number.
     * If given parameter is prime number, it will be included in result array.
     * @return Integer[] array with primes.
     */
    public static Integer[] getAllPrimesBelow(int maxValue) {
        if (maxValue < 2) {
            System.out.println(
                    "Value (" + maxValue + ") can not be smaller then 2. There is no smaller prime number then 2.");
            return null;
        } else {
            return erastothenesSieve(maxValue);
        }
    }

    /**
     * Sieve of Eratosthenes is a simple algorithm to find prime numbers.
     * @param maxValue method will return prime numbers under given value.
     * @return Integer[] array with primes.
     */
    public static Integer[] erastothenesSieve(int maxValue) {
        int sqrtMaxValue = (int) Math.sqrt(maxValue);
        List<Integer> resultList = new ArrayList<>();
        int[] allNumbersToCheck = new int[maxValue + 1];

        for (int i = 1; i <= maxValue; i++) {
            allNumbersToCheck[i] = i;
        }

        for (int i = 2; i <= sqrtMaxValue; i++) {
            if (allNumbersToCheck[i] != 0) {
                int j = 2 * i;
                while (j <= maxValue) {
                    allNumbersToCheck[j] = 0;
                    j += i;
                }
            }
        }
        for (int i = 2; i < allNumbersToCheck.length; i++) {
            if (allNumbersToCheck[i] != 0) {
                resultList.add(allNumbersToCheck[i]);
            }
        }
        Integer[] result = new Integer[resultList.size()];
        return resultList.toArray(result);
    }

}

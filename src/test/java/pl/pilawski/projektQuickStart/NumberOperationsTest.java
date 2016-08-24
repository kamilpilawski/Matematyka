package pl.pilawski.projektQuickStart;

import java.util.List;
import junit.framework.Assert;

import org.junit.Test;

public class NumberOperationsTest {

    @Test
    public void getAllPrimesBelowTest() {
        int param = 1927;
        Integer[] result = NumberOperations.getAllPrimesBelow(param);
        System.out.println("There is " + result.length + " prime numbers up to " + param);
        for (Integer prime : result) {
            System.out.print(prime + " : ");
        }
    }

    @Test
    public void factorialTest() {
        int param = 5;
        for (int i = -3; i <= 10; i++) {
            param = i;
            System.out.println("Factorial of " + param + " is " + NumberOperations.factorial(param) + " value have "
                    + NumberOperations.factorial(param).toString().length() + " numbers");
        }
    }

    @Test
    public void isPrimeTest() {
        int i = 9973;
        System.out.println("isPrime(" + i + ")" + NumberOperations.isPrime(i));
    }

    @Test
    public void primeFactorizationTest() {
        List<Integer> resultList = NumberOperations.primeFactorization(153, null);
        for (Integer integer : resultList) {
            System.out.println(integer);
        }
    }
    
    @Test
    public void amountOfUnitsTest(){
        Integer amount=NumberOperations.amountOfUnits(1927);
        Assert.assertNotNull(amount);
        System.out.println("Amount of units at given ring: "+amount);
    }

}

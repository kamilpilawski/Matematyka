package pl.pilawski.projektQuickStart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.emory.mathcs.backport.java.util.Arrays;

public class CryptoTest {

    @Test
    public void encryptingTest() {
        Crypto crypto = new Crypto();
        int[] message = {16};
        int paramA = 41, paramB = 16, ring = Crypto.POLISHALPHABET.length;
        System.out.println("literka: " + crypto.encrypt(message, paramA, paramB, ring)[0]);
        assertNotNull(crypto.encrypt(message, paramA, paramB, ring));
    }

    @Test
    public void decryptingTest() {
        Crypto crypto = new Crypto();
        String originalMessage = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPRSŚTUWYZŻŹ .?!0123456789,";
        int paramA = 5, paramB = 3, ring = Crypto.POLISHALPHABET.length;

        int[] messageArray = crypto.messageToCipher(originalMessage, Crypto.POLISHALPHABET);
        int[] encryptedArray = crypto.encrypt(messageArray, paramA,
                paramB, ring);

        int[] decryptedArray = crypto.decryptByParameters(encryptedArray, paramA, paramB, ring);
        String decryptedMessage = crypto.cipherToMessage(decryptedArray, Crypto.POLISHALPHABET);

        // System.out.println("Ori: " + originalMessage);
        // System.out.println("Dec: " + decryptedMessage);
        // System.out.println("Encrypted: " + crypto.cipherToMessage(encryptedArray, Crypto.POLISHALPHABET));
        // System.out.println("\n");
        // System.out.println("message array: ");
        // for (int i = 0; i < messageArray.length; i++) {
        // System.out.print(messageArray[i] + " : ");
        // }
        // System.out.println();
        // System.out.println("encrypted array: ");
        // for (int i = 0; i < encryptedArray.length; i++) {
        // System.out.print(encryptedArray[i] + " : ");
        // }
        // System.out.println();
        // System.out.println("decrypted array: ");
        // for (int i = 0; i < decryptedArray.length; i++) {
        // System.out.print(decryptedArray[i] + " : ");
        // }
        assertTrue(originalMessage.equals(decryptedMessage));
    }

    @Test
    public void amountOfUnitsTest() {
        int ring = 26;
        assertTrue(NumberOperations.amountOfUnits(ring) == 12);
    }

    @Test
    public void writeCoprimeIntegersTest() {
        int ring = 47;
        assertTrue(!NumberOperations.getCoprimeIntegers(ring).isEmpty());
    }

    @Test
    public void findInvertibleTest() {
        int ring = 26, variable = 3;
        assertTrue(NumberOperations.getInvertible(ring, variable) == 9);
    }

    @Test
    public void writeUnitsPairsTest() {
        int ring = 26;
        assertTrue(!NumberOperations.writeUnitsPairs(ring).isEmpty());
    }

    @Test
    public void decryptBruteForceTest() {
        Crypto crypto = new Crypto();
        // message says KAMIL
        int[] message = {13, 0, 16, 11, 14};
        // encrypting message
        int[] encryptedMessage = crypto.encrypt(message, 5, 3, Crypto.POLISHALPHABET.length);
        // decrpyting message
        String[] resultArray = crypto.decryptBruteForce(encryptedMessage, Crypto.POLISHALPHABET).split("\n");
        // print results
        System.out.println("ilosc wynikow: " + resultArray.length);
        for (int i = 0; i < resultArray.length; i++) {
            System.out.println(resultArray[i]);
            resultArray[i] = resultArray[i].substring(0, message.length);
        }
        // check if array contains original message
        assertTrue(Arrays.asList(resultArray).contains(crypto.cipherToMessage(message, Crypto.POLISHALPHABET)));
    }

    @Test
    public void decryptBruteForceSpecialMessageTest() {
        Crypto crypto = new Crypto();
        System.out.println("Wiadomosc zaszyfrowana: "
                + crypto.cipherToMessage(Crypto.ENCRYPTEDMESSAGE, Crypto.POLISHALPHABET));
        String[] resultArray = crypto.decryptBruteForce(Crypto.ENCRYPTEDMESSAGE, Crypto.POLISHALPHABET).split("\n");
        Set<String> improvedResultSet = new HashSet<String>();
        for (String singleLine : resultArray) {
            if (singleLine.substring(0, Crypto.ENCRYPTEDMESSAGE.length).contains(" ")
                    && singleLine.matches("^[A-Z].*")) {
                improvedResultSet.add(singleLine);
                System.out.println(singleLine);
            }
            System.out.println(singleLine);
        }
        System.out.println("wynikow bylo: " + resultArray.length);
        System.out.println("wynikow jest: " + improvedResultSet.size());
    }

    @Test
    public void statisticsOfCipherTest() {
        Crypto crypto = new Crypto();
        String encodedMessage = "ŚYN3L6ŚŃH42ŁHUW4ŃH,NÓLUŃH9YNŁL,JLU1ŃHLYL7S9N06Ź4EŃŁHULJŚYNLEHL,EŃHLŹEJZHZLK9J1ŃL969NW9JY?2ŚYLZ242ÓA32ĆL,YŃE1NL,JLYŃHUŃLU1ŃHL?9YNKŃŁA";
        char[] alphabet = Crypto.POLISHALPHABET;
        String[][] statistics = crypto.statisticsOfCipher(encodedMessage, alphabet);
        assertNotNull(statistics);
        assertTrue(statistics.length == alphabet.length);
    }

    @Test
    public void cipherToMessageTest() {
        Crypto crypto = new Crypto();
        int[] message = new int[47];
        for (int i = 0; i <= 46; i++) {
            message[i] = i;
        }
        assertEquals(crypto.cipherToMessage(message, Crypto.POLISHALPHABET),
                "AĄBCĆDEĘFGHIJKLŁMNŃOÓPRSŚTUWYZŻŹ .?!0123456789,");
    }

    @Test
    public void messageToCipherTest() {
        Crypto crypto = new Crypto();
        String message = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPRSŚTUWYZŻŹ .?!0123456789,";
        int[] messageAsArray = new int[47];
        for (int i = 0; i <= 46; i++) {
            messageAsArray[i] = i;
        }
        int[] resutlArray = crypto.messageToCipher(message, Crypto.POLISHALPHABET);
        for (int i = 0; i < message.length(); i++) {
            assertTrue(messageAsArray[i] == resutlArray[i]);
        }
        assertTrue(crypto.cipherToMessage(messageAsArray, Crypto.POLISHALPHABET).equals(message));
    }

    @Test
    public void decryptionTest() {
        Crypto crypto = new Crypto();
        int[] encryptedMessage = {16};
        int paramA = 41;
        int paramB = -16;
        int ring = 47;
        for (int i : crypto.decryptByParameters(encryptedMessage, paramA, paramB, ring)) {
            System.out.println("Decrpyted M is : " + i);
        }
    }

}

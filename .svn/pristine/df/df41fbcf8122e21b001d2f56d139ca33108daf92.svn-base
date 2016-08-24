package pl.pilawski.projektQuickStart;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;

/**
 * Class contains methods which allows to encrypt and decrypt messages.
 * Formula f(x)=ax+b mod m is used for encryption/decrypt messages.
 * a - first parameter ( a must belong to the set of coprime numbers for m )
 * b - second parameter
 * x - message
 * m - the number of letters in the alphabet (26 for english alphabet)
 * @author KPilawski
 *
 */
public class Crypto {
    /**
     * Crypted message (with polish alphabet).
     */
    static int[] ENCRYPTEDMESSAGE = {24, 28, 17, 39, 14, 42, 24, 18, 10, 40, 38, 15, 10, 26, 27, 40, 18, 10, 46, 17, 20,
            14, 26, 18, 10, 45, 28, 17, 15, 14, 46, 12, 14, 26, 37, 18, 10, 14, 28, 14, 43, 23, 45,
            17, 36, 42, 31, 40, 6, 18, 15, 10, 26, 14, 12, 24, 28, 17, 14, 6, 10, 14,
            46, 6, 18, 10, 14, 31, 6, 12, 29, 10, 29, 14, 13, 45, 12, 37, 18, 14, 45, 42, 45, 17, 27,
            45, 12, 28, 34, 38, 24, 28, 14, 29, 38, 40, 38, 20, 0, 39, 38, 4, 14, 46, 28, 18, 6, 37,
            17, 14, 46, 12, 14, 28, 18, 10, 26, 18, 14, 26, 37, 18, 10, 14, 34, 45, 28, 17, 13, 18,
            15, 0};
    /**
     * Polish alphabet with special signs and numbers.
     */
    static char[] POLISHALPHABET = {'A', 'Ą', 'B', 'C', 'Ć', 'D', 'E', 'Ę', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'Ł',
            'M', 'N', 'Ń', 'O', 'Ó', 'P', 'R', 'S', 'Ś', 'T', 'U', 'W', 'Y', 'Z', 'Ż', 'Ź', ' ', '.', '?', '!', '0',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', ','};
    /**
     * Parameters which are used for encryption/decrypt.
     */
    private int a, b, m;

    /**
     * Public constructor.
     */
    Crypto() {
    }

    /**
     * This method is encrypting message.
     * @param message int[] message
     * @param paramA int param A (coprime for ring)
     * @param paramB param B (offset signed integer)
     * @param ring ring/body (length of alphabet)
     * @return int[] encrypted array.
     */
    public int[] encrypt(int[] message, int paramA, int paramB, int ring) {
        int[] encryptedArray = new int[message.length];
        for (int i = 0; i < encryptedArray.length; i++) {
            encryptedArray[i] = encode(paramA, paramB, message[i], ring);
        }
        return encryptedArray;
    }

    /**
     * Method decrypts messages using default values for parameters (A, B, M),
     * @param encryptedMessage
     * @return
     */
    public String decrypt(String encryptedMessage) {
        String result = "";
        for (char character : encryptedMessage.toCharArray()) {
            result += Character.toString(decode(character));
        }
        return result;
    }

    /**
     * Method will try to break code using brute force method.
     * @param encryptedMessage
     * @param ring
     * @return String all possibilities.
     */
    public String decryptBruteForce(int[] encryptedMessage, char[] alphabet) {
        String result = "";
        int alphabetLength = alphabet.length;
        List<Integer> corpimeNumbers = NumberOperations.getCoprimeIntegers(alphabetLength);
        for (Integer coprimeNumber : corpimeNumbers) {
            for (int offset = 0; offset < alphabetLength; offset++) {
                for (int character : encryptedMessage) {
                    int index = decode(coprimeNumber, offset, character, alphabetLength);
                    String sign = Character.toString(alphabet[index]);
                    result += sign;
                }
                result += " a:" + coprimeNumber + "(" + NumberOperations.getInvertible(alphabetLength, coprimeNumber)
                        + ")" + " b:" + offset + "\n";
            }
        }
        return result;
    }

    /**
     * 
     * @param encryptedMessage
     * @param paramA
     * @param paramB
     * @param ring
     */
    public int[] decryptByParameters(int[] encryptedMessage, int paramA, int paramB, int ring) {
        int[] decryptedMessage = new int[encryptedMessage.length];
        for (int i = 0; i < encryptedMessage.length; i++) {
            decryptedMessage[i] = decode(paramA, paramB, encryptedMessage[i], ring);
        }
        return decryptedMessage;
    }

    /**
     * Method is encoding single character.
     * @param paramA param A (coprime for ring)
     * @param paramB param B (can be signed integer)
     * @param character int value for character
     * @param ring ring/body (alphabet length)
     * @return int encoded character.
     */
    private int encode(int paramA, int paramB, int character, int ring) {
        if (NumberOperations.gcd(paramA, ring) == 1) {
            int encodedChar = (paramA * character + paramB) % ring;
            while (encodedChar < 0) {
                encodedChar += ring;
            }
            return encodedChar;
        } else {
            throw new ArithmeticException("Parameter A(" + paramA
                    + ") can not be used for encryption. Only this numbers can be used for this length of alphabet: "
                    + NumberOperations.getCoprimeIntegers(ring));
        }
    }

    /**
     * Method is decoding single character.
     * @param c
     * @return
     */
    private char decode(char c) {
        int a = NumberOperations.getInvertible(getM(), getA());
        return (char) (((a * (c - getB())) % 26) + 65);
    }

    /**
     * Method is decoding single number.
     * Method is using formula according to Wikipedia.
     * d(y)=a^(-1)*(y-b) mod RING.
     * @param a A param
     * @param b B param
     * @param y Y param
     * @param ring Ring
     * @return int uncoded number.
     * @throws ArithmeticException 
     */
    private int decode(int a, int b, int y, int ring) {
        int result = (NumberOperations.getInvertible(ring, a) * (y - b)) % ring;
        while (result < 0) {
            result += ring;
        }
        return result;
    }

    /**
     * Method is translating int array to String message using alphabet.
     * @param messageAsArray array of integers which are message.
     * @param alphabet array with letters and signs.
     * @return String message.
     */
    public String cipherToMessage(int[] messageAsArray, char[] alphabet) {
        String message = "";
        for (int character : messageAsArray) {
            message += alphabet[character];
        }
        return message;
    }

    /**
     * Method is creating array of integers according to given string and alphabet.
     * @param message string message which should be converted.
     * @param alphabet
     * @return int[] array of integers which are number of letters in, given as parameter, alphabet.
     */
    public int[] messageToCipher(String message, char[] alphabet) {
        int[] resultArray = new int[message.length()];
        for (int j = 0; j < message.length(); j++) {
            for (int i = 0; i < alphabet.length; i++) {
                if (message.toCharArray()[j] == alphabet[i]) {
                    resultArray[j] = i;
                }
            }
        }
        return resultArray;
    }

    /**
     * Method is counting occurrence of each letter at cipher.
     * @param cipher encoded message.
     * @return String[][] with statistics.
     */
    public String[][] statisticsOfCipher(String cipher, char[] alphabet) {
        String[][] statistics = new String[alphabet.length][2];
        for (int i = 0; i < alphabet.length; i++) {
            int occurrence = StringUtils.countMatches(cipher, Character.toString(alphabet[i]));
            statistics[i][0] = Integer.toString(occurrence);
            statistics[i][1] = Character.toString(alphabet[i]);
        }
        return statistics;
    }

    /**
     * Print message using polish alphabet.
     * @param message
     */
    public void printMessage(int[] message) {
        System.out.print("Message is: ");
        for (int character : message) {
            System.out.print(Crypto.POLISHALPHABET[character]);
        }
        System.out.println();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "A:" + this.getA() + " B:" + this.getB() + " M:" + this.getM();
    }

}

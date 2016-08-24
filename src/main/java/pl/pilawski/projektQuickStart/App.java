package pl.pilawski.projektQuickStart;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Crypto crypto = new Crypto();

        int[] resultArr = crypto.messageToCipher("ABC DEF GHI JKL MNO PRS TUW YZ ,.!", Crypto.POLISHALPHABET);
        for (int number : resultArr) {
            System.out.print(number + " ");
        }

        System.out.println("Wiadomosc to: " + crypto.cipherToMessage(resultArr, Crypto.POLISHALPHABET));

        int[] kamilJeCmyCrypted = crypto.encrypt(resultArr, 5, 3, Crypto.POLISHALPHABET.length);

        System.out.println(
                "Wiadomosc zaszyfrowana to: " + crypto.cipherToMessage(kamilJeCmyCrypted, Crypto.POLISHALPHABET));

        // String[] decryptedMessages = crypto.decryptBruteForce(kamilJeCmyCrypted, Crypto.POLISHALPHABET).split("\n");
        // Set<String> improvedResultSet = new HashSet<>();
        // for (String singleLine : decryptedMessages) {
        // if (singleLine.substring(0, kamilJeCmyCrypted.length).contains(" ")
        // && singleLine.matches("^[A-Z].*")) {
        // improvedResultSet.add(singleLine);
        // System.out.println(singleLine);
        // }
        // }
        // System.out.println("wynikow bylo: " + decryptedMessages.length);
        // System.out.println("wynikow jest: " + improvedResultSet.size());

        System.out.println("Y Magia:");
        // crypto.decryptByParameters(kamilJeCmyCrypted, Crypto.POLISHALPHABET, 5, 3);

    }
}

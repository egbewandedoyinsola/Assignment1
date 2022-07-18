package a01;

/**
 * package a01;
 * 
 * 
 * import java.io.File;
 * import java.io.FileNotFoundException;
 * import java.io.PrintWriter;
 * import java.util.Scanner;
 * 
 * 
 * This program repeatedly asks a bunch of commands if it encounters the word
 * decrypt it decrypts the given encrypted file given by user.
 *
 * @author Doyinsola Egbewande (A00438008)
 */
public class Cryptography {

    public static final Scanner KBD = new Scanner(System.in);

    public static void main(String[] args) {

        // Print Introduction
        printIntroduction();
        // Commands
        mainLoop();

    }

    /**
     * Print the introduction to this program.
     */
    private static void printIntroduction() {
        System.out.println("Cryptography Program\n"
                + "--------------------\n"
                + "\n"
                + "This program encrypts and decrypts files.\n"
                + "\n"
                + "By Doyinsola Egbewande (A00438008) ");
    }

    /**
     * This is the major while loop that keeps asking user for commands until
     * user enters any abbreviation of the word quit
     */
    private static void mainLoop() {
        // String firstAns = firstQuestion();
        String answer;
        System.out.print(
                "\nEnter command (Encrypt, Decrypt or Quit): ");
        answer = KBD.nextLine();
        while (!quit(answer)) {
            commands(answer);
            answer = firstQuestion();
        }
        if (quit(answer)) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        }
        System.out.println("");
    }

    /**
     * This method to asks user what command they would like to perform
     * iteration
     *
     * @return users response
     */
    private static String firstQuestion() {
        String ans;
        ans = "";
        // ans= KBD.nextLine();
        while (ans.isEmpty()) {
            System.out.print(
                    "\nEnter command (Encrypt, Decrypt or Quit): ");
            ans = KBD.nextLine();
        }
        return ans;

    }

    /**
     * users response to commands
     *
     * @param answer users response
     *
     */
    public static void commands(String answer) {

        if (encrypt(answer)) {
            System.out.println("\nI'm sorry, but I don't know how to encrypt "
                    + "yet.");
        } else if (decrypt(answer)) {
            try {
                Scanner result = getInputFile("to decrypt");
                PrintWriter outputFile = getOutputFile();
                outputFile.print(readDecrytpedLines(result));
                outputFile.close();
            } catch (FileNotFoundException fnf) {
                System.out.println();
            }

        } else if (enterKey(answer)) {
            System.out.print(
                    "\nEnter command (Encrypt, Decrypt or Quit): ");

        } else {
            System.out.println(
                    "I'm sorry, I don't recognize the command "
                            + answer.toUpperCase()
                            + ".");
        }

        // System.out.print("\n");
    }

    /**
     * This method decrypts users file line by line
     *
     * @param fin the file we want to decrypt
     * @return decrypted string
     */
    private static String readDecrytpedLines(Scanner fin) {
        if (fin == null) {
            return null;
        }
        // reading file content
        String n;
        int shiftValue;
        String decryptedValue = "";

        while (fin.hasNextLine()) {
            n = fin.nextLine();
            shiftValue = Integer.parseInt(n.substring(0, 3));
            for (int start = 3; start < n.length(); start = start + 3) {
                try {
                    // Separate string list into 3
                    decryptedValue += (char) (Integer.parseInt(n.substring(start, start + 3))
                            - shiftValue);

                } catch (StringIndexOutOfBoundsException oobex) {
                    System.out.println(
                            "One of the lines seems to be short.\n"
                                    + "The output may be corrupted!");
                }
            }

        }
        return decryptedValue;
    }

    /**
     * Connect to a file named by the user.
     *
     * @return a Scanner connected to a file named by the user.
     * @param userSaid what that particular command says
     * @throws FileNotFoundException if user gives up
     */
    private static Scanner getInputFile(String userSaid)
            throws FileNotFoundException {
        Scanner result = null;
        String name = null;

        while (result == null) {
            // System.out.println();
            System.out.printf("Enter the name of the file %s: ", userSaid);
            name = KBD.nextLine();
            if (name.isEmpty()) {
                throw new FileNotFoundException();
            }
            try {
                result = new Scanner(new File(name));
            } catch (FileNotFoundException fnf) {
                System.out.println("\nI'm sorry, but I couldn't open that "
                        + "file.");

            }
        }

        return result;
    }

    /**
     * Connect to a file named by the user.
     *
     * @return a PrintWriter connected to a file named by the user.
     * @throws FileNotFoundException if user gives up
     */
    private static PrintWriter getOutputFile()
            throws FileNotFoundException {
        PrintWriter outputFile = null;
        String name = null;

        while (outputFile == null) {
            // System.out.println();
            System.out.print("Enter the name of the file for "
                    + "decrypted output: ");
            name = KBD.nextLine();
            if (name.isEmpty()) {
                throw new FileNotFoundException();
            }
            try {
                outputFile = new PrintWriter(new File(name));
            } catch (FileNotFoundException fnf) {
                System.out.println("I'm sorry, but I couldn't open that file.");
                // System.out.println("(Leave the name blank to give up.)");
            }
        }

        return outputFile;
    }

    /**
     * Check a word to see if it counts as a "encrypt".
     *
     * @param word the word to check
     * @return whether the word counts as a "encrypt"
     */
    private static boolean encrypt(String word) {
        return word.equalsIgnoreCase("e")
                || word.equalsIgnoreCase("en")
                || word.equalsIgnoreCase("enc")
                || word.equalsIgnoreCase("encr")
                || word.equalsIgnoreCase("encry")
                || word.equalsIgnoreCase("encryp")
                || word.equalsIgnoreCase("encrypt");
    }

    /**
     * Check a word to see if it counts as a "decrypt".
     *
     * @param word the word to check
     * @return whether the word counts as a "decrypt"
     */
    private static boolean decrypt(String word) {
        return word.equalsIgnoreCase("d")
                || word.equalsIgnoreCase("de")
                || word.equalsIgnoreCase("dec")
                || word.equalsIgnoreCase("decr")
                || word.equalsIgnoreCase("decry")
                || word.equalsIgnoreCase("decryp")
                || word.equalsIgnoreCase("decrypt");
    }

    /**
     * Check a word to see if it counts as a "quit".
     *
     * @param word the word to check
     * @return whether the word counts as a "quit"
     */
    private static boolean quit(String word) {
        return word.equalsIgnoreCase("quit")
                || word.equalsIgnoreCase("qui")
                || word.equalsIgnoreCase("qu")
                || word.equalsIgnoreCase("q");
    }

    /**
     * Check a word to see if it counts as a "enterKey".
     *
     * @param word the word to check
     * @return whether the word counts as a "enterKey"
     */
    private static boolean enterKey(String word) {
        return word.equalsIgnoreCase("");

    }

}

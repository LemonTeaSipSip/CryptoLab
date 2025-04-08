package CryptoLab;
import java.util.*;

public class SHA {

    static char itos(int n) {
        return (n == 0) ? '0' : '1';
    }

    static String decToBinary(int n) {
        StringBuilder res = new StringBuilder();
        int count = 8;
        int i = n;

        while (i != 0 || count > 0) {
            if (i == 0 && res.length() < 8) {
                while (res.length() != 8) {
                    res.insert(0, "0");
                }
                return res.toString();
            }
            res.insert(0, itos(i % 2));
            i /= 2;
            count--;
        }

        return res.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give the original message to check: ");
        String inp = scanner.nextLine();
        int n = inp.length();

        List<String> inputChain = new ArrayList<>();
        List<String> paddingChain = new ArrayList<>();

        // Part 1 - Binary representation of input
        System.out.println("1. Showing the original message in binary:");
        for (int i = 0; i < n; i++) {
            String temp = decToBinary((int) inp.charAt(i));
            System.out.print(temp + " ");
            inputChain.add(temp);
        }

        int totalMessageBits = n * 8;
        System.out.println("\nThe total message bits size: " + totalMessageBits);

        // Calculate number of required padding bits
        int i = 1;
        while ((512 * i) - 64 - totalMessageBits < 0) {
            i++;
        }
        int x = (512 * i) - 64 - totalMessageBits;
        System.out.println("\n2. The total number of padding bits is: " + x);

        String messageLength = decToBinary(totalMessageBits); // message length in 8-bit binary
        System.out.println("Message length (8-bit representation): " + messageLength);

        // Add the 1 followed by 0s
        paddingChain.add("10000000");
        int paddingBlocks = x / 8;
        int temp = paddingBlocks - 1;

        while (temp != 0) {
            paddingChain.add("00000000");
            temp--;
        }

        System.out.println("The number of padding blocks is (without the message size block): " + paddingBlocks);

        // Print padding bits
        System.out.println("3. The padding bits are: ");
        for (String s : paddingChain) {
            System.out.print(s + " ");
        }

        // Display final message + padding
        System.out.println("\n\nThe Message in bits are below (The size of the message is: " + totalMessageBits + "):");
        for (String s : inputChain) {
            System.out.print(s + " ");
        }

        System.out.println("\nHere is the padding bits without the filler bits: ");
        for (int j = 0; j < paddingChain.size() - 1; j++) {
            System.out.print(paddingChain.get(j) + " ");
        }

        System.out.println("\nHere is the message size bits: " + messageLength);

        scanner.close();
    }
}

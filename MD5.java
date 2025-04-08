import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MD5{

    // Function to convert integer to 8-bit binary string
    static String decToBinary(int n) {
        StringBuilder res = new StringBuilder();
        int count = 8;
        while (count-- > 0) {
            res.insert(0, (n % 2 == 0) ? '0' : '1');
            n /= 2;
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

        // Part 1: Convert input to binary
        System.out.println("\n1. Showing the original message in binary:");
        for (int i = 0; i < n; i++) {
            String temp = decToBinary(inp.charAt(i));
            System.out.print(temp + " ");
            inputChain.add(temp);
        }

        int totalMessageBits = n * 8;
        System.out.println("\n\nThe total message bits size: " + totalMessageBits);

        // Part 2: Calculate required padding
        int i = 1;
        while ((512 * i) - 64 - totalMessageBits < 0) {
            i++;
        }
        int x = (512 * i) - 64 - totalMessageBits;

        System.out.println("\n2. The total number of padding bits is: " + x);

        // For simplicity, consider last 8 bits of 64-bit length representation
        String messageLength = decToBinary(totalMessageBits);
        System.out.println("Message length in binary (last 8-bits considered): " + messageLength);

        // Add initial 1 bit followed by zeros
        paddingChain.add("10000000");
        int paddingBlocks = x / 8;

        for (int temp = paddingBlocks - 1; temp > 0; temp--) {
            paddingChain.add("00000000");
        }

        System.out.println("The number of padding blocks (without message size block): " + paddingBlocks);

        // Display padding bits
        System.out.println("\n3. The padding bits are:");
        for (String p : paddingChain) {
            System.out.print(p + " ");
        }

        // Display final message bits and padding
        System.out.println("\n\nThe Message in bits (Total size: " + totalMessageBits + "):");
        for (String m : inputChain) {
            System.out.print(m + " ");
        }

        System.out.println("\n\nHere is the padding bits without the filler bits: ");
        for (int j = 0; j < paddingChain.size() - 1; j++) {
            System.out.print(paddingChain.get(j) + " ");
        }

        System.out.println("\nHere is the message size bits: " + messageLength);

        scanner.close();
    }
}

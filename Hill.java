

import java.util.*;

public class Hill {

    public static int[][] keyMat = new int[][]{{1, 2, 1}, {2, 3, 2}, {2, 2, 1}};
    public static int[][] invKeyMat = new int[][]{{-1, 0, 1}, {2, -1, 0}, {-2, 2, -1}};
    public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        String text, outText = "", outText1 = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Plain text for Encryption: ");
        text = sc.next();
        text = text.toUpperCase();
        text = text.replaceAll("\\s", "");
        int n = text.length() % 3;
        if (n != 0) {
            for (int i = 1; i <= (3 - n); i++) {
                text += 'X';
            }
        }
        System.out.println("Padded Text: " + text);
        char[] ptextChars = text.toCharArray();
        for (int i = 0; i < text.length(); i += 3) {
            outText += encrypt(ptextChars[i], ptextChars[i + 1], ptextChars[i + 2]);
        }
        System.out.println("Encrypted Message: " + outText);
        char[] ptextChars1 = outText.toCharArray();
        for (int i = 0; i < outText.length(); i += 3) {
            outText1 += decrypt(ptextChars1[i], ptextChars1[i + 1], ptextChars1[i + 2]);
        }
        System.out.println("Decrypted Message: " + outText1);
    }

    private static String encrypt(char a, char b, char c) {
        String ret = "";
        int x, y, z;
        int posA = a - 65;
        int posB = b - 65;
        int posC = c - 65;
        x = (posA * keyMat[0][0] + posB * keyMat[1][0] + posC * keyMat[2][0]) % 26;
        y = (posA * keyMat[0][1] + posB * keyMat[1][1] + posC * keyMat[2][1]) % 26;
        z = (posA * keyMat[0][2] + posB * keyMat[1][2] + posC * keyMat[2][2]) % 26;
        x = (x < 0) ? (26 + x) : x;
        y = (y < 0) ? (26 + y) : y;
        z = (z < 0) ? (26 + z) : z;
        a = key.charAt(x);
        b = key.charAt(y);
        c = key.charAt(z);
        ret = "" + a + b + c;
        return ret;
    }

    private static String decrypt(char a, char b, char c) {
        String ret = "";
        int x, y, z;
        int posA = a - 65;
        int posB = b - 65;
        int posC = c - 65;
        x = (posA * invKeyMat[0][0] + posB * invKeyMat[1][0] + posC * invKeyMat[2][0]) % 26;
        y = (posA * invKeyMat[0][1] + posB * invKeyMat[1][1] + posC * invKeyMat[2][1]) % 26;
        z = (posA * invKeyMat[0][2] + posB * invKeyMat[1][2] + posC * invKeyMat[2][2]) % 26;
        x = (x < 0) ? (26 + x) : x;
        y = (y < 0) ? (26 + y) : y;
        z = (z < 0) ? (26 + z) : z;
        a = key.charAt(x);
        b = key.charAt(y);
        c = key.charAt(z);
        ret = "" + a + b + c;
        return ret;
    }
}

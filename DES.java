
class DES {
    static int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    static int[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    static int[] key = {
        0,1,0,1, 1,0,1,0, 0,1,0,1, 1,0,1,0,
        1,1,0,0, 0,1,1,0, 1,1,0,0, 0,1,1,0,
        1,0,0,1, 1,1,0,0, 0,1,1,0, 1,0,0,1,
        1,1,0,0, 0,1,1,0, 1,0,0,1, 1,1,0,0
    };

    static void initialPermutation(int[] input, int[] output) {
        for (int i = 0; i < 64; i++) {
            output[i] = input[IP[i] - 1];
        }
    }

    static void finalPermutation(int[] input, int[] output) {
        for (int i = 0; i < 64; i++) {
            output[i] = input[FP[i] - 1];
        }
    }

    static void xorOperation(int[] a, int[] b, int[] result, int len) {
        for (int i = 0; i < len; i++) {
            result[i] = a[i] ^ b[i];
        }
    }

    static void simpleDES(int[] plaintext, int[] encrypted) {
        int[] temp = new int[64];
        initialPermutation(plaintext, temp);
        xorOperation(temp, key, encrypted, 64);
        finalPermutation(encrypted, encrypted);
    }

    static void printBinary(int[] data, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(data[i]);
            if ((i + 1) % 8 == 0) System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] plaintext = {
            0,1,1,0, 1,0,0,1, 1,1,0,0, 0,1,0,1,
            1,0,0,1, 0,1,1,0, 1,0,1,0, 1,0,1,0,
            1,0,1,0, 0,1,1,0, 0,1,0,1, 1,0,1,0,
            1,1,0,0, 1,1,0,0, 0,1,0,1, 1,0,1,0
        };

        int[] encrypted = new int[64];
        int[] decrypted = new int[64];

        System.out.println("Original Plaintext:");
        printBinary(plaintext, 64);

        simpleDES(plaintext, encrypted);
        System.out.println("Encrypted Text:");
        printBinary(encrypted, 64);

        simpleDES(encrypted, decrypted);
        System.out.println("Decrypted Text:");
        printBinary(decrypted, 64);
    }
}

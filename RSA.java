

import java.util.Scanner;

public class RSA {

    static long p, q, n, t, flag;
    static long[] e = new long[100];
    static long[] d = new long[100];
    static long[] temp = new long[100];
    static long[] m = new long[100];
    static long[] en = new long[100];
    static int j = 0;
    static String msg;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ENTER FIRST PRIME NUMBER: ");
        p = scanner.nextLong();
        if (!isPrime(p) || p == 1) {
            System.out.println("WRONG INPUT");
            return;
        }

        System.out.print("ENTER ANOTHER PRIME NUMBER: ");
        q = scanner.nextLong();
        if (!isPrime(q) || q == 1 || p == q) {
            System.out.println("WRONG INPUT");
            return;
        }

        System.out.print("ENTER MESSAGE: ");
        scanner.nextLine(); // consume leftover newline
        msg = scanner.nextLine();

        for (int i = 0; i < msg.length(); i++) {
            m[i] = msg.charAt(i);
        }

        n = p * q;
        t = (p - 1) * (q - 1);

        ce();

        System.out.println("\nPOSSIBLE VALUES OF e AND d ARE:");
        for (int i = 0; i < j - 1; i++) {
            System.out.println(e[i] + "\t" + d[i]);
        }

        encrypt();
        decrypt();

        scanner.close();
    }

    static boolean isPrime(long pr) {
        if (pr == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(pr); i++) {
            if (pr % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void ce() {
        int k = 0;
        for (int i = 2; i < t; i++) {
            if (t % i == 0) {
                continue;
            }
            if (isPrime(i) && i != p && i != q) {
                e[k] = i;
                long x = cd(e[k]);
                if (x > 0) {
                    d[k] = x;
                    k++;
                }
                if (k == 99) {
                    break;
                }
            }
        }
        j = k;
    }

    static long cd(long x) {
        long k = 1;
        while (true) {
            k += t;
            if (k % x == 0) {
                return k / x;
            }
        }
    }

    static void encrypt() {
        long pt, ct, key = e[0], k;
        int i = 0;
        long len = msg.length();

        while (i < len) {
            pt = m[i];
            pt = pt - 96;
            k = 1;
            for (int j = 0; j < key; j++) {
                k = (k * pt) % n;
            }
            temp[i] = k;
            ct = k + 96;
            en[i] = ct;
            i++;
        }
        en[i] = -1;

        System.out.print("\nTHE ENCRYPTED MESSAGE IS:\n");
        i = 0;
        while (en[i] != -1) {
            System.out.print((char) en[i]);
            i++;
        }
        System.out.println();
    }

    static void decrypt() {
        long pt, ct, key = d[0], k;
        int i = 0;

        while (en[i] != -1) {
            ct = temp[i];
            k = 1;
            for (int j = 0; j < key; j++) {
                k = (k * ct) % n;
            }
            pt = k + 96;
            m[i] = pt;
            i++;
        }
        m[i] = -1;

        System.out.print("\nTHE DECRYPTED MESSAGE IS:\n");
        i = 0;
        while (m[i] != -1) {
            System.out.print((char) m[i]);
            i++;
        }
        System.out.println();
    }
}

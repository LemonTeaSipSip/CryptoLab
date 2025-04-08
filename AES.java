package CryptoLab;
import java.util.*;

public class AES {
    
    static void printList(List<Integer> list) {
        for (int val : list) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    static List<Integer> conv(String inp) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < inp.length(); i++) {
            int k = 4 * (inp.length() - 1 - i);
            switch (Character.toUpperCase(inp.charAt(i))) {
                case '1': res.add(k); break;
                case '2': res.add(k + 1); break;
                case '3': res.add(k + 1); res.add(k); break;
                case '4': res.add(k + 2); break;
                case '5': res.add(k + 2); res.add(k); break;
                case '6': res.add(k + 2); res.add(k + 1); break;
                case '7': res.add(k + 2); res.add(k + 1); res.add(k); break;
                case '8': res.add(k + 3); break;
                case '9': res.add(k + 3); res.add(k); break;
                case 'A': res.add(k + 3); res.add(k + 1); break;
                case 'B': res.add(k + 3); res.add(k + 1); res.add(k); break;
                case 'C': res.add(k + 3); res.add(k + 2); break;
                case 'D': res.add(k + 3); res.add(k + 2); res.add(k); break;
                case 'E': res.add(k + 3); res.add(k + 2); res.add(k + 1); break;
                case 'F': res.add(k + 3); res.add(k + 2); res.add(k + 1); res.add(k); break;
                default: break;
            }
        }
        return res;
    }

    static char binToHex(String a) {
        return Integer.toHexString(Integer.parseInt(a, 2)).toUpperCase().charAt(0);
    }

    static String crush(List<Integer> res) {
        int[] arr = new int[8];
        for (int val : res) {
            if (val == 8) {
                arr[4]++;
                arr[3]++;
                arr[1]++;
                arr[0]++;
            } else {
                arr[val]++;
            }
        }
        StringBuilder finBin = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            finBin.append(arr[i] % 2 == 0 ? '0' : '1');
        }
        return "" + binToHex(finBin.substring(0, 4)) + binToHex(finBin.substring(4, 8));
    }

    static String crossMult(String a, String b) {
        List<Integer> powers = new ArrayList<>();
        if (a.equals("01")) {
            powers.add(0);
        } else if (a.equals("02")) {
            powers.add(1);
        } else if (a.equals("03")) {
            powers.add(1);
            powers.add(0);
        }

        List<Integer> mult = conv(b);
        List<Integer> res = new ArrayList<>();

        for (int m : mult) {
            for (int p : powers) {
                res.add(m + p);
            }
        }
        return crush(res);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sample Result");

        String[][] defMatrix = {
            {"02", "03", "01", "01"},
            {"01", "02", "03", "01"},
            {"01", "01", "02", "03"},
            {"03", "01", "01", "02"}
        };

        String[][] multMatrix = new String[4][4];
        System.out.println("Give the Columns to perform the operation on:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                multMatrix[i][j] = scanner.next().toUpperCase();
            }
        }

        System.out.println("The result is:");
        for (int i = 0; i < 4; i++) { // Row
            for (int j = 0; j < 4; j++) { // Column
                List<String> temp = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    temp.add(crossMult(defMatrix[i][k], multMatrix[k][j]));
                }

                List<Integer> hexCross = new ArrayList<>();
                for (String s : temp) {
                    hexCross.addAll(conv(s));
                }

                System.out.print(crush(hexCross) + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}

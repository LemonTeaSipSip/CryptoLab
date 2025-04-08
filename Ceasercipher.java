package CryptoLab;

class Ceasercipher {
    static StringBuffer encrypt(String text, int s) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);//for decryption convert the + to -;
                result.append(ch);
            } else if (Character.isWhitespace(text.charAt(i))) {
                result.append(' ');
            } else {
                char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);//for decryption convert the + to -;
                result.append(ch);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String text = "GERMANY UNDER ATTACK";
        int s = 4;
        System.out.println("Text: " + text);
        System.out.println("Shift: " + s);
        System.out.println("Cipher: " + encrypt(text, s));
        System.out.println();
        s = 18;
        System.out.println("Text: " + text);
        System.out.println("Shift: " + s);
        System.out.println("Cipher: " + encrypt(text, s));
        System.out.println();
    }
}


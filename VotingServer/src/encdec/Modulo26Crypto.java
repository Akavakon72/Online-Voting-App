package encdec;

public class Modulo26Crypto {

    public static void main(String[] args) {

        String plainText = "Sachin";
        String secretKey = "SECRETKEY";
        System.out.println("Plain Text Before Encryption: " + plainText);
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("Encrypted Text After Encryption: " + encryptedText);
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted Text After Decryption: " + decryptedText);
    }

    private static String encrypt(String plainText, String secretKey) {
        StringBuffer encryptedString = new StringBuffer();
        int encryptedInt;
        for (int i = 0; i < plainText.length(); i++) {
            int plainTextInt = (int) (plainText.charAt(i) - 'A');
            int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
            encryptedInt = (plainTextInt + secretKeyInt) % 26;
            encryptedString.append((char) ((encryptedInt) + (int) 'A'));
        }
        return encryptedString.toString();
    }

    private static String decrypt(String decryptedText, String secretKey) {
        StringBuffer decryptedString = new StringBuffer();
        int decryptedInt;
        for (int i = 0; i < decryptedText.length(); i++) {
            int decryptedTextInt = (int) (decryptedText.charAt(i) - 'A');
            int secretKeyInt = (int) (secretKey.charAt(i) - 'A');
            decryptedInt = decryptedTextInt - secretKeyInt;
            if (decryptedInt < 1) {
                decryptedInt += 26;
            }
            decryptedString.append((char) ((decryptedInt) + (int) 'A'));
        }
        return decryptedString.toString();
    }
}

package Controllers;

import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecureAuth {

    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;


    public static String createHash(String password, String salt) {
        try {
            // create hash of provided password
            char[] chars = password.toCharArray();
            byte[] saltBytes = salt.getBytes();
            PBEKeySpec spec = new PBEKeySpec(chars, saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return new String(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateSalt() {
        try {
            // generate a secure random salt
            SecureRandom sr = SecureRandom.getInstanceStrong();
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return new String(salt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


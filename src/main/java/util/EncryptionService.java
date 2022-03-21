/***********************************************************************************************************************************************************
 * EncryptionService is an utility class that contain methods that handles encryption and verification of the passwords during registration and login process
 ***********************************************************************************************************************************************************/

package main.java.util;

import java.security.NoSuchAlgorithmException;
// import java.security.SecureRandom;
// import java.util.Random;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @author Eugene Tin
 */
public class EncryptionService {

  private static final int ITERATIONS = 10000;
  private static final int KEY_LENGTH = 256;
  private static String SALT = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";

  // Generate salt with 10 rounds
  // public static String getSalt() {
  //   StringBuilder returnValue = new StringBuilder(10);
  //   for (int i = 0; i < 10; i++) {
  //     returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
  //   }
  //   return new String(returnValue);
  // }

  // Hash password with salt
  public static byte[] hash(char[] password, byte[] salt) {
    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
    Arrays.fill(password, Character.MIN_VALUE);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AssertionError(
        "Error while hashing a password: " + e.getMessage(),
        e
      );
    } finally {
      spec.clearPassword();
    }
  }

  // Generate secure password
  public static String generateSecurePassword(String password) {
    String returnValue = null;
    byte[] securePassword = hash(password.toCharArray(), SALT.getBytes());

    returnValue = Base64.getEncoder().encodeToString(securePassword);

    return returnValue;
  }

  // Verify passwords
  public static boolean verifyUserPassword(
    String providedPassword,
    String securedPassword
  ) {
    boolean returnValue = false;

    // Generate New secure password with the same salt
    String newSecurePassword = generateSecurePassword(providedPassword);

    // Check if two passwords are equal
    returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

    return returnValue;
  }
}

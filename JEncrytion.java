// Code borrowed from
// https://mkyong.com/java/jce-encryption-data-encryption-standard-aes-tutorial
// Changes made by Calen Olsen, Cody Binder

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class JEncrytion {
    public static void main(String[] argv) {
        try {
            long bestEncryptTime = 999999999;
            long bestDecryptTime = 999999999;
            
            // file for to be encrypted and decrypted
            Path fileName = Path.of("/home/calen/463/Project/small.txt");

            // read the file
            String str = Files.readString(fileName);

            // Printing the string

            byte[] text = str.getBytes();

            for (int i = 0; i < 20; i++) {

                byte[] decodedKey = new byte[] { 0x4D, 0x75, 0x4F, 0x4F, 0x7A, 0x7C, 0x78, 0x7C, 0x13, 0x4F, 0x52, 0x78,
                        0x75, 0x79, 0x45, 0x7C };
                SecretKey myDesKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

                Cipher aesCipher;

                // Create the cipher
                aesCipher = Cipher.getInstance("AES/cbc/PKCS5Padding");
                // time the ecncrtion 
                long startTime = System.currentTimeMillis();
                
                //make an IV
                SecureRandom randomSecureRandom = new SecureRandom();
                byte[] iv = new byte[aesCipher.getBlockSize()];
                randomSecureRandom.nextBytes(iv);
                IvParameterSpec ivParams = new IvParameterSpec(iv);
                aesCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                //encrypt the text
                byte[] textEncrypted = aesCipher.doFinal(text);
                long endTime = System.currentTimeMillis();
                long encryptTime = (endTime - startTime);
                
                // check if it is best time yet
                System.out.println("That took encrypt " + encryptTime + " milliseconds");
                if (encryptTime < bestEncryptTime) {
                    bestEncryptTime = encryptTime;
                }

                // time decryption
                startTime = System.currentTimeMillis();
                aesCipher.init(Cipher.DECRYPT_MODE, myDesKey, ivParams);
                // decryption
                aesCipher.doFinal(textEncrypted);
                endTime = System.currentTimeMillis();
                long decryptTime = (endTime - startTime);
                System.out.println("That took decrypt " + decryptTime + " milliseconds");
                if (decryptTime < bestDecryptTime) {
                    bestDecryptTime = decryptTime;
                }

                // Initialize the same cipher for decryption

            }
            System.out.println("best encrypt: " + String.valueOf(bestEncryptTime));
            System.out.println("best decrypt: " + String.valueOf(bestDecryptTime));

            //catch errors
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

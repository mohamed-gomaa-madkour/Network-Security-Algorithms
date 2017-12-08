package sample;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by MOHAM on 08-Dec-17.
 */
public class DESInterface {
DESInterface(File file,String Key){

    byte[] text = getByteArrayFromFile(file);
    long key = getKey(Key);
    long[] blocks = splitInputIntoBlocks(text);
    runCipher(blocks, key);

}

    private static byte[] getByteArrayFromFile(File file) {

        byte[] fileBuff = new byte[(int) file.length()];

        try {
            DataInputStream fileStream = new DataInputStream(new FileInputStream(file));
            fileStream.readFully(fileBuff);
            fileStream.close();
        } catch (IOException e) {
            printErrorAndDie("Cannot read from file.");
        }

        return fileBuff;
    }
    /**
     * Runs standard DES mode encryption and decryption on @param blocks using @param key with
     * appropriate output displayed.
     */

    private static void runCipher(long[] blocks, long key) {

        DES des = new DES();
        byte[] bytes;
        long[] cipherTexts = new long[blocks.length], plainTexts = new long[blocks.length];




        for (int i = 0; i < blocks.length; i++) {
            cipherTexts[i] = des.encrypt(blocks[i], key);
        }
        Controller.des_cipher=cipherTexts;


        for (int i = 0; i < cipherTexts.length; i++) {
            plainTexts[i] = des.decrypt(cipherTexts[i], key);
        }

        Controller.des_plain=plainTexts;

    }


    /**
     * Splits the input bytes into blocks of 64 bits.
     *
     * @param input The input text as a byte array.
     * @return An array of longs, representing the 64 bit blocks.
     */
    private static long[] splitInputIntoBlocks(byte[] input) {
        long blocks[] = new long[input.length / 8 + 1];

        for (int i = 0, j = -1; i < input.length; i++) {
            if (i % 8 == 0)
                j++;
            blocks[j] <<= 8;
            blocks[j] |= input[i];
        }

        return blocks;
    }
    /**
     * Gets a key from @param reader and formats it correctly.
     *
     * @return A 64 bit key as type long. If the input is greater than 64 bits, it will be
     * truncated. If less than 64 bits, it will be left-padded with 0s.
     */

    private static long getKey(String keyStr) {
         keyStr = "";
        byte[] keyBytes;
        long key64 = 0;

        if (keyStr.length() > 8) {
            System.out.println("Input is greater than 64 bits.");
            System.exit(0);
        }

        keyBytes = keyStr.getBytes();

        for (byte keyByte : keyBytes) {
            key64 <<= 8;
            key64 |= keyByte;
        }

        return key64;
    }

    private static void printErrorAndDie(String message) {
        System.err.println("Fatal IO error encountered." + "\n" + message);
        System.exit(1);
    }
}

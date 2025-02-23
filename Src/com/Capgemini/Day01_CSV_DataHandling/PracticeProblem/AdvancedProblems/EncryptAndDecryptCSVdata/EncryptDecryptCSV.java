package com.Capgemini.Day01_CSV_DataHandling.PracticeProblems.AdvancedProblems.EncryptAndDecryptCSVdata;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.io.FileWriter;

import java.io.FileReader;

import java.util.List;


public class EncryptDecryptCSV {

    private static final String SECRET_KEY = "1234567890123456";  // 16-byte key for AES encryption

    public static void main(String[] args) {
        String inputCsvPath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/EncryptAndDecryptCSVdata/inputCsv"; // Input CSV file path
        String outputCsvPath ="src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/EncryptAndDecryptCSVdata/output.Csv"; // Output encrypted CSV file path
        String decryptedCsvPath = "src/main/java/Day01_CSV_DataHandling/PracticeProblems/AdvancedProblems/EncryptAndDecryptCSVdata/decryptedCsv"; // Output decrypted CSV file path

        // Encrypt and write to CSV
        encryptAndWriteCsv(inputCsvPath, outputCsvPath);

        // Decrypt and read from CSV
        decryptAndReadCsv(outputCsvPath, decryptedCsvPath);
    }

    // Encrypt sensitive fields and write to CSV
    public static void encryptAndWriteCsv(String inputCsvPath, String outputCsvPath) {
        try {
            // Create CSVReader to read input file
            CSVReader csvReader = new CSVReader(new FileReader(inputCsvPath));
            List<String[]> records = csvReader.readAll();

            // Create CSVWriter to write output file
            CSVWriter csvWriter = new CSVWriter(new FileWriter(outputCsvPath));

            // Write header (no encryption needed)
            String[] header = records.get(0);
            csvWriter.writeNext(header);

            // Encrypt and write records
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                // Encrypt the sensitive fields (e.g., Salary and Email)
                record[2] = encrypt(record[2]);  // Assuming Salary is at index 2
                record[3] = encrypt(record[3]);  // Assuming Email is at index 3

                // Write the encrypted record to the output CSV
                csvWriter.writeNext(record);
            }

            csvReader.close();
            csvWriter.close();
            System.out.println("Data encrypted and written to CSV successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Decrypt sensitive fields and read from CSV
    public static void decryptAndReadCsv(String inputCsvPath, String decryptedCsvPath) {
        try {
            // Create CSVReader to read input file
            CSVReader csvReader = new CSVReader(new FileReader(inputCsvPath));
            List<String[]> records = csvReader.readAll();

            // Create CSVWriter to write output file
            CSVWriter csvWriter = new CSVWriter(new FileWriter(decryptedCsvPath));

            // Write header (no decryption needed)
            String[] header = records.get(0);
            csvWriter.writeNext(header);

            // Decrypt and write records
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);

                // Decrypt the sensitive fields (e.g., Salary and Email)
                record[2] = decrypt(record[2]);  // Assuming Salary is at index 2
                record[3] = decrypt(record[3]);  // Assuming Email is at index 3

                // Write the decrypted record to the output CSV
                csvWriter.writeNext(record);
            }

            csvReader.close();
            csvWriter.close();
            System.out.println("Data decrypted and written to CSV successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Encrypt a string using AES
    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return new String(encryptedData, StandardCharsets.ISO_8859_1);  // Encode in ISO_8859_1 to avoid Unicode issues
    }

    // Decrypt a string using AES
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(encryptedData.getBytes(StandardCharsets.ISO_8859_1));
        return new String(decryptedData, StandardCharsets.UTF_8);
    }
}

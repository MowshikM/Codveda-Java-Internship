
package LEVEL2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandling {

    public static void main(String[] args) {

        int lines = 0;
        int words = 0;

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader("LEVEL2/input.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                lines++;

                if (!line.trim().isEmpty()) {
                    words += line.trim().split("\\s+").length;
                }
            }

            br.close();

            System.out.println("File read successfully!");

            System.out.println("Total Lines: " + lines);
            System.out.println("Total Words: " + words);

            FileWriter fw = new FileWriter("LEVEL2/output.txt");

            fw.write("File Processing Results\n");
            fw.write("-----------------------\n");
            fw.write("Total Lines: " + lines + "\n");
            fw.write("Total Words: " + words + "\n");

            fw.close();

            System.out.println("Results written to output.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found!");
        } catch (IOException e) {
            System.out.println("An error occurred while handling the file.");
        }
    }
}

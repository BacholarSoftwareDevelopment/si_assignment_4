package dk.si.producer.file;

import java.io.*;

public class FileReader {

    public final String FILE_NAME = "src/main/resources/content.txt";

    public String readContentFromTextFile(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        StringBuilder contentString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String lineOfContent;
            while ((lineOfContent = br.readLine()) != null) {
                contentString.append(lineOfContent).append("\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return contentString.toString();
    }
}

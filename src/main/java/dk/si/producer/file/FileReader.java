package dk.si.producer.file;

import java.io.*;

public class FileReader {

    private InputStream inputStream = null;
    private StringBuilder contentString = new StringBuilder();
    private final String FILE_NAME = "src/main/resources/content.txt";

    /**
     *
     * @param filename
     * @return InputStream
     */
    public InputStream getInputStream(String filename) {
        try {
            inputStream = new FileInputStream(filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return inputStream;
    }

    /**
     *
     * @return String
     */
    public String readContentFromTextFile() {
        inputStream = getInputStream(FILE_NAME);

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

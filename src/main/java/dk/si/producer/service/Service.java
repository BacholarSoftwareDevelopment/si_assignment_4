package dk.si.producer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service {

    private final String API_PATH = "http://www.thomas-bayer.com/restnames/name.groovy?name=";
    private HttpURLConnection con;
    private URL url;

    private StringBuilder contentString = new StringBuilder();

    /**
     *
     * @param name
     * @return String
     * @throws IOException
     */
    public String getXMLDataGenderByName(String name) throws IOException {
        url = new URL( API_PATH+ name);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/xml");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
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

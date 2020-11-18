package dk.si.producer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Service {

    public String getXMLDataGenderByName(String name) throws IOException {
        URL url = new URL("http://www.thomas-bayer.com/restnames/name.groovy?name=" + name);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/xml");

        StringBuilder contentString = new StringBuilder();

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

package dk.si.producer.file;

import dk.si.producer.model.Member;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderXML {

    public static void main(String[] args) {
        String filePath = "src/main/resources/members.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("member");
            //now XML is loaded as Document in memory, lets convert it to Object List
            List<Member> members = new ArrayList<Member>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                members.add(getEmployee(nodeList.item(i)));
            }
            //lets print Employee list information
            for (Member emp : members) {
                System.out.println(emp.toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }

    }


    private static Member getEmployee(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        Member member = new Member();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            member.setName(getTagValue("name", element));
            member.setEmail(getTagValue("email", element));
        }

        return member;
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}

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

    private final String FILE_PATH = "src/main/resources/members.xml";
    private final String NODE_TAG = "member";
    private final String NODE_TAG_NAME = "name";
    private final String NODE_TAG_EMAIL = "email";
    private List<Member> members = new ArrayList<>();

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    private NodeList nodeList;

    /**
     *
     * @return List<Member>
     */
    public List<Member> getMembersFromXMLFile() {
        File xmlFile = new File(FILE_PATH);
        dbFactory = DocumentBuilderFactory.newInstance();

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            nodeList = doc.getElementsByTagName(NODE_TAG);

            for (int i = 0; i < nodeList.getLength(); i++) {
                members.add(getMember(nodeList.item(i)));
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        return members;
    }

    /**
     *
     * @param node
     * @return Member
     */
    private Member getMember(Node node) {

        Member member = new Member();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            member.setName(getTagValue(NODE_TAG_NAME, element));
            member.setEmail(getTagValue(NODE_TAG_EMAIL, element));
        }
        return member;
    }

    /**
     *
     * @param tag
     * @param element
     * @return String
     */
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}

package dk.si.producer.resource;

import dk.si.producer.file.FileReader;
import dk.si.producer.file.FileReaderXML;
import dk.si.producer.model.Mail;
import dk.si.producer.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("kafka")
public class MemberResource {
    @Autowired
    private KafkaTemplate<String, Mail> kafkaTemplate; // key = TOPIC value = Mail object
    private static final String TOPIC = "messageTopic";

    @GetMapping("/publish")
    public String postMessagesToClients() throws IOException {

        for (Member member : new FileReaderXML().getMembersFromXMLFile()) {
            String message = new FileReader().readContentFromTextFile();
            Mail mail = new Mail(member, message);
            mail.setContent(mail.getContent().replace("XX", mail.getSalutation()));
            mail.setContent(mail.getContent().replace("NN", member.getName()));
            kafkaTemplate.send(TOPIC, member.getName(), mail);
            ;
        }
        return "Published successfully!";
    }
}

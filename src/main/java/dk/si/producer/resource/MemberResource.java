package dk.si.producer.resource;

import dk.si.producer.file.FileReader;
import dk.si.producer.file.FileReaderXML;
import dk.si.producer.model.Mail;
import dk.si.producer.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("kafka")
public class MemberResource {
    @Autowired
    private KafkaTemplate<String, Mail> kafkaTemplate; // key = TOPIC value = message
    private static final String TOPIC = "messageTopic";

    @GetMapping("/publish")
    public String postMessagesToClients() throws IOException {

        for (Member member : new FileReaderXML().getMembersFromXMLFile()){
            String message = new FileReader().readContentFromTextFile();
            Mail mail = new Mail(member, message);
            System.out.println(mail);
            mail.getContent().replace("XX", "TEST");
            //mail.changeText("XX", mail.getSalutation());
            //mail.changeText("NN", mail.getMember().getName());
            kafkaTemplate.send(TOPIC, mail);
        }
        return "Published successfully!";
    }

}

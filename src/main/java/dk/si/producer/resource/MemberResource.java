package dk.si.producer.resource;

import dk.si.producer.model.Mail;
import dk.si.producer.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class MemberResource {
    @Autowired
    private KafkaTemplate<String, Mail> kafkaTemplate; // key = TOPIC value = message
    private static final String TOPIC = "messageTopic";


    @GetMapping("/publish/{name}")
    public String postMessagesToClients(@PathVariable("name") String name) {

        kafkaTemplate.send(TOPIC, new Mail(new Member(name, "email"), "Hallo"));
        return "Published successfully!";
    }
}

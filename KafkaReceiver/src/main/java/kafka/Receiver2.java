package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver2 {

    @KafkaListener(topics = "demo", groupId = "gid")
    public void onMessage(String value) {
        System.out.println("Receiver2 received message: " + value);
    }
}

package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver3 {

    @KafkaListener(topics = "mynewtopic", groupId = "gid3")
    public void onMessage(String value) {
        System.out.println("Receiver3 received message: " + value);
    }
}

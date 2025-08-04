package kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver2 {
//
//    @KafkaListener(topics = "demo", groupId = "gid2")
//    public void onMessage(String value) {
//        System.out.println("Receiver2 received message: " + value);
//    }
    @KafkaListener(topics = "demo",groupId = "gid4")
    public void onMessage(String value) {
        System.out.println("Receiver2 got: " + value);
    }
}

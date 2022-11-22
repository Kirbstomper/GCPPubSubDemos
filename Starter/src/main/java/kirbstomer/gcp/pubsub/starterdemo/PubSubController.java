package kirbstomer.gcp.pubsub.starterdemo;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PubSubController {

    @Autowired
    MessagePublisher publisher;

    @PostMapping("/api/publish")
    public void publishMessage(@RequestBody MessagePojo msg) {
        log.info("Message received over http! " + msg.message());
        publisher.publishMessage(msg.message());
    }

    @GetMapping("/api/read")
    public List<String> readMessages() {
        log.info("reading messages from testSubscription");
        return publisher.readMessages()
                .stream()
                .map(msg -> msg.getPayload())
                .toList();
    }

    record MessagePojo(String message) {
    };

}

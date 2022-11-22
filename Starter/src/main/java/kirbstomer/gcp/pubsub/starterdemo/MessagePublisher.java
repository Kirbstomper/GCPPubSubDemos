package kirbstomer.gcp.pubsub.starterdemo;

import java.util.List;

import org.checkerframework.checker.units.qual.m;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.cloud.spring.pubsub.support.converter.ConvertedAcknowledgeablePubsubMessage;
import com.google.pubsub.v1.PubsubMessage;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessagePublisher {

    @Autowired
    private com.google.cloud.spring.pubsub.core.PubSubTemplate template;

    /***
     * Publishes a message to the test_topic, test subscription
     * 
     * @param msg
     */
    public void publishMessage(String msg) {

        log.info("Sending message to test topic: " + msg);
        template.publish("testTopic", msg);
    }

    /***
     * Reads messages from the test-topic subscription and prints to console
     */
    public List<ConvertedAcknowledgeablePubsubMessage<String>> readMessages(){
        List<ConvertedAcknowledgeablePubsubMessage<String>> list = template.pullAndConvert("testSubscription", null, null, String.class);
        
        list.forEach(msg ->{
            log.info("Time: " + msg.getPubsubMessage().getPublishTime() 
            + " Payload:" + msg.getPayload()) ;
        });
        return list;
        
    }

}

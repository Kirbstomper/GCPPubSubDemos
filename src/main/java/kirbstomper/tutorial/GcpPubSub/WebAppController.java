package kirbstomper.tutorial.GcpPubSub;

import org.springframework.beans.factory.annotation.Autowired;

import kirbstomper.tutorial.GcpPubSub.PubSubApplication.PubsubOutboundGateway;

public class WebAppController {
    
    @Autowired
    private PubsubOutboundGateway messagingGateway;
    
}

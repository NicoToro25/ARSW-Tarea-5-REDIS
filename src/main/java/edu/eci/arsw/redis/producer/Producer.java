package edu.eci.arsw.redis.producer;

import edu.eci.arsw.redis.connection.PSRedisListenerContainer;
import edu.eci.arsw.redis.connection.PSRedisTemplate;
import edu.eci.arsw.redis.receiver.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class Producer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Inject
    private ApplicationContext appContext;

    @Inject
    PSRedisTemplate template;

    @Inject
    Receiver receiver;

    @Inject
    PSRedisListenerContainer container;

    public void run(String... args) throws Exception {

        for (int i = 0; i < 7; i++){
            Receiver receiver = appContext.getBean(Receiver.class);
            container.addMessageListener(receiver, new PatternTopic("PSChannel"));
        }

        Integer i = 0;
        Thread.sleep(500);

        while (i < 6){
            i = i + 1;
            LOGGER.info("Sending message..." + i);
            template.convertAndSend("PSChannel", "Hello from Redis! Message " + i);
            Thread.sleep(500);
        }

        System.exit(0);
    }

}

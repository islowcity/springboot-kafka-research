package slowcity.com.kafka.consumer;

import java.util.Optional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import slowcity.com.kafka.topic.Topics;


@Component
public class KafkaReceiver {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@KafkaListener(topics = {Topics.topic1})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }

    }
}
 
package slowcity.com.kafka.provider;

import java.util.Date;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import slowcity.com.kafka.beans.Message;
import slowcity.com.kafka.topic.Topics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
@Slf4j
public class KafkaSender {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send(Topics.topic1, gson.toJson(message));
    }
}

 
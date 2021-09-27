package info.bowkett.lineage.cdc.listener;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {
  @KafkaListener(topics = "lineage-server.lineage.order")
  public void consume(String message) {
//    Gson gson = new Gson();
//    gson.fromJson(message, type);
    log.warn(String.format("#### -> Consumed message -> *[%s]*", message));
  }
}
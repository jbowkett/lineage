package info.bowkett.lineage.cdc.listener;

import com.google.gson.Gson;
import info.bowkett.lineage.model.RecordDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@Slf4j
public class Consumer {

  public static final Gson GSON = new Gson();

  @KafkaListener(topics = "lineage-server.lineage.order")
  public RecordDescriptor consume(String message) {
    final var s = this.parseLineage(message);

    final var recordDescriptor = GSON.fromJson(s, RecordDescriptor.class);
    log.info("[{}]", recordDescriptor);
    return recordDescriptor;
  }

  public String parseLineage(String msg) {
    final var lineageStartStanza = msg.indexOf("\"lineage\":");
    final var lineageStart = msg.indexOf('{', lineageStartStanza);
    final var stack = new LinkedList<Character>();
    final var buffer = new StringBuilder();
    final var lineageStanza = msg.substring(lineageStart);
    final var escapedLineageStanza = lineageStanza.replaceAll("\\\\\"", "\"");

    lineageLoop:
    for (Character character : escapedLineageStanza.toCharArray()) {
      switch(character){
        case '{': stack.push(character); break;
        case '}': stack.pop(); break;
      }
      buffer.append(character);
      if(stack.isEmpty()){
        break lineageLoop;
      }
    }
    return  buffer.toString();
  }
}
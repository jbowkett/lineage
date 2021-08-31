package info.bowkett.lineage.order.creation;

import com.google.gson.Gson;
import info.bowkett.lineage.model.RecordDescriptor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
@Slf4j
public class RecordConverter implements AttributeConverter<RecordDescriptor, String> {

  static{
    log.info("Also loaded");
  }

  private final Gson gson = new Gson();

  @Override
  public String convertToDatabaseColumn(RecordDescriptor recordDescriptor) {
    return gson.toJson(recordDescriptor);
  }

  @Override
  public RecordDescriptor convertToEntityAttribute(String recordDescriptorJson) {
    return gson.fromJson(recordDescriptorJson, RecordDescriptor.class);
  }
}

package info.bowkett.lineage.cdc.listener;

import info.bowkett.lineage.model.RecordDescriptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class AggregatedDescriptorPersistence implements DescriptorPersistence{
  private final List<DescriptorPersistence> persistenceDelegates;

  @Override
  public void persist(RecordDescriptor descriptor) {
    persistenceDelegates.forEach(descriptorPersistence -> descriptorPersistence.persist(descriptor));
  }
}

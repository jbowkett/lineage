package info.bowkett.lineage.cdc.listener.neo;

import info.bowkett.lineage.cdc.listener.DescriptorPersistence;
import info.bowkett.lineage.model.RecordDescriptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class Persistence implements DescriptorPersistence {
  private final Repository repository;
  @Override
  public void persist(RecordDescriptor descriptor) {
    log.info("about to save...");
    this.repository.save(NeoRecordDescriptor.from(descriptor));
    log.info("saved.");
  }
}

package info.bowkett.lineage.cdc.listener.postgres;

import info.bowkett.lineage.cdc.listener.DescriptorPersistence;
import info.bowkett.lineage.model.RecordDescriptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class PostgresPersistence implements DescriptorPersistence {
  private final PostgresRepository repository;

  public void persist(RecordDescriptor recordDescriptor) {
    final List<PostgresRecordDescriptor> convertedRecords = PostgresRecordDescriptor.from(recordDescriptor);
    convertedRecords.forEach(prd -> {
      final var existingRecord = this.repository.
          findByParentRowIdAndParentTableNameAndLineageChildRowIdAndLineageChildTableName(
              prd.getParentRowId(), prd.getParentTableName(),
              prd.getLineageChildRowId(), prd.getLineageChildTableName()
          );
      if (existingRecord.isEmpty()) {
        repository.save(prd);
      }
    });
  }
}

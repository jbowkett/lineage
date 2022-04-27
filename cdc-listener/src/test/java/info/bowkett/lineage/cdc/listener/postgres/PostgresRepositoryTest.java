package info.bowkett.lineage.cdc.listener.postgres;

import info.bowkett.lineage.model.RecordDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class PostgresRepositoryTest {

  @Autowired
  private PostgresRepository repository;


  @Test
  void peristMultipleTest(){
    assertNotNull(repository);
    assertDoesNotThrow(() -> {
      final List<PostgresRecordDescriptor> records = PostgresRecordDescriptor.from(new RecordDescriptor("12345", "a_table"));
      final var postgresRecordDescriptor = records.get(0);
      repository.save(postgresRecordDescriptor);
    });
    assertEquals(1, repository.count());
  }

  @Test
  void persistAndRetrieveTest(){
    final List<PostgresRecordDescriptor> records = PostgresRecordDescriptor.from(new RecordDescriptor("a_table"));
    final var postgresRecordDescriptor = records.get(0);
    repository.save(postgresRecordDescriptor);
    final String rowId = postgresRecordDescriptor.getDescriptorRowId();
    log.info("rowid = "+rowId);
    final Optional<PostgresRecordDescriptor> found = repository.findById(rowId);
    assertNotNull(found.get());
  }

  @AfterEach
  void clearRepository(){
    this.repository.deleteAll();
  }
}

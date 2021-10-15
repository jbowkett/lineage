package info.bowkett.lineage.cdc.listener;

import info.bowkett.lineage.cdc.listener.neo.NeoRecordDescriptor;
import info.bowkett.lineage.cdc.listener.neo.Persistence;
import info.bowkett.lineage.cdc.listener.neo.Repository;
import info.bowkett.lineage.model.RecordDescriptor;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DescriptorPersistenceTest {


  @Test
  void ensureItCallsTheRepositoryWithAConvertedRecordOnSave(){
    final var repository = mock(Repository.class);
    final var persistence = new Persistence(repository);
    final var recordDescriptor = mock(RecordDescriptor.class);
    persistence.persist(recordDescriptor);
    verify(repository,once()).save(any(NeoRecordDescriptor.class));
  }

  private VerificationMode once() {
    return times(1);
  }
}

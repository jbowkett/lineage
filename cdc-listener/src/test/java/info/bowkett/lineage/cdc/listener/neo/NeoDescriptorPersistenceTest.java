package info.bowkett.lineage.cdc.listener.neo;

import info.bowkett.lineage.model.RecordDescriptor;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NeoDescriptorPersistenceTest {


  @Test
  void ensureItCallsTheRepositoryWithAConvertedRecordOnSave(){
    final var repository = mock(NeoRepository.class);
    final var persistence = new NeoPersistence(repository);
    final var recordDescriptor = mock(RecordDescriptor.class);
    when(recordDescriptor.getParents()).thenReturn(new RecordDescriptor[0]);
    persistence.persist(recordDescriptor);
    verify(repository,once()).save(any(NeoRecordDescriptor.class));
  }

  private VerificationMode once() {
    return times(1);
  }
}

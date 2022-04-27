package info.bowkett.lineage.cdc.listener;

import info.bowkett.lineage.model.RecordDescriptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AggregatedDescriptorPersistenceTest {

  AggregatedDescriptorPersistence aggregatedDescriptorPersistence;

  @Mock
  DescriptorPersistence descriptorPersistenceMock_one;

  @Mock
  DescriptorPersistence descriptorPersistenceMock_two;

  @Test
  void ensureItCallsPersistOnAllChildPersistenceInstances(){
    final List<DescriptorPersistence> persistenceMocks = List.of(this.descriptorPersistenceMock_one, descriptorPersistenceMock_two);

    aggregatedDescriptorPersistence = new AggregatedDescriptorPersistence(persistenceMocks);
    final var  recordDescriptor = mock(RecordDescriptor.class);
    aggregatedDescriptorPersistence.persist(recordDescriptor);

    verify(descriptorPersistenceMock_one, once()).persist(recordDescriptor);
    verify(descriptorPersistenceMock_two, once()).persist(recordDescriptor);
  }

  private VerificationMode once() {
    return times(1);
  }

}

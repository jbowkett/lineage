package info.bowkett.lineage.cdc.listener.postgres;

import info.bowkett.lineage.model.RecordDescriptor;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostgresPersistenceTest {

  @Mock
  PostgresRepository mockedRepository;

  @Test
  void ensureItCallsPersistWithAConvertedDescriptor(){
    final PostgresPersistence postgresPersistence = new PostgresPersistence(mockedRepository);
    final RecordDescriptor recordDescriptor = new RecordDescriptor("test");
    postgresPersistence.persist(recordDescriptor);
    verify(mockedRepository, once()).save(any(PostgresRecordDescriptor.class));
  }

  private VerificationMode once() {
    return times(1);
  }
}

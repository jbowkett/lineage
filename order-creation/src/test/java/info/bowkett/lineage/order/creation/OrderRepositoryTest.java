package info.bowkett.lineage.order.creation;

import info.bowkett.lineage.model.RecordDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class OrderRepositoryTest {

  @Autowired
  private OrderRepository repository;

  @Test
  void peristMultipleTest(){
    assertDoesNotThrow(() -> {
      final Order s = new Order("ticker", new RecordDescriptor("input_table"), 1, 1.2, new Date(), 1,2);
      final Order save = repository.save(s);
      final Order second = new Order( "ticker", new RecordDescriptor("input_table"), 1, 1.2, new Date(), 1,2);
      repository.save(second);
    });
  }

  @Test
  void persistAndRetrieveTest(){
    final Order s = new Order("ticker", new RecordDescriptor("another_input_table"), 1, 1.2, new Date(), 1,2);
    final Order saved = repository.save(s);
    final Optional<Order> found = repository.findById(s.getRowId());
    assertNotNull(found.get());
  }

}

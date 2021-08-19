package info.bowkett.lineage.order.creation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class OrderCreatorTest {

  @Mock
  private OrderRepository orderRepository;

  @InjectMocks
  private OrderCreator orderCreator;

  @RepeatedTest(25)
  void ensureItGeneratesUniqueOrders(){
    final Order firstOrder = this.orderCreator.generateOrder();
    log.info("firstOrder : {}", firstOrder);
    final Order secondOrder = this.orderCreator.generateOrder();
    log.info("secondOrder : {}", secondOrder);
    final boolean equals = firstOrder.equals(secondOrder);
    assertFalse(equals, "orders should be unique");
  }

}

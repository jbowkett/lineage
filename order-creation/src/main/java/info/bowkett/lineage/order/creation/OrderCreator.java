package info.bowkett.lineage.order.creation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.*;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreator {
  private static Logger log = getLogger(OrderCreator.class);

  private final OrderRepository repo;

  @Scheduled(fixedRate = 5000)
  public void PersistOrder(){
    log.debug("Going to create an order"+repo);
    repo.save(new Order("Order created at :"+System.currentTimeMillis()));
  }
}

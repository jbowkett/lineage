package info.bowkett.lineage.order.creation;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"orderDateUtc"})
@ToString
@Slf4j
public class Order {


  static enum Direction{BUY, SELL}

  private String ticker;
  private Integer quantity;
  private Double price;
  private Date orderDateUtc;
  private Direction direction;
  private Integer buyerId;
  private Integer sellerId;

  public Order(String ticker, Integer quantity, Double price, Date orderDateUtc, Direction direction, Integer buyerId, Integer sellerId) {
    this.ticker = ticker;
    this.quantity = quantity;
    this.price = price;
    this.orderDateUtc = orderDateUtc;
    this.direction = direction;
    this.buyerId = buyerId;
    this.sellerId = sellerId;
  }

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long rowId;

}

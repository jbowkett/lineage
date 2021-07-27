package info.bowkett.lineage.order.creation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "order")
public class Order {

  private String description;

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long rowId;

  public Order(String description) {
    this.description = description;
  }
}

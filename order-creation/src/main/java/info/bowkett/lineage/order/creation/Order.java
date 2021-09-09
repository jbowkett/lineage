package info.bowkett.lineage.order.creation;

import com.google.gson.Gson;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.Date;
import info.bowkett.lineage.model.RecordDescriptor;
import org.springframework.data.domain.Persistable;

import static lombok.AccessLevel.NONE;


@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"orderDateUtc"})
@ToString
@Slf4j
public class Order implements Persistable {

  @Id
  @Setter(NONE)
  private String rowId;

  private String ticker;
  @Getter(NONE)
  @Setter(NONE)
  private String lineage;
  private Integer quantity;
  private Double price;
  private Date orderDateUtc;
  private Integer buyerId;
  private Integer sellerId;

  private static final Gson gson = new Gson();

  public Order(String ticker, RecordDescriptor lineage, Integer quantity, Double price, Date orderDateUtc, Integer buyerId, Integer sellerId) {
    this.ticker = ticker;
    setLineage(lineage);
    this.quantity = quantity;
    this.price = price;
    this.orderDateUtc = orderDateUtc;
    this.buyerId = buyerId;
    this.sellerId = sellerId;
    this.rowId = lineage.getRowId();
  }


  public void setLineage(RecordDescriptor lineage){
    this.lineage = gson.toJson(lineage);
  }

  public RecordDescriptor getLineage(){
    return gson.fromJson(lineage, RecordDescriptor.class);
  }

  @Override
  public String getId() {
    return rowId;
  }

  @Override
  public boolean isNew() {
    return true;
  }
}

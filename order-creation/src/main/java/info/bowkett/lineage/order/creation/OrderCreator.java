package info.bowkett.lineage.order.creation;

import info.bowkett.lineage.model.RecordDescriptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;
import java.util.Date;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreator {
  private final OrderRepository repo;
  private final Random random = new Random();

  @Scheduled(fixedRate = 1000)
  public void PersistOrder() {
    repo.save(generateOrder());
    log.debug("Order created.");
  }

  private static enum Trade {
    FACEBOOK("NASDAQ", "FB", 355.45),
    APPLE("NASDAQ", "AAPL", 146.36),
    AMAZON("NASDAQ", "AMZN", 3201.22),
    NETFLIX("NASDAQ", "NFLX", 521.87),
    GOOGLE("NASDAQ", "GOOGL", 2708.98),
    TRIFORK("CPH", "TRIFOR", 181.20);

    private final String exchange;
    private final String ticker;
    private final double mid;

    Trade(String exchange, String ticker, double mid) {
      this.exchange = exchange;
      this.ticker = ticker;
      this.mid = mid;
    }
    private String getBadge(){
      return this.exchange+":"+this.ticker;
    }
  }


  private enum Participant {

    CreditSuisse(1, "Credit Suisse"),
    JamesBowkett(2, "James Bowkett"),
    BernieMadoff(3, "Bernie Madoff"),
    Citigroup(4, "Citigroup, Inc"),
    Ltcm(5, "Long Term Capital Management"),
    KnightCapital(6, "Knight Capital");

    private final int id;
    private final String name;

    Participant(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  protected Order generateOrder() {
    var participants = asList(Participant.values());
    shuffle(participants);
    var buyer = participants.get(0);
    var seller = participants.get(1);

    final Trade[] values = Trade.values();
    var trade = values[random.nextInt(values.length)];

    var record = new RecordDescriptor("order");

    return new Order(trade.getBadge(), record, Math.abs(random.nextInt(1_000)), trade.mid, new Date(), buyer.id, seller.id);
  }
}

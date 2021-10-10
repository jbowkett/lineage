package info.bowkett.lineage.cdc.listener.neo;
import info.bowkett.lineage.model.RecordDescriptor;
import lombok.AllArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@AllArgsConstructor
@NodeEntity(label = "RecordDescriptor")
public class NeoRecordDescriptor {

  @Id
  String id;

  private final String rowId;

  @Relationship(type="PARENT_OF", direction=Relationship.INCOMING)
  private final NeoRecordDescriptor[] parents;

}

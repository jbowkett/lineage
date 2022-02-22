package info.bowkett.lineage.cdc.listener.neo;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoRepository extends Neo4jRepository<NeoRecordDescriptor, String> {
}

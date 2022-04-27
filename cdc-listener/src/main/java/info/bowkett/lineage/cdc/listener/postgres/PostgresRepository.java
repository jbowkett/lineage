package info.bowkett.lineage.cdc.listener.postgres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresRepository extends CrudRepository<PostgresRecordDescriptor, String> {
}

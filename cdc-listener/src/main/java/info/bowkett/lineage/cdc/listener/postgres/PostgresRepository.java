package info.bowkett.lineage.cdc.listener.postgres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostgresRepository extends CrudRepository<PostgresRecordDescriptor, String> {

  List<PostgresRecordDescriptor> findByParentRowIdAndParentTableNameAndLineageChildRowIdAndLineageChildTableName(String parentRowId, String parentTableName, String lineageChildRowId, String lineageChildTableName);

}

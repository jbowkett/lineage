package info.bowkett.lineage.cdc.listener;

import info.bowkett.lineage.model.RecordDescriptor;

public interface DescriptorPersistence {
  public void persist(RecordDescriptor descriptor);
}

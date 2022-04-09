package dao.persistable;

import dao.Identifiable;
import dao.Repository;

public interface PersistableRepo<K, V extends Identifiable<K>> extends Repository<K, V>, Persistable {
}

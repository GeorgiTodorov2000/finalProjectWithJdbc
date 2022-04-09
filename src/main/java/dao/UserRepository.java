package dao;

import dao.persistable.PersistableRepo;
import model.User;

/**
 * Public interface for managing lifecycle of User objects
 */
public interface UserRepository extends PersistableRepo<Long, User> {
    User findByIdInt(int id);
    User updateRole(User user);
}

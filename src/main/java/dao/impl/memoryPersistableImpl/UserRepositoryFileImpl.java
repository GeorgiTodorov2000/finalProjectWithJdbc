//package dao.impl;
//
//import dao.UserRepository;
//import exception.NonexistingEntityException;
//import model.User;
//
//public class UserRepositoryFileImpl extends PersistableRepositoryFileImpl<Long, User>
//        implements UserRepository {
//
//    public UserRepositoryFileImpl(dao.persistable.LongIdGenerator idGenerator, String dbFileName) { super(idGenerator, dbFileName); }
//
//
//    @Override
//    public User deleteById(Long id) throws NonexistingEntityException {
//        return null;
//    }
//
//    public User findById(Long id) {
//        return null;
//    }
//
//}

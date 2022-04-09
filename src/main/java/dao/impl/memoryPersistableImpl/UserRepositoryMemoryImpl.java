//package dao.impl;
//
//import dao.persistable.LongIdGenerator;
//import dao.UserRepository;
//import exception.*;
//import model.User;
//
//import java.util.*;
//
//public class UserRepositoryMemoryImpl implements UserRepository {
//    private Long userId = 0L;
//    private Map<Long, User> users = new HashMap<Long, User>(); // O(1) find by ID
//
//    @Override
//    public Collection<User> findAll() {
//        return users.values();
//
//    }
//
//    //TODO
//    @Override
//    public List<User> findAllSorted(Comparator<User> comparator) {
//        return null;
//    }
//
//
//    @Override
//    public User findById(Long id) {
//        return users.get(id);
//    }
//
//    @Override
//    public User create(User user) {
//        users.put(user.getId(), user);
//        return user;
//    }
//
//    @Override
//    public void addAll(Collection<User> entities) {
//
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public User update(User user) throws NonexistingEntityException {
//        User old = findById(user.getId());
//        if(old == null) {
//            throw new NonexistingEntityException("User with ID='" + user.getId() + "' does not exist.");
//        }
//        users.put(user.getId(), user);
//        return user;
//    }
//
//    @Override
//    public User deleteById(Long id) throws NonexistingEntityException {
//        User old = users.remove(id);
//        if(old == null) {
//            throw new NonexistingEntityException("User with ID='" + id + "' does not exist.");
//        }
//        return old;
//    }
//
//    @Override
//    public long count() {
//        return users.size();
//    }
//
//    @Override
//    public void load() {
//
//    }
//
//    @Override
//    public void save() {
//
//    }
//}

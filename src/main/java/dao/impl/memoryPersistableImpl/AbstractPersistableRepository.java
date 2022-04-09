//package dao.impl;
//
//import dao.Identifiable;
//import dao.persistable.PersistableRepo;
//import exception.NonexistingEntityException;
//
//import java.util.*;
//
//public abstract class AbstractPersistableRepository<K,V extends Identifiable<K>>
//        implements PersistableRepo<K,V> {
//    private Map<Long, V> users = new HashMap<Long, V>(); // O(1) find by ID
//    private dao.persistable.LongIdGenerator idGenerator;
//
//    public AbstractPersistableRepository(dao.persistable.LongIdGenerator idGenerator) {
//        this.idGenerator = idGenerator;
//    }
//
//    @Override
//    public Collection<V> findAll() {
//        return users.values();
//    }
//
//    @Override
//    public List<V> findAllSorted(Comparator<V> comparator) { //O(N* log N) => O(1)
//        var sorted = new ArrayList<>(users.values());
//        sorted.sort(comparator);
//        return sorted;
//    }
//
//    public V findById(K id) {
//        return users.get(id);
//    }
//
////    @Override
////    public V findById(K id, Class<V> cls) {
////        try {
////            var field = cls.getDeclaredField("id");
////            field.setAccessible(true);
////            System.out.println(">>> private ID = " + field.get((V) books.get(id)));
////            var ctor = cls.getConstructor();
////            V obj = ctor.newInstance();
////            if (cls.isInstance(obj)) {
////                System.out.println(">>>> Is instance of " + cls.getSimpleName());
////            }
////        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
////            e.printStackTrace();
////        } catch (NoSuchFieldException e) {
////            e.printStackTrace();
////        }
////        return books.get(id);
////    }
//
//    @Override
//    public V create(V entity) {
//        entity.setId(idGenerator.getNextId());
//        users.put(entity.getId(), entity);
//        return entity;
//    }
//
//
//    public void addAll(Collection<V> entities) {
//        for(var entity: entities) {
//            users.put(entity.getId(), entity);
//        }
//    }
//
//
//    public void clear() {
//        users.clear();
//    }
//
//    @Override
//    public V update(V entity) throws NonexistingEntityException {
//        V old = findById(entity.getId());
//        if(old == null) {
//            throw new NonexistingEntityException("Book with ID='" + entity.getId() + "' does not exist.");
//        }
//        users.put(entity.getId(), entity);
//        return entity;
//    }
//
//
//    public V deleteById(K id) throws NonexistingEntityException {
//        V old = users.remove(id);
//        if(old == null) {
//            throw new NonexistingEntityException("Book with ID='" + id + "' does not exist.");
//        }
//        return old;
//    }
//
//    @Override
//    public long count() {
//        return users.size();
//    }
//
//    public dao.persistable.LongIdGenerator getIdGenerator() {
//        return idGenerator;
//    }
//
//    public void setIdGenerator(dao.persistable.LongIdGenerator idGenerator) {
//        this.idGenerator = idGenerator;
//    }
//}
//
//
//
//
//
//
//
//
//
//

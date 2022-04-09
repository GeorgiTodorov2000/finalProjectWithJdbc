//package dao.impl;
//
//import dao.Identifiable;
//import exception.NonexistingEntityException;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class PersistableRepositoryFileImpl<K, V extends Identifiable<K>> extends AbstractPersistableRepository<K, V> {
//    private String dbFileName;
//
//    public PersistableRepositoryFileImpl(dao.persistable.LongIdGenerator idGenerator, String dbFileName) {
//        super(idGenerator);
//        this.dbFileName = dbFileName;
//    }
//
//    @Override
//    public void load() {
//        try (var in = new ObjectInputStream(
//                new BufferedInputStream(
//                        new FileInputStream(dbFileName)))) {
//            clear();
//            getIdGenerator().reset((Long) in.readObject());
//            addAll((Collection<V>)in.readObject());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void save() {
//        try (var out = new ObjectOutputStream(
//                new BufferedOutputStream(
//                        new FileOutputStream(dbFileName)))) {
//            out.writeObject(getIdGenerator().getCurrentId());
//            out.writeObject(new ArrayList(findAll()));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public V deleteById(Long id) throws NonexistingEntityException {
//        return null;
//    }
//
//    @Override
//    public V findById(Long id) throws NonexistingEntityException {
//        return null;
//    }
//}

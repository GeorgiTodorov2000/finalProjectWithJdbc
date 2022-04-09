package service;

import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.User;

import java.util.Collection;

public interface UserService {
    void loadData();
    void saveData();
    Collection<User> getAllUsers();
    User getUserById(Long id) throws NonexistingEntityException;
    User getUserByIdInt(int id) throws NonexistingEntityException;
    void updateUser(User user) throws NonexistingEntityException;
    void updateRole(User user) throws NonexistingEntityException;
    void deleteUser(User user) throws NonexistingEntityException;
    User createUser(User user) throws InvalidEntityDataException;
}

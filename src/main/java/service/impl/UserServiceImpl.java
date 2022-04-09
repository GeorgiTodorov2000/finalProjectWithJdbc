package service.impl;

import dao.UserRepository;
import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.User;
import service.UserService;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void loadData() {
        userRepository.load();
    }

    @Override
    public void saveData() {
        userRepository.save();
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws NonexistingEntityException {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByIdInt(int id) throws NonexistingEntityException {
        return userRepository.findByIdInt(id);
    }


    @Override
    public void updateUser(User user) throws NonexistingEntityException {
        userRepository.update(user);
    }

    @Override
    public void updateRole(User user) throws NonexistingEntityException {
        userRepository.updateRole(user);
    }

    @Override
    public void deleteUser(User user) throws NonexistingEntityException {
        userRepository.deleteById(user.getId());
    }


    @Override
    public User createUser(User User) throws InvalidEntityDataException {

        User newUser = userRepository.create(User);
        userRepository.save();

        return newUser;
    }

    private void userValidator(User user) throws InvalidEntityDataException {
        String regex = "^(.+)@(.+)$";
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$";
        Pattern pattern = Pattern.compile(regex);
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(user.getEmail());
        Matcher passwordMatcher = passwordPattern.matcher(user.getPassword());

        if(user.getFirstName().length() < 2 || user.getFirstName().length() > 15) {
            throw new InvalidEntityDataException("User First Name should be between 2 and 15 chars long");
        } else if(user.getLastName().length() < 2 || user.getLastName().length() > 15) {
            throw new InvalidEntityDataException("User First Name should be between 2 and 15 chars long");
        } else if(!matcher.matches()) {
            throw new InvalidEntityDataException("Email invalid");
        } else if(user.getUsername().length() < 2 || user.getUsername().length() > 15) {
            throw new InvalidEntityDataException("Username must be between 2 and 15 chars long");
        } else if(!passwordMatcher.matches() || user.getPassword().length() < 8 || user.getPassword().length() > 15) {
            throw new InvalidEntityDataException("Password must be between 8 and 15 length, it MUST contain at least one digit, one capital letter, and one sign different than letter or digit");
        }
    }
}

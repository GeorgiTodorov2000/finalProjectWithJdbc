package dao.impl;

import dao.UserRepository;
import exception.EntityPersistenceException;
import exception.NonexistingEntityException;
import lombok.extern.slf4j.Slf4j;
import model.Gender;
import model.Role;
import model.StatusReader;
import model.User;

import java.sql.*;
import java.util.*;

@Slf4j
public class UserRepositoryJdbc implements UserRepository {

    public static final String SELECT_ALL_USERS =
            "select * from `user`;";
    public static final String INSERT_NEW_USER =
            "insert into `user` (`firstName`, `lastName`, `email`, `username`, `password`, `gender`, `role`, `status`, `profileCreated`, `profileUpdated`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_USER =
            "UPDATE `final_project`.`user` SET `firstName` = ?, `lastName` = ?, `email` = ?, `username` = ?, `password` = ? , `profileUpdated` = ? WHERE (`id` = ?);";
    public static final String DELETE_USER =
            "DELETE from `user` WHERE (`id` = ?);";
    public static final String UPDATE_USER_ROLE =
            "UPDATE `final_project`.`user` SET `role` = ? WHERE (`id` = ?);";


    private Connection connection;

    public UserRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }

    @Override
    public Collection<User> findAll() {
        try (var stmt = connection.prepareStatement(SELECT_ALL_USERS)) {
            // 4. Set params and execute SQL query
            var rs = stmt.executeQuery();
            // 5. Transform ResultSet to Book
            return toUsers(rs);
        } catch (SQLException ex) {
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_USERS, ex);
        }
    }

    // utility methods
    public List<User> toUsers(ResultSet rs) throws SQLException {
        List<User> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new User(
                    rs.getLong(1),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password"),
                    Gender.valueOf(rs.getString("gender")),
                    Role.valueOf(rs.getString("role")),
                    StatusReader.valueOf(rs.getString("status"))
            ));
        }
        return results;
    }

    @Override
    public List<User> findAllSorted(Comparator<User> comparator) {
        return null;
    }

    @Override
    public User create(User user) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getGender().name());
            stmt.setString(7, user.getRole().name());
            stmt.setString(8, user.getStatus().name());
            stmt.setTimestamp(9, Timestamp.valueOf(user.getProfileCreated()));
            stmt.setTimestamp(10, Timestamp.valueOf(user.getProfileModified()));
            System.out.println(stmt.toString());
            var affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                    return user;
                } else {
                    throw new EntityPersistenceException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_USERS, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_USERS, ex);
        }
    }

    @Override
    public void addAll(Collection<User> entities) {

    }

    @Override
    public void clear() {

    }

    @Override
    public User updateRole(User user) {
        try (var stmt = connection.prepareStatement(UPDATE_USER_ROLE)) {
            stmt.setString(1, user.getRole().name());
            stmt.setLong(2, user.getId());
            // 5. Execute insert statement
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            // more updates here ...
            connection.commit();
            connection.setAutoCommit(true);

            // 6. Check results and Get generated primary key
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating restaurant failed, no rows affected.");
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + UPDATE_USER, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + UPDATE_USER, ex);
        }
        return user;
    }

    @Override
    public User update(User user) throws NonexistingEntityException {
        try (var stmt = connection.prepareStatement(UPDATE_USER)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setTimestamp(6, Timestamp.valueOf(user.getProfileModified()));
            stmt.setLong(7, user.getId());
            // 5. Execute insert statement
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            // more updates here ...
            connection.commit();
            connection.setAutoCommit(true);

            // 6. Check results and Get generated primary key
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating restaurant failed, no rows affected.");
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + UPDATE_USER, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + UPDATE_USER, ex);
        }
        return user;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) throws NonexistingEntityException {
        try (var stmt = connection.prepareStatement(DELETE_USER)) {
            stmt.setLong(1, id);
            // 5. Execute insert statement
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            // more updates here ...
            connection.commit();
            connection.setAutoCommit(true);

            // 6. Check results and Get generated primary key
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating restaurant failed, no rows affected.");
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + DELETE_USER, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + DELETE_USER, ex);
        }
    }

    @Override
    public User findById(Long id) throws NonexistingEntityException {
        return null;
    }

    @Override
    public User findByIdInt(int id) {
        try (var stmt = connection.prepareStatement(SELECT_ALL_USERS)) {
            // 4. Set params and execute SQL query
            var rs = stmt.executeQuery();
            // 5. Transform ResultSet to Book
            return toUsers(rs).get(id);
        } catch (SQLException ex) {
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_USERS, ex);
        }
    }


}

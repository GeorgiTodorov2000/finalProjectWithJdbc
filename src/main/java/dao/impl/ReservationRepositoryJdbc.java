package dao.impl;

import dao.ReservationRepository;
import exception.EntityPersistenceException;
import model.FoodDelivery;
import model.Reservation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationRepositoryJdbc implements ReservationRepository {
    public static final String SELECT_ALL_RESERVATIONS =
            "select * from `reservations`;";
    public static final String INSERT_NEW_RESERVATION =
            "insert into `reservations` (`restaurantId`, `reservationDate`, `user_id`) values (?, ?, ?);";

    private Connection connection;

    public ReservationRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Reservation reservation) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, reservation.getRestaurantId());
            stmt.setString(2, reservation.getReservationDate());
            stmt.setLong(3, reservation.getUserId());
            System.out.println(stmt.toString());
            var affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setIdReservation(generatedKeys.getLong(1));
                } else {
                    throw new EntityPersistenceException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_RESERVATIONS, ex);
            }
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_RESERVATIONS, ex);
        }
    }
}

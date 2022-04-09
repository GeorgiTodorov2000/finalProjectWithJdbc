package model;

public class Reservation {
    private Long idReservation;
    private Long restaurantId;
    private String reservationDate;
    private Long userId;

    public Reservation() {
    }

    public Reservation(Long restaurantId, String reservationDate, Long userId) {
        this.restaurantId = restaurantId;
        this.reservationDate = reservationDate;
        this.userId = userId;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", restaurantId=" + restaurantId +
                ", reservationDate='" + reservationDate + '\'' +
                ", userId=" + userId +
                '}';
    }
}

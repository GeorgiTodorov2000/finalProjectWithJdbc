package service.impl;

import dao.FoodDeliveryRepository;
import dao.ReservationRepository;
import model.FoodDelivery;
import model.Reservation;
import service.ReservationService;

public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void create(Reservation reservation) {
        reservationRepository.create(reservation);
    }
}

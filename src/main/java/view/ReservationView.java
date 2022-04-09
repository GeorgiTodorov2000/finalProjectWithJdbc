package view;

import model.Recipe;
import model.Reservation;
import model.Restaurant;
import model.User;

import java.sql.SQLOutput;
import java.util.*;

public class ReservationView {
    public Reservation input(Restaurant restaurant, User user) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Write Day");
        int day = scan.nextInt();
        while(day < 1 || day > 31) {
            System.out.println("Invalid day");
            day = scan.nextInt();
        }
        System.out.println("Write Month");
        int month = scan.nextInt();
        while(month < 1 || month > 12) {
            System.out.println("Invalid month");
            month = scan.nextInt();
        }
        System.out.println("Write Year");
        int year = scan.nextInt();
        while(year < 2022 || year > 2025) {
            System.out.println("Invalid year, also can't be more than 3 years from now");
            year = scan.nextInt();
        }
        System.out.println("Write Hour");
        int hour = scan.nextInt();
        while(hour < 0 || hour > 24) {
            System.out.println("Invalid hour");
            hour = scan.nextInt();
        }
        System.out.println("Write Minutes");
        int minutes = scan.nextInt();
        while(minutes < 0 || minutes > 60) {
            System.out.println("Invalid minutes");
            minutes = scan.nextInt();
        }
        System.out.println("Write your phone number");
        int phoneNumber = scan.nextInt();
        // there is no phone number smaller than 6 numbers
        while(phoneNumber < 100000) {
            phoneNumber = scan.nextInt();
        }
        System.out.println("The restaurant " + restaurant.getName() + " will contact you about the reservation: " +
                day + "." + month + "." + year + " on phone number: " + phoneNumber);
        Reservation reservation = new Reservation();
        String date = day + "-" + month + "-" + year + " " + hour + ":" + minutes + ":" + 00;
        reservation.setReservationDate(date);
        reservation.setRestaurantId(restaurant.getId());
        reservation.setUserId(user.getId());
        return reservation;
    }
}

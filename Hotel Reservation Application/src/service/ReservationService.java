package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static final ReservationService instance = new ReservationService();
    private final Map<String, IRoom> rooms = new HashMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return instance;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return rooms.values().stream()
                .filter(room -> reservations.stream()
                        .noneMatch(reservation -> reservation.getRoom().equals(room) &&
                                (checkInDate.before(reservation.getCheckoutDate()) && checkOutDate.after(reservation.getCheckInDate()))))
                .toList();
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.stream()
                .filter(reservation -> reservation.getCustomer().equals(customer))
                .toList();
    }

    public void printAllReservations() {
        reservations.forEach(System.out::println);
    }
}


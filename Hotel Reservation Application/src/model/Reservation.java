package model;

/**
 * @author 20B01
 *
 */
import java.util.Date;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkoutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkoutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkoutDate = checkoutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkoutDate=" + checkoutDate +
                '}';
    }
}
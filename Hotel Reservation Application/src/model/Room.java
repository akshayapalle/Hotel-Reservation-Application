package model;
/**
 * @author 20B01
 *
 */
public class Room implements IRoom {
    private final String roomNumber;
    private final Double price;
    private final RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return price == 0.0;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + (price == 0.0 ? "Free" : price) +
                ", roomType=" + roomType +
                '}';
    }
}

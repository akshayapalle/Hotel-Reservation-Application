package model;

/**
 * @author 20B01
 *
 */
public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public String toString() {
        return "FreeRoom{" + super.toString() + "}";
    }
}

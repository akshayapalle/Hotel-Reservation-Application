package model;
/**
 * @author 20B01
 *
 */
public interface IRoom {
    String getRoomNumber();
    Double getRoomPrice();
    RoomType getRoomType();
    boolean isFree();
}

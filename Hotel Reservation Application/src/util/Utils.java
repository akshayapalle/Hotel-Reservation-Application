/**
 * 
 */
package util;

/**
 * @author 20B01
 *
 */
import api.AdminResource;
import model.Room;
import model.RoomType;

import java.util.Arrays;

public class Utils {

    public static void loadTestData() {
        AdminResource adminResource = AdminResource.getInstance();

        // Adding test rooms
        adminResource.addRoom(Arrays.asList(
                new Room("101", 100.0, RoomType.SINGLE),
                new Room("102", 150.0, RoomType.DOUBLE),
                new Room("103", 0.0, RoomType.SINGLE) // Free room
        ));

        System.out.println("Test data loaded successfully!");
    }
}

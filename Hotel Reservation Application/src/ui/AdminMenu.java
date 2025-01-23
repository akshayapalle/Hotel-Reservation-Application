/**
 * 
 */
package ui;

/**
 * @author 20B01
 *
 */
import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayAdminMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addARoom();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nAdmin Menu");
        System.out.println("-----------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.print("Please select an option: ");
    }

    private static void seeAllCustomers() {
        adminResource.getAllCustomers().forEach(System.out::println);
    }

    private static void seeAllRooms() {
        adminResource.getAllRooms().forEach(System.out::println);
    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addARoom() {
        List<IRoom> rooms = new ArrayList<>();
        boolean addingRooms = true;

        while (addingRooms) {
            System.out.print("Enter room number: ");
            String roomNumber = scanner.nextLine();

            System.out.print("Enter price per night: ");
            double price = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter room type (1 for SINGLE, 2 for DOUBLE): ");
            int roomTypeInput = Integer.parseInt(scanner.nextLine());
            RoomType roomType = roomTypeInput == 1 ? RoomType.SINGLE : RoomType.DOUBLE;

            rooms.add(new Room(roomNumber, price, roomType));

            System.out.print("Would you like to add another room? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                addingRooms = false;
            }
        }

        adminResource.addRoom(rooms);
        System.out.println("Rooms added successfully!");
    }
}


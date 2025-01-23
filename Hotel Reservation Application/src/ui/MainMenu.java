package ui;

import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    findAndReserveRoom();
                    break;
                case "2":
                    viewReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    AdminMenu.displayAdminMenu();
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
        System.out.println("\nWelcome to the Hotel Reservation Application");
        System.out.println("---------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.print("Please select an option: ");
    }

    private static void findAndReserveRoom() {
        try {
            System.out.print("Enter check-in date (MM/dd/yyyy): ");
            Date checkInDate = parseDate(scanner.nextLine());

            System.out.print("Enter check-out date (MM/dd/yyyy): ");
            Date checkOutDate = parseDate(scanner.nextLine());

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for the selected dates.");
            } else {
                System.out.println("Available Rooms:");
                for (IRoom room : availableRooms) {
                    System.out.println(room);
                }

                System.out.print("Would you like to book a room? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter the room number you want to book: ");
                    String roomNumber = scanner.nextLine();

                    IRoom room = hotelResource.getRoom(roomNumber);
                    if (room != null) {
                        Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                        System.out.println("Reservation confirmed: " + reservation);
                    } else {
                        System.out.println("Invalid room number.");
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewReservations() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        try {
            Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
            if (reservations.isEmpty()) {
                System.out.println("No reservations found for the given email.");
            } else {
                reservations.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createAccount() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        try {
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Date parseDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.parse(date);
    }
}


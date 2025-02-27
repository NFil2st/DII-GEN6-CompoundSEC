import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static ArrayList<String> accessLogs = new ArrayList<>();
    private static final int CARD_VALIDITY_SECONDS = 15;
    private static TimeBasedEncryption timeBasedEncryption = new TimeBasedEncryption(CARD_VALIDITY_SECONDS);
    private static TimeBasedEncryption timeBasedEncryptionAdmin = new TimeBasedEncryption(3600);


    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            informationCard info = informationCard.getInstance();
            KeyCard keyCard = null;

            System.out.print("Enter 1 for Admin, 2 for Customer, 0 for Manager: ");
            int role = sc.nextInt();

            if (role == 1) {
                System.out.print("Enter Password for Admin: ");
                String password = sc.next();
                if (info.passwordAdmin.equals(password)) {
                    System.out.println("Welcome to Admin card!");
                    String timestamp = getCurrentTime();
                    logAccess("Admin", timestamp);

                    Set<Integer> allowedRooms = IntStream.of(info.roomAdmin).boxed().collect(Collectors.toSet());
                    Set<Integer> allowedFloors = IntStream.of(info.floorAdmin).boxed().collect(Collectors.toSet());

                    keyCard = new AdminCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
                    timeBasedEncryptionAdmin.createCard();

                    System.out.print("Show AccessLogs(yes/no)");
                    String response = sc.next();
                    if (response.equalsIgnoreCase("yes")) {
                        System.out.println(accessLogs);
                    } else {
                        System.out.println("Ok Let's go");
                    }
                } else {
                    System.err.println("You have no right.");
                }
                if (keyCard != null) {
                    System.out.print("Enter room number: ");
                    int ExRoom = sc.nextInt();
                    System.out.print("Enter floor level: ");
                    int ExFloor = sc.nextInt();
                    if (timeBasedEncryptionAdmin.isCardValid()) {
                        System.out.println("Can access room " + ExRoom + ": " + keyCard.canAccessRoom(ExRoom));
                        System.out.println("Can access floor " + ExFloor + ": " + keyCard.canAccessFloor(ExFloor));
                    } else {
                        System.out.println("Card has expired. Access denied.");
                    }
                } else {
                    System.out.println("No card available. Access denied.");
                }
                continue;
            } else if (role == 2) {
                String timestamp = getCurrentTime();
                logAccess("Customer", timestamp);
                Set<Integer> allowedRooms = IntStream.of(info.roomCustomer).boxed().collect(Collectors.toSet());
                Set<Integer> allowedFloors = IntStream.of(info.floorCustomer).boxed().collect(Collectors.toSet());

                keyCard = new CustomerCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
                timeBasedEncryption.displayCardStatus();
            } else if (role == 0) {
                System.out.print("Enter Password for Manager: ");
                String password = sc.next();
                if (info.passwordManager.equals(password)) {
                    System.out.println("Welcome to Manager card!");

                    Set<Integer> allowedRooms = IntStream.of(info.roomCustomer).boxed().collect(Collectors.toSet());
                    Set<Integer> allowedFloors = IntStream.of(info.floorCustomer).boxed().collect(Collectors.toSet());

                    keyCard = new ManagerCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
                    timeBasedEncryption.createCard();

                    System.out.print("Do you want to change access? (yes/no): ");
                    String response = sc.next();
                    if (response.equalsIgnoreCase("yes")) {
                        System.out.println("Access change.");
                        if (keyCard instanceof ManagerCardDecorator) {
                            System.out.print("Enter new room access: ");
                            int newRooms = sc.nextInt();
                            System.out.print("Enter new floor access: ");
                            int newFloors = sc.nextInt();
                            String timestamp = getCurrentTime();
                            logAccess("Manager", timestamp);

                            info.roomCustomer = new int[]{newRooms};
                            info.floorCustomer = new int[]{newFloors};

                            allowedRooms = IntStream.of(info.roomCustomer).boxed().collect(Collectors.toSet());
                            allowedFloors = IntStream.of(info.floorCustomer).boxed().collect(Collectors.toSet());

                            keyCard = new ManagerCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
                            timeBasedEncryption.displayCardStatus();
                        } else {
                            System.out.println("You are not a Manager. Access change denied.");
                        }
                    }
                } else {
                    System.err.println("You have no right.");
                }
            }

            if (keyCard != null) {
                    System.out.print("Enter room number: ");
                    int ExRoom = sc.nextInt();
                    System.out.print("Enter floor level: ");
                    int ExFloor = sc.nextInt();
                if (timeBasedEncryption.isCardValid()) {
                    System.out.println("Can access room " + ExRoom + ": " + keyCard.canAccessRoom(ExRoom));
                    System.out.println("Can access floor " + ExFloor + ": " + keyCard.canAccessFloor(ExFloor));
                } else {
                    System.out.println("Card has expired. Access denied.");
                }
            } else {
                System.out.println("No card available. Access denied.");
            }

            System.out.print("Enter 0 for Exit : ");
            String Exit = sc.next();
            if (Exit.equals("0")) {
                break;
            }
        }
    }

    private static void logAccess(String role, String timestamp) {
        accessLogs.add(role + " accessed at " + timestamp);
        accessLogs.add("\n----------------------------------------------------------------\n");
    }

    private static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(dtf);
    }
}

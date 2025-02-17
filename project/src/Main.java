import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
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

                Set<Integer> allowedRooms = IntStream.of(info.roomAdmin).boxed().collect(Collectors.toSet());
                Set<Integer> allowedFloors = IntStream.of(info.floorAdmin).boxed().collect(Collectors.toSet());

                keyCard = new AdminCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
            } else {
                System.err.println("You have no right.");
            }
        } else if (role == 2) { 
            Set<Integer> allowedRooms = IntStream.of(info.roomCustomer).boxed().collect(Collectors.toSet());
            Set<Integer> allowedFloors = IntStream.of(info.floorCustomer).boxed().collect(Collectors.toSet());

            keyCard = new CustomerCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
        } else if (role == 0) { 
            System.out.print("Enter Password for Manager: ");
            String password = sc.next();
            if (info.passwordManager.equals(password)) {
                System.out.println("Welcome to Manager card!");

                Set<Integer> allowedRooms = IntStream.of(info.roomAdmin).boxed().collect(Collectors.toSet());
                Set<Integer> allowedFloors = IntStream.of(info.floorAdmin).boxed().collect(Collectors.toSet());

                keyCard = new ManagerCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));

                System.out.print("Do you want to change access? (yes/no): ");
                String response = sc.next();
                if (response.equalsIgnoreCase("yes")) {
                    System.out.println("Access changed.");
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

            System.out.println("Can access room " + ExRoom + ": " + keyCard.canAccessRoom(ExRoom));
            System.out.println("Can access floor " + ExFloor + ": " + keyCard.canAccessFloor(ExFloor));
        } else {
            System.err.println("No card available. Access denied.");
        }
    }
}

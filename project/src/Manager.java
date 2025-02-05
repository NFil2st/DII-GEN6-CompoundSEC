import java.util.Scanner;

public class Manager {

    informationCard info = new informationCard();

        Card card = new Card(0, 0);

        EmployeeCardmanager employeeCard = new EmployeeCardmanager(card);

        int[] allowedRooms = {card.getRoom()};
        int[] allowedFloors = {card.getFlood()};
        
        KeyCard keyCard = employeeCard.getEmployeeKeycard(allowedRooms, allowedFloors);

    public boolean comeRoom(int r){
        for (int room : allowedRooms) {
            if (room == r) {
                return true;
            }
        }
        return false;
            }

    public boolean comeFloor(int f){
        for (int floor : allowedFloors) {
            if (floor == f) {
                return true;
            }
        }
        return false;
            }

    public void changeAccess() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Edit Access to Room: " );
        int r = sc.nextInt();
        System.out.print("Edit Access to Floor: " );
        int f = sc.nextInt();

        card.setRoom(r);
        card.setFlood(f);

        allowedRooms = new int[]{card.getRoom()};
        allowedFloors = new int[]{card.getFlood()}; 
    }
}
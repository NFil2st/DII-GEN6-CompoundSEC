public class Customer {

    informationCard info = new informationCard();

    Card card = new Card(0, 0);

    EmployeeCardadmin employeeCard = new EmployeeCardadmin(card);

    int[] allowedRooms = info.roomCustomer;
    int[] allowedFloors = info.floorCustomer;

    KeyCard keyCard = employeeCard.getEmployeeKeycard(info.roomCustomer, info.floorCustomer);

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
}

public class Admin {

    informationCard info = new informationCard();

        Card card = new Card(0, 0);

        EmployeeCardadmin employeeCard = new EmployeeCardadmin(card);

        int[] allowedRooms = info.roomAdmin;
        int[] allowedFloors = info.floorAdmin;

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
}

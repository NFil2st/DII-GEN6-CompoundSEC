import javax.smartcardio.Card;
import java.util.Set;

interface KeyCard {
    boolean canAccessRoom(int roomNumber);
    boolean canAccessFloor(int floorNumber);
}

class EmployeeKeycard implements KeyCard {
    private Set<Integer> allowedRooms;
    private Set<Integer> allowedFloors;

    public EmployeeKeycard(Set<Integer> rooms, Set<Integer> floors) {
        this.allowedRooms = rooms;
        this.allowedFloors = floors;
        System.out.println("EmployeeKeycard initialized with rooms: " + allowedRooms + " and floors: " + allowedFloors);
    }

    @Override
    public boolean canAccessRoom(int roomNumber) {
        return allowedRooms.contains(roomNumber);
    }

    @Override
    public boolean canAccessFloor(int floorNumber) {
        return allowedFloors.contains(floorNumber);
    }
}

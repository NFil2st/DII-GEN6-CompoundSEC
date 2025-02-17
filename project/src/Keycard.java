import java.util.Set;
import java.util.HashSet;

class Card {
    private int room;
    private int floor;

    public Card(int r, int f) {
        room = r;
        floor = f;
    }

    public int getRoom() {
        return room;
    }

    public int getFloor() {
        return floor;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}

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

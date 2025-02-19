import java.util.Set;

class ManagerCardDecorator extends BaseCardDecorator {
    private static final Set<Integer> managerRooms = Set.of(0);
    private static final Set<Integer> managerFloors = Set.of(0);

    public ManagerCardDecorator(KeyCard card) {
        super(card);
    }

    @Override
    public boolean canAccessRoom(int roomNumber) {
        return super.canAccessRoom(roomNumber) || managerRooms.contains(roomNumber);
    }

    @Override
    public boolean canAccessFloor(int floorNumber) {
        return super.canAccessFloor(floorNumber) || managerFloors.contains(floorNumber);
    }
}



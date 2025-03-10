import java.util.Set;

class AdminCardDecorator extends BaseCardDecorator {
    private static final Set<Integer> adminRooms = Set.of(0);
    private static final Set<Integer> adminFloors = Set.of(0);

    public AdminCardDecorator(KeyCard card) {
        super(card);
    }

    @Override
    public boolean canAccessRoom(int roomNumber) {
        return super.canAccessRoom(roomNumber) || adminRooms.contains(roomNumber);
    }

    @Override
    public boolean canAccessFloor(int floorNumber) {
        return super.canAccessFloor(floorNumber) || adminFloors.contains(floorNumber);
    }
}

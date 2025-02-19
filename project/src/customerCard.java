import java.util.Set;

class CustomerCardDecorator extends BaseCardDecorator {
    private static final Set<Integer> customerRooms = Set.of(0);
    private static final Set<Integer> customerFloors = Set.of(0);

    public CustomerCardDecorator(KeyCard card) {
        super(card);
    }

    @Override
    public boolean canAccessRoom(int roomNumber) {
        return super.canAccessRoom(roomNumber) || customerRooms.contains(roomNumber);
    }

    @Override
    public boolean canAccessFloor(int floorNumber) {
        return super.canAccessFloor(floorNumber) || customerFloors.contains(floorNumber);
    }
}

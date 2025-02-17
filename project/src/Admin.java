import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Admin {
    private KeyCard keyCard;

    public Admin() {
        informationCard info = informationCard.getInstance();
        Set<Integer> allowedRooms = IntStream.of(info.roomAdmin).boxed().collect(Collectors.toSet());
        Set<Integer> allowedFloors = IntStream.of(info.floorAdmin).boxed().collect(Collectors.toSet());

        this.keyCard = new AdminCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
    }

    public boolean comeRoom(int r) {
        return keyCard.canAccessRoom(r);
    }

    public boolean comeFloor(int f) {
        return keyCard.canAccessFloor(f);
    }
}

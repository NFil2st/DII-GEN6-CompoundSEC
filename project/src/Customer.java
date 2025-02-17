import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Customer {
    private KeyCard keyCard;

    public Customer() {
        informationCard info = informationCard.getInstance();
        Set<Integer> allowedRooms = IntStream.of(info.roomCustomer).boxed().collect(Collectors.toSet());
        Set<Integer> allowedFloors = IntStream.of(info.floorCustomer).boxed().collect(Collectors.toSet());

        this.keyCard = new CustomerCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
    }

    public boolean comeRoom(int r) {
        return keyCard.canAccessRoom(r);
    }

    public boolean comeFloor(int f) {
        return keyCard.canAccessFloor(f);
    }
}

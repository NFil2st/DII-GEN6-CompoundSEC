import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Manager {
    private KeyCard keyCard;
    private informationCard info = informationCard.getInstance();

    public Manager() {
        Set<Integer> allowedRooms = IntStream.of(info.roomManager).boxed().collect(Collectors.toSet());
        Set<Integer> allowedFloors = IntStream.of(info.floorManager).boxed().collect(Collectors.toSet());

        keyCard = new AdminCardDecorator(new EmployeeKeycard(allowedRooms, allowedFloors));
    }

    public boolean comeRoom(int r) {
        return keyCard.canAccessRoom(r);
    }

    public boolean comeFloor(int f) {
        return keyCard.canAccessFloor(f);
    }
}

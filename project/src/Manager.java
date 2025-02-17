import java.util.Set;

public class Manager {
    private KeyCard keyCard;

    public Manager() {
        this.keyCard = new ManagerCardDecorator(new EmployeeKeycard(Set.of(), Set.of())); // Manager เข้าทุกที่
    }

    public boolean comeRoom(int r) {
        return keyCard.canAccessRoom(r);
    }

    public boolean comeFloor(int f) {
        return keyCard.canAccessFloor(f);
    }

    public void changeAccess() {
        System.out.println("Manager can modify access rights.");
    }
}

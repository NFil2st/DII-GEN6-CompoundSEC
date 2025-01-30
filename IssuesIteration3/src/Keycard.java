class Card {
    private int room;
    private int flood;

    public Card(int r,int f) {
        room = r;
        flood = f;
    }
    public int getRoom() {
        return room;
    }

    public int getFlood() {
        return flood;
    }
}

interface KeyCard {
    boolean canAccessRoom(int roomNumber);

    boolean canAccessFloor(int floorNumber);
}

abstract class AccessCard {
    protected Card card1;

    protected AccessCard(Card card1) {
        this.card1 = card1;
    }

    abstract public void manufacture();


    class EmployeeKeycard implements KeyCard {
        private int[] allowedRooms = {card1.getRoom()};
        private int[] allowedFloors = {card1.getFlood()};

        @Override
        public boolean canAccessRoom(int roomNumber) {
            for (int room : allowedRooms) {
                if (room == roomNumber) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean canAccessFloor(int floorNumber) {
            for (int floor : allowedFloors) {
                if (floor == floorNumber) {
                    return true;
                }
            }
            return false;
        }
    }
}
class EmployeeCard extends AccessCard {

    public EmployeeCard(Card card1) {
        super(card1);
    }

    @Override
    public void manufacture() {
        System.out.println("Access to Room: " + card1.getRoom());
        System.out.println("Access to Floor: " + card1.getFlood());
    }

    public KeyCard getEmployeeKeycard() {
        return new EmployeeKeycard();
    }
}

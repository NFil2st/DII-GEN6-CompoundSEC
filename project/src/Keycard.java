class Card {
    private int room;
    private int flood;

    public Card(int r, int f) {
        room = r;
        flood = f;
    }

    

    public int getRoom() {
        return room;
    }

    public int getFlood() {
        return flood;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setFlood(int flood) {
        this.flood = flood;
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
        private int[] allowedRooms;
        private int[] allowedFloors;

        public EmployeeKeycard(int[] rooms, int[] floors) {
            this.allowedRooms = rooms;
            this.allowedFloors = floors;
        }

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

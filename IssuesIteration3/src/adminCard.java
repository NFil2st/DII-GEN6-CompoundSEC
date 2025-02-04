class EmployeeCardadmin extends AccessCard {

    public EmployeeCardadmin(Card card1) {
        super(card1);
    }

    @Override
    public void manufacture() {
        
        System.out.println("Access to Room: " + card1.getRoom());
        System.out.println("Access to Floor: " + card1.getFlood());
    }

    public KeyCard getEmployeeKeycard(int[] rooms, int[] floors) {

        return new EmployeeKeycard(rooms, floors);
    }
}
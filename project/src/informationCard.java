public class informationCard {
    private static informationCard instance;

    public static final int[] roomAdmin = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static final int[] floorAdmin = {1, 2, 3, 4, 5, 6};

    public int[] roomCustomer = {1};
    public int[] floorCustomer = {1};

    
    public static final String passwordAdmin = "1234";

    public static final String passwordManager = "12345";
    
    private informationCard() {}

    public static informationCard getInstance() {
        if (instance == null) {
            instance = new informationCard();
        }
        return instance;
    }
}

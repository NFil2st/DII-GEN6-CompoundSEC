abstract class BaseCardDecorator implements KeyCard {
    protected KeyCard decoratedCard;

    public BaseCardDecorator(KeyCard card) {
        this.decoratedCard = card;
    }

    @Override
    public boolean canAccessRoom(int roomNumber) {
        return decoratedCard.canAccessRoom(roomNumber);
    }

    @Override
    public boolean canAccessFloor(int floorNumber) {
        return decoratedCard.canAccessFloor(floorNumber);
    }
}


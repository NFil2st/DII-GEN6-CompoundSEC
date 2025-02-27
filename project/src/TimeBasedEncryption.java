import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeBasedEncryption {
    private LocalDateTime cardCreationTime;
    private int validDurationInSeconds;

    public TimeBasedEncryption(int validDurationInSeconds) {
        this.validDurationInSeconds = validDurationInSeconds;
    }

    public void createCard() {
        this.cardCreationTime = LocalDateTime.now();
    }

    public boolean isCardValid() {
        if (cardCreationTime == null) {
            return false;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        long elapsedTime = ChronoUnit.SECONDS.between(cardCreationTime, currentTime);

        return elapsedTime < validDurationInSeconds;
    }
    public void displayCardStatus() {
        if (isCardValid()) {
            System.out.println("Card is valid and can be used.");
        } else {
            System.out.println("Card has expired.");
        }
    }
}

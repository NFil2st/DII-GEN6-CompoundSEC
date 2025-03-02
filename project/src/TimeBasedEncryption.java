import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeBasedEncryption {
    private LocalDateTime cardCreationTime;
    private int validDurationInSeconds;
    public static int count = 0;

    public TimeBasedEncryption(int validDurationInSeconds) {
        this.validDurationInSeconds = validDurationInSeconds;
    }

    public void createCard() {
        this.cardCreationTime = LocalDateTime.now();
        count++;
    }

    public boolean isCardValid() {
        if (cardCreationTime == null) {
            return false;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        long elapsedTime = ChronoUnit.SECONDS.between(cardCreationTime, currentTime);
        if (elapsedTime > validDurationInSeconds){
            count--;
        }
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

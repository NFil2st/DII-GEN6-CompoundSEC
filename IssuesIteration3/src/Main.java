import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Card exampleCard = new Card(101, 2);

        AccessCard empCard = new EmployeeCard(exampleCard);

        EmployeeCard employeeCard = (EmployeeCard) empCard;
        KeyCard employeeKeycard = employeeCard.getEmployeeKeycard();

        empCard.manufacture();

        System.out.print("Enter room number : ");
        int ExRoom = sc.nextInt();
        System.out.print("Enter floor level : ");
        int ExFloor = sc.nextInt();

        System.out.println("Employee can access room " + ExRoom + " : " + employeeKeycard.canAccessRoom(ExRoom));
        System.out.println("Employee can access floor " + ExFloor +" : " + employeeKeycard.canAccessFloor(ExFloor));

    }
}

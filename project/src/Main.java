import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        informationCard info = new informationCard();
        Object card = null;

        System.out.print("Enter 1 Admin , Enter 2 customer : ");
        int role = sc.nextInt();
        if(role == 1){
            System.out.print("Enter password for admin only : ");
            int password = sc.nextInt();

            if(password == info.passwordAdmin){
                System.out.println("Welcome to Admin card!");
                card = new Admin();
            }else{
                System.err.println("You have no right.");
            }
        }else if(role == 2){
            card = new Customer();
        }

        System.out.print("Enter room number : ");
        int ExRoom = sc.nextInt();
        System.out.print("Enter floor level : ");
        int ExFloor = sc.nextInt();

        if (card instanceof Admin) {
            System.out.println("Admin can access room " + ExRoom + " : " + ((Admin) card).comeRoom(ExRoom));
            System.out.println("Admin can access floor " + ExFloor + " : " + ((Admin) card).comeFloor(ExFloor));
        } 
        else if (card instanceof Customer) {
            System.out.println("Customer can access room " + ExRoom + " : " + ((Customer) card).comeRoom(ExRoom));
            System.out.println("Customer can access floor " + ExFloor + " : " + ((Customer) card).comeFloor(ExFloor));
        }
        else {
        System.err.println("No card available. Access denied.");
    }
}
}

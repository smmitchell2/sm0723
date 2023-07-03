import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class ToolRentalProgram {
    
    public static void main(String[] args) {
        // User input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter tool code: ");
        String toolCode = scanner.nextLine();

        System.out.print("Enter checkout date (MM/dd/yyyy): ");
        String checkoutDateStr = scanner.nextLine();
        LocalDate checkoutDate = LocalDate.parse(checkoutDateStr, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        System.out.print("Enter rental days: ");
        int rentalDays = scanner.nextInt();
 

        System.out.print("Enter discount (e.g., 0.2 for 20%): ");
        double discount = scanner.nextDouble();
 
        RentalAgreement ra = new RentalAgreement(toolCode,checkoutDate,rentalDays,discount);
        
        scanner.close();

        printRentalAgreement(ra);
    }
}


 
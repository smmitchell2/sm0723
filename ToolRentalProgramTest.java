import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ToolRentalProgramTest {

    @Test
    void testTool1RentalProgram() {
        // Test input values
        String testToolCode = "JAKR";
        LocalDate testCheckoutDate = LocalDate.parse("2015-09-03");
        int testRentalDays = 5;
        double testDiscount = 101;

        // Expected values
        // test 1
        double testExpectedFinalCharges = 0;

        // Run the program and should error
        RentalAgreement ra = new RentalAgreement(testToolCode,testCheckoutDate,testRentalDays,testDiscount);

        // Verify the output
        //Assertions.assertEquals(testExpectedFinalCharges, ra.getFinalCharges());
    }

    void testTool2RentalProgram() {
        // Test input values
        String testToolCode = "LADW";
        LocalDate testCheckoutDate = LocalDate.parse("2020-07-02");
        int testRentalDays = 3;
        double testDiscount = 10;

        // Expected values
        // test 1
        double testExpectedFinalCharges = 3.98;

        // Run the program
        RentalAgreement ra = new RentalAgreement(testToolCode,testCheckoutDate,testRentalDays,testDiscount);

        // Verify the output
        Assertions.assertEquals(testExpectedFinalCharges, ra.getFinalCharges());
    }

    void testTool3RentalProgram() {
        // Test input values
        String testToolCode = "CHNS";
        LocalDate testCheckoutDate = LocalDate.parse("2015-07-02"); 
        int testRentalDays = 5;
        double testDiscount = 25;

        // Expected values
        // test 1
        double testExpectedFinalCharges = 5.96

        // Run the program
        RentalAgreement ra = new RentalAgreement(testToolCode,testCheckoutDate,testRentalDays,testDiscount);

        // Verify the output
        Assertions.assertEquals(testExpectedFinalCharges, ra.getFinalCharges());
    }

    void testTool4RentalProgram() {
        // Test input values
        String testToolCode = "JAKD";
        LocalDate testCheckoutDate = LocalDate.parse("2015-09-13"); 
        int testRentalDays = 6;
        double testDiscount = 0;

        // Expected values
        // test 1
        double testExpectedFinalCharges = 5.98;

        // Run the program and should error
        RentalAgreement ra = new RentalAgreement(testToolCode,testCheckoutDate,testRentalDays,testDiscount);

        // Verify the output
        Assertions.assertEquals(testExpectedFinalCharges, ra.getFinalCharges());
    }

    void testTool5RentalProgram() {
        // Test input values
        String testToolCode = "JAKR";
        LocalDate testCheckoutDate = LocalDate.parse("2015-07-02");
        int testRentalDays = 9;
        double testDiscount = 0;

        // Expected values
        // test 1
        double testExpectedFinalCharges = 14.95;

        // Run the program
        RentalAgreement ra = new RentalAgreement(testToolCode,testCheckoutDate,testRentalDays,testDiscount);

        // Verify the output
        Assertions.assertEquals(testExpectedFinalCharges, ra.getFinalCharges());
    }

    void testTool6RentalProgram() {
        // Test input values
        String testToolCode = "JAKR";
        LocalDate testCheckoutDate = LocalDate.parse("2020-07-02"); // Labor Day on September 4th, 2023
        int testRentalDays = 4;
        double testDiscount = 50;

        // Expected values
        // test 1
        double testExpectedFinalCharges = 2.99;

        // Run the program
        RentalAgreement ra = new RentalAgreement(testToolCode,testCheckoutDate,testRentalDays,testDiscount);

        // Verify the output
        Assertions.assertEquals(testExpectedFinalCharges, ra.getFinalCharges());
    }
}



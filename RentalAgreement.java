import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private boolean weekendCharge;
    private boolean holidayCharge;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int rentalDays;
    private int chargeableDays;
    private double dailyRentalRate;
    private double discount;
    private double totalCharges;
    private double discountAmount;
    private double finalCharges;
    private String formattedCheckoutDate;
    private String formattedDueDate;

    public RentalAgreement(String toolCode, LocalDate checkoutDate,int rentalDays,
                           double discount) {
        checkRentalDays(rentalDays);
        checkDiscount(discount);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.formattedCheckoutDate = checkoutDate.format(dateFormatter);
        this.formattedDueDate = dueDate.format(dateFormatter);
        this.toolCode = toolCode;
        this.checkoutDate = adjustForHoliday(checkoutDate);
        this.dueDate = calculateDueDate(checkoutDate,rentalDays);
        this.rentalDays = rentalDays;
        // Set Rate charged,tool type, and brand
        switch(toolCode){
            case "CHNS":
                this.dailyRentalRate = 1.99;
                this.toolType = "Chainsaw";
                this.toolBrand = "Stihl";
                this.weekendCharge = false;
                this.holidayCharge = true;
                break;
            case "LADW":
            this.dailyRentalRate = 1.49;
                toolType = "Ladder";
                toolBrand = "Werner";
                weekendCharge = true;
                holidayCharge = false;
                break;
            case "JAKD":
                this.dailyRentalRate = 2.99;
                toolType = "Jackhammer";
                toolBrand = "DeWalt";
                weekendCharge = false;
                holidayCharge = false;
                break;
            case "JAKR":
                this.dailyRentalRate = 2.99;
                this.toolType = "Jackhammer";
                this.toolBrand = "Ridgid";
                this.weekendCharge = false;
                this.holidayCharge = false;
                break;
            default:
                throw new SecurityException("Tool Code used is not supported.");
        }

        this.chargeableDays = calculateChargeableDays(checkoutDate,this.dueDate,this.weekendCharge,this.holidayCharge);
        this.discount = discount * .01;
        this.totalCharges = this.dailyRentalRate * rentalDays;
        this.discountAmount = getDiscountAmount(this.totalCharges,discount);
        this.finalCharges = getFinalCharges(this.totalCharges,this.discountAmount);
    }

    public static double getDiscountAmount(double totalCharges, double discount) {
        return totalCharges * discount;
    }

    public static double getFinalCharges(double totalCharges, double discountAmount) {
        return totalCharges - discountAmount;
    }

    private static LocalDate adjustForHoliday(LocalDate date) {
        // Independence Day - July 4th
        if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                date = date.minusDays(1); // Observed on Friday before
            } else if (dayOfWeek == DayOfWeek.SUNDAY) {
                date = date.plusDays(1); // Observed on Monday after
            }
        }
        // Labor Day - First Monday in September
        if (date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
            int dayOfMonth = date.getDayOfMonth();
            if (dayOfMonth <= 7) {
                date = date.plusWeeks(1); // Move to the second Monday
            }
        }
        return date;
    }

    private static LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        if (dueDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            dueDate = dueDate.plusDays(2); // Move to Monday if due date falls on Saturday
        } else if (dueDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            dueDate = dueDate.plusDays(1); // Move to Monday if due date falls on Sunday
        }
        return dueDate;
    }

    private static boolean isHoliday(LocalDate date) {
        // Check if date is a holiday (customize according to your specific holiday logic)
        // For example, if Labor Day and Independence Day are holidays:
        return isLaborDay(date) || isIndependenceDay(date);
    }

    private static boolean isLaborDay(LocalDate date) {
        // Labor Day - First Monday in September
        return date.getMonthValue() == 9 && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7;
    }

    private static boolean isIndependenceDay(LocalDate date) {
        // Independence Day - July 4th (observed on the closest weekday if it falls on a weekend)
        if (date.getMonthValue() == 7 && date.getDayOfMonth() == 4) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                return date.minusDays(1).getMonthValue() != 7;
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return date.plusDays(1).getMonthValue() != 7;
            }
            return true;
        }
        return false;
    }

    private static int calculateChargeableDays(LocalDate checkoutDate, LocalDate dueDate, boolean weekendCharge, boolean holidayCharge) {
        int chargeableDays = 0;
        LocalDate currentDate = checkoutDate;
        while (!currentDate.isAfter(dueDate)) {
            boolean isWeekend = (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY);
            boolean isHoliday = isHoliday(currentDate);

            if (!isWeekend || weekendCharge) {
                if (!isHoliday || holidayCharge) {
                    chargeableDays++;
                }
            }

            currentDate = currentDate.plusDays(1);
        }
        return chargeableDays;
    }

    private static void checkRentalDays(int rentalDays){
        if (rentalDays < 1){
            throw new SecurityException("Not valid amount of rental days.");
        }
    }

    private static void checkDiscount(double discount){
        if (discount < 0 || discount > 100){
            throw new SecurityException("Not valid discount amount.");
        }
    }

    public String getToolCode() {
        return toolCode;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public int getChargeableDays() {
        return chargeableDays;
    }

    public double getDailyRentalRate() {
        return dailyRentalRate;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalCharges() {
        return totalCharges;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalCharges() {
        return finalCharges;
    }

    public void printRentalAgreement(RentalAgreement ra){
        // Generate rental agreement
        System.out.println("=== Rental Agreement ===");
        System.out.println("Tool code: " + ra.toolCode);
        System.out.println("Tool type: " + ra.toolType);
        System.out.println("Tool brand: " + ra.toolBrand);
        System.out.println("Checkout date: " + ra.formattedCheckoutDate);
        System.out.println("Due date: " + ra.formattedDueDate);
        System.out.println("Rental days: " + ra.rentalDays);
        System.out.println("Chargeable days: " + ra.chargeableDays);
        System.out.println("Daily rental rate: $" + ra.STANDARD_DAILY_RATE);
        System.out.println("Discount: " + (ra.discount * 100) + "%");
        System.out.println("Total charges: $" + ra.totalCharges);
        System.out.println("Discount amount: $" + ra.discountAmount);
        System.out.println("Final charges: $" + ra.finalCharges);
    }
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("=== Rental Agreement ===\n");
        sb.append("Tool code: ").append(toolCode).append("\n");
        sb.append("Tool type: ").append(toolType).append("\n");
        sb.append("Tool brand: ").append(toolBrand).append("\n");
        sb.append("Checkout date: ").append(checkoutDate.format(dateFormatter)).append("\n");
        sb.append("Due date: ").append(dueDate.format(dateFormatter)).append("\n");
        sb.append("Rental days: ").append(rentalDays).append("\n");
        sb.append("Chargeable days: ").append(chargeableDays).append("\n");
        sb.append("Daily rental rate: $").append(dailyRentalRate).append("\n");
        sb.append("Discount: ").append(discount * 100).append("%\n");
        sb.append("Total charges: $").append(totalCharges).append("\n");
        sb.append("Discount amount: $").append(discountAmount).append("\n");
        sb.append("Final charges: $").append(finalCharges).append("\n");
        return sb.toString();
    }
}
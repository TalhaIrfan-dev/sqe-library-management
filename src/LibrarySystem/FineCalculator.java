package LibrarySystem;
public class FineCalculator {

    public static double calculateFine(int overdueDays) {

        if (overdueDays <= 0) {
            return 0;
        }

        if (overdueDays <= 5) {
            return overdueDays * 10;
        }

        if (overdueDays <= 10) {
            return overdueDays * 20;
        }

        return overdueDays * 30;
    }

    public static String fineTier(int overdueDays)
    {
        if (overdueDays < 0){
            throw new IllegalArgumentException("Overdue days cannot be negative");
        }

        if (overdueDays == 0){
            return "None";
        }

        if (overdueDays <= 7){
            return "Low";
        }

        if (overdueDays <= 14){
            return "Medium";
        }

        if (overdueDays <= 30){
            return "High";
        }

        return "Severe";
    }
}
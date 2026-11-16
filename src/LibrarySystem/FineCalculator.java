package LibrarySystem;

public class FineCalculator {

    private static final int FIRST_FINE_LIMIT = 5;
    private static final int SECOND_FINE_LIMIT = 10;

    private static final int LOW_TIER_LIMIT = 7;
    private static final int MEDIUM_TIER_LIMIT = 14;
    private static final int HIGH_TIER_LIMIT = 30;

    public static double calculateFine(int overdueDays) {

        if (overdueDays <= 0) {
            return 0;
        }

        if (overdueDays <= FIRST_FINE_LIMIT) {
            return overdueDays * 10;
        }

        if (overdueDays <= SECOND_FINE_LIMIT) {
            return overdueDays * 20;
        }

        return overdueDays * 30;
    }

    public static String fineTier(int overdueDays)
    {
        if (overdueDays < 0) {
            throw new IllegalArgumentException("Overdue days cannot be negative");
        }

        if (overdueDays == 0) {
            return "None";
        }

        if (overdueDays <= LOW_TIER_LIMIT) {
            return "Low";
        }

        if (overdueDays <= MEDIUM_TIER_LIMIT) {
            return "Medium";
        }

        if (overdueDays <= HIGH_TIER_LIMIT) {
            return "High";
        }

        return "Severe";
    }
}
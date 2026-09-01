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
}
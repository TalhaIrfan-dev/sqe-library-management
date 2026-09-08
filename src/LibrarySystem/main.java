package LibrarySystem;
import java.util.ArrayList;
import java.util.List;

class main
{
    BookManager bm = new BookManager();
    MemberManager mm = new MemberManager();
   

    public static void main(String[] args)
    {

        System.out.println("TC-10: " + FineCalculator.calculateFine(0));
        System.out.println("TC-11: " + FineCalculator.calculateFine(7));
        System.out.println("TC-12: " + FineCalculator.calculateFine(10));

        try {
            System.out.println(FineCalculator.fineTier(-3));
        } catch (IllegalArgumentException e) {
            System.out.println("Negative value: Exception passed");
        }
        System.out.println(FineCalculator.fineTier(0));
        System.out.println(FineCalculator.fineTier(4));
        System.out.println(FineCalculator.fineTier(10));
        System.out.println(FineCalculator.fineTier(20));
        System.out.println(FineCalculator.fineTier(45));
    }
    
}
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
    }
    
}
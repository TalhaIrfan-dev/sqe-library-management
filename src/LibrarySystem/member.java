package LibrarySystem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class member
{
    String memberId;
    String memberName;
    List<book> borrowedBooks = new ArrayList<>();

    Map<book, LocalDate> dueDates = new HashMap<>();

    member(String memberId, String memberName)
    {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public String getmemberId()
    {return memberId;}
    public String getmemberName()
    {return memberName;}
    public List getborrowedBooks()
    {return borrowedBooks;}
    public Map getdueDates()
    {return dueDates;}
}